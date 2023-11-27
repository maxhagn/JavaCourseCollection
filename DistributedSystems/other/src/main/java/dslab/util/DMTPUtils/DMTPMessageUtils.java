package dslab.util.DMTPUtils;

import dslab.models.DMTP.DMTPMessage;

public class DMTPMessageUtils {
    public DMTPMessage messageFromString(String input) {
        return new DMTPMessage(input);
    }

}
