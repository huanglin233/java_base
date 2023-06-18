package com.hl.javase.io;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.Set;

/**
 * nio 服务端
 *
 * @author huanglin by 2021/5/15
 */
public class NioServer {
    public static void main(String[] args) throws IOException{
        // 1. serverSelector负责轮询是否有新的连接，服务端监测到新的连接之后，不再创建一个新的线程，
        // 而是直接将新连接绑定到clientSelector上，这样就不用 IO 模型中 1w 个 while 循环在死等
        Selector selector1 = Selector.open();
        // 2. clientSelector负责轮询连接是否有数据可读
        Selector selector2 = Selector.open();

        SelectorConnecation selectorConnecation = new SelectorConnecation(selector1, selector2);
        SelectorHandler     selectorHandler     = new SelectorHandler(selector2);
        // 启动监听线程
        selectorConnecation.start();
        // 启动任务处理线程
        selectorHandler.start();

    }
}

class SelectorConnecation extends Thread {
    
    Selector selectorCon;
    Selector selectorHan;
    
    public SelectorConnecation(Selector selector, Selector selectorHan) {
        this.selectorCon = selector;
        this.selectorHan = selectorHan;
    }

    @Override
    public void run() {
        try {
            // 对应IO编程中服务端启动
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(3333));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selectorCon, SelectionKey.OP_ACCEPT);

            while(true) {
                // 监测是否有新的连接，这里的1指的是阻塞的时间为 1ms
                if(selectorCon.select(1) > 0) {
                    Set<SelectionKey>      selectionKeys = selectorCon.selectedKeys();
                    Iterator<SelectionKey> iterator      = selectionKeys.iterator();
                    while(iterator.hasNext()) {
                        SelectionKey next = iterator.next();

                        if(next.isAcceptable()) {
                            try{
                                // (1) 每来一个新连接，不需要创建一个线程，而是直接注册到clientSelector
                                System.out.println("---有新的链接---");
                                SocketChannel accept = ((ServerSocketChannel) next.channel()).accept();
                                accept.configureBlocking(false);
                                accept.register(selectorHan, SelectionKey.OP_READ);
                            } finally {
                                iterator.remove();
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}

class SelectorHandler extends Thread {

    Selector selectorHan;
    
    public SelectorHandler(Selector selectorHan) {
        this.selectorHan = selectorHan;
    }

    @Override
    public void run() {
        try{
            // (2) 批量轮询是否有哪些连接有数据可读，这里的1指的是阻塞的时间为 1ms
            while(true) {
                if(selectorHan.select(1) > 0)  {
                    Set<SelectionKey> selectionKeys = selectorHan.selectedKeys();
                    Iterator<SelectionKey> iterator = selectionKeys.iterator();

                    while(iterator.hasNext()) {
                        SelectionKey next = iterator.next();
                        if(next.isReadable()) {
                            try{
                                System.out.println("---有新的任务--");
                                SocketChannel socketChannel = (SocketChannel) next.channel();
                                ByteBuffer allocate = ByteBuffer.allocate(1024);
                                // (3) 面向 Buffer
                                int read = socketChannel.read(allocate);
                                if( read != -1) {
                                    //切换模式为读模式，其实就是把postion位置设置为0，可以从0开始读取
                                    allocate.flip();
                                    System.out.println(read);
                                    System.out.println(Charset.defaultCharset().newDecoder().decode(allocate).toString());
                                }
                                //数据读完后清空缓冲区
                                allocate.clear();
                            }finally {
                                iterator.remove();
                                next.interestOps(SelectionKey.OP_READ);
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}