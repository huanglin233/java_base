package com.hl.netty.rpc.client;

import java.util.concurrent.Callable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author huanglin by 2021/5/17
 */
public class NettyClientHandler extends ChannelInboundHandlerAdapter implements Callable{

    private ChannelHandlerContext context;
    private String                result;
    private String                param;
    
    @Override
    public void channelActive(final ChannelHandlerContext ctx) throws Exception {
        System.out.println("---初始化链接---");
        context = ctx;
    }

    @Override
    public synchronized void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        System.out.println("---读取数据---");
        System.out.println("recive : " + msg.toString());
        result= msg.toString();
        // 唤醒等待线程
        notify();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }

    /**
     * 被代理对象调用,发送数据给服务器
     */
    @Override
    public synchronized Object call() throws Exception {
        System.out.println("call 进入");
        context.writeAndFlush(param);
        // 等待channelRead方法获取到服务器的结果,唤醒
        wait();
        System.out.println("call 返回");

        return result;
    }

    public void setParam(String param) {
        this.param = param;
    }
}