package commands;

import packets.ErrorPacket;
import packets.FilePacket;
import packets.commit.InputPacket;
import packets.commit.OutputPacket;
import serializator.ISerializable;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Commit implements ICommand {
    public int id;
    public FilePacket[] files;

    Commit(InputPacket packet){
        this.id = packet.id;
        this.files = packet.files;
    }

    @Override
    public ISerializable formPacket() {
        return null;
    }

    @Override
    public String execute(ISerializable packet) {
//        Config conf = Config.getInstance();
//        IVersionProvider vp = conf.versionCounter.get(id);
//        vp.incrementVersion();
//        File repo = DataProvider.getFolder(id, conf.currentRepo.get(id));
//        try {
//            if (repo.createNewFile()) throw new ExecutionException();
//            for (FilePacket fp: files){
//                File toWrite = new File(repo.getPath() + "\\" + fp.fileName);
//                    if (!toWrite.createNewFile()) throw new ExecutionException();
//                    FileOutputStream fos = new FileOutputStream(toWrite);
//                    fos.write(fp.rawFile);
//                }
//        } catch (FileNotFoundException e) {
//            ErrorPacket error = new ErrorPacket();
//            error.errorNumber = 1;
//            error.error = "Your file was not found while handling Your query!";
//            return error;
//        } catch (IOException e) {
//            ErrorPacket error = new ErrorPacket();
//            error.error = "Some IOException was occurred while handling Your query!";
//            error.errorNumber = 2;
//            return error;
//        } catch (ExecutionException e) {
//            ErrorPacket error = new ErrorPacket();
//            return error;
//        }
//        OutputPacket packet = new OutputPacket();
//        packet.state = "Ok, fine!";
//        return packet;
        return null;
    }
}
