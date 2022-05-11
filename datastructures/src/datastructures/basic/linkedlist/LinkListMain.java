package datastructures.basic.linkedlist;

public class LinkListMain {

    public static void main(String[] args) {
        Node node1 = new Node();
        node1.setNext(null);
        node1.setValue("1");

        LinkedList linkedList = new LinkedList(node1);
        linkedList.printLinkedList();
    }
}
