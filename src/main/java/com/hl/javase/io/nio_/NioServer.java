package com.hl.javase.io.nio_;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huanglin
 * @date 2024/02/15 19:36
 */
public class NioServer {

    public static void main(String[] args) throws Exception {
        Selector selector = Selector.open();

        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.configureBlocking(false);
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        ServerSocket      socket  = ssc.socket();
        InetSocketAddress address = new InetSocketAddress("localhost", 8888);
        socket.bind(address);

        while(true) {
            selector.select();
            Set<SelectionKey>      selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> keyIterator   = selectionKeys.iterator();

            while(keyIterator.hasNext()) {
                SelectionKey key = keyIterator.next();
                if(key.isAcceptable()) {
                    ServerSocketChannel ssc1 = (ServerSocketChannel) key.channel();
                    // 服务器会为每个新连接创建一个 SocketChannel
                    SocketChannel sc = ssc1.accept();
                    sc.configureBlocking(false);
                    sc.register(selector, SelectionKey.OP_READ);
                } if(key.isReadable()) {
                    SocketChannel sc = (SocketChannel) key.channel();
                    System.out.println(readDataFromSocketChannel(sc));
                    sc.close();
                } if(key.isWritable()) {
                    // ...
                }

                keyIterator.remove();
            }
        }
    }

    private static String readDataFromSocketChannel(SocketChannel sc) throws Exception {
        ByteBuffer    buffer = ByteBuffer.allocate(1024);
        StringBuilder data   = new StringBuilder();

        while(true) {
            buffer.clear();
            int n = sc.read(buffer);
            if(n == -1) {
                break;
            }

            buffer.flip();
            int    limit = buffer.limit();
            char[] dst   = new char[limit];
            for(int i = 0; i < limit; i++) {
                dst[i] = (char) buffer.get(i);
            }
            data.append(dst);
            buffer.clear();
        }

        return data.toString();
    }
}
