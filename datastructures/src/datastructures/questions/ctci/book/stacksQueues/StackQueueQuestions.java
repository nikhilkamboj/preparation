package datastructures.questions.ctci.book.stacksQueues;


import java.util.Stack;

public class StackQueueQuestions {


    public void sortStack(Stack<Integer> stack) {
        Stack<Integer> tempStack = new Stack<>();

        while (!stack.isEmpty()) {
            int top = stack.pop();

            while (!tempStack.isEmpty() || tempStack.peek() > top) {
                stack.push(tempStack.pop());
            }
            tempStack.push(top);
        }
    }
}
