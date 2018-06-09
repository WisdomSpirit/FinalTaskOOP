package workers;//package workers;
//
//import commands.ICommand;
//
//import java.io.File;
//
//public class FileWorker {
//    private boolean isRecursive = false;
//    private String path = System.getProperty("user.dir");
//    private String result = "";
//
//    public boolean isRecursive(){
//        return this.isRecursive;
//    }
//
//    public void setRecursive(){
//        this.isRecursive = true;
//    }
//
//    public void setPath(String path){
//        this.path = path;
//    }
//
//    public boolean execute(ICommand command){
//        return real_execute(command, this.path);
//    }
//
//    public String getResult(){
//        return this.result;
//    }
//
//    private boolean real_execute(ICommand command, String path){
//        File root = new File(path);
//        File[] children = root.listFiles();
//        try {
//            assert children != null;
//            for (File file : children) {
//                if (file.isDirectory() && this.isRecursive) {
//                    real_execute(command, file.getPath());
//                } else {
////                    command.execute(file);
//                    this.result += file.getPath() + " => " + command.getResult() + "\n";
//                }
//            }
//            return true;
//        }
//        catch (Exception e){
//            return false;
//        }
//    }
//}