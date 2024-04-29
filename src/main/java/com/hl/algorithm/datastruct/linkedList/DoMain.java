package com.hl.algorithm.datastruct.linkedList;

/**
 * @author huanglin
 * @date 2024/04/28 23:27
 */
public class DoMain {

    /**
     * 两个相交链表,找出相交点
     * @param h1 其中一个链表的头节点
     * @param h2 其中一个链表的头节点
     * @return 相交节点
     */
    public static Node getIntersectionNode(Node h1, Node h2) {
        Node l1 = h1, l2 = h2;
        while(l1 != l2) {
            l1 = l1 == null ? l2 : l1.getNext();
            l2 = l2 == null ? l1 : l2.getNext();
        }

        return l1;
    }

    /**
     * 反转链表 -- 递归
     * @param head 头节点
     * @return 反转后的头节点
     */
    public static Node reverseList1(Node head) {
        if(head == null || head.getNext() == null) {
            return null;
        }

        Node next = head.getNext();
        Node node = reverseList1(next);
        next.setNext(head);
        head.setNext(null);

        return node;
    }

    /**
     * 反转列表 -- 头插法
     * @param head 节点头
     * @return
     */
    public static Node reverseList2(Node head) {
        Node node = new Node(-1);
        while(head != null) {
            Node next = head.getNext();
            head.setNext(node.getNext());
            node.setNext(head);
            head = next;
        }

        return node.getNext();
    }
}
