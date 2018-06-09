package packets;

import serializator.ISerializable;

public class ErrorPacket implements ISerializable {
    public String error;
    public int errorNumber;

    @Override
    public String getCommand() {
        return "Error";
    }
}
