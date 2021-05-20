package leecode.tree.common;

import java.util.Stack;

/**
 * Created by zhanghuan on 2020/6/12.
 */
public class TreeOrder {

    public static void main(String[] args) {

        TreeNode[] node = new TreeNode[10];
        for (int i = 0; i < 10; i++) {
            node[i] = new TreeNode(i);
        }

        for (int i = 0; i < 10; i++) {
            if (i * 2 + 1 < 10) {
                node[i].setLeft(node[i * 2 + 1]);
            }
            if (i * 2 + 2 < 10) {
                node[i].setRight(node[i * 2 + 2]);
            }
        }
        //前序遍历 从根节点开始
        System.out.print("前序递归实现\n");
        preOrderRe(node[0]);
        System.out.println("\n前序非递归实现");
        preOrderStack(node[0]);
        System.out.println("\n中序递归实现");
        inOrder(node[0]);
        System.out.println("\n中序非递归实现");
        inOrderStack(node[0]);
        System.out.println("\n后序递归实现");
        postOrder(node[0]);
        System.out.println("\n后序非递归实现");
        postOrderStack(node[0]);
    }

    public static void preOrderRe(TreeNode biTree) {//递归实现
        System.out.print(biTree.getValue());
        TreeNode leftTree = biTree.getLeft();
        if (leftTree != null) {
            preOrderRe(leftTree);
        }
        TreeNode rightTree = biTree.getRight();
        if (rightTree != null) {
            preOrderRe(rightTree);
        }
    }

    /**
     * 递归实现 二叉树中序遍历
     * @param root 根节点
     */
    public static void inOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        inOrder(root.getLeft());
        System.out.print(root.getValue());
        inOrder(root.getRight());
    }

    /**
     * 递归实现 二叉树后序遍历
     * @param root 根节点
     */
    public static void postOrder(TreeNode root) {
        if (root == null) {
            return;
        }
        postOrder(root.getLeft());
        postOrder(root.getRight());
        System.out.print(root.getValue());
    }

    /**
     * https://leetcode-cn.com/problems/binary-tree-preorder-traversal/solution/leetcodesuan-fa-xiu-lian-dong-hua-yan-shi-xbian-2/
     *根-->左-->右
     * @param treeNode 二叉搜索树前序遍历 非递归实现
     */
    public static void preOrderStack(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        Stack<TreeNode> stack = new Stack<TreeNode>();
        //先将跟节点放入栈中
        stack.add(treeNode);
        //循环遍历栈 弹出元素
        while (!stack.isEmpty()) {
            //第一次 根节点弹出
            TreeNode root = stack.pop();
            System.out.print(root.getValue());
            if (root.getRight() != null) {
                stack.push(root.getRight());
            }
            if (root.getLeft() != null) {
                stack.push(root.getLeft());
            }
        }
    }

    /**
     * 二叉树中序遍历非递归实现
     *
     * @param root 根节点
     */
    public static void inOrderStack(TreeNode root) {
        if (root == null) {
            return;
        }
        TreeNode tempNode = root;
        Stack<TreeNode> stack = new Stack<TreeNode>();
        while (!stack.isEmpty() || tempNode != null) {
            while (tempNode != null) {
                stack.push(tempNode);
                tempNode = tempNode.getLeft();
            }
            TreeNode node = stack.pop();
            System.out.print(node.getValue());
            if (node.getRight() != null) {
                tempNode = node.getRight();
            }
        }
    }

    /**
     * 二叉树后序遍历非递归实现
     * 右-->左-->根
     * @param root 根节点
     */
    public static void postOrderStack(TreeNode root) {
        if (root == null) {
            return;
        }
        Stack<TreeNode> stack1 = new Stack<>();
        Stack<TreeNode> stack2 = new Stack<>();
        stack1.push(root);
        while (!stack1.isEmpty()) {
            TreeNode node = stack1.pop();
            stack2.push(node);
            if (node.getLeft() != null) {
                stack1.push(node.getLeft());
            }
            if (node.getRight() != null) {
                stack1.push(node.getRight());
            }
        }
        while (!stack2.isEmpty()) {
            System.out.print(stack2.pop().getValue());
        }
    }


}

