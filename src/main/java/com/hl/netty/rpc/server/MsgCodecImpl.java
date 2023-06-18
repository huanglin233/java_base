package com.hl.netty.rpc.server;

import com.hl.netty.rpc.MsgCodec;

/**
 * @author huanglin by 2021/5/17
 */
public class MsgCodecImpl implements MsgCodec {
    private static int count =0;

    @Override
    public String getMsg(String msg) {
        System.out.println("收到消息客户端消息 => " + msg);

        return "服务端 ==> 收到消息 [" + msg + "] 第" + (++count) + "次";
    }
}