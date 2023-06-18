package com.hl.netty.rpc.client;

import java.lang.reflect.Proxy;
import java.util.concurrent.*;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

/**
 *
 * @author huanglin by 2021/5/17
 */
public class NettyClient {

    private static ThreadPoolExecutor executor = new ThreadPoolExecutor(10, 20, 1, TimeUnit.SECONDS, new ArrayBlockingQueue<>(1024), new ThreadPoolExecutor.CallerRunsPolicy());
    private static NettyClientHandler client;
    private int count = 0;

    /**
     * 代理模式获取一个代理对象
     */
    public Object getBean(final Class<?> serviceClass, final String providerName) {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                                        new Class<?>[]{serviceClass}, (proxy, method, args) -> {
                                            System.out.println("进入代理" + (++count) + "次数");
                                            if(client == null) {
                                                initClient();
                                            }
                                            // 设置要发给服务器端的消息
                                            // providerName 协议头
                                            // args[0] 是客户端传递的参数
                                            client.setParam(providerName + args[0]);

                                            return executor.submit(client).get();
                                        });
    }

    /**
     * 初始化客户端
     */
    private static void initClient() {
        client = new NettyClientHandler();
        final NioEventLoopGroup group = new NioEventLoopGroup();
        final Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(group)
            .channel(NioSocketChannel.class)
            .option(ChannelOption.TCP_NODELAY, true)
            .handler(new ChannelInitializer<SocketChannel>(){
                @Override
                protected void initChannel(final SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();
                    pipeline.addLast(new StringDecoder());
                    pipeline.addLast(new StringEncoder());
                    pipeline.addLast(client);
                }
            });

        try{
            bootstrap.connect("localhost", 8083).sync();
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}