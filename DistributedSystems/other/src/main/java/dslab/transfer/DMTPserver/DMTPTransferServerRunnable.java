package dslab.transfer.DMTPserver;

import dslab.models.Message;
import dslab.servers.DMTPServerRunnable;

import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class DMTPTransferServerRunnable extends DMTPServerRunnable {
    private final BlockingQueue<Message> incomingMessageQueue;
    public DMTPTransferServerRunnable(Socket socket, String componentId, BlockingQueue<Message> incomingMessageQueue) {
        super(socket);
        this.incomingMessageQueue = incomingMessageQueue;
    }

    @Override
    protected void messageReceived(Message message) {
        System.out.println(message.toString());
        incomingMessageQueue.add(message);
    }
}
