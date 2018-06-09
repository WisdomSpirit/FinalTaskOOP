package providers;

import packets.FilePacket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class DataProvider {
    public static File getRepo(int id, String repoName){
        String path = "archive\\" + String.valueOf(id) + "\\" + repoName;
        return new File(path);
    }

    private static File[] getLatestVersionRepository(int id) throws ProviderException {
//        Config conf = Config.getInstance();
//        String repoName = conf.currentRepo.get(id);
//        System.out.println(repoName);
//        if (repoName == null) throw new ProviderException();
//        File currentRepo = new File(repoName);
//
//        HashMap<String, File> names = new HashMap<>();
//        getFile(currentRepo, names);
//
//        Object[] values = names.values().toArray();
//        File[] res = new File[values.length];
//        for (int i = 0; i < values.length; i++) {
//            res[i] = (File) values[i];
//        }
//        return res;
        return null;
    }

    private static void getFile(File file, HashMap<String, File> names){
        File[] list = file.listFiles();
        if (list != null)
            for (File f: list)
                if (f.isDirectory())
                   getFile(f, names);
                else
                    if (!names.containsKey(f.getName())) names.put(f.getName(), f);
                    else names.replace(f.getName(), f);
    }

    private static ArrayList<FilePacket> getRawFiles(File[] list) throws IOException, ProviderException {
        ArrayList<FilePacket> result = new ArrayList<>();
        if (list != null) {
            for (File f : list) {
                if (!f.isDirectory()) {
                    FilePacket filePacket = new FilePacket();
                    filePacket.fileName = f.getPath();
                    byte[] b = new byte[(int) f.length()];
                    FileInputStream fis = new FileInputStream(f);
                    if (fis.read(b) >= 0)
                        filePacket.rawFile = b;
                    else throw new ProviderException();
                    result.add(filePacket);
                } else {
                    result.addAll(getRawFiles(f.listFiles()));
                }
            }
        }
        return result;
    }

    public static FilePacket[] getLatestVersionFilesById(int id) throws IOException, ProviderException {
        File[] list = DataProvider.getLatestVersionRepository(id);
        System.out.println("1" + Arrays.toString(list));
        ArrayList<FilePacket> toRes = getRawFiles(list);
        System.out.println("2" + toRes.toString());
        FilePacket[] result = new FilePacket[toRes.size()];
        int index = 0;
        while (index < toRes.size()){
            result[index] = toRes.get(index);
            index++;
        }
        System.out.println(Arrays.toString(result));
        return result;
    }
}
