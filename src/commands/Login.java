package commands;

import client.ClientINFO;
import packets.ErrorPacket;
import packets.login.*;
import serializator.ISerializable;


public class Login implements ICommand {
    private String login;
    private String password;

    public Login(String command) {
        String[] args = command.split(" ");
        this.login = args[1];
        this.password = args[2];
    }

    @Override
    public ISerializable formPacket() {
        InputPacket packet = new InputPacket();
        packet.login = this.login;
        packet.password = this.password;
        return packet;
    }

    @Override
    public String execute(ISerializable packet) {
        OutputPacket response;
        try{
            response = (OutputPacket) packet;
        } catch (Exception e){
            return "Server's answer is in incorrect format, couldn't understand!";
        }
        ClientINFO client = ClientINFO.getInstance();
        client.setId(response.id);
        client.setLogin(login);
        return "Ok, Fine! Your id is " + client.getId();
    }
}
