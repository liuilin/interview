package com.liuilin.JUC;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author liuqiang
 * @since 2021-08-13
 */
public class BinaryTree {
    public static void main(String[] args) {
        BinaryTreeNode node10 = new BinaryTreeNode(10, null, null);
        BinaryTreeNode node8 = new BinaryTreeNode(8, null, null);
        BinaryTreeNode node9 = new BinaryTreeNode(9, null, node10);
        BinaryTreeNode node7 = new BinaryTreeNode(7, null, null);
        BinaryTreeNode node6 = new BinaryTreeNode(6, null, null);
        BinaryTreeNode node5 = new BinaryTreeNode(5, node8, node9);
        BinaryTreeNode node4 = new BinaryTreeNode(4, null, null);
        BinaryTreeNode node3 = new BinaryTreeNode(3, node6, node7);
        BinaryTreeNode node2 = new BinaryTreeNode(2, node4, node5);
        BinaryTreeNode node1 = new BinaryTreeNode(1, node2, node3);

        BinaryTree tree = new BinaryTree();
        //采用递归的方式进行遍历
        System.out.println("-----前序遍历------");
        tree.preOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.preOrderNonRecursive(node1);
        System.out.println();


        //采用递归的方式进行遍历
        System.out.println("-----中序遍历------");
        tree.inOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.inOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----后序遍历------");
        tree.postOrder(node1);
        System.out.println();
        //采用非递归的方式遍历
        tree.postOrderNonRecursive(node1);
        System.out.println();

        //采用递归的方式进行遍历
        System.out.println("-----层序遍历------");
        tree.levelOrder(node1);
        System.out.println();
    }

    //前序遍历递归的方式
    public void preOrder(BinaryTreeNode root) {
        if (null != root) {
            System.out.print(root.getData() + "\t");
            preOrder(root.getLeft());
            preOrder(root.getRight());
        }
    }

    //前序遍历非递归的方式
    public void preOrderNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            while (root != null) {
                System.out.print(root.getData() + "\t");
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) break;
            root = stack.pop();
            root = root.getRight();
        }
    }

    //中序遍历采用递归的方式
    public void inOrder(BinaryTreeNode root) {
        if (null != root) {
            inOrder(root.getLeft());
            System.out.print(root.getData() + "\t");
            inOrder(root.getRight());
        }
    }

    //中序遍历采用非递归的方式
    public void inOrderNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            while (root != null) {
                stack.push(root);
                root = root.getLeft();
            }
            if (stack.isEmpty()) break;
            root = stack.pop();
            System.out.print(root.getData() + "\t");
            root = root.getRight();
        }
    }

    //后序遍历采用递归的方式
    public void postOrder(BinaryTreeNode root) {
        if (root != null) {
            postOrder(root.getLeft());
            postOrder(root.getRight());
            System.out.print(root.getData() + "\t");
        }
    }

    //后序遍历采用非递归的方式
    public void postOrderNonRecursive(BinaryTreeNode root) {
        Stack<BinaryTreeNode> stack = new Stack<BinaryTreeNode>();
        while (true) {
            if (root != null) {
                stack.push(root);
                root = root.getLeft();
            } else {
                if (stack.isEmpty()) return;

                if (null == stack.lastElement().getRight()) {
                    root = stack.pop();
                    System.out.print(root.getData() + "\t");
                    while (root == stack.lastElement().getRight()) {
                        System.out.print(stack.lastElement().getData() + "\t");
                        root = stack.pop();
                        if (stack.isEmpty()) {
                            break;
                        }
                    }
                }

                if (!stack.isEmpty())
                    root = stack.lastElement().getRight();
                else
                    root = null;
            }
        }
    }

    //层序遍历
    public void levelOrder(BinaryTreeNode root) {
        BinaryTreeNode temp;
        Queue<BinaryTreeNode> queue = new LinkedList<BinaryTreeNode>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            temp = queue.poll();
            System.out.print(temp.getData() + "\t");
            if (null != temp.getLeft())
                queue.offer(temp.getLeft());
            if (null != temp.getRight()) {
                queue.offer(temp.getRight());
            }
        }
    }
}

class BinaryTreeNode {
    private int data;
    private BinaryTreeNode left;
    private BinaryTreeNode right;

    public BinaryTreeNode() {
    }

    public BinaryTreeNode(int data, BinaryTreeNode left, BinaryTreeNode right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public BinaryTreeNode getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode left) {
        this.left = left;
    }

    public BinaryTreeNode getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode right) {
        this.right = right;
    }
}