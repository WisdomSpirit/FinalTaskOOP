package packets.clone;

import packets.FilePacket;
import serializator.ISerializable;

public class OutputPacket implements ISerializable {
    public FilePacket[] files;

    @Override
    public String getCommand() {
        return "Clone";
    }
}
