package dslab.util.DMTPUtils;

import dslab.models.DMTP.DMTPMessage;
import dslab.models.DMTP.DMTPMessageType;

public class DMTPServerCommandService {
        public String getOkResponse(String data) {
            return new DMTPMessage(DMTPMessageType.OK, data).toMessageString();
        }
        public String getOkByeResponse() {
            return new DMTPMessage(DMTPMessageType.OK, "bye").toMessageString();
        }
        public String getErrorResponse(String reason) {
            return new DMTPMessage(DMTPMessageType.ERROR, reason).toMessageString();
        }
}
