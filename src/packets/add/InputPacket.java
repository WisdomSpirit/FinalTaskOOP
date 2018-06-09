package packets.add;

import serializator.ISerializable;

public class InputPacket implements ISerializable {
    public String repoName;
    public int id;

    @Override
    public String getCommand() {
        return "Add";
    }
}