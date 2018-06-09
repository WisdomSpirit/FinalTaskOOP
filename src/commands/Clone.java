package commands;

import client.ClientINFO;
import packets.ErrorPacket;
import packets.FilePacket;
import packets.clone.InputPacket;
import packets.clone.OutputPacket;
import serializator.ISerializable;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;

public class Clone implements ICommand {
    private String[] args;

    public Clone(String command) {
        this.args = command.split(" ");
    }

    @Override
    public ISerializable formPacket() {
        ClientINFO client = ClientINFO.getInstance();
        InputPacket packet = new InputPacket();
        packet.id = client.getId();
        packet.repoName = args[2];
        return packet;
    }

    @Override
    public String execute(ISerializable packet) {
        try {
            OutputPacket response = (OutputPacket) packet;
            File file = new File(args[1]);
            boolean flag = false;

            if (!file.mkdir()) {
                File[] toDelete = file.listFiles();
                if (toDelete != null)
                    for (File f: toDelete)
                        f.delete();
            }

            if (!(args.length == 4 && args[3].equals("."))) {
                flag = true;
                if (!new File(args[1] + "\\" + args[2]).mkdirs())
                    return String.format("Couldn't create File with name %s", args[1] + "\\" + args[2]);
            }

            for (FilePacket fp : response.files) {
                System.out.println(fp.fileName);
                String[] path = fp.fileName.split("\\\\");
                System.out.println(Arrays.toString(path));
                StringBuilder name = new StringBuilder();
                for (int i = 0; i < path.length; i++) {
                    if (path[i].equals(args[2])) {
                        if (flag) name.append(args[1]).append("\\").append(args[2]);
                        else name.append(args[1]);

                        for (int j = i+2; j < path.length; j++) {
                            name.append("\\").append(path[j]);
                        }
                    }
                }
                System.out.println(name.toString());

                if (!new File(name.toString()).createNewFile())
                    return String.format("Couldn't create File with name %s", name.toString());
                try (FileOutputStream fos = new FileOutputStream(name.toString())) {
                    fos.write(fp.rawFile);
                } catch (Exception e){
                    file.delete();
                    return String.format("Couldn't write data into %s", name.toString());
                }
            }

            return String.format("Ok, Fine! Repo \"%s\" was cloned to your \"%s\" directory", args[2], args[1]);
        } catch (ClassCastException e){
            ErrorPacket response;
            try {
                response = (ErrorPacket) packet;
            } catch (ClassCastException a){
                return "Couldn't understand server's answer!";
            }
            return String.format("%s -> %s", response.errorNumber, response.error);
        } catch (Exception e){
            e.printStackTrace();
            return "Something went wrong! Write us your error case!";
        }
    }
}
