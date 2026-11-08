
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class DequeTest {

    private Deque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new Deque<>();
    }

    @Test
    void newDequeIsEmpty() {
        assertEquals(0, deque.size());
    }

    @Test
    void sizeGrowsWithEachAdd() {
        deque.addFront(1);
        assertEquals(1, deque.size());
        deque.addTail(2);
        assertEquals(2, deque.size());
        deque.addFront(3);
        assertEquals(3, deque.size());
    }

    @Test
    void sizeShrinksWithEachRemove() {
        for (int i = 0; i < 4; i++) {
            deque.addTail(i);
        }
        deque.removeFront();
        assertEquals(3, deque.size());
        deque.removeTail();
        assertEquals(2, deque.size());
        deque.removeFront();
        deque.removeTail();
        assertEquals(0, deque.size());
    }

    @Test
    void addFrontPutsValuesBeforeOthers() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertEquals(3, deque.removeFront());
        assertEquals(2, deque.removeFront());
        assertEquals(1, deque.removeFront());
    }

    @Test
    void addTailPutsValuesAfterOthers() {
        deque.addTail(1);
        deque.addTail(2);
        deque.addTail(3);
        assertEquals(3, deque.removeTail());
        assertEquals(2, deque.removeTail());
        assertEquals(1, deque.removeTail());
    }

    @Test
    void removeFrontTakesFromTheHead() {
        deque.addTail(1);
        deque.addTail(2);
        deque.addTail(3);
        assertEquals(1, deque.removeFront());
        assertEquals(2, deque.removeFront());
        assertEquals(3, deque.removeFront());
    }

    @Test
    void removeTailTakesFromTheTail() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertEquals(1, deque.removeTail());
        assertEquals(2, deque.removeTail());
        assertEquals(3, deque.removeTail());
    }

    @Test
    void addFrontAndRemoveTailWorkAsQueue() {
        deque.addFront(1);
        deque.addFront(2);
        deque.addFront(3);
        assertEquals(1, deque.removeTail());
        assertEquals(2, deque.removeTail());
        assertEquals(3, deque.removeTail());
    }

    @Test
    void addTailAndRemoveFrontWorkAsQueue() {
        deque.addTail(1);
        deque.addTail(2);
        deque.addTail(3);
        assertEquals(1, deque.removeFront());
        assertEquals(2, deque.removeFront());
        assertEquals(3, deque.removeFront());
    }

    @Test
    void removeFrontOnEmptyDequeReturnsNull() {
        assertNull(deque.removeFront());
        assertEquals(0, deque.size());
    }

    @Test
    void removeTailOnEmptyDequeReturnsNull() {
        assertNull(deque.removeTail());
        assertEquals(0, deque.size());
    }

    @Test
    void addManyValuesToBothEnds() {
        for (int i = 0; i < 10000; i++) {
            deque.addFront(i);
            deque.addTail(i);
        }
        assertEquals(20000, deque.size());
        assertEquals(9999, deque.removeFront());
        assertEquals(9999, deque.removeTail());
        assertEquals(19998, deque.size());
    }
}

