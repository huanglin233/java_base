package com.hl.algorithm.datastruct.tree.avl;

/**
 * AVL测试
 * @author huanglin
 * @date 2024/08/10 20:10
 */
public class AVLTreeTest {
    
    private static int[] arr = {3, 2, 1, 4, 5, 6, 7, 16, 15, 14, 13, 12, 11, 10};
    public static void main(String[] args) {
        int i;
        AVLTree<Integer> avlTree = new AVLTree<>();
        System.out.println("---依次添加: ");
        for(i = 0; i < arr.length; i++) {
            System.out.printf("%d", i);
            avlTree.insert(arr[i]);
        }

        System.out.printf("\n---前序遍历: ");
        avlTree.preOrder();

        System.out.printf("\n---中序遍历: ");
        avlTree.inOrder();

        System.out.printf("\n---后序遍历: ");
        avlTree.postOrder();

        System.out.println("---高度: " + avlTree.height());
        System.out.println("---最小值: " + avlTree.minimum());
        System.out.println("---最大值: " + avlTree.maximun());
        System.out.println("---打印树结构: ");
        avlTree.print();

        i = 8;
        System.out.println("---删除结点: " + i);
        avlTree.remove(i);

        System.out.println("---高度: " + avlTree.height());
        System.out.printf("\n---中序遍历: ");
        avlTree.inOrder();
        System.out.println("---打印树结构: ");
        avlTree.print();

        // 销毁avl树
        avlTree.destroy();
    }
}
