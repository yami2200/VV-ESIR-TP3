package fr.istic.vv;

import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {

    Comparator<Integer> integerComparator = new Comparator<Integer>() {
        @Override
        public int compare(Integer o1, Integer o2) {
            return o1-o2;
        }
    };
    BinaryHeap<Integer> binaryHeap = new BinaryHeap<>(integerComparator);

    @Test
    public void testPop1() {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            binaryHeap.pop();
        });
        assertEquals("Le tas est vide.", exception.getMessage());
    }

    @Test
    public void testPop2() {
        binaryHeap.push(3);
        assertEquals(binaryHeap.pop(), 3);
    }

    @Test
    public void testPop3() {
        binaryHeap.push(3);
        binaryHeap.pop();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            binaryHeap.pop();
        });
        assertEquals("Le tas est vide.", exception.getMessage());
    }

    @Test
    public void testPop4() {
        binaryHeap.push(4);
        binaryHeap.push(1);
        binaryHeap.push(5);
        binaryHeap.push(2);
        binaryHeap.push(3);
        assertEquals(binaryHeap.pop(), 1);
    }

    @Test
    public void testPop5() {
        binaryHeap.push(4);
        binaryHeap.push(1);
        binaryHeap.push(5);
        binaryHeap.push(2);
        binaryHeap.push(3);
        binaryHeap.pop();
        binaryHeap.pop();
        binaryHeap.pop();
        assertEquals(binaryHeap.pop(), 4);
    }

    @Test
    public void testPeek1() {
        binaryHeap.push(3);
        assertEquals(binaryHeap.peek(), 3);
    }

    @Test
    public void testPeek2() {
        binaryHeap.push(1);
        binaryHeap.push(2);
        binaryHeap.push(3);
        binaryHeap.push(4);
        binaryHeap.push(5);
        assertEquals(binaryHeap.peek(), 1);
    }

    @Test
    public void testPeek3() {
        binaryHeap.push(5);
        binaryHeap.push(4);
        binaryHeap.push(3);
        binaryHeap.push(2);
        binaryHeap.push(1);
        assertEquals(binaryHeap.peek(), 1);
    }

    @Test
    public void testPeek4() {
        binaryHeap.push(3);
        binaryHeap.push(1);
        binaryHeap.push(4);
        binaryHeap.push(2);
        binaryHeap.push(5);
        assertEquals(binaryHeap.peek(), 1);
    }

    @Test
    public void testPeek5() {
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            binaryHeap.peek();
        });
        assertEquals("Le tas est vide.", exception.getMessage());
    }

    @Test
    public void testPeek6() {
        binaryHeap.push(3);
        binaryHeap.pop();
        Throwable exception = assertThrows(NoSuchElementException.class, () -> {
            binaryHeap.peek();
        });
        assertEquals("Le tas est vide.", exception.getMessage());
    }

    @Test
    public void testCount1() {
        assertEquals(binaryHeap.count(), 0);
    }

    @Test
    public void testCount2() {
        binaryHeap.push(3);
        assertEquals(binaryHeap.count(), 1);
    }

    @Test
    public void testCount3() {
        binaryHeap.push(3);
        binaryHeap.pop();
        assertEquals(binaryHeap.count(), 0);
    }

    @Test
    public void testCount4() {
        binaryHeap.push(3);
        binaryHeap.push(3);
        binaryHeap.push(3);
        binaryHeap.push(3);
        binaryHeap.push(3);
        assertEquals(binaryHeap.count(), 5);
    }

    @Test
    public void testToString() {
        binaryHeap.push(3);
        binaryHeap.push(5);
        binaryHeap.push(4);
        binaryHeap.push(2);
        binaryHeap.push(1);
        assertEquals(binaryHeap.toString(), "[1, 2, 3, 4, 5]");
    }

}
