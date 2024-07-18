package com.hl.algorithm.datastruct.linkedList;

import lombok.Data;

/**
 * 链表节点
 * @author huanglin
 * @date 2024/04/28 23:26
 */
@Data
public class Node {

    /**
     * 数据域
     */
    private int val;

    /**
     * 指针域,指下一个节点
     */
    private Node next;

    public Node(int val) {
        this.val = val;
    }
}
