package datastructures.basic.linkedlist;

import datastructures.basic.DoubleNode;

public class DoublyLinkedList<T> {
    private DoubleNode<T> head;

    public DoublyLinkedList() {
        head = null;
    }

    public DoublyLinkedList(DoubleNode<T> head) {
        this.head = head;
    }

    // insertAtHead()
    public T insertAtHead(T value) {
        DoubleNode<T> newNode = new DoubleNode<>(value, head, null);
        DoubleNode<T> currentHead = head;
        currentHead.setPrev(newNode);
        head = newNode;
        return value;
    }


    // insertAtTail()
    public T insertAtTail(T value) {
        DoubleNode<T> newNode = new DoubleNode<>(value, null, null);
        DoubleNode<T> temp = head;

        if (head == null) {
            head = newNode;
            return value;
        }

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        newNode.setPrev(temp);
        temp.setNext(newNode);
        return value;
    }

    // print()
    private void printList() {
        DoubleNode<T> temp = head;

        while (temp != null) {
            System.out.print(temp.getValue());
            if (temp.getNext() != null) {
                System.out.print("-->");
            }
            temp = temp.getNext();
        }
    }

    // ReversePrint()
    private void reversePrint() {
        DoubleNode<T> temp = head;

        while (temp.getNext() != null) {
            temp = temp.getNext();
        }

        while (temp.getPrev() != null) {
            System.out.print(temp.getValue());
            if (temp.getPrev() != null) {
                System.out.print("-->");
            }
        }
    }

}
