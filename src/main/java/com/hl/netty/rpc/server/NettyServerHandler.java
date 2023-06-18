package com.hl.netty.rpc.server;

import com.hl.netty.rpc.client.ClientBootstrap;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * @author huanglin by 2021/5/17
 */
public class NettyServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(final ChannelHandlerContext ctx, final Object msg) throws Exception {
        System.out.println("request msg => " + msg);
        // 定义一个协议,比如"helloService#hello#你好
        if(msg.toString().startsWith(ClientBootstrap.PROVIDER_NAME)) {
            // 截取协议后的内容
            String codecMsg = new MsgCodecImpl().getMsg(msg.toString().substring(msg.toString().lastIndexOf("#") + 1));
            System.out.println("response msg => " + codecMsg);
            ctx.writeAndFlush(codecMsg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
    }
}