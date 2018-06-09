package packets.login;

import serializator.ISerializable;

public class OutputPacket implements ISerializable{
    public int id;

    @Override
    public String getCommand() {
        return "Login";
    }
}