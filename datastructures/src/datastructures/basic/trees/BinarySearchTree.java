package datastructures.basic.trees;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.ConcurrentLinkedDeque;

public class BinarySearchTree {

    TreeNode<Integer> root;

    public TreeNode<Integer> insertNode(TreeNode<Integer> root, Integer value) {
        if (root == null) {
            root = new TreeNode<>(value, null, null);
        } else if (value <= root.getValue()) {
            root.setLeft(insertNode(root.getLeft(), value));
        } else {
            root.setRight(insertNode(root.getRight(), value));
        }
        return root;
    }

    public Integer FindMax(TreeNode<Integer> root) {
        while (root.getRight() != null) {
            root = root.getRight();
        }
        return root.getValue();
    }

    public Integer FindMin(TreeNode<Integer> root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root.getValue();
    }

    public int findHeight(TreeNode<Integer> root) {
        if (root == null) {
            return -1;
        }
        int leftHeight = findHeight(root.getLeft());
        int rightHeight = findHeight(root.getRight());

        return Math.max(leftHeight, rightHeight) + 1;
    }

    public void breadthFirstTraversal(TreeNode<Integer> root) {
        Queue<TreeNode<Integer>> queue = new ConcurrentLinkedDeque<>();
        if (root == null) {
            return;
        }
        queue.add(root);
        while (!queue.isEmpty()) {
            TreeNode<Integer> node = queue.poll();
            System.out.println(node.getValue());
            queue.add(node.getLeft());
            queue.add(node.getRight());
        }
    }

    public void preOrderTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        System.out.println(root.getValue());
        preOrderTraversal(root.getLeft());
        preOrderTraversal(root.getRight());
    }

    public void inOrderTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        inOrderTraversal(root.getLeft());
        System.out.println(root.getValue());
        inOrderTraversal(root.getRight());
    }

    public void postOrderTraversal(TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        postOrderTraversal(root.getLeft());
        postOrderTraversal(root.getRight());
        System.out.println(root.getValue());
    }

    public boolean checkIfBinaryTreeIsBst(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        return isSubTreeSmaller(root.getLeft(), root.getValue())
                && isSubTreeLarger(root.getRight(), root.getValue())
                && checkIfBinaryTreeIsBst(root.getLeft())
                && checkIfBinaryTreeIsBst(root.getRight());
    }

    // checks for a root, if all values of subtree are smaller or equal then param value
    private boolean isSubTreeSmaller(TreeNode<Integer> root, int value) {
        if (root == null) {
            return true;
        }
        return root.getValue() <= value
                && isSubTreeSmaller(root.getLeft(), value)
                && isSubTreeSmaller(root.getRight(), value);
    }

    // checks for a root, if all values of subtree are larger then param value
    private boolean isSubTreeLarger(TreeNode<Integer> root, int value) {
        if (root == null) {
            return true;
        }
        return root.getValue() > value
                && isSubTreeLarger(root.getLeft(), value)
                && isSubTreeLarger(root.getRight(), value);
    }

    // using Range
    public boolean checkIfBst(TreeNode<Integer> root) {
        if (root == null) {
            return true;
        }
        int maxRange = Integer.MAX_VALUE;
        int minRange = Integer.MIN_VALUE;
        return checkIfBst(root, maxRange, minRange);
    }

    private boolean checkIfBst(TreeNode<Integer> root, int maxValue, int minValue) {
        if (root == null) {
            return true;
        }
        if (root.getValue() >= minValue && root.getValue() < maxValue) {
            return checkIfBst(root.getLeft(), root.getValue(), minValue)
                    && checkIfBst(root.getRight(), maxValue, root.getValue());
        }
        return false;
    }

    // using DFS, InOrder Traversal
    public boolean checkIfBstUsingInorderTraversal() {
        Stack<Integer> stack = new Stack<>();
        checkIfBst(stack, root);
        Integer top = stack.pop();
        while (!stack.isEmpty()) {
            if (stack.peek() > top) {
                break;
            }
            top = stack.pop();
        }
        return stack.isEmpty();
    }

    private void checkIfBst(Stack<Integer> stack, TreeNode<Integer> root) {
        if (root == null) {
            return;
        }
        checkIfBst(stack, root.getLeft());
        stack.push(root.getValue());
        checkIfBst(stack, root.getRight());
    }

    public TreeNode<Integer> delete(TreeNode<Integer> root, int value) {
        if (root == null) {
            return null;
        }
        if (root.getValue() > value) {
            root.left = delete(root.getLeft(), value);
        } else if (root.getValue() < value) {
            root.right = delete(root.getRight(), value);
        } else {
            if (root.left == null && root.right == null) {
                return null;
            } else if (root.left == null && root.right != null) {
                TreeNode<Integer> tempNode = root.getRight();
                root.right = null;
                return tempNode;
            } else if (root.left != null && root.right == null) {
                TreeNode<Integer> tempNode = root.getLeft();
                root.left = null;
                return tempNode;
            } else if (root.left != null && root.right != null) {
                TreeNode<Integer> largestLeftSubTreeNode = findLargestLeftTree(root.getLeft());
                root.setValue(largestLeftSubTreeNode.getValue());
                root.left = delete(root.getLeft(), largestLeftSubTreeNode.getValue());
                return root;
            }
        }
        return root;
    }

    private TreeNode<Integer> findLargestLeftTree(TreeNode<Integer> root) {
        if (root.getRight() == null) {
            return root;
        }
        return findLargestLeftTree(root.getRight());
    }

    public TreeNode<Integer> findInOrderSuccessor(TreeNode<Integer> root, int val) {
        if (root == null) {
            return null;
        }
        TreeNode<Integer> searchNode = findValue(root, val);

        if (searchNode.getRight() != null) {
            return findMin(root);
        }
        TreeNode<Integer> ancestor = root;
        TreeNode<Integer> successor = null;
        while (ancestor.getValue() != val) {
            if (ancestor.getValue() > val) {
                successor = ancestor;
                ancestor = ancestor.getLeft();
            } else {
                ancestor = ancestor.getRight();
            }
        }
        return successor;
    }

    private TreeNode<Integer> findValue(TreeNode<Integer> root, int val) {
        if (root.getValue() == val) {
            return root;
        }
        if (root.getValue() >= val) {
            return findValue(root.getLeft(), val);
        } else {
            return findValue(root.getRight(), val);
        }
    }

    private TreeNode<Integer> findMin(TreeNode<Integer> root) {
        if (root.getLeft() == null) {
            return root;
        }
        return findMin(root.getLeft());
    }

}
