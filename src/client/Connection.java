package client;

import java.io.*;
import java.net.Socket;
import java.util.Arrays;

public class Connection {
    private Socket destination;
    private InputStream in;
    private OutputStream out;

    public Connection(Socket destination) throws IOException {
        this.destination = destination;
        this.in = new BufferedInputStream(destination.getInputStream());
        this.out = new BufferedOutputStream(destination.getOutputStream());
    }

    public byte[] read() throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        boolean flag = false;

        while (true) {
            byte[] buffer = new byte[1024];
            in.read(buffer);
            for (byte b : buffer) {
                if (b != 0) {
                    baos.write(b);
                }
                else {
                    flag = true;
                    break;
                }
            }
            if (flag) break;
        }

        return baos.toByteArray();
    }

    public void send(byte[] data) throws IOException {
        out.write(data);
        out.flush();
    }

    public void close() throws IOException {
        in.close();
        out.close();
        destination.close();
    }
}
