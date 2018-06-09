package commands;

import client.ClientINFO;
import packets.log.InputPacket;
import packets.log.OutputPacket;
import serializator.ISerializable;

public class Log implements ICommand {

    public Log(String command){ }

    @Override
    public ISerializable formPacket() {
        ClientINFO client = ClientINFO.getInstance();
        InputPacket packet = new InputPacket();
        packet.id = client.getId();
        return packet;
    }

    @Override
    public String execute(ISerializable packet) {
        OutputPacket response;
        try {
            response = (OutputPacket) packet;
        } catch (Exception e) {
            return "Server's answer is in incorrect format, couldn't understand!";
        }
        ClientINFO client = ClientINFO.getInstance();
        if (response.result.length() != 0)
            return client.getLogin() + " " + response.result;
        else
            return client.getLogin() + " hasn't commited anything yet!";
    }
}
