package dslab.transfer.forwarder;

import dslab.models.Message;
import dslab.nameserver.INameserverRemote;

import java.util.concurrent.BlockingQueue;

public class MessageQueueHandlerRunnable implements Runnable {
    private final BlockingQueue<Message> incomingMessages;
    private final DMTPTransferService dmtpTransferService;

    public MessageQueueHandlerRunnable(BlockingQueue<Message> incomingMessages, String componentId, INameserverRemote rootNameserver) {
        this.incomingMessages = incomingMessages;
        dmtpTransferService = new DMTPTransferService(componentId, rootNameserver);
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
