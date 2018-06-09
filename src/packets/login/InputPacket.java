package packets.login;

import serializator.ISerializable;

public class InputPacket implements ISerializable{
    public String login;
    public String password;

    @Override
    public String getCommand() {
        return "Login";
    }
}