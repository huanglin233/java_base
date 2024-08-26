package com.hl.algorithm.datastruct.tree.huffman;

/**
 * 哈夫曼树
 * @author huanglin
 * @date 2024/08/13 20:51
 */
public class Huffman {
    
    /**
     * 根节点
     */
    private HuffmanNode root;

    /**
     * 构造哈夫曼树
     */
    public Huffman(int a[]) {
        HuffmanNode parent = null;
        MinHeap     heap   = new MinHeap(a); 

        for(int i = 0; i < a.length - 1; i++) {
            // 最小节点是左孩子
            // 其次才是右孩子
            HuffmanNode left  = heap.dumpFromMinimum();            
            HuffmanNode right = heap.dumpFromMinimum();
            // 新建parent节点，左右孩子分别是left/right
            // parent的大小是左右孩子之和
            parent       = new HuffmanNode(left.key + right.key, left, right, null);
            left.parent  = parent;
            right.parent = parent;

            // 将parent节点拷贝刀最小堆中
            heap.insert(parent);
        }

        root = parent;
        // 最后销毁最小堆
        heap.destroy();
    }

    /**
     * 前序遍历哈夫曼树
     * @param tree
     */
    private void preOrder(HuffmanNode tree) {
        if(tree != null) {
            System.out.print(tree.key + " ");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 前序遍历哈夫曼树
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历哈夫曼树
     * @param tree
     */
    private void inOrder(HuffmanNode tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.print(tree.key + " ");
            inOrder(tree.right);
        }
    }

    /**
     * 中序遍历哈夫曼树
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 中序遍历哈夫曼树
     * @param tree
     */
    private void postOrder(HuffmanNode tree) {
        if(tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.print(tree.key + " ");
        }
    }

    /**
     * 后续遍历哈夫曼树
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 摧毁哈夫曼树
     * @param tree
     */
    private void destory(HuffmanNode tree) {
        if(tree != null) {
            return;
        }

        if(tree.left != null) {
            destory(tree.left);
        }
        if(tree.right != null) {
            destory(tree.right);
        }

        tree = null;
    }

    /**
     * 摧毁哈夫曼树
     */
    public void destory() {
        destory(root);
    }

    /**
     * 打印哈夫曼树
     * 
     * @param tree      AVL树
     * @param key       节点值
     * @param direction 0为根结点，1为左子结点，2为右子结点
     */
    private void print(HuffmanNode tree, int key, int direction) {
        if(tree != null) {
            if(direction == 0) {
                System.out.println("TREE: " + tree.key + " is root");
            } else {
                System.out.println(tree.key + " is " + key + "'s " + (direction == 1 ? "left" : "right") + " child");
            }

            print(tree.left, tree.key, 1);
            print(tree.right, tree.key, 2);
        }
    }

    /**
     * 打印树结构
     */
    public void print() {
        print(root, root.key, 0);
    }
}
