package datastructures.basic.linkedlist;

public class LinkedList {
    private Node head;

    public LinkedList(Node head) {
        this.head = head;
    }

    public Node getHead() {
        return head;
    }

    public void setHead(Node head) {
        this.head = head;
    }

    public void printLinkedList() {
        Node currentNode = head;
        while (currentNode != null) {
            System.out.println("The current value is : " + currentNode.getValue());
            currentNode = currentNode.getNext();
        }

        System.out.println("Completed printing linked list");
    }
}
