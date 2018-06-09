package packets.update;

import packets.FilePacket;
import serializator.ISerializable;

public class OutputPacket implements ISerializable {
    public FilePacket[] files;

    @Override
    public String getCommand() {
        return "Update";
    }
}
