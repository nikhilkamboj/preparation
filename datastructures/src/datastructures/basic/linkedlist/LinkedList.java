package datastructures.basic.linkedlist;

import datastructures.basic.List;
import datastructures.basic.Node;

public class LinkedList<T> implements List<T> {
    private Node<T> head;

    /**
     * creates an empty linked list
     */
    public LinkedList() {
        head = null;
    }

    /**
     * @param head, linked list with existing head
     */
    public LinkedList(Node<T> head) {
        this.head = head;
    }

    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public void printLinkedList() {
        Node<T> currentNode = head;
        while (currentNode != null) {
            System.out.print(currentNode.getValue());
            if (currentNode.getNext() != null) {
                System.out.print("-->");
            }
            currentNode = currentNode.getNext();
        }
        System.out.println("");
    }


    @Override
    public T retrieveLastElement() {
        Node<T> temp = head;
        while (temp.getNext() != null) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public T insertElement(T t) {
        Node<T> newNode = new Node<>(t);
        if (head == null) {
            head = newNode;
        } else {
            Node<T> temp = head;
            while (temp.getNext() != null) {
                temp = temp.getNext();
            }
            temp.setNext(newNode);
        }
        return t;
    }

    @Override
    public T removeElement(int elementPosition) {
        Node<T> temp = head;
        int i = 1;
        if (elementPosition == 1) {
            head = head.getNext();
            return temp.getValue();
        }
        while (i < elementPosition - 1) {
            temp = temp.getNext();
            i++;
        }
        T val = temp.getNext().getValue();
        temp.setNext(temp.getNext().getNext());
        return val;
    }

    @Override
    public T retrieveElement(int elementPosition) {
        Node<T> temp = head;
        int i = 1;
        while (i <= elementPosition) {
            temp = temp.getNext();
            i++;
        }
        return temp.getValue();
    }

    @Override
    public T findElement(T t) {
        Node<T> temp = head;
        while (temp.getValue() != t) {
            temp = temp.getNext();
        }
        return temp.getValue();
    }

    @Override
    public T insertElementBeginning(T t) {
        Node<T> newNode = new Node<>(t);
        newNode.setNext(head);
        head = newNode;
        return t;
    }

    @Override
    public T insertElementNthPosition(T t, int elementPosition) {
        Node<T> newNode = new Node<T>(t);
        Node<T> temp = head;
        int i = 1;

        if (elementPosition == 1) {
            newNode.setNext(head);
            head = newNode;
            return t;
        }
        while (i < elementPosition - 1) {
            temp = temp.getNext();
            i++;
        }
        newNode.setNext(temp.getNext());
        temp.setNext(newNode);
        return t;
    }

    public void reverseList() {
        Node<T> current = head;
        Node<T> prev = null;
        Node<T> next;

        while (current != null) {
            next = current.getNext();
            current.setNext(prev);
            prev = current;
            current = next;
        }

        head = prev;
    }

    public void printReverse(Node<T> node) {
        if (node.getNext() != null) {
            printReverse(node.getNext());
        }
        System.out.print(node.getValue() + " ");
    }

    public void printRecursively(Node<T> head) {
        if (head == null) {
            return;
        }

        System.out.print(head.getValue() + " ");
        printRecursively(head.getNext());
    }

    public void reverseRecursively(Node<T> node) {
       if (node.getNext() == null) {
           head = node;
           return;
       }
       Node<T> nextNode = node.getNext();
       reverseRecursively(nextNode);
       nextNode.setNext(node);
       node.setNext(null);
    }
}
