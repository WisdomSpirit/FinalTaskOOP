package packets.commit;

import packets.FilePacket;
import serializator.ISerializable;

public class InputPacket implements ISerializable {
    public int id;
    public FilePacket[] files;

    @Override
    public String getCommand() {
        return "Commit";
    }
}