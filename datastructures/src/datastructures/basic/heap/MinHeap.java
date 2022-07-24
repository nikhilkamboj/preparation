package datastructures.basic.heap;

// each root in min heap is smaller than the child element
public class MinHeap {
    int capacity = 15;
    int size = 0;
    int[] heap = new int[capacity];


    public void insert(int newVal) {
        heap[size] = newVal;
        size++;
        heapifyUp();
    }

    // removing root of the heap
    public void poll() {
        heap[0] = heap[size-1];
        size--;

        heapifyDown();
    }

    private void heapifyUp() {
        int currentIndex = size - 1;
        int parentIndex = getParentIndex(currentIndex);

        while (parentIndex >= 0 && heap[parentIndex] > heap[currentIndex]) {
            swap(currentIndex, parentIndex);
            currentIndex = parentIndex;
            parentIndex = getParentIndex(currentIndex);
        }
    }

    private void heapifyDown() {
        int currentIndex  = 0;

        while (hasLeftChild(currentIndex)) {
            int smallerChildIndex = getLeftChild(currentIndex);
            if (heap[smallerChildIndex] > heap[getRightChild(currentIndex)]) {
                smallerChildIndex = getRightChild(currentIndex);
            }

            if (heap[currentIndex] < heap[smallerChildIndex]) {
                break;
            } else {
                swap(currentIndex, smallerChildIndex);
            }
            currentIndex = smallerChildIndex;
        }
    }

    private boolean hasLeftChild(int index) {
        return getLeftChild(index) <= size;
    }

    private int getParentIndex(int currentIndex) {
        return (currentIndex - 1)/2;
    }

    private void swap(int childIndex, int parentIndex) {
        int childValue = heap[childIndex];
        heap[childIndex] = heap[parentIndex];
        heap[parentIndex] = childValue;
    }

    private int getLeftChild(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChild(int parentIndex) {
        return 2 * parentIndex + 2;
    }
}
