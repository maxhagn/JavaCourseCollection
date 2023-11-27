package dslab.mailbox;

import at.ac.tuwien.dsg.orvell.Shell;
import at.ac.tuwien.dsg.orvell.StopShellException;
import at.ac.tuwien.dsg.orvell.annotation.Command;
import dslab.ComponentFactory;
import dslab.mailbox.DMAPServer.DMAPMailboxServerConnectionListener;
import dslab.mailbox.DMTPServer.DMTPMailboxServerConnectionListener;
import dslab.nameserver.AlreadyRegisteredException;
import dslab.nameserver.INameserverRemote;
import dslab.nameserver.InvalidDomainException;
import dslab.nameserver.ZoneNotRegisteredException;
import dslab.util.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class MailboxServer implements IMailboxServer, Runnable {

    private final String componentId;
    private final Shell shell;
    private final MailStorage mailstorage = new MailStorage();
    private Integer dmtpPort;
    private Integer dmapPort;
    private String domain;
    private String registryHost;
    private Integer registryPort;
    private String nameserverRootId;
    private ServerSocket dmtpSocket;
    private ServerSocket dmapSocket;


    /**
     * Creates a new server instance.
     *
     * @param componentId the id of the component that corresponds to the Config resource
     * @param config      the component config
     * @param in          the input stream to read console input from
     * @param out         the output stream to write console output to
     */
    public MailboxServer(String componentId, Config config, InputStream in, PrintStream out) {
        this.componentId = componentId;
        this.dmtpPort = config.getInt("dmtp.tcp.port");
        this.dmapPort = config.getInt("dmap.tcp.port");
        this.domain = config.getString("domain");
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
            dmapSocket = new ServerSocket(dmapPort);
            DMAPMailboxServerConnectionListener dmapServer =
                    new DMAPMailboxServerConnectionListener(dmapSocket, componentId, mailstorage);
            new Thread(dmapServer).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.dmapPort, e);
        }

        try {
            dmtpSocket = new ServerSocket(dmtpPort);

            DMTPMailboxServerConnectionListener dmtpServer =
                    new DMTPMailboxServerConnectionListener(dmtpSocket, componentId, mailstorage);
            new Thread(dmtpServer).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.dmtpPort, e);
        }

        try {
            Registry registry = LocateRegistry.getRegistry(registryHost,registryPort);
            INameserverRemote root = (INameserverRemote) registry.lookup(nameserverRootId);
            root.registerMailboxServer(domain, "127.0.0.1:" + dmtpPort);
        } catch (RemoteException e) {
            System.out.println("Cannot connect to root nameserver.");
        } catch (InvalidDomainException e) {
            throw new RuntimeException("Error invalid domain.", e);
        } catch (NotBoundException e) {
            throw new RuntimeException("Error while lookup root nameserver.", e);
        } catch (AlreadyRegisteredException e) {
            System.out.println("Already registered - Server continuous running.");
        } catch (ZoneNotRegisteredException e) {
            System.out.println("Required nameserver zone not found - Server continuous running.");
        }



        shell.run();
    }

    @Override
    @Command
    public void shutdown() {
        if (dmtpSocket != null) {
            System.out.println("Stopping DMTP Server");
            try {
                dmtpSocket.close();
            } catch (IOException e) {
                System.out.println("error closing DMTP-Server");
            }
        }
        if (dmapSocket != null) {
            System.out.println("Stopping DMAP Server");
            try {
                dmapSocket.close();
            } catch (IOException e) {
                System.out.println("error closing DMAP-Server");
            }
        }
        throw new StopShellException();
    }

    public static void main(String[] args) throws Exception {
        IMailboxServer server = ComponentFactory.createMailboxServer(args[0], System.in, System.out);
        server.run();
    }
}