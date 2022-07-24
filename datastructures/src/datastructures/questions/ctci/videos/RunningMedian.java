package datastructures.questions.ctci.videos;

import java.util.Comparator;
import java.util.PriorityQueue;

public class RunningMedian {


    public double[] findRunningMedian(int[] array) {
        // minHeap
        PriorityQueue<Integer> higherHalfHeap = new PriorityQueue<>();
        // max heap
        PriorityQueue<Integer> lowerHalfHeap = new PriorityQueue<>(new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -1 * o1.compareTo(o2);
            }
        });

        double[] median = new double[array.length];

        for (int i = 0; i < array.length; i++) {
            addToHeap(array[i], lowerHalfHeap, higherHalfHeap);
            reBalanceHeap(lowerHalfHeap, higherHalfHeap);
            addToMedian(median, lowerHalfHeap, higherHalfHeap, i);
        }
        return median;
    }

    private void addToHeap(int value, PriorityQueue<Integer> lowerHalfHeap, PriorityQueue<Integer> higherHalfHeap) {
        if (lowerHalfHeap.size() == 0 || lowerHalfHeap.peek() > value) {
            lowerHalfHeap.add(value);
        } else {
            higherHalfHeap.add(value);
        }
    }

    private void reBalanceHeap(PriorityQueue<Integer> lowerHalfHeap, PriorityQueue<Integer> higherHalfHeap) {
        PriorityQueue<Integer> bigger =  lowerHalfHeap.size() > higherHalfHeap.size() ? lowerHalfHeap : higherHalfHeap;
        PriorityQueue<Integer> smaller =  lowerHalfHeap.size() > higherHalfHeap.size() ? higherHalfHeap : lowerHalfHeap;

        if (bigger.size() - smaller.size() >= 2) {
            smaller.add(bigger.poll());
        }
    }

    private void addToMedian(double[] median, PriorityQueue<Integer> lowerHalfHeap,
                             PriorityQueue<Integer> higherHalfHeap, int index) {
        double medianValue;
        if (lowerHalfHeap.size() == higherHalfHeap.size()) {
            medianValue = ((double)lowerHalfHeap.peek() + higherHalfHeap.peek())/2;
        } else {
            medianValue = higherHalfHeap.peek();
        }

        median[index] = medianValue;
    }


}
