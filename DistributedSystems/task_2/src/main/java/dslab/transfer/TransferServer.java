package dslab.transfer;

import at.ac.tuwien.dsg.orvell.Shell;
import at.ac.tuwien.dsg.orvell.StopShellException;
import at.ac.tuwien.dsg.orvell.annotation.Command;
import dslab.ComponentFactory;
import dslab.models.Message;
import dslab.nameserver.AlreadyRegisteredException;
import dslab.nameserver.INameserverRemote;
import dslab.nameserver.InvalidDomainException;
import dslab.transfer.DMTPserver.DMTPServerConnectionListener;
import dslab.transfer.forwarder.MessageQueueHandlerRunnable;
import dslab.util.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TransferServer implements ITransferServer, Runnable {

    private final String componentId;
    private final Integer dmtpPort;
    private final Shell shell;
    DMTPServerConnectionListener dmtpServer;
    Thread transferHandlerThread;
    ServerSocket serverSocket;
    private String registryHost;
    private Integer registryPort;
    private String nameserverRootId;


    private final BlockingQueue<Message> incomingMessageQueue = new LinkedBlockingQueue<>();

    /**
     * Creates a new server instance.
     *
     * @param componentId the id of the component that corresponds to the Config resource
     * @param config      the component config
     * @param in          the input stream to read console input from
     * @param out         the output stream to write console output to
     */
    public TransferServer(String componentId, Config config, InputStream in, PrintStream out) {
        this.componentId = componentId;
        this.dmtpPort = config.getInt("tcp.port");
        this.registryHost = config.getString("registry.host");
        this.registryPort = config.getInt("registry.port");
        this.nameserverRootId = config.getString("root_id");
        shell = new Shell(in, out);
        shell.register(this);
        shell.setPrompt(componentId + "> ");
    }

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(dmtpPort);
            dmtpServer = new DMTPServerConnectionListener(serverSocket, componentId, incomingMessageQueue);
            new Thread(dmtpServer).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.dmtpPort, e);
        }

        INameserverRemote root = null;
        try {
            Registry registry = LocateRegistry.getRegistry(registryHost,registryPort);
            root = (INameserverRemote) registry.lookup(nameserverRootId);
        } catch (RemoteException e) {
            System.out.println("Cannot connect to root nameserver.");
        } catch (NotBoundException e) {
            throw new RuntimeException("Error while lookup root nameserver.", e);
        }

        transferHandlerThread = new Thread(new MessageQueueHandlerRunnable(incomingMessageQueue, componentId, root ));
        transferHandlerThread.start();

        shell.run();
    }

    @Override
    @Command
    public void shutdown() {
        if (serverSocket != null) {
            System.out.println("Stopping Server");
            try {
                serverSocket.close();
            } catch (IOException e) {
                System.out.println("error closing DMTP-Server");
            }
        }
        if (this.transferHandlerThread != null) {
            this.transferHandlerThread.interrupt();
        }
        throw new StopShellException();
    }

    public static void main(String[] args) throws Exception {
        ITransferServer server = ComponentFactory.createTransferServer(args[0], System.in, System.out);
        server.run();
    }

}
