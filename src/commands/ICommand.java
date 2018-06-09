package commands;

import serializator.ISerializable;

public interface ICommand {
    //COMMAND should take one parameter - String[] arg (Client queryLine.split(" "))
//    This method should form an InputPacket for Server
    ISerializable formPacket();
//    This method should handle the response from Server
    String execute(ISerializable packet);
}
