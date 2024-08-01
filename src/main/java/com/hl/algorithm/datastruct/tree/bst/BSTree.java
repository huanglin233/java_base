package com.hl.algorithm.datastruct.tree.bst;

/**
 * 二叉搜索树
 * @author huanglin
 * @date 2024/07/17 20:24
 */
public class BSTree<T extends Comparable<T>> {
    
    /**
     * 根节点
     */
    private BSTNode<T> root;

    public class BSTNode<T extends Comparable<T>> {
        /**
         * 值
         */
        T key;
        /**
         * 左子树
         */
        BSTNode<T> left;
        /**
         * 右子树
         */
        BSTNode<T> right;
        /**
         * 父节点
         */
        BSTNode<T> parent;

        public BSTNode(T key, BSTNode<T> parent, BSTNode<T> left, BSTNode<T> right) {
            this.key    = key;
            this.parent = parent;
            this.left   = left;
            this.right  = right;
        }

        public T getKey() {
            return key;
        }

        public String toString() {
            return "key:" + this.key;
        }
    }

    /**
     * 前序遍历
     * @param tree 二叉搜索树
     */
    private void preOrder(BSTNode<T> tree) {
        if(tree != null) {
            System.out.println(tree.getKey());
            preOrder(tree.left);
            preOrder(tree.right);
        }
    }

    /**
     * 前序遍历
     */
    public void preOrder() {
        preOrder(root);
    }

    /**
     * 中序遍历
     * @param tree 二叉搜索树
     */
    private void inOrder(BSTNode<T> tree) {
        if(tree != null) {
            inOrder(tree.left);
            System.out.println(tree.getKey());
            inOrder(tree.right);
        }
    }

    /**
     * 中序遍历
     */
    public void inOrder() {
        inOrder(root);
    }

    /**
     * 后序遍历
     * @param tree 二叉搜索树
     */
    private void postOrder(BSTNode<T> tree) {
        if(tree != null) {
            postOrder(tree.right);
            postOrder(tree.left);
            System.out.println(tree.getKey());
        }
    }

    /**
     * 后序遍历
     */
    public void postOrder() {
        postOrder(root);
    }

    /**
     * 递归查找"二叉树的"中键值为key的节点
     * @param tree 二叉树
     * @param key  查找的key
     * @return
     */
    private BSTNode<T> search(BSTNode<T> tree, T key) {
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
     * 递归查找"二叉树"中键值为key的节点
     * @param key 查找的key
     * @return
     */
    public BSTNode<T> search(T key) {
        return search(root, key);
    }

    /**
     * 非递归查找"二叉树"中键值为key的节点
     * @param tree 二叉树
     * @param key 查找的key
     * @return
     */
    private BSTNode<T> iterativeSearch(BSTNode<T> tree, T key) {
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

        return tree;
    }

    /**
     * 非递归查找"二叉树"中键值为key的节点
     * @param key 查找的key
     * @return
     */
    public BSTNode<T> iterativeSearch(T key) {
        return iterativeSearch(root, key);
    }

    /**
     * 查找最小结点: 返回tree为根结点的二叉树的最小结点
     * @param tree 二叉树
     * @return
     */
    private BSTNode<T> minimum(BSTNode<T> tree) {
        if(tree == null) {
            return null;
        }

        while(tree.left != null) {
            tree = tree.left;
        }

        return tree;
    }

    /**
     * 获取二叉树的最小值
     * @return
     */
    public T minimum() {
        return minimum(root).key;
    }
 
    /**
     * 查找最大结点: 返回tree为根结点的二叉树的最大结点
     * @param tree 二叉树
     * @return
     */
    private BSTNode<T> maximum(BSTNode<T> tree) {
        if(tree == null) {
            return null;
        }

        while(tree.right != null) {
            tree = tree.right;
        }

        return tree;
    }

    /**
     * 查找二叉树的最大值
     * @return
     */
    public T maximum() {
        return maximum(root).key;
    }

    /**
     * 查找结点node前驱结点: 即查找'二叉树中数据值小于该结点的最大最结点'
     * @param tree
     * @return
     */
    public BSTNode<T> predecessor(BSTNode<T> node) {
        // 如果node存在左孩子,则node的前驱结点为'其左孩子为根的子树值最大的结点'
        if(node.left != null) {
            return maximum(node.left);
        }

        // 如果node没有左孩子
        // 1.node是一个右孩子，则node的前驱结点为他的父级结点
        // 2.node是一个左孩子，则查找node的最低父结点，且是该最低父结点的右孩子,该最低父结点就弄node是前驱结点
        BSTNode<T> y = node.parent;
        while(y != null && (node == y.left)) {
            node = y;
            y = y.parent;
        }

        return y;
    }

    /**
     * 查找结点node后驱结点: 即查找'二叉树中数据值大于该结点的最小值'
     * @param node
     * @return
     */
    public BSTNode<T> successor(BSTNode<T> node) {
        // 如果node存在右孩子,则node的后驱结点为'其右孩子为根的子树值最小的结点'
        if(node.right != null) {
            return minimum(node.right);
        }

        // 如果node没有右孩子
        // 1.node是一个左孩子,则node的后驱结点为他的父级结点
        // 2.node是一个右孩子,则查找node的最低父结点,且是该最低父结点的左孩子,该最低父结点就弄node是后驱结点
        BSTNode<T> y = node.parent;
        while(y != null && (node == y.right)) {
            node = y;
            y = y.parent;
        }

        return y;
     }

     /**
      * 将结点插入二叉树中
      * @param bst  二叉树
      * @param node 插入的结点
      */
     private void insert(BSTree<T> bst, BSTNode<T> node) {
        int cmp;
        BSTNode<T> y = null;
        BSTNode<T> x = bst.root;

        // 查找插入node的位置
        while(x != null) {
            y = x;
            cmp = node.key.compareTo(x.key);
            if(cmp < 0) {
                x = x.left;
            } else if(cmp > 0) {
                x = x.right;
            }
        }

        node.parent = y;
        if(y == null) {
            bst.root = node;
        } else {
            cmp = node.key.compareTo(y.key);
            if(cmp < 0) {
                y.left = node;
            } else {
                y.right = node;
            }
        }
     }

     /**
      * 向二叉树插入一个值
      * @param key 插入值
      */
     public void insert(T key) {
        BSTNode<T> node = new BSTNode<T>(key, null, null, null);
        if(node != null) {
            insert(this, node);
        }
     }

     /**
      * 删除二叉树中的结点
      * @param tree 二叉数据
      * @param node 删除结点
      */
     private BSTNode<T> remvoe(BSTree<T> tree, BSTNode<T> node) {
        BSTNode<T> x = null;
        BSTNode<T> y = null;

        if((node.left == null) || node.right == null) {
            y = node;
        } else {
            y = successor(node);
        }

        if(y.left != null) {
            x = y.left;
        } else {
            x = y.right;
        }

        if(x != null) {
            x.parent = y.parent;
        }

        if(y.parent == null) {
            tree.root = x;
        } else if(y == y.parent.left) {
            y.parent.left = x;
        } else {
            y.parent.right = x;
        }

        if(y != node) {
            node.key = y.key;
        }

        return y;
     }

     /**
      * 删除二叉树中值为key的结点
      * @param key
      */
     public void remove(T key) {
        BSTNode<T> r, node;

        if((r = search(root, key)) != null) {
            if((node = remvoe(this, r)) != null) {
                node = null;
            }
        }
     }

     /**
      * 销毁二叉树
      * @param tree 二叉树
      */
     private void destroy(BSTNode<T> tree) {
        if(tree == null) {
            return;
        }

        if(tree.left != null) {
            destroy(tree.left);
        }
        if(tree.right != null) {
            destroy(tree.right);
        }
     }

     /**
      * 清空二叉树数据
      */
     public void clear() {
        destroy(root);
        root = null;
     }

     /**
      * 修剪二叉树
      * @param root 二叉树根结点
      * @param L    最小值
      * @param R    最大值
      * @return
      */
     public BSTNode<T> trimBst(BSTNode<T> root, T L, T R) {
        if(root == null) {
            return null;
        }

        int cmp = root.key.compareTo(R);
        if(cmp > 0) {
            return trimBst(root.left, L, R);
        }
        if(cmp < 0) {
            return trimBst(root.right, L, R);
        }

        root.left  = trimBst(root.left, L, R);
        root.right = trimBst(root.right, L, R);

        return root;
     }

     /**
      * 查找二叉树中第k小的结点 --中序查找
      * @param root 二叉树根结点
      * @param k    第k小的结点
      * @return 第k小结点的值
      */
     public T kthSmallestByInOrder(BSTNode<T> root, int k) {
        return inOrder(root, k, 0);
     }

     /**
      * 中序查找
      * @param node 结点
      * @param k    第k小的结点
      * @param cnt  结点计数
      * @return
      */
     private T inOrder(BSTNode<T> node, int k, int cnt) {
        if(node == null) {
            return null;
        }
        inOrder(node.left, k, cnt);
        cnt++;
        if(cnt == k) {
            return node.key;
        }
        inOrder(node.right, k, cnt);

        return null;
     }

     /**
      * 查找二叉树中第k小的结点 --递归查找
      * @param root 二叉树根结点
      * @param k    第k小的结点
      * @return 第k小结点的值
      */
     public T kthSmallestByRecursion(BSTNode<T> root, int k) {
        int leftCount = count(root);
        if(leftCount == k - 1) {
            return root.key;
        }
        if(leftCount > k -1) {
            return kthSmallestByRecursion(root.left, k);
        }
               
        return kthSmallestByRecursion(root.right, k - leftCount - 1);
     }

     /**
      * 计算一个棵树的结点数
      * @param node 树的父结点
      * @return 树的结点数
      */
     private int count(BSTNode<T> node) {
        if(node == null) {
            return 0;
        }

        return 1 + count(node.left) + count(node.right);
     }


     /**
      * 树结点每个值都加上大于他的值
      * @param tree 树父结点
      * @return
      */
     public BSTNode<Integer> covertBsTree(BSTNode<Integer> tree) {
        traver(tree, 0);

        return tree;
     }

     /**
      * 遍历右子树，结点每个值都加上大于他的值---递归
      * @param node 树的父结点
      * @param sum  累加值
      */
     private void traver(BSTNode<Integer> node, int sum) {
        if(node == null) {
            return;
        }
        traver(node.right, sum);
        sum += node.key;
        node.key = sum;
        traver(node.left, sum);
     }

     /**
      * 二叉查找树的最近公共祖先
      * @param root 树的根节点
      * @param p    结点1
      * @param q    结点2
      * @return
      */
     public BSTNode<T> lowestCommonAncestor(BSTNode<T> root, BSTNode<T> p, BSTNode<T> q) {
        if(root.key.compareTo(p.key) > 0 && root.key.compareTo(q.key) > 0) {
            return lowestCommonAncestor(root.left, p, q);
        }
        if(root.key.compareTo(p.key) < 0 && root.key.compareTo(q.key) < 0) {
            return lowestCommonAncestor(root.right, p, q);
        }

        return root;
     }

     /**
      * 二叉树的最近公共祖先
      * @param root
      * @param p
      * @param q
      * @return
      */
     public BSTNode<T> lowestCommonAncestor2(BSTNode<T> root, BSTNode<T> p, BSTNode<T> q) {
        if(root == null || root.key == p.key || root.key == q.key) {
            return root;
        }
        BSTNode<T> left = lowestCommonAncestor2(root.left, p, q);
        BSTNode<T> right = lowestCommonAncestor2(root.right, p, q);
        if(left != null && right != null) {
            return root;
        }
        return left != null ? left : right;
     }
  }
