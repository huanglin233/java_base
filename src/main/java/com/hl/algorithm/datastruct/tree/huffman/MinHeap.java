package com.hl.algorithm.datastruct.tree.huffman;

import java.util.ArrayList;
import java.util.List;

/**
 * 最小堆
 * @author huanglin
 * @date 2024/08/12 22:58
 */
public class MinHeap {

    /**
     * 存储堆的数组
     */
    private List<HuffmanNode> heap;

    protected MinHeap(int[] a) {
        heap = new ArrayList<>();
        // 初始化数组
        for (int i = 0; i < a.length; i++) {
            heap.add(new HuffmanNode(a[i], null, null, null));
        }
        // 从(size/2 -1) --> 0 依次遍历,遍历之后得到的数组就是一个最小堆 
        for(int i = a.length / 2 - 1; i >= 0; i--) {
            filterdown(i, a.length);
        }
    }

    /**
     * 最小堆的向下调整算法
     * -- 数组实现的堆中：第N个节点的左孩子的索引值为(2N + 1), 右孩子的索引值为(2N + 2)
     * 
     * @param start 被下调节点的起始位置(一般为0，表示从第1个开始)
     * @param end   截至范围(一般为数组中最后一个元素的索引)
     */
    protected void filterdown(int start, int end) {
        int c = start;
        int l = 2 * c + 1;
        HuffmanNode tmp = heap.get(c);
        while(l < end) {
            // "l" 是左孩子， "l + 1" 是右孩子
            if(l < end && (heap.get(l).compareTo(heap.get(l + 1)) > 0)) {
                // 左右孩子选择较小者，即heap[l + 1]
                l++;
            }

            int cmp = tmp.compareTo(heap.get(l));
            if(cmp <= 0) {
                break; 
            } else {
                heap.set(c, heap.get(l));
                c = l;
                l = 2 * l + 1;
            }
        }

        heap.set(c, tmp);
    }

    /**
     * 最小堆的向上调整算法(从start开始向上直到0,调整堆)
     * -- 数组实现的堆中：第N个节点的左孩子的索引值为(2N + 1), 右孩子的索引值为(2N + 2)
     * 
     * @param start 被上调节点的起始位置(一般为0，表示从第1个开始)
     */
    protected void filterUp(int start) {
        // 当前节点的位置
        int c = start; 
        // 父节点的位置
        int p = (c - 1) / 2;
        HuffmanNode tmp = heap.get(c);
        while(c > 0){
            int cmp = heap.get(p).compareTo(tmp);
            if(cmp <= 0) {
                break;
            } else {
                heap.set(c, heap.get(p));
                c = p;
                p = (p - 1) / 2;
            }
        }

        heap.set(c, tmp);
    }

    /**
     * node节点插入二叉堆中
     * @param node
     */
    protected void insert(HuffmanNode node) {
        int size = heap.size();
        heap.add(node);
        filterUp(size);
    }

    /**
     * 交换两个节点的全部数据
     * @param i 索引1
     * @param j 索引2
     */
    private void swapNode(int i, int j) {
        HuffmanNode tmp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, tmp);
    }

    /**
     * 新建一个节点, 并将最小堆中最小节点的数据复制给该节点
     * 然后除最小节点外的数据重新构成最小堆
     * 
     * @return
     */
    protected HuffmanNode dumpFromMinimum() {
        int size = heap.size();

        // 如果"堆"已空，则返回null
        if(size == 0) {
            return null;
        }

        // 将"最小节点"克隆一份，将克隆得到的对象赋值给node
        HuffmanNode node = (HuffmanNode)heap.get(0).clone();
        // 交换"最小的节点"和"最后的一个节点"
        heap.set(0, heap.get( size - 1));
        // 删除最后的节点
        heap.remove(size - 1);
        if(heap.size() > 1) {
            filterdown(0, heap.size() - 1);
        }

        return node;
    }

    /**
     * 摧毁最小堆
     */
    public void destroy() {
        heap.clear();
        heap = null;
    }
}