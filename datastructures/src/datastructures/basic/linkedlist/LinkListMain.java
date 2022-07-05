package datastructures.basic.linkedlist;


public class LinkListMain {

    public static void main(String[] args) {
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.insertElement(1);
        linkedList.insertElement(2);
        linkedList.insertElement(3);
        linkedList.reverseUsingStack();
        linkedList.printLinkedList();
    }
}
