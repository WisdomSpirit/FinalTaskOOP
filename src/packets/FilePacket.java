package packets;

import serializator.ISerializable;

public class FilePacket implements ISerializable {
    public String fileName;
    public byte[] rawFile;

    @Override
    public String getCommand() {
        return "File";
    }
}
