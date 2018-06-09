package packets.commit;

import serializator.ISerializable;

public class OutputPacket implements ISerializable {
    public String state;
    
    @Override
    public String getCommand() {
        return "Commit";
    }
}