package datastructures.questions.ctci.book.stacksQueues;

import java.util.Stack;

public class QueueWithStacks {
    Stack<Integer> mainStack = new Stack<>();
    Stack<Integer> tempStack = new Stack<>();

    public void add(int item) {
        mainStack.push(item);
    }

    public int shiftStack() {
        if (tempStack.isEmpty()) {
            while (!mainStack.isEmpty()) {
                tempStack.push(mainStack.pop());
            }
        }
        return tempStack.peek();
    }

    public int removeTop() {
        shiftStack();
        return tempStack.pop();
    }

    public int peek() {
        shiftStack();
        return tempStack.peek();
    }

}
