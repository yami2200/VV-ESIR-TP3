package fr.istic.vv;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.NoSuchElementException;

class BinaryHeap<T> {

    Comparator<T> comparator;
    List<T> heap;
    
    public BinaryHeap(Comparator<T> comparator) {
        this.comparator = comparator;
        heap = new ArrayList<>();
    }

    public T pop() {
        if(heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide.");
        }
        T objectToPop = heap.get(0);
        heap.remove(0);
        return objectToPop;
    }

    public T peek() {
        if(heap.isEmpty()) {
            throw new NoSuchElementException("Le tas est vide.");
        }
        T objectToPop = heap.get(0);
        return objectToPop;
    }

    public void push(T element) {
        heap.add(element);
        heap.sort(comparator);
    }

    public int count() {
        return heap.size();
    }

    public String toString() {
        return heap.toString();
    }

}