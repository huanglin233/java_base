package com.hl.netty.rpc;

/**
 * 通用接口
 *
 * @author huanglin by 2021/5/17
 */
public interface MsgCodec {

    /**
     * 获取信息
     * @param  msg 获取信息关键词
     * @return 返回信息 String类型
     */
    String getMsg(String msg);
}