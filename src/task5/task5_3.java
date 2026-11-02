
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class QueueTest {

    private Queue<Integer> queue;

    @BeforeEach
    void setUp() {
        queue = new Queue<>();
    }

    @Test
    void newQueueIsEmpty() {
        assertEquals(0, queue.size());
    }

    @Test
    void sizeGrowsWithEachEnqueue() {
        for (int i = 1; i <= 5; i++) {
            queue.enqueue(i);
            assertEquals(i, queue.size());
        }
    }

    @Test
    void sizeShrinksWithEachDequeue() {
        for (int i = 0; i < 5; i++) {
            queue.enqueue(i);
        }
        for (int i = 4; i >= 0; i--) {
            queue.dequeue();
            assertEquals(i, queue.size());
        }
    }

    @Test
    void enqueueAcceptsDuplicates() {
        queue.enqueue(7);
        queue.enqueue(7);
        assertEquals(2, queue.size());
        assertEquals(7, queue.dequeue());
        assertEquals(7, queue.dequeue());
    }

    @Test
    void enqueueManyValues() {
        for (int i = 0; i < 10000; i++) {
            queue.enqueue(i);
        }
        assertEquals(10000, queue.size());
        assertEquals(0, queue.dequeue());
        assertEquals(9999, queue.size());
    }

    @Test
    void dequeueReturnsValuesInInsertionOrder() {
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
    }

    @Test
    void dequeueOnEmptyQueueReturnsNull() {
        assertNull(queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    void dequeueOnDrainedQueueReturnsNullAndSizeStaysZero() {
        queue.enqueue(1);
        queue.dequeue();
        assertNull(queue.dequeue());
        assertNull(queue.dequeue());
        assertEquals(0, queue.size());
    }

    @Test
    void queueIsReusableAfterBeingEmptied() {
        queue.enqueue(1);
        queue.dequeue();
        queue.enqueue(2);
        assertEquals(1, queue.size());
        assertEquals(2, queue.dequeue());
    }

    @Test
    void interleavedEnqueueAndDequeueKeepsOrder() {
        queue.enqueue(1);
        queue.enqueue(2);
        assertEquals(1, queue.dequeue());
        queue.enqueue(3);
        assertEquals(2, queue.dequeue());
        assertEquals(3, queue.dequeue());
        assertNull(queue.dequeue());
    }

    @Test
    void worksWithStrings() {
        Queue<String> words = new Queue<>();
        words.enqueue("a");
        words.enqueue("b");
        assertEquals("a", words.dequeue());
        assertEquals("b", words.dequeue());
    }
}

