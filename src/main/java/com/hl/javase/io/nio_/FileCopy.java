package com.hl.javase.io.nio_;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author huanglin
 * @date 2024/02/15 19:01
 */
public class FileCopy {

    public static void fastCopy(String src, String dist) throws Exception {
        FileInputStream fileInputStream = new FileInputStream(src);
        FileChannel     inChannel       = fileInputStream.getChannel();

        FileOutputStream fileOutputStream = new FileOutputStream(dist);
        FileChannel      outChannel       = fileOutputStream.getChannel();

        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        while(true) {
            int r = inChannel.read(buffer);
            if(r == -1) {
                break;
            }

            buffer.flip();

            outChannel.write(buffer);
            buffer.clear();
        }
    }
}
