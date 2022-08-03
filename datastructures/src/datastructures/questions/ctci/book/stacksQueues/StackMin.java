package datastructures.questions.ctci.book.stacksQueues;

import java.util.Stack;

public class StackMin extends Stack<Integer> {

    Stack<Integer> minimumStack;

    public StackMin() {
        minimumStack = new Stack<>();
    }

    @Override
    public Integer push(Integer item) {
        if (item <= min()) {
            minimumStack.push(item);
        }
        return super.push(item);
    }

    @Override
    public synchronized Integer pop() {
        int poppedValue = super.pop();
        if (poppedValue == min()) {
            minimumStack.pop();
        }
        return poppedValue;
    }

    public int min() {
        if (minimumStack.isEmpty()) {
            return Integer.MAX_VALUE; // Start condition
        }
        return minimumStack.peek();
    }

}
