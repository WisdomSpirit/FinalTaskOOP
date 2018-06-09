package client;

import commands.ICommand;
import factory.Factory;
import factory.FactoryException;
import org.javatuples.Pair;
import serializator.ISerializable;
import serializator.SerializationException;
import serializator.Serializator;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.Socket;

public class Client {
    private String args;
    private Connection server;

    public Client(String args, Pair<String, Integer> server) {
        this.args = args;
        try {
            this.server = new Connection(new Socket(server.getValue0(), server.getValue1()));
        } catch (IOException e) {
            System.out.println("Couldn't make up connection!");
        }
    }

    public void start() {
        try {
            ICommand cmd = Factory.recogniseCommand(args);
            ISerializable query = cmd.formPacket();
            server.send(Serializator.serialize(query).getBytes("UTF-8"));
            ISerializable response = Serializator.deserialize(server.read());
            System.out.println(cmd.execute(response));
        } catch (FactoryException e) {
            e.printStackTrace();
            System.out.println("There is no such command!");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            System.out.println("Couldn't recognize your encoding, use UTF-8(default value), please!");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Exception in Internet connection was occurred, while handling your query!");
        } catch (SerializationException e) {
            e.printStackTrace();
            System.out.println("Couldn't recognise Server answer or your query!");
        } finally {
            try {
                server.close();
            } catch (IOException e) {
                System.out.println("Couldn't close connection and free resourses! Fatal Error!");
            }
        }
    }
}