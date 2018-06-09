package serializator;

import javafx.util.Pair;
import org.javatuples.Quartet;
import packets.FilePacket;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;

public class Serializator {
    public static <T extends ISerializable> String serialize(T packet) throws SerializationException {
        StringBuilder result = new StringBuilder();
        Class cls = packet.getClass();
        String clsName = cls.getName();
        result.append("(").append(clsName.length()).append(")").append(clsName);
        Field[] fields = packet.getClass().getDeclaredFields();
        for (Field field:fields){
            String type = field.getType().getSimpleName();
            result.append(type.substring(0,2));
            String str = "";
            int len = 1;
            String name = field.getName();
            System.out.println(type);
            try {
                switch (type) {
                    case "int":
                        str = String.valueOf(field.getInt(packet));
                        break;
                    case "Int":
                        str = String.valueOf(field.getInt(packet));
                        break;
                    case "long":
                        str = String.valueOf(field.getLong(packet));
                        break;
                    case "Long":
                        str = String.valueOf(field.getLong(packet));
                        break;
                    case "boolean":
                        str = String.valueOf(field.getBoolean(packet));
                        break;
                    case "Boolean":
                        str = String.valueOf(field.getBoolean(packet));
                        break;
                    case "byte[]":
                        str = String.valueOf(Arrays.toString((byte[]) field.get(packet)));
                        break;
                    case "byte":
                        str = String.valueOf(field.getByte(packet));
                        break;
                    case "Byte":
                        str = String.valueOf(field.getByte(packet));
                        break;
                    case "double":
                        str = String.valueOf(field.getDouble(packet));
                        break;
                    case "Double":
                        str = String.valueOf(field.getDouble(packet));
                        break;
                    case "String":
                        str = (String) field.get(packet);
                        break;
                    case "char":
                        str = String.valueOf(field.getChar(packet));
                        break;
                    case "FilePacket[]":
                        StringBuilder stringBuilder = new StringBuilder();
                        FilePacket[] fp = (FilePacket[]) field.get(packet);
                        for (FilePacket p: fp){
                            File file = new File(p.fileName);
                            byte[] b = new byte[(int) file.length()];
                            stringBuilder.append("(").append(p.fileName.length()).append(")").append(p.fileName)
                            .append("(").append(1).append(")");
                            FileInputStream fis = new FileInputStream(file);
                            int count = fis.read(b);
                            if (count >= 0) {
                                String temp = Arrays.toString(b);
                                stringBuilder.append("(").append(temp.length()).append(")").append(temp);
                            }
                            str = stringBuilder.toString();
                        }
                        len = fp.length;
                }
                result.append("(").append(name.length()).append(")").append(name).append("(").append(len).append(")");
                result.append("(").append(str.length()).append(")").append(str);
            } catch (IllegalAccessException | IOException e){
                throw new SerializationException(e.getMessage(), e.getCause());
            }
        }
        System.out.println(result.toString());
        return result.toString();
    }

    private static Pair<Integer, Integer> getLength(String[] data, int i) throws SerializationException {
        if (data[i].equals("(")){
            i++;
            StringBuilder length = new StringBuilder();
            while (!data[i].equals(")")){
                length.append(data[i]);
                i++;
            }
            i++;
            return new Pair<>(Integer.valueOf(length.toString()), i);
        }
        else throw new SerializationException();
    }

    private static Quartet<String, String, Integer, Integer> getVariable(String[] data, int i)
            throws SerializationException {
        Pair<Integer, Integer> res = getLength(data, i);
        int len = res.getKey();
        i = res.getValue();

        StringBuilder varName = new StringBuilder();
        for (int j = 0; j < len; j++, i++) {
            varName.append(data[i]);
        }

        res = getLength(data, i);
        int count = res.getKey();
        i = res.getValue();

        res = getLength(data, i);
        len = res.getKey();
        i = res.getValue();

        StringBuilder varValue = new StringBuilder();
        for (int j = 0; j < len; j++, i++) {
            varValue.append(data[i]);
        }

        return new Quartet<>(varName.toString(), varValue.toString(), i, count);
    }

    private static byte[] parseByteArrayFromString(String strVariable){
        String[] temp = strVariable.substring(1, strVariable.length() - 1)
                .split(", ");
        System.out.println(Arrays.toString(temp));
        byte[] res = new byte[temp.length];
        for (int j = 0; j < temp.length; j++)
            res[j] = Byte.parseByte(temp[j]);
        return res;
    }

    public static <T extends ISerializable> T deserialize(byte[] raw) throws SerializationException {
        String[] data;
        try {
            data = new String(raw, "UTF-8").split("");
        } catch (UnsupportedEncodingException e) {
            throw new SerializationException(e.getMessage(), e.getCause());
        }
        Pair<Integer, Integer> length = getLength(data, 0);
        int len = length.getKey();
        int index = length.getValue();
        StringBuilder className = new StringBuilder();
        while (len > 0){
            className.append(data[index]);
            index++;
            len -= 1;
        }

        Class<T> cls;
        try {
            cls = (Class<T>) Class.forName(className.toString());
        } catch (ClassNotFoundException e) {
            throw new SerializationException(e.getMessage(), e.getCause());
        }

        Constructor[] constrs = cls.getDeclaredConstructors();
        T clsInstance = null;
        for (Constructor constr:constrs){
            try{
                clsInstance = (T) constr.newInstance();
                break;
            }
            catch(Exception ignored){
            }
        }

        if (clsInstance == null) throw new SerializationException();
        Field[] fields = clsInstance.getClass().getDeclaredFields();

        for (int i = index; i < data.length-1;) {
            String type = data[i] + data[i+1];
            i += 2;

            Quartet<String, String, Integer, Integer> var = getVariable(data, i);
            String varName = var.getValue0();
            String varValue = var.getValue1();
            i = var.getValue2();
            int count = var.getValue3();

            for (Field f: fields)
                if (f.getName().equals(varName)) {
                    try {
                        f.setAccessible(true);
                        switch (type) {
                            case "St":
                                f.set(clsInstance, varValue);
                                break;
                            case "in":
                                f.setInt(clsInstance, Integer.valueOf(varValue));
                                break;
                            case "In":
                                f.setInt(clsInstance, Integer.valueOf(varValue));
                                break;
                            case "bo":
                                f.setBoolean(clsInstance, Boolean.valueOf(varValue));
                                break;
                            case "Bo":
                                f.setBoolean(clsInstance, Boolean.valueOf(varValue));
                                break;
                            case "ch":
                                f.setChar(clsInstance, varValue.charAt(0));
                                break;
                            case "[B":
                                byte[] result = parseByteArrayFromString(varValue);
                                f.set(clsInstance, result);
                                break;
                            case "Fi":
                                FilePacket[] filePacket = new FilePacket[count];
                                int c = 0;
                                for (int j = 0; j < count; j++) {
                                    Quartet<String, String, Integer, Integer> variable =
                                            getVariable(varValue.split(""), c);
                                    c = variable.getValue2();
                                    FilePacket pack = new FilePacket();
                                    pack.fileName = variable.getValue0();
                                    pack.rawFile = parseByteArrayFromString(variable.getValue1());
                                    filePacket[j] = pack;
                                }
                                f.set(clsInstance, filePacket);
                                break;
                        }
                    } catch (IllegalAccessException e) {
                        throw new SerializationException(e.getMessage(), e.getCause());
                    }
                }
        }
        return clsInstance;
    }
}
