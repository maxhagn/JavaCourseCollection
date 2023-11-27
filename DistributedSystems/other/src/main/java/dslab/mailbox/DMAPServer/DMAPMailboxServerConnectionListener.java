package dslab.mailbox.DMAPServer;


import dslab.mailbox.MailStorage;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class DMAPMailboxServerConnectionListener implements Runnable {
    private final String componentId;
    private final ServerSocket serverSocket;
    private boolean isStopped = false;
    private final ExecutorService threadPool =
            Executors.newFixedThreadPool(10);
    private List<Socket> sockets = new ArrayList<>();
    private final MailStorage mailStorage;

    public DMAPMailboxServerConnectionListener(ServerSocket serverSocket, String componentId, MailStorage mailStorage) {
        this.serverSocket = serverSocket;
        this.componentId = componentId;
        this.mailStorage = mailStorage;
    }

    public void run() {
        while (!isStopped) {
            Socket clientSocket = null;
            try {
                clientSocket = this.serverSocket.accept();
                if (!isStopped) {
                    sockets.add(clientSocket);
                    this.threadPool.execute(
                            new DMAPServerRunnable(clientSocket,
                                    componentId, mailStorage));
                }
                sockets = sockets.stream().filter(socket -> socket != null && !socket.isClosed()).collect(Collectors.toList());
            } catch (SocketException e) {
                this.isStopped = true;
                shutdown();
                System.out.println("DMAP-Server Stopped.");
            } catch (IOException e) {
                throw new RuntimeException(
                        "Error accepting DMAP client connection", e);
            }
        }
    }

    private void shutdown() {
        for (Socket socket : sockets) {
            try {
                if (socket != null) {
                    socket.close();
                }
            } catch (IOException e) {
                System.out.println("Could not close client connection: " + e.getMessage());
            }
        }
        try {
            this.serverSocket.close();
        } catch (IOException e) {
            System.out.println("Could not close Socket: " + e.getMessage());
        }
        this.threadPool.shutdown();
    }
}
