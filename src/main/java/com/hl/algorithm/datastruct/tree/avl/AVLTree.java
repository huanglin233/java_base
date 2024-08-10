package com.hl.algorithm.datastruct.tree.avl;

import com.hl.javase.test.A;

import AVLNode;

/**
 * @author huanglin
 * @date 2024/08/10 16:29
 */
public class AVLTree<T extends Comparable<T>> {

    // 根结点
    AVLNode<T> root;
    
    // AVL树结点--内部类
    class AVLNode<T extends Comparable<T>> {
        // 关键字
        T key;
        // 树的高度
        int height;
        // 左孩子
        AVLNode<T> left;
        // 右孩子
        AVLNode<T> right;

        public AVLNode(T key, AVLNode<T> left, AVLNode<T> right) {
            this.key = key;
            this.left = left;
            this.right = right;
        }
    }

    // 构造函数
    public AVLTree() {
        root = null;
    }

    /**
     * 获取结点的包含结点孩子的树高度
     * @return
     */
    private int height(AVLNode<T> tree) {
        if(tree != null) {
            return tree.height;
        }

        return 0;
    }

    /**
     * avl树的高度
     * @return
     */
    public int height() {
        return height(root);
    }

    /**
     * 比较连个数的大小
     * @param a
     * @param b
     * @return
     */
    private int max(int a , int b) {
        return a > b ? a : b;
    }

    /**
     * 前序遍历 'AVL树'
     * @param tree
     */
    private void preOrder(AVLNode<T> tree) {
        if(tree != null) {
            System.out.println(tree.key + "");
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历 'AVL树'
     * @param tree
     */
    private void inOrder(AVLNode<T> tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.println(tree.key + "");
            preOrder(tree.right);
        }
    } 

    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后续遍历 'AVL树'
     * @param tree
     */
    private void postOrder(AVLNode<T> tree) {
        if(tree != null) {
            postOrder(tree.left);
            postOrder(tree.right);
            System.out.println(tree.key + "");
        }
    }

    public void postOrder() {
        postOrder(root);
    }

    /**
     * 递归实现 -- 在一棵树中查找结点
     * @param tree 树
     * @param key  查找的值
     * @return
     */
    private AVLNode<T> search(AVLNode<T> tree, T key) {
        if(tree == null) {
            return null;
        }

        int cmp = key.compareTo(tree.key);
        if(cmp < 0) {
            return search(tree.left, key);
        } else if(cmp > 0) {
            return search(tree.right, key);
        } else {
            return tree;
        }
    }

    /**
     * 递归实现 -- 在AVL树中查找一个值
     * @param key 查找的值
     * @return
     */
    public AVLNode<T> search(T key) {
        return search(root, key);
    }

    /**
     * 非递归实现 -- 在一棵树中查找
     * @param tree 树
     * @param key  查找的值
     * @return
     */
    private AVLNode<T> iterativeSearch(AVLNode<T> tree, T key) {
        while(tree != null) {
            int cmp = key.compareTo(tree.key);
            if(cmp < 0) {
                tree = tree.left;
            } else if(cmp > 0) {
                tree = tree.right;
            } else {
                return tree;
            }
        }

        return null;
    }

    public AVLNode<T> iterativeSearch(T key) {
        return iterativeSearch(root, key);
    }

    /**
     * 查找树的最小结点
     * @param tree
     * @return
     */
    private AVLNode<T> minimum(AVLNode<T> tree) {
        if(tree == null) {
            return null;
        }

        while(tree.left != null) {
            tree = tree.left;
        }

        return tree;
    }

    /**
     * 查找AVL树的最小的值
     * @return
     */
    public T minimum() {
        AVLNode<T> minimum = minimum(root);
        if(minimum != null) {
            return minimum.key;
        } else {
            return null;
        }
    }

    /**
     * 查找树的最大结点
     * @param tree
     * @return
     */
    private AVLNode<T> maximun(AVLNode<T> tree) {
        if(tree == null) {
            return null;
        }

        while(tree.right != null) {
            tree = tree.right;
        }

        return tree;
    }

    /**
     * 查找avl树的最大值
     * @return
     */
    public T maximun() {
        AVLNode<T> maximun = maximun(root);;
        if(maximun != null) {
            return maximun.key;
        } else {
            return null;
        }
    }
    
    /**
     * LL: 左左对应的情况(左单旋转)
     * @param node
     * @return 旋转后的根结点
     */

    private AVLNode<T> leftLeftRotation(AVLNode<T> node) {
        AVLNode<T> ret;

        ret = node.left;
        node.left = ret.right;
        ret.right = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        ret.height = max(height(ret.left), node.height) + 1;

        return ret;
        
    }

    /**
     * RR: 右右对应的情况(右单旋转)
     * @param node
     * @return 旋转后的根结点
     */
    private AVLNode<T> rightRightRotation(AVLNode<T> node) {
        AVLNode<T> ret;

        ret = node.right;
        node.right = ret.left;
        ret.left = node;

        node.height = max(height(node.left), height(node.right)) + 1;
        ret.height = max(height(ret.right), node.height) + 1;

        return ret;
    }

    /**
     * LR: 左右对应的情况(左双旋转)
     * @param node
     * @return 旋转后的根结点
     */
    private AVLNode<T> leftRightRotation(AVLNode<T> node) {
        node.left = rightRightRotation(node.left);

        return leftLeftRotation(node);
    }

    /**
     * RL: 右左对应的情况(右双旋转)
     * @param node
     * @return 旋转后的根结点
     */
    private AVLNode<T> rightLeftRotation(AVLNode<T> node) {
        node.right = leftLeftRotation(node.right);

        return rightRightRotation(node);
    }

    /**
     * 将结点插入到AVL树中, 并返回根结点
     * @param tree AVL树
     * @param key 插入的值
     * @return 根结点
     */
    private AVLNode<T> insert(AVLNode<T> tree, T key) {
        if(tree == null) {
            // 新建结点
            tree = new AVLNode<T>(key, null, null);
        } else {
            int cmp = key.compareTo(tree.key);
            if(cmp < 0) {
                // 将key插入到左子树中
                tree.left = insert(tree.left, key);
                // 插入结点后，判断AVL树是否失去了平衡,失去了则进行相应的调整
                if(height(tree.left) - height(tree.right) == 2) {
                    if(key.compareTo(tree.left.key) < 0) {
                        tree = leftLeftRotation(tree);
                    } else {
                        tree = leftLeftRotation(tree);
                    }
                }
            } else if(cmp > 0) {
                // 将key插入到右子树中
                tree.right = insert(tree.right, key);
                // 插入结点后，判断AVL树是否失去了平衡,失去了则进行相应的调整
                if(height(tree.right) - height(tree.left) == 2) {
                    if(key.compareTo(tree.right.key) > 0) {
                        tree = rightRightRotation(tree);
                    } else {
                        tree = rightLeftRotation(tree);
                    }
                }
            } else {
                System.out.println("添加失败，不允许添加相同的值！");
            }
        }

        tree.height = max(height(tree.left), height(tree.right)) + 1;

        return tree;
    }

    public void insert(T key) {
        root = insert(root, key);
    }

    /**
     * 删除值为key的结点 -- 并返回删除后的根结点
     * @param tree AVL树
     * @param key  删除的结点值
     * @return
     */
    private AVLNode<T> remove(AVLNode<T> tree, T key) {
        if(tree == null || key == null) {
            return null;
        }

        int cmp = key.compareTo(tree.key);
        if(cmp < 0) {
            // 待删除的结点在tree的左子树中
            tree.left = remove(tree.left, key);
            if(height(tree.right) - height(tree.left) == 2) {
                AVLNode<T> r = tree.right;
                if(height(r.left) > height(r.right)) {
                    tree = rightLeftRotation(tree);
                } else {
                    tree = rightRightRotation(tree);
                }
            }
        } else if(cmp > 0) {
            // 待删除的结点在tree的右子树中
            tree.right = remove(tree.right, key);
            if(height(tree.left) - height(tree.right) == 2) {
                AVLNode<T> l = tree.left;
                if(height(l.right) > height(l.left)) {
                    tree = leftRightRotation(tree);
                } else {
                    tree = leftLeftRotation(tree);
                }
            }
        } else {
            // 删除对应的结点
            if((tree.left != null) && (tree.right != null)) {
                // 要删除结点的左右子节点都不为空
                if(height(tree.left) > height(tree.right)) {
                    // 如果tree的左子树比右子树高
                    // 则(01)找出tree的左子树中的最大结点
                    //   (02)将该最大结点的值赋值给tree
                    //   (03)删除该最大结点
                    // 这类似于用"tree的左子树中最大结点"做"tree"的替身；
                    AVLNode<T> max = maximun(tree.left);
                    tree.key = max.key;
                    tree.left = remove(tree.left, max.key);
                } else {
                    // 如果tree的右子树比左子树高,或左右子树高度相等
                    // 则(01)找出tree的右子树中的最小结点
                    //   (02)将该最小结点的值赋值给tree
                    //   (03)删除该最小结点
                    // 这类似于用"tree的右子树中最小结点"做"tree"的替身；
                    AVLNode<T> min= minimum(tree.right);
                    tree.key = min.key;
                    tree.right = remove(tree.right, min.key);
                }
            } else {
                AVLNode<T> tmp = null;
                if(tree.left != null) {
                    tmp = tree.left;
                } else {
                    tmp = tree.right;
                } 
                tree = tmp;
            }
        }

        tree.height = max(height(tree.left), height(tree.right)) + 1;
        return tree;
    }

    /**
     * 删除值为key的结点
     * @param key
     */
    public void remove(T key) {
        root = remove(root, key);
    }

    /**
     * 销毁树
     * @param tree 需要销毁的树
     */
    private void destroy(AVLNode<T> tree) {
        if(tree == null) {
            return;
        }

        if(tree.left != null) {
            destroy(tree.left);
        }
        if(tree.right != null) {
            destroy(tree.right);
        }

        tree = null;
    }
    
    /**
     * 销毁AVL树
     */
    public void destroy() {
        destroy(root);
    }

    /**
     * 打印AVL树
     * @param tree      AVL树
     * @param key       节点值
     * @param direction 0为根结点，1为左子结点，2为右子结点
     */
    private void print(AVLNode<T> tree, T key, int direction) {
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
     * 打印树
     */
    public void print() {
        if(root != null) {
            print(root, root.key, 0);
        }
    }

}
