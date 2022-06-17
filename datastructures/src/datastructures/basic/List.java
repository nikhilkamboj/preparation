package datastructures.basic;

public interface List<T> {
    T retrieveLastElement();

    T insertElement(T t);

    T removeElement(int elementPosition);

    T retrieveElement(int elementPosition);

    T findElement(T t);

    T insertElementBeginning(T t);

    T insertElementNthPosition(T t, int elementPosition);
}
