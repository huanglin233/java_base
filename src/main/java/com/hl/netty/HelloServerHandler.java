package com.hl.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;

/**
 * @author huanglin by 2021/5/17
 */
public class HelloServerHandler extends ChannelInboundHandlerAdapter{

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        try{
            ByteBuf in = (ByteBuf)msg;
            System.out.println("message form client: " + in.toString(CharsetUtil.UTF_8));
            // 发消息给客户端
            ctx.writeAndFlush(Unpooled.copiedBuffer("你也好!", CharsetUtil.UTF_8));
        } finally{
            ReferenceCountUtil.release(msg);
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        // TODO Auto-generated method stub
        super.exceptionCaught(ctx, cause);
    }
}