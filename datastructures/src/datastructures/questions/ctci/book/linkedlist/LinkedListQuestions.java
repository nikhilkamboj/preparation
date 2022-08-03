package datastructures.questions.ctci.book.linkedlist;

import datastructures.basic.Node;
import datastructures.basic.linkedlist.LinkedList;

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
                // update right larger LL
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


    public LinkedList<Integer> sumLinkedList(Node<Integer> head1, Node<Integer> head2) {
        // TODO: write a better solution with recursive approach.
        return null;
    }

    public Node<Integer> getIntersectionPoint(Node<Integer> head1, Node<Integer> head2) {
        Node<Integer> current1 = head1;
        Node<Integer> current2 = head2;
        Node<Integer> largerLengthHead = null;
        Node<Integer> smallerLengthHead = null;
        if (current1 == current2) {
            return current1;
        }
        int firstListLength = 1;
        int secondListLength = 1;
        while (current1.getNext() != null) {
            current1 = current1.getNext();
            firstListLength++;
        }

        while (current2.getNext() != null) {
            current2 = current2.getNext();
            secondListLength++;
        }

        if (current1 == current2) {
            return null;
        }
        int lengthDifference;
        if (firstListLength >= secondListLength) {
            lengthDifference = firstListLength - secondListLength;
            largerLengthHead = head1;
            smallerLengthHead = head2;
        } else {
            lengthDifference = secondListLength - firstListLength;
            largerLengthHead = head2;
            smallerLengthHead = head1;
        }

        for (int i = 0; i < lengthDifference; i++) {
            largerLengthHead = largerLengthHead.getNext();
        }

        while (smallerLengthHead != largerLengthHead) {
            smallerLengthHead = smallerLengthHead.getNext();
            largerLengthHead = largerLengthHead.getNext();
        }
        return smallerLengthHead;
    }

    /***
     * To understand the logic follow example
     * 1. Suppose X is standing 10 unit distance behind Y, X takes twice steps at once then Y
     * 2. So both meet at 20 Unit distance, both taking steps equal to distance between them I.E. 10.
     */
    public Node<Integer> getPointOfIntersection(Node<Integer> head) {
        // find if there is intersection at all
        Node<Integer> pointOfCollisionOfPointers = isThereIntersection(head);

        if (pointOfCollisionOfPointers == null) {
            // there is no intersection in the LL
            return null;
        }

        Node<Integer> current = head;

        // point of collision is K steps away from point of intersection
        // so point of intersection must be reached with simple iteration
        while (current != pointOfCollisionOfPointers) {
            current = current.getNext();
            pointOfCollisionOfPointers = pointOfCollisionOfPointers.getNext();
        }
        return current;
    }

    private Node<Integer> isThereIntersection(Node<Integer> head) {
        Node<Integer> slowNode = head;
        Node<Integer> fastNode = head;

        while (fastNode.getNext() != null || fastNode.getNext().getNext() != null) {
            // there is intersection in the LL
            if (fastNode == slowNode) {
                return slowNode;
            }
            fastNode = fastNode.getNext().getNext();
            slowNode = slowNode.getNext();
        }
        return null;
    }

}
