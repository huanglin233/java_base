package com.hl.algorithm.datastruct.tree.trie;

import java.util.TreeMap;

/**
 * 前缀树
 * @author huanglin
 * @date 2024/08/14 20:37
 */
public class Trie {

    /**
     * 根结点
     */
    private Node root;
    /**
     * Trie单词个数
     */
    private int size;

    private Trie() {
        root = new Node();

    }
    
    private class Node {

        /**
         * 是否是某个单词的结束
         */
        public boolean isWord;

        /**
         * 到下一个结点的映射
         */ 
        public TreeMap<Character, Node> next;

        public Node(boolean isWord) {
            this.isWord = isWord;
            // 初始化字典树
            next = new TreeMap();
        }

        public Node() {
            this(false);
        }
    }

    /**
     * 获得前缀树中存储的单词个数
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * -- 非递归实现
     * 向前缀树添加一个新的单词word：将单词拆分成一个个字符c，然后从根结点开始往下添加
     * @param word
     */
    public void add(String word) {
        Node cur = root;

        // 循环判断新的cur结点是否包含下一个字符到下一个结点的映射
        for(int i = 0; i < word.length(); i++) {
            // 将c当成一个结点插入前缀树中
            char c = word.charAt(i);
            // 判断cur.next是不是已经指向我们要找的c字符相应结点
            if(cur.next.get(c) == null) {
                // 新建结点
                cur.next.put(c, new Node());
            }

            // 否则，就直接将cur指向下一个结点
            cur = cur.next.get(c);
        }

        // 判断该结点是否是否表示一个单词的结尾
        if(!cur.isWord) {
            // 确定cur是新的单词
            cur.isWord = true;
            size++;
        }
    }

    /**
     * 向前缀树插入一个单词
     * @param word 插入单词
     */
    public void recursionAdd(String word) {
        Node cur = root;
        add(cur, word, 0);
    }

    /**
     * -- 非递归实现
     * 查询前缀树中的单词
     * @param word 查询的单词
     * @return
     */
    public boolean contains(String word) {
        Node cur = root;
        for(int i = 0; i < word.length(); i++) {
            char c = word.charAt(i);
            if(cur.next.get(c) == null) {
                return false;
            } else {
                cur = cur.next.get(c);
            }
        }

        return cur.isWord;
    }

    /**
     * -- 递归实现
     * 查询
     * @param word
     * @return
     */
    public boolean recursionContains(String word) {
        Node cur = root;
        return contains(cur, word, 0);
    }

    /**
     * -- 非递归实现
     * 查询前缀树中是否包含一个单词前缀
     * @param prefix 单词前缀
     * @return
     */
    public boolean isPrefix(String prefix) {
        Node cur = root;
        for(int i = 0; i < prefix.length(); i++) {
            char c = prefix.charAt(i);
            if(cur.next.get(c) == null) {
                return false;
            }

            cur = cur.next.get(c);
        }

        return true;
    }

    /**
     * -- 递归实现
     * 查询前缀树中是否包含一个单词前缀
     * @param prefix 单词前缀
     * @return
     */
    public boolean recursionIsPrefix(String prefix) {
        Node cur = root;
        return isPrefix(cur, prefix, 0);
    }

    /**
     * -- 递归实现
     * 向前缀树添加一个新的单词
     * @param node  要添加的结点
     * @param word  添加的单词
     * @param index 当前单词的索引
     */
    private void add(Node node, String word, int index) {
        if(!node.isWord && index == word.length()) {
            node.isWord = true;
            size++;
        }
        
        if(word.length() > index) {
            char addLetter = word.charAt(index);
            if(node.next.get(addLetter) == null) {
                node.next.put(addLetter, new Node());
            }

            // 基于已经存在的字符进行下个字符的递归查询
            add(node.next.get(addLetter), word, index + 1);
        }
    }

    /**
     * -- 递归实现
     * 查询前缀树中的单词
     * @param node   查询的结点
     * @param word   查询的单词
     * @param index  查询字符的索引
     */
    private boolean contains(Node node, String word, int index) {
        if(index == word.length()) {
            return node.isWord;
        }

        char c = word.charAt(index);
        if(node.next.get(c) == null) {
            return false;
        } else {
            return contains(node.next.get(c), word, index + 1);
        }
    }

    /**
     * -- 非递归实现
     * 查询前缀树中是否包含一个单词前缀
     * @param node   字符结点
     * @param prefix 前缀单词
     * @param index  当前字符的索引
     * @return
     */
    private boolean isPrefix(Node node, String prefix, int index) {
        if(prefix.length() == index) {
            return true;
        }

        char c = prefix.charAt(index);
        if(root.next.get(c) == null) {
            return false;
        } else {
            return isPrefix(node.next.get(c), prefix, index + 1);
        }
    }
}
