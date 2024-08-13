package com.hl.algorithm.datastruct.tree.huffman;

import lombok.extern.slf4j.Slf4j;

/**
 * 哈夫曼树节点
 * @author huanglin
 * @date 2024/08/12 22:38
 */
@Slf4j
public class HuffmanNode implements Comparable, Cloneable{
    
    // 权值
    protected int key;
    // 左孩子
    protected HuffmanNode left;
    // 右孩子
    protected HuffmanNode right;
    // 父节点
    protected HuffmanNode parent;

    public  HuffmanNode(int key, HuffmanNode left, HuffmanNode right, HuffmanNode parent) {
        this.key    = key;
        this.left   = left;
        this.right  = right;
        this.parent = parent;
    }

    @Override
    protected Object clone() {
        Object obj = null;
        try {
            obj = (HuffmanNode) super.clone();
        } catch(CloneNotSupportedException e) {
            log.error("不支持复制", e);
        }

        return obj;
    }

    @Override
    public int compareTo(Object o) {
        return this.key - ((HuffmanNode) o).key;
    }
}
