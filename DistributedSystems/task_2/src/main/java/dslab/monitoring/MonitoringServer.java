package dslab.monitoring;

import at.ac.tuwien.dsg.orvell.Shell;
import at.ac.tuwien.dsg.orvell.StopShellException;
import at.ac.tuwien.dsg.orvell.annotation.Command;
import dslab.ComponentFactory;
import dslab.util.Config;

import java.io.InputStream;
import java.io.PrintStream;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Map;

public class MonitoringServer implements IMonitoringServer {
    private final String componentId;
    private final Config config;
    private final InputStream in;
    private final PrintStream out;
    private MonitoringUdpListener monitoringUdpListener;
    private DatagramSocket datagramSocket;
    private final Shell shell;
    private final MonitoringStorage monitoringStorage = new MonitoringStorage();

    /**
     * Creates a new server instance.
     *
     * @param componentId the id of the component that corresponds to the Config resource
     * @param config      the component config
     * @param in          the input stream to read console input from
     * @param out         the output stream to write console output to
     */
    public MonitoringServer(String componentId, Config config, InputStream in, PrintStream out) {
        this.componentId = componentId;
        this.config = config;
        this.in = in;
        this.out = out;
        shell = new Shell(in, out);
        shell.register(this);
        shell.setPrompt(componentId + "> ");
    }

    @Override
    public void run() {
        // TODO
        try {
            datagramSocket = new DatagramSocket(config.getInt("udp.port"));
            monitoringUdpListener = new MonitoringUdpListener(datagramSocket, monitoringStorage);
            new Thread(monitoringUdpListener).start();
        } catch (SocketException e) {
            System.out.println("Error creating Monitoring Socket");
        }
        shell.run();
    }

    @Override
    @Command
    public void addresses() {
        printMap(monitoringStorage.addressCounts);
    }

    @Override
    @Command
    public void servers() {
        printMap(monitoringStorage.serverCounts);
    }

    private void printMap(Map<String, Long> data) {
        for (String key : data.keySet()) {
            this.out.format("%s %d\r\n", key, data.get(key));
        }
    }

    @Override
    @Command
    public void shutdown() {
        if (datagramSocket != null) {
            datagramSocket.close();
        }
        throw new StopShellException();
    }

    public static void main(String[] args) throws Exception {
        IMonitoringServer server = ComponentFactory.createMonitoringServer(args[0], System.in, System.out);
        server.run();
    }

}
