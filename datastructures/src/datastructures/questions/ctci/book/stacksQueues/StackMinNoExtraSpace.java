package datastructures.questions.ctci.book.stacksQueues;

import java.util.Stack;

public class StackMinNoExtraSpace extends Stack<StackMinNoExtraSpace.MinStackNode> {

    @Override
    public MinStackNode push(MinStackNode item) {
        if (item.getValue() <= min()) {
            item.setMinTillNow(item.value);
        } else {
            item.setMinTillNow(min());
        }
        return super.push(item);
    }

    @Override
    public synchronized MinStackNode pop() {
        return super.pop();
    }

    public int min() {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        }
        return this.peek().getMinTillNow();
    }

    public class MinStackNode {
        int value;
        int minTillNow;

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }

        public int getMinTillNow() {
            return minTillNow;
        }

        public void setMinTillNow(int minTillNow) {
            this.minTillNow = minTillNow;
        }
    }
}
