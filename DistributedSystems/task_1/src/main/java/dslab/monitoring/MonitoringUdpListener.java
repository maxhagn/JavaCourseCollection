package dslab.monitoring;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.Arrays;

public class MonitoringUdpListener implements Runnable {

    private final DatagramSocket datagramSocket;
    private final MonitoringStorage monitoringStorage;
    boolean running = true;

    public MonitoringUdpListener(DatagramSocket datagramSocket, MonitoringStorage monitoringStorage) {
        this.monitoringStorage = monitoringStorage;
        this.datagramSocket = datagramSocket;
    }


    @Override
    public void run() {
        byte[] buffer;
        DatagramPacket packet;
        while (running) {
            try {
                while (true) {
                    buffer = new byte[1024];
                    packet = new DatagramPacket(buffer, buffer.length);
                    datagramSocket.receive(packet);
                    byte[] trimmedPacket = Arrays.copyOfRange(packet.getData(), packet.getOffset(), packet.getLength());
                    String request = new String(trimmedPacket);
                    System.out.println("Received request-packet from client: " + request);
                    logRequest(request);
                }
            } catch (SocketException e) {
                running = false;
                System.out.println("Monitoring Server stopped");
            } catch (IOException e) {
                throw new RuntimeException("IOException in Monitoring-Server Socket");
            } finally {
                if (datagramSocket != null && !datagramSocket.isClosed()) {
                    datagramSocket.close();
                }
            }
        }
    }

    private void logRequest(String request) {
        String[] splitRequest = request.split("\\s");
        if (splitRequest.length < 2) {
            // invalid request
            return;
        }
        monitoringStorage.countServer(splitRequest[0]);
        monitoringStorage.countAddress(splitRequest[1]);
    }
}
