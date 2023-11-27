package dslab.mailbox;

import at.ac.tuwien.dsg.orvell.Shell;
import at.ac.tuwien.dsg.orvell.StopShellException;
import at.ac.tuwien.dsg.orvell.annotation.Command;
import dslab.ComponentFactory;
import dslab.mailbox.DMAPServer.DMAPMailboxServerConnectionListener;
import dslab.mailbox.DMTPServer.DMTPMailboxServerConnectionListener;
import dslab.util.Config;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.ServerSocket;

public class MailboxServer implements IMailboxServer, Runnable {

    private final String componentId;
    private final Shell shell;
    private final MailStorage mailstorage = new MailStorage();
    private Integer dmtpPort;
    private Integer dmapPort;
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
        shell = new Shell(in, out);
        shell.register(this);
        shell.setPrompt(componentId + "> ");
    }

    @Override
    public void run() {

        try {
            dmtpSocket = new ServerSocket(dmtpPort);

            DMTPMailboxServerConnectionListener dmtpServer =
                    new DMTPMailboxServerConnectionListener(dmtpSocket, componentId, mailstorage);
            new Thread(dmtpServer).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.dmtpPort, e);
        }

        try {
            dmapSocket = new ServerSocket(dmapPort);
            DMAPMailboxServerConnectionListener dmapServer =
                    new DMAPMailboxServerConnectionListener(dmapSocket, componentId, mailstorage);
            new Thread(dmapServer).start();
        } catch (IOException e) {
            throw new RuntimeException("Cannot open port " + this.dmapPort, e);
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
