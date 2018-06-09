package packets.log;

import serializator.ISerializable;

public class InputPacket implements ISerializable {
    public int id;

    @Override
    public String getCommand() {
        return "Log";
    }
}
