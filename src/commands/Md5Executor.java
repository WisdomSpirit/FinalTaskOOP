package commands;//package commands;
//
//import serializator.ISerializable;
//
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.InputStream;
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//public class Md5Executor implements ICommand {
//    private static MessageDigest md;
//
//    private String result = "";
//
//    public Md5Executor() throws ExecutionException {
//        try {
//            md = MessageDigest.getInstance("MD5");
//        } catch (NoSuchAlgorithmException e) {
//            throw new ExecutionException();
//        }
//    }
//
//    @Override
//    public ISerializable execute() {
//        byte[] buffer = new byte[102400];
//        int read = 0;
//        InputStream is = null;
//        try {
//            is = new FileInputStream(file);
//            while ((read = is.read(buffer)) != -1) {
//                md.update(buffer, 0, read);
//            }
//        } catch (IOException e) {
//            return false;
//        }
//
//        byte[] digest = md.digest();
//        this.result = convertByteArrayToHexString(digest);
//        return true;
//    }
//
//    private static String convertByteArrayToHexString(byte[] arrayBytes) {
//        StringBuilder stringBuffer = new StringBuilder();
//        for (byte arrayByte : arrayBytes) {
//            stringBuffer.append(Integer.toString((arrayByte & 0xff) + 0x100, 16)
//                    .substring(1));
//        }
//        return stringBuffer.toString();
//    }
//
//    public String getResult(){
//        return this.result;
//    }
//}
//
//
