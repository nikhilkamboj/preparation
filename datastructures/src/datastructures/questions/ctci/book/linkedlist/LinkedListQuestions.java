package datastructures.questions.ctci.book.linkedlist;

import datastructures.basic.Node;

public class LinkedListQuestions {

    /**
     * here we are going for 0(N2) time complexity, by using a set or hasTabele we can go for
     * 0(N) complexity
     */
    public void removeDuplicatesNoExtraSpace(Node<Integer> head) {
        Node<Integer> current = head;
        while (current != null) {
            Node<Integer> next = current.getNext();
            while (next != null) {
                if (next.getValue().equals(current.getValue())) {
                    current.setNext(next.getNext());
                } else {
                    current = next;
                }
                next = next.getNext();
            }
            current = current.getNext();
        }
    }


    public int findKthFromLast(Node<Integer> head, int k) {
        if (head == null) {
            return -1;
        }
        Node<Integer> current = head;
        Node<Integer> kthFromCurrent = current;

        int i = 1;
        while (i <= k && kthFromCurrent != null) {
            kthFromCurrent = kthFromCurrent.getNext();
            i++;
        }
        if (kthFromCurrent == null) {
            return -1;
        }

        while (kthFromCurrent.getNext() != null) {
            kthFromCurrent = kthFromCurrent.getNext();
            current = current.getNext();
        }

        return current.getValue();
    }

    public void deleteMiddleElement(Node<Integer> head) {
        Node<Integer> slowPointer = head;
        Node<Integer> fastPointer = head;
        Node<Integer> prev = null;

        while (fastPointer.getNext() != null && fastPointer.getNext().getNext() != null) {
            fastPointer = fastPointer.getNext().getNext();
            prev = slowPointer;
            slowPointer = slowPointer.getNext();
        }

        prev.setNext(slowPointer.getNext());
    }

    public boolean deleteMiddleNodeWithNoHeadGiven(Node<Integer> middleNode) {
        if (middleNode == null || middleNode.getNext() == null) {
            return false;
        }
        middleNode.setValue(middleNode.getNext().getValue());
        middleNode.setNext(middleNode.getNext().getNext());
        return true;
    }

    /**
     * pivoting LL on pivotVal such that all small are on the left and larger or equal to pivotVal on right
     */
    public Node<Integer> partitionLinkedListOn(Node<Integer> head, int pivotValue) {
        Node<Integer> leftHead = null;
        Node<Integer> rightHead = null;

        Node<Integer> mainLinkedListCurrent = head;
        Node<Integer> leftCurrent = null;
        Node<Integer> rightCurrent = null;

        while (mainLinkedListCurrent != null) {
            if (mainLinkedListCurrent.getValue() < pivotValue) {
                // update left small LL
                if (leftHead == null) {
                    leftHead = mainLinkedListCurrent;
                    leftCurrent = mainLinkedListCurrent;
                }
                leftCurrent.setNext(mainLinkedListCurrent);
                leftCurrent = mainLinkedListCurrent;
            } else {
                // update right larger list
                if (rightHead == null) {
                    rightHead = mainLinkedListCurrent;
                    rightCurrent = mainLinkedListCurrent;
                }
                rightCurrent.setNext(mainLinkedListCurrent);
                rightCurrent = mainLinkedListCurrent;
            }
            mainLinkedListCurrent = mainLinkedListCurrent.getNext();
        }
        if (leftCurrent == null) {
            return rightHead;
        }
        leftCurrent.setNext(rightHead);
        rightCurrent.setNext(null);
        return leftCurrent;
    }


}
