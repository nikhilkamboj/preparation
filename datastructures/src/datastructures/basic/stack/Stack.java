package datastructures.basic.stack;

/**
 * LIFO: Last In First Out
 * @param <T>
 */
public interface Stack<T> {

    T pop();

    void push(T t);

    T top();

    boolean isEmpty();
}
