package dslab.util.DMTPUtils;

import dslab.exceptions.ProtocolViolationException;
import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;
import dslab.models.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.List;
import java.util.stream.Collectors;

public final class DMTPMessageUtils {

    private static void writeDMTPMessageToWriter(PrintWriter writer, DMTPMessage msg) {
        writer.write(msg.toMessageString() + "\r\n");
        writer.flush();
    }

    private static void throwExceptionIfNotOk(DMTPMessage message) throws ProtocolViolationException {
        if(!DMTPMessageType.OK.equals(message.getType())) {
            throw new ProtocolViolationException();
        }
    }
}