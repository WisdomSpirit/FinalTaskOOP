package packets.add;

import serializator.ISerializable;

public class OutputPacket implements ISerializable {
    public String status;

    @Override
    public String getCommand() {
        return "Add";
    }
}
