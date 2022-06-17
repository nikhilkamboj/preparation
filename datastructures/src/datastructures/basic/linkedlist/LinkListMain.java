package datastructures.basic.linkedlist;


public class LinkListMain {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.insertElement(2);
        linkedList.insertElement(3);
        linkedList.insertElementNthPosition(1, 1);
        linkedList.insertElementNthPosition(4, 4);
        linkedList.reverseRecursively(linkedList.getHead());
        linkedList.printLinkedList();
    }
}
