package datastructures.questions.ctci.book.stacksQueues;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedDeque;

public class ShelterQueue {
    Queue<Dog> dogQueue = new ConcurrentLinkedDeque<>();
    Queue<Cat> catQueue = new ConcurrentLinkedDeque<>();
    private int order;

    public void enqueue(Animal animal) {
        order++;
        animal.setOrder(order);
        if (animal instanceof Dog) {
            dogQueue.add((Dog) animal);
        } else {
            catQueue.add((Cat) animal);
        }
    }

    public Animal deQueueAny() {
        Dog dog = dogQueue.peek();
        Cat cat = catQueue.peek();

        if (dogQueue.isEmpty()) {
            return catQueue.poll();
        } else if (catQueue.isEmpty()) {
            return dogQueue.poll();
        }

        if (dog.getOrder() < cat.getOrder()) {
            return dogQueue.poll();
        } else {
            return catQueue.poll();
        }
    }

    public void deQueueCat() {
        catQueue.poll();
    }

    public void deQueueDog() {
        dogQueue.poll();
    }
}
