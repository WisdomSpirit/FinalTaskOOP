package commands;

import client.ClientINFO;
import packets.ErrorPacket;
import packets.FilePacket;
import packets.update.InputPacket;
import packets.update.OutputPacket;
import serializator.ISerializable;

import java.io.IOException;

public class Update implements ICommand {
    String[] args;

    Update(String command){
        this.args = command.split(" ");
    }

    @Override
    public ISerializable formPacket() {
        ClientINFO client = ClientINFO.getInstance();
        InputPacket packet = new InputPacket();
        packet.id = client.getId();
        return packet;
    }

    @Override
    public String execute(ISerializable packet) {
//        try {
//            FilePacket[] files = getLatestVersionFilesById(id);
//            OutputPacket packet = new OutputPacket();
//            packet.files = files;
//            return packet;
//        } catch (IOException e) {
//            ErrorPacket error = new ErrorPacket();
//            error.error = "Some IOException was occurred while handling Your query!";
//            error.errorNumber = 2;
//            return error;
//        } catch (providers.ProviderException e) {
//            ErrorPacket error = new ErrorPacket();
//            error.errorNumber = 6;
//            error.error = "Some ProviderException was occurred while handling Your query!";
//            return error;
//        }
        return null;
    }
}
