package commands;//package commands;
//
//import serializator.ISerializable;
//
//import java.io.File;
//
//public class FileListExecutor implements ICommand {
//    private String result = "";
//
//    @Override
//    public ISerializable execute() {
//        try {
//            StringBuilder str = new StringBuilder();
//        String[] list;
//            if (!file.isDirectory()) {
//                list = file.getParentFile().list();
//            }
//            else {
//                list = file.list();
//            }
//            for (String f: list != null ? list : new String[0]){
//                str.append(f);
//                str.append("\n");
//            }
//            this.result = str.toString();
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
//
//    @Override
//    public String getResult() {
//        return this.result;
//    }
//}
