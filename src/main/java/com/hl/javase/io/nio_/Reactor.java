package com.hl.javase.io.nio_;

import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * @author huanglin
 * @date 2024/02/18 23:50
 */
public class Reactor implements Runnable {

    private final Selector selector;
    private final ServerSocketChannel serverChannel;

    public Reactor(int port) throws Exception{
        // 创建服务端的ServerSocketChannel
        serverChannel = ServerSocketChannel.open();
        // 设置为非阻塞模式
        serverChannel.configureBlocking(false);
        // 创建一个Selector多路复用器
        selector = Selector.open();
        SelectionKey register = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
        // 绑定端口
        serverChannel.bind(new InetSocketAddress(port));
        // 为服务端channel绑定一个acceptor
        register.attach(new Acceptor(serverChannel));
    }
    @Override
    public void run() {
        try {
            while(!Thread.interrupted()) {
                // 服务端使用一个线程不断等待客户端的连接到达
                selector.select();
                Set<SelectionKey>      keys        = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = keys.iterator();
                while(keyIterator.hasNext()) {
                    dispatch(keyIterator.next());
                    keyIterator.remove();
                }

                selector.selectNow();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void dispatch(SelectionKey key) throws Exception {
        // 这里的attachement也即前面为服务端channe绑定的Acceptor,调用其run()方法进行,
        // 客户端连接的获取,并且进行分发
        Runnable runnable = (Runnable) key.attachment();
        runnable.run();
    }
}
