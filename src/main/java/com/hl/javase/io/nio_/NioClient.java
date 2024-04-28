package com.hl.javase.io.nio_;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author huanglin
 * @date 2024/02/15 20:06
 */
public class NioClient {

    public static void main(String[] args) throws IOException {
        Socket       socket = new Socket("localhost", 83);
        OutputStream out    = socket.getOutputStream();
        String       s      = "hello world -- over";
        out.write(s.getBytes());
        out.close();
    }
}
