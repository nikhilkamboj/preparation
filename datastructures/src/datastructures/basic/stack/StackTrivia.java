package datastructures.basic.stack;

import java.util.Stack;

public class StackTrivia {

    public int evaluatePrefix(String[] expression) {
        Stack<String> stack = new Stack();
        for (int i = expression.length -1; i >= 0; i--) {
            if (!isOperator(expression[i])) {
                stack.push(expression[i]);
            } else {
                int integerFirst = Integer.parseInt(stack.pop());
                int integerSecond = Integer.parseInt(stack.pop());
                calculateOperatorOperation(integerFirst, integerSecond, expression[i], stack);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    public int evaluatePostfix(String[] expression) {
        Stack<String> stack = new Stack();
        for (String c: expression) {
            if (!isOperator(c)) {
                stack.push(c);
            } else {
                int integerSecond = Integer.parseInt(stack.pop());
                int integerFirst = Integer.parseInt(stack.pop());
                calculateOperatorOperation(integerFirst, integerSecond, c, stack);
            }
        }
        return Integer.parseInt(stack.pop());
    }

    private boolean isOperator(String c) {
        return c.equals("+") || c.equals("-") || c.equals("*") || c.equals("/");
    }

    private void calculateOperatorOperation(int first, int second, String operator, Stack<String> stack) {
        switch (operator) {
            case "+":
                stack.push(String.valueOf(first + second));
                break;
            case "-":
                stack.push(String.valueOf(first - second));
                break;
            case "*":
                stack.push(String.valueOf(first * second));
                break;
            case "/":
                stack.push(String.valueOf(first / second));
                break;
        }
    }

    public String convertInfixToPostfix(String[] expression) {
        Stack<String> stack = new Stack();
        StringBuilder postfixExpression = new StringBuilder();
        for (String c: expression) {
            if (!isOperator(c)) {
                postfixExpression.append(c);
            } else {
                // TODO: check for parenthesis problem also
                if (!stack.isEmpty() && stackPeekHasHigherPrecedence(stack.peek(), c)) {
                    while (!stack.isEmpty()) {
                        postfixExpression.append(stack.peek());
                        stack.pop();
                    }
                }
                stack.push(c);
            }
        }
        while (!stack.isEmpty()) {
            postfixExpression.append(stack.peek());
            stack.pop();
        }
        return postfixExpression.toString();
    }

    private boolean stackPeekHasHigherPrecedence(String stackPeek, String currentOperator) {
        return stackPeek.equals("*") || stackPeek.equals("/") && currentOperator.equals("+") || currentOperator.equals("-");
    }

    public boolean checkBalancedParentheses(String expresion) {
        char[] expressionArray = expresion.toCharArray();
        Stack<Character> stack = new Stack<>();
        for (char c : expressionArray) {
            if (openingBracket(c)) {
                stack.push(c);
            } else {
                if (stack.isEmpty() || !correspondingClosingBracket(stack.peek(), c)) {
                    return false;
                }
                stack.pop();
            }
        }
        return true;
    }

    private boolean openingBracket(char val) {
        return val == '(' || val == '{' || val == '[';
    }

    private boolean correspondingClosingBracket(char openingBracket, char closingBracket) {
        if (openingBracket == '[' && closingBracket == ']') {
            return true;
        } else if (openingBracket == '{' && closingBracket == '}') {
            return true;
        } else return openingBracket == '(' && closingBracket == ')';
    }
}
