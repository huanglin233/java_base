package com.hl.algorithm.datastruct.tree.huffman;

/**
 * 哈夫曼树测试类
 * 
 * @author huanglin
 * @date 2024/08/13 22:48
 */
public class HuffmanTest {
    
    private static final int a[] = {5, 6, 8, 7, 15};

    public static void main(String[] args) {
        int     i;
        Huffman tree;

        System.out.println("---添加数组---");
        for(i = 0; i < a.length; i++) {
            System.out.print(a[i] + "");
        }

        tree = new Huffman(a);
        System.out.println("\n---前序遍历---");
        tree.preOrder();
        System.out.println("\n---中序遍历---");
        tree.inOrder();
        System.out.println("\n---后续遍历---");
        tree.postOrder();
        System.out.println("\n---树结构---");
        tree.print();
        System.out.println("\n---摧毁树结构");
        tree.destory();
    }
}
