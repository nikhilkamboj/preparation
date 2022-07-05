package datastructures.basic.stack;

import datastructures.basic.Node;

public class LinkedStackImpl<T> implements Stack<T>{

    Node<T> head = null;

    @Override
    public T pop() {
        if (head == null) {
            System.out.println("Empty Stack, no pop allowed.");
        }
        Node<T> temp = head;
        head = temp.getNext();
        return temp.getValue();
    }

    @Override
    public void push(T t) {
        Node<T> newNode = new Node<>(t);
        Node<T> temp = head;
        head = newNode;
        newNode.setNext(temp);
    }

    @Override
    public T top() {
        if (head == null) {
            return null;
        }
        return head.getValue();
    }

    @Override
    public boolean isEmpty() {
        return head == null;
    }
}
