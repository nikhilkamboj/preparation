package datastructures.basic;

public interface List {
    <T> T retrieveLastElement();

    <T> T insertElement(T t);

    <T> T removeElement(int elementPosition);

    <T> T retrieveElement(int elementPosition);

    <T> T findElement(T t);
}
