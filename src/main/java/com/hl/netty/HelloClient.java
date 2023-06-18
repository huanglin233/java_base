package com.hl.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

/**
 * @author huanglin by 2021/5/17
 */
public class HelloClient {

    private final String host;
    private final int    port;
    private final String message;

    public HelloClient(final String host, final int port, final String message) {
        this.host    = host;
        this.port    = port;
        this.message = message;
    }

    private void start() throws InterruptedException {
        // 1.创建一个NioEvenetGroup;
        NioEventLoopGroup clientGroup = new NioEventLoopGroup();
        try {
            // 2.创建客户端启动引导/辅助类型: BootStrap
            Bootstrap bootstrap = new Bootstrap();
            // 3.指定线程
            bootstrap.group(clientGroup)
                // 4.指定Io类型
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    protected void initChannel(SocketChannel ch) throws Exception {
                        ChannelPipeline p = ch.pipeline();
                        // 5.这里自定义消息的业务类型处理逻辑
                        p.addLast(new HelloClientHandler(message));
                    }
                });
            // 6.尝试建立链接
            ChannelFuture future = bootstrap.connect(host, port).sync();
            // 7.等待连接关闭（阻塞，直到Channel关闭）
            future.channel().closeFuture().sync();
        } finally {
            // 8.优雅的关闭线程组资
            clientGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloClient("127.0.0.1",8083, "你好呀!").start();
    }
}