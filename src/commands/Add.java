package commands;

import client.ClientINFO;
import packets.ErrorPacket;
import packets.add.InputPacket;
import packets.add.OutputPacket;
import serializator.ISerializable;

import java.io.OutputStream;


public class Add implements ICommand {
    private String[] args;

    public Add(String command){
        this.args = command.split(" ");
    }

    @Override
    public ISerializable formPacket() {
        ClientINFO client = ClientINFO.getInstance();
        InputPacket packet = new InputPacket();
        packet.id = client.getId();
        packet.repoName = args[1];
        return packet;
    }

    @Override
    public String execute(ISerializable packet) {
        try{
            OutputPacket response = (OutputPacket) packet;
            return String.format("%s Your repo \"%s\" was created", response.status, args[1]);
        } catch (ClassCastException e){
            ErrorPacket response;
            try {
                response = (ErrorPacket) packet;
            } catch (ClassCastException a){
                return "Couldn't understand server's answer!";
            }
            return String.format("%s -> %s", response.errorNumber, response.error);
        } catch (Exception e){
            return "Something went wrong! Write us your error case!";
        }
    }
}
