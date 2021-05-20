package leecode.tree.common;

/**
 * Created by zhanghuan on 2020/6/12.
 */
public class TreeNode {
    private int value;

    private TreeNode left;

    private TreeNode right;

    TreeNode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public TreeNode getLeft() {
        return left;
    }

    public void setLeft(TreeNode left) {
        this.left = left;
    }

    public TreeNode getRight() {
        return right;
    }

    public void setRight(TreeNode right) {
        this.right = right;
    }
}
