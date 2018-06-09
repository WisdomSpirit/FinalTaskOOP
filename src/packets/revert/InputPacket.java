package packets.revert;

import serializator.ISerializable;

public class InputPacket implements ISerializable {
    public int id;

    @Override
    public String getCommand() {
        return "Revert";
    }
}
