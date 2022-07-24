package datastructures.questions.ctci.videos;

import datastructures.basic.Node;

import java.util.HashSet;
import java.util.Set;

public class LinkListCycle {

    public boolean checkIfCycle(Node<Integer> head) {
        if (head == null) {
            return false;
        }
        Set<Node<Integer>> nodeSet = new HashSet<>();
        boolean isCyclic = false;
        while (head != null) {
            if (nodeSet.contains(head)) {
                isCyclic = true;
                break;
            }
            nodeSet.add(head);
            head = head.getNext();
        }
        return isCyclic;
    }

    public boolean checkIfCyclicWithNoExtraMemory(Node<Integer> head) {
        if (head == null) {
            return false;
        }
        Node<Integer> headNext = head.getNext();

        while (head.getNext() != null && headNext.getNext().getNext() != null) {
            if (headNext == head) {
                return true;
            }
            head = head.getNext();
            headNext = headNext.getNext().getNext();
        }
        return false;
    }
}
