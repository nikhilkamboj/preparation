package datastructures.questions.ctci.videos;

import java.util.Stack;

public class MyQueue<T> {
    // using 2 stacks problem
    Stack<T> stackNewestOnTop = new Stack<>();
    Stack<T> stackOldestOnTop = new Stack<>();

    // enqueue
    public T enqueue(T t) {
        stackNewestOnTop.push(t);
        return t;
    }

    // dequeue
    public T dequeue() {
        shiftStacks();
        return stackOldestOnTop.pop();
    }

    // peek
    public T peek() {
        shiftStacks();
        return stackOldestOnTop.peek();
    }

    private void shiftStacks() {
        while (!stackNewestOnTop.isEmpty()) {
            stackOldestOnTop.push(stackNewestOnTop.pop());
        }
    }


    public static void main(String[] args) {
        MyQueue<String> stringMyQueue = new MyQueue<>();
        stringMyQueue.enqueue("1");
        stringMyQueue.enqueue("2");
        stringMyQueue.enqueue("3");

        System.out.println(stringMyQueue.dequeue());
        System.out.println(stringMyQueue.peek());

    }
}
