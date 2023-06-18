package com.hl.netty;

import javax.naming.InitialContext;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 创建netty服务端
 *
 * @author huanglin by 2021/5/17
 */
public class HelloServer {
    private int port;

    public HelloServer(int port) {
        this.port = port;
    }

    private void start() throws InterruptedException {
        // 1.bossGroup 用于接收连接，workerGroup 用于具体的处理
        EventLoopGroup bossGroup  = new NioEventLoopGroup(1);
        EventLoopGroup workeGroup = new NioEventLoopGroup(1);
        try{
            // 2.创建服务端启动引导/辅助类：ServerBootstrap
            ServerBootstrap bootstrap = new ServerBootstrap();
            // 3.给引导类配置两个线程,确定了线程模型
            bootstrap.group(bossGroup, workeGroup)
                // (非必备)打印日志
                .handler(new LoggingHandler(LogLevel.INFO))
                // 4.指定 IO模型
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<SocketChannel>(){
                    @Override
                    public void initChannel(SocketChannel ch) {
                        ChannelPipeline p = ch.pipeline();
                        // 5.可以自定义客户端消息的业务逻辑
                        p.addLast(new HelloServerHandler());
                    }
                });
            // 6.绑定端口,调用sync方法阻塞知道绑定完成
            ChannelFuture future = bootstrap.bind(port).sync();
            // 7.阻塞等待知道服务器channel关闭(closeFuture()方法获取Channel的CloseFuture对象,然后调用sync()方法)
            future.channel().closeFuture().sync();
        } finally {
            // 8.优雅的关闭线程组资源
            bossGroup.shutdownGracefully();
            workeGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        new HelloServer(8083).start();
    }
}
