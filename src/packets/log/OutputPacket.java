package packets.log;

import serializator.ISerializable;

public class OutputPacket implements ISerializable {
    public String result;

    @Override
    public String getCommand() {
        return "Log";
    }
}
