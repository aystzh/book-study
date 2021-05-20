package leecode.tree;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 给定一个 N 叉树，返回其节点值的前序遍历。  即：根-->childrenList
 * https://leetcode-cn.com/problems/n-ary-tree-preorder-traversal/
 * Created by zhanghuan on 2020/6/29.
 */
public class NAryTreePreorderTraversal {
    public List<Integer> preorder(Node root) {
        List<Integer> res = Lists.newArrayList();
        if (root == null) {
            return res;
        }
        helper(root, res);
        return res;
    }

    private void helper(Node root, List<Integer> res) {
        if (root == null) {
            return;
        }
        res.add(root.val);
        for (Node node : root.children) {
            helper(node, res);
        }
    }
}

class Node {
    public List<Node> children;

    public int val;

    public Node(List<Node> children, int val) {
        this.children = children;
        this.val = val;
    }

    public Node(int val) {
        this.val = val;
    }

    public Node() {
    }
}