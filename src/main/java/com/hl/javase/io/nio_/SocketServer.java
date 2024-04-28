package com.hl.javase.io.nio_;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.log4j.BasicConfigurator;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 服务端实现多路复用io
 * @author huanglin
 * @date 2024/02/20 23:12
 */
public class SocketServer {

    static {
        BasicConfigurator.configure();
    }

    private static final Log LOGGER = LogFactory.getLog(SocketServer.class);
    /**
     * 改进的java nio server的代码中，由于buffer的大小设置的比较小。
     * 我们不再把一个client通过socket channel多次传给服务器的信息保存在beff中了(因为根本存不下)<br>
     * 我们使用socketchanel的hashcode作为key(当然您也可以自己确定一个id)，信息的stringbuffer作为value，存储到服务器端的一个内存区域MESSAGEHASHCONTEXT。
     */
    private static final ConcurrentMap<Integer, StringBuffer> MESSAGEHASHCONTEXT = new ConcurrentHashMap<Integer , StringBuffer>();

    public static void main(String[] args) throws Exception {
        ServerSocketChannel serverChannel = ServerSocketChannel.open();
        serverChannel.configureBlocking(false);
        ServerSocket socket = serverChannel.socket();
        socket.setReuseAddress(true);
        socket.bind(new InetSocketAddress(83));

        Selector selector = Selector.open();
        // 注意、服务器通道只能注册SelectionKey.OP_ACCEPT事件
        serverChannel.register(selector, SelectionKey.OP_ACCEPT);

        try {
            while(true) {
                // 如果条件成立,说明本次询问selector,并没有获取到任何想要的事件
                if(selector.select(100) == 0) {
                    continue;
                }

                // 获取到监控的事件
                Iterator<SelectionKey> selectionKey = selector.selectedKeys().iterator();
                while(selectionKey.hasNext()) {
                    SelectionKey readyKey = selectionKey.next();
                    // 这个已经处理的readyKey一定要移除,如果不移除,就会一直存在在selector.selectionKeys集合中
                    // 待到下一次select.select() > 0 时,这个readyKey又会被处理一次
                    selectionKey.remove();
                    SelectableChannel channel = readyKey.channel();
                    if(readyKey.isValid() && readyKey.isAcceptable()) {
                        SocketServer.LOGGER.info("=====channel通过已经准备好=====");
                        /*
                         * 当server socket channel通道已经准备好，就可以从server socket channel中获取socketChannel了
                         * 拿到socket channel后，要做的事情就是马上到selector注册这个socket channel感兴趣的事情。
                         * 否则无法监听到这个socket channel到达的数据
                         **/
                        ServerSocketChannel serverSocketChannel = (ServerSocketChannel) channel;
                        SocketChannel       socketChannel       = serverSocketChannel.accept();
                        registerSocketChannel(socketChannel, selector);
                    } else if(readyKey.isValid() && readyKey.isConnectable()) {
                        SocketServer.LOGGER.info("=====socket channel 建立连接=====");
                    } else if(readyKey.isValid() && readyKey.isReadable()) {
                        SocketServer.LOGGER.info("=====socket channel 数据已经准备完毕,可以开始读取数据并处理=====");
                        readSocketChannel(readyKey);
                    }
                }
            }
        } catch (Exception e) {
            SocketServer.LOGGER.error(e.getMessage(), e);
        } finally {
            socket.close();
        }
    }

    /**
     * 在server socket channel接收到/准备好 一个新的 TCP连接后。
     * 就会向程序返回一个新的socketChannel。<br>
     *  但是这个新的socketChannel并没有在selector“选择器/代理器”中注册，
     * 所以程序还没法通过selector通知这个socket channel的事件。
     * 于是我们拿到新的socket channel后，要做的第一个事情就是到selector“选择器/代理器”中注册这个
     * socket channel感兴趣的事件
     * @param socketChannel 新的socket channel
     * @param selector selector“选择器/代理器”
     * @throws Exception
     */
    private static void registerSocketChannel(SocketChannel socketChannel, Selector selector) throws Exception{
        socketChannel.configureBlocking(false);
        // socket通道可以且只可以注册三种事件SelectionKey.OP_READ, SelectionKey.OP_WRITE, SelectionKey.OP_CONNECT
        socketChannel.register(selector, SelectionKey.OP_READ, ByteBuffer.allocate(2048));
    }

    private static void readSocketChannel(SelectionKey readyKey) throws Exception {
        SocketChannel clientSocketChannel = (SocketChannel) readyKey.channel();
        // 获取客户端使用的端口
        InetSocketAddress sourceSocketAddress = (InetSocketAddress) clientSocketChannel.getRemoteAddress();
        int               resourcePort        = sourceSocketAddress.getPort();

        // 拿到这个socket channel使用的缓存区,准备读取数据
        ByteBuffer buffer = (ByteBuffer) readyKey.attachment();
        // 将通道的数据写入到缓存区
        int          realLen = 0;
        StringBuffer message = new StringBuffer();

        // 将目前通道中的数据写入到缓存区, 最大可写入的数据量就是buff的容量
        while((realLen = clientSocketChannel.read(buffer)) != -1) {
            // 把buffer切换成读模式,否则会由于limit = capacity,导致在read没有写满的情况下,就会导致多读
            buffer.flip();
            int position        = buffer.position();
            int capacity        = buffer.capacity();
            byte[] mesaageBytes = new byte[capacity];
            buffer.get(mesaageBytes, position, realLen);

            // 这种方式也可以读取数据的,而且不用关心position的位置
            // 因为是目前buffer所有的数据全部转出为一个byte数组
            // byte[] messageBytes = buffer.array();

            String  messageString = new String(mesaageBytes, 0, realLen, "UTF-8");
            message.append(messageString);

            // 再切换成'写'模式，直接清空缓存的方式,最快捷
            buffer.clear();
        }

        if(URLEncoder.encode(message.toString(), "utf-8").indexOf("over") != -1) {
            int channelUUID = clientSocketChannel.hashCode();
            SocketServer.LOGGER.info("端口: " + resourcePort + "客户端发来的消息: " + message.toString());
            StringBuffer completeMessage;
            // 清空messagehashcontext中的历史记录
            StringBuffer historyMessage = MESSAGEHASHCONTEXT.remove(channelUUID);
            if(historyMessage == null) {
                completeMessage = message;
            } else {
                completeMessage = historyMessage.append(message);
            }

            //进行业务处理

            // 回发数据,并关闭channel
            ByteBuffer sendBuffer = ByteBuffer.wrap(URLEncoder.encode("服务器处理后的数据", "utf-8").getBytes());
            clientSocketChannel.write(sendBuffer);
            clientSocketChannel.close();
        } else {
            SocketServer.LOGGER.info("端口: " + resourcePort + "客户端发来的消息未接受完,继续接受: " + URLDecoder.decode(message.toString(), "utf-8"));
            int          channelUUID    = clientSocketChannel.hashCode();
            StringBuffer historyMessage = MESSAGEHASHCONTEXT.get(channelUUID);
            if(historyMessage == null) {
                historyMessage = new StringBuffer();
            }
            MESSAGEHASHCONTEXT.put(channelUUID, historyMessage.append(message));
        }
    }
}
