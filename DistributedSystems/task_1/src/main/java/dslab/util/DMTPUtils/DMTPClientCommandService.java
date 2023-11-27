package dslab.util.DMTPUtils;

import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;

import java.util.List;

public class DMTPClientCommandService {
    public String getBeginCommand() {
        return new DMTPMessage(DMTPMessageType.BEGIN).toMessageString();
    }
    public String getErrorResponse(List<String> recipients) {
        return new DMTPMessage(DMTPMessageType.TO, String.join(",", recipients)).toMessageString();
    }
}
