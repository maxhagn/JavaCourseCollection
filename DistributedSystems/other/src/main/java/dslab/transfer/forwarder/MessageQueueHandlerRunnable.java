package dslab.transfer.forwarder;

import dslab.models.Message;

import java.util.concurrent.BlockingQueue;

public class MessageQueueHandlerRunnable implements Runnable {
    private final BlockingQueue<Message> incomingMessages;
    private final DMTPTransferService dmtpTransferService;

    public MessageQueueHandlerRunnable(BlockingQueue<Message> incomingMessages, String componentId) {
        this.incomingMessages = incomingMessages;
        dmtpTransferService = new DMTPTransferService(componentId);
    }

    @Override
    public void run() {
        try {
            while (true) {
                Message message = incomingMessages.take();
                dmtpTransferService.forward(message);
            }
        } catch (InterruptedException e) {
            //stopped
        }
    }
}
