package com.hl.algorithm.datastruct.linkedList;

import java.util.Stack;

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

    /**
     * 归并两个有序的链表
     * @param node1 链表1的头节点
     * @param node2 链表2的头节点
     * @return 归并后的链表头节点
     */
    public static Node mergeTwoLists(Node node1, Node node2) {
        if(node1 == null) {
            return node2;
        }

        if(node2 == null) {
            return node1;
        }

        if(node1.getVal() < node2.getVal()) {
            node1.setNext(mergeTwoLists(node1.getNext(), node2));
            return node1;
        } else {
            node2.setNext(mergeTwoLists(node1, node2.getNext()));
            return node2;
        }
    }

    /**
     * 从有序表中删除重复的节点
     * @param head 有序列表头节点
     * @return
     */
    public static Node deleteDuplicateNode(Node head) {
        if(head == null || head.getNext() == null) {
            return head;
        }

        head.setNext(deleteDuplicateNode(head.getNext()));

        return head.getVal() == head.getNext().getVal() ? head.getNext() : head;
    }

    /**
     * 删除链表的倒数第n个节点
     * @param head 链表头节点
     * @param n    要删除的导出的n个节点
     * @return 删除指定得节点后的链表头节点
     */
    public static Node removeNthFromEnd(Node head, int n) {
        Node fast = head;
        while(n-- > 0) {
            fast = fast.getNext();
        }
        if(fast == null) {
            assert head != null;
            return head.getNext();
        }
        Node slow = head;
        while(fast.getNext() != null) {
            fast = fast.getNext();
            slow = head.getNext();
        }

        slow.setNext(slow.getNext().getNext());

        return head;
    }

    /**
     * 交换链表中相邻得节点
     * @param head 链表头节点
     * @return 交换后的链表头节点
     */
    public static Node swaPairs(Node head) {
        Node node = new Node(-1);
        node.setNext(head);
        Node pre = node;
        while(pre.getNext() != null && pre.getNext().getNext() != null) {
            Node cur      = pre.getNext(), next = cur.getNext();
            Node nextNext = next.getNext();
            pre.setNext(next);
            next.setNext(cur);
            cur.setNext(nextNext);
            pre = next;
        }

        return pre.getNext();
    }

    /**
     * 两个链表求和
     * @param n1 链表1
     * @param n2 链表2
     * @return
     */
    public static Node addTowNumbers(Node n1, Node n2) {
        Stack<Integer> stack1 = buildStack(n1);
        Stack<Integer> stack2 = buildStack(n2);
        Node           head   = new Node(-1);
        int            carry  = 0;
        while(!stack1.isEmpty() || !stack2.isEmpty() || carry != 0) {
            int x   = stack1.isEmpty() ? 0 : stack1.pop();
            int y   = stack2.isEmpty() ? 0 : stack2.pop();
            int sum = x + y + carry;

            Node node = new Node(sum % 10);
            node.setNext(head.getNext());
            head.setNext(node);
            carry = sum / 10;
        }

        return head.getNext();
    }

    /**
     * 把链表存入堆中 --后进先出
     * @param node 链表头节点
     * @return 堆
     */
    private static Stack<Integer> buildStack(Node node) {
        Stack<Integer> stack = new Stack<Integer>();
        while(node != null) {
            stack.push(node.getVal());
            node = node.getNext();
        }

        return stack;
    }

    /**
     * 判断一个链表是否为回文链表
     * @param head 链表头节点
     * @return 是否为回文链表
     */
    public static boolean isPalindrome(Node head) {
        if(head == null || head.getNext() == null) {
            return true;
        }

        Node slow = head, fast = head.getNext();
        while(fast != null && fast.getNext() != null) {
            slow = slow.getNext();
            fast = fast.getNext().getNext();
        }

        if(fast != null) {
            slow = slow.getNext();
        }

        cut(head, slow);
        return isEqual(head, reverseList2(slow));
    }

    /**
     * 分割链表
     * @param root 头节点
     * @param k    切割份数
     * @return
     */
    public static Node[] splitListToParts(Node root, int k) {
        // 先计算链表的长度
        int len = 0;
        Node cur = root;
        while (cur != null) {
            len++;
            cur = cur.getNext();
        }

        int    mod   = len % k;
        int    size  = len / k;
        Node[] nodes = new Node[k];
        cur = root;
        for(int i = 0; cur != null && i < k; i++) {
            nodes[i] = cur;
            int curSize = size + (mod-- > 0 ? 1 : 0);
            for(int j = 0; j < curSize - 1; j++) {
                cur = cur.getNext();
            }
            Node next = cur.getNext();
            cur.setNext(null);
            cur = next;
        }

        return nodes;
    }

    /**
     * 链表元素按奇偶聚集
     * @param head
     * @return
     */
    public Node oddEvenList(Node head) {
        if(head == null) {
            return null;
        }

        Node odd      = head;
        Node even     = head.getNext();
        Node evenHead = even;
        while(even != null && even.getNext() != null) {
           odd.setNext(odd.getNext().getNext());
           odd = odd.getNext();
           even.setNext(even.getNext().getNext());
           even = even.getNext();
        }
        odd.setNext(evenHead);

        return head;
    }

    /**
     * 切割链表
     * @param head
     * @param cutNode
     */
    private static void cut(Node head, Node cutNode) {
        while(head.getNext() != cutNode) {
            head = head.getNext();
        }

        head.setNext(null);
    }


    /**
     * 比较值
     * @param n1 链表头节点1
     * @param n2 链表头节点2
     * @return boolean
     */
    private static boolean isEqual(Node n1, Node n2) {
        while(n1 != null && n2 != null) {
            if(n1.getVal() != n2.getVal()) {
                return false;
            }

            n1 = n1.getNext();
            n2 = n2.getNext();
        }

        return true;
    }
}
