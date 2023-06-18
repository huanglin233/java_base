package com.hl.netty.rpc.client;

import com.hl.netty.rpc.MsgCodec;

/**
 * 消费端启动类
 *
 * @author huanglin by 2021/5/17
 */
public class ClientBootstrap {
    /**协议头 */
    public static final String PROVIDER_NAME = "HelloService#hello#";

    public static void main(final String[] args) throws InterruptedException {
        final NettyClient client = new NettyClient();
        // 创建代理对象
        MsgCodec msgCodec = (MsgCodec) client.getBean(MsgCodec.class, PROVIDER_NAME);
        while(true) {
            Thread.sleep(2 * 1000);
            String msg = msgCodec.getMsg("hello provider");
            System.out.println("结果 : " + msg);
        }
    }
}