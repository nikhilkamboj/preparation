package datastructures.basic.stack;

public class ArrayStackImpl<T> implements Stack<T> {

    int MAX = Integer.MAX_VALUE;
    T[] stack = (T[]) new Object[MAX];
    int top = -1;

    @Override
    public T pop() {
        if (top == -1) {
            System.out.println("Stack is empty, can't be popped!!!");
            return null;
        }
        return stack[top--];
    }

    @Override
    public void push(T t) {
        if (top == MAX) {
            System.out.println("Stack is full!!");
            return;
        }
        stack[++top] = t;
    }

    @Override
    public T top() {
        return stack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }
}
