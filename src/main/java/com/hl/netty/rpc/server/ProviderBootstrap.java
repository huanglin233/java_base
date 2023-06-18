package com.hl.netty.rpc.server;

/**
 * @author huanglin by 2021/5/17
 */
public class ProviderBootstrap {

    public static void main(String[] args) throws InterruptedException {
        NettyServer.startServer("localhost", 8083);
    }
}