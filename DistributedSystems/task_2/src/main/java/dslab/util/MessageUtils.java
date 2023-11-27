package dslab.util;

import dslab.models.Message;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageUtils {
    SecretKeySpec key;
    public MessageUtils(String keypath) {
        File keyFile = new File(keypath);
        try {
            key = Keys.readSecretKey(keyFile);
        } catch (IOException e) {
            throw new RuntimeException("Could not open hmac key file");
        }
    }
    public byte[] calculateHash(Message message) {
        try {
            Mac mac = Mac.getInstance("HmacSHA256");
            mac.init(key);
            String messageString = String.join("\n",
                    message.getFrom(),
                    String.join(",", message.getTo()),
                    message.getSubject(),
                    message.getData());
            mac.update(messageString.getBytes());
            return mac.doFinal();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Algorithm not found");
        } catch (InvalidKeyException e) {
            throw new RuntimeException("Provided hmac key not valid");
        }
    }
    public boolean checkHash(Message message) {
       byte[] actualHash = calculateHash(message);
       return MessageDigest.isEqual(actualHash, message.getHash());
    }
}
