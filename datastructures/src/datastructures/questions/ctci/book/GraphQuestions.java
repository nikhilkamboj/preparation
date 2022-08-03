package datastructures.questions.ctci.book;

import datastructures.basic.trees.TreeNode;
import datastructures.questions.ctci.Graph;
import datastructures.questions.ctci.Vertex;

import java.util.*;

public class GraphQuestions {

    // BFS
    public boolean isThereRoute(Graph graph, Vertex first, Vertex second) {
        Set<Vertex> visitedSet = new HashSet<>();
        LinkedList<Vertex> queue = new LinkedList<>();

        queue.add(first);

        while (!queue.isEmpty()) {
            Vertex currentVertex = queue.poll();
            List<Vertex> connectedVertexes = currentVertex.getConnections();
            if (!visitedSet.contains(currentVertex)) {
                visitedSet.add(currentVertex);
                for (Vertex vertex : connectedVertexes) {
                    if (vertex == second) {
                        return true;
                    }
                    queue.add(vertex);
                }
            }
        }
        return false;
    }

    // initial version, cluttered, creating new array everytime, NOT AT ALL OPTIMUM
    public TreeNode<Integer> createTreeFrom(int[] sortedArray) {
        if (sortedArray == null) {
            return null;
        }
        if (sortedArray.length == 1) {
            TreeNode<Integer> treeNode = new TreeNode<>();
            treeNode.setValue(sortedArray[0]);
            return treeNode;
        }
        int mid = (sortedArray.length - 1)/2;
        TreeNode<Integer> root = new TreeNode<>();
        root.setValue(sortedArray[mid]);
        int[] leftArray;
        if (mid - 1 < 0) {
            leftArray = null;
        } else {
            leftArray = Arrays.copyOfRange(sortedArray, 0, mid - 1);
        }
        int[] rightArray;
        if (mid + 1 >= sortedArray.length) {
            rightArray = null;
        } else {
            rightArray = Arrays.copyOfRange(sortedArray, mid + 1, sortedArray.length - 1);
        }
        TreeNode<Integer> leftSubtreeRoot = createTreeFrom(leftArray);
        TreeNode<Integer> rightSubtreeRoot = createTreeFrom(rightArray);
        root.setLeft(leftSubtreeRoot);
        root.setRight(rightSubtreeRoot);
        return root;
    }

    // simple and clean approach, index based approach
    public TreeNode<Integer> createMinimalTreeFrom(int[] sortedArray) {
        return createMinimalTreeFrom(sortedArray, 0, sortedArray.length-1);
    }

    private TreeNode<Integer> createMinimalTreeFrom(int[] sortedArray, int start, int end) {
        if (start > end) {
            return null;
        }
        int mid = (start + end)/2;
        TreeNode<Integer> root = new TreeNode(sortedArray[mid]);
        root.setLeft(createMinimalTreeFrom(sortedArray, start, mid-1));
        root.setRight(createMinimalTreeFrom(sortedArray, mid + 1, end));
        return root;
    }

    /**
     * if we know the number of nodes in previous level, we can find in the current level
     *
     *  THIS IS BFS IMPLEMENTATION
     */
    public List<LinkedList<TreeNode<Integer>>> getLevelLinkedList(TreeNode<Integer> root) {
        List<LinkedList<TreeNode<Integer>>> linkedTreeNodes = new ArrayList<>();
        LinkedList<TreeNode<Integer>> currentLinkedList = new LinkedList<>();
        if (root != null) {
            currentLinkedList.add(root);
        }
        while (currentLinkedList.size() > 0) {
            linkedTreeNodes.add(currentLinkedList);
            LinkedList<TreeNode<Integer>> previousLevelList = currentLinkedList;
            currentLinkedList = new LinkedList<>();
            for (TreeNode<Integer> cTreeNode: previousLevelList) {
                if (cTreeNode.getLeft() != null) {
                    currentLinkedList.add(cTreeNode.getLeft());
                }
                if (cTreeNode.getRight() != null) {
                    currentLinkedList.add(cTreeNode.getRight());
                }
            }
        }
        return linkedTreeNodes;
    }

    /**
     * THIS IS DFS IMPLEMENTATION , IN-ORDER
     */

    public List<LinkedList<TreeNode<Integer>>> getLevelLinkedListDFS(TreeNode<Integer> root) {
        List<LinkedList<TreeNode<Integer>>> arrayList = new ArrayList<>();
        return getLevelLinkedListDFS(root, 0, arrayList);
    }

    public List<LinkedList<TreeNode<Integer>>> getLevelLinkedListDFS(TreeNode<Integer> root,
                                                                     int level,
                                                                     List<LinkedList<TreeNode<Integer>>> arrayList) {
        if (root == null) {
            return null;
        }

        /***
         * LinkedList currentLevelLinkedList = null;
         * if (arrayList.size == level) {
         *    currentLevelLinkedList = new LinkedList();
         *    arrayList.add(currentLevelLinkedList);
         *   } else {
         *      currentLevelLinkedList = arrayList.get(level);
         *   }
         */
        if (arrayList.get(level) == null) {
            arrayList.add(level, new LinkedList<>());
        }
        LinkedList<TreeNode<Integer>> levelLinkedList = arrayList.get(level);
        getLevelLinkedListDFS(root.getLeft(), level + 1, arrayList);
        levelLinkedList.add(root);
        getLevelLinkedListDFS(root.getRight(), level + 1, arrayList);
        return arrayList;
    }
}
