package datastructures.basic.stack;

public class StackMain {
    public static void main(String[] args) {
        StackTrivia stackTrivia = new StackTrivia();
        String expression = "][]{{{}}}";
        String[] postExpresion = {"8", "7", "6", "*", "+", "9", "7", "*", "*"};
        String[] prefixExpression = {"+", "+", "8", "*", "7", "6", "*", "9", "7"};
        String[] infixExpression = {"8", "+", "7", "*", "6", "+", "9", "*", "7"};
        System.out.println(stackTrivia.convertInfixToPostfix(infixExpression));
    }
}
