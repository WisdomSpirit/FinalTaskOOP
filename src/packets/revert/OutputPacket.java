package packets.revert;

import serializator.ISerializable;

public class OutputPacket implements ISerializable {
    @Override
    public String getCommand() {
        return "Revert";
    }
}
