
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class StackTest {

    private Stack<Integer> stack;

    @BeforeEach
    void setUp() {
        stack = new Stack<>();
    }

    @Test
    void newStackIsEmpty() {
        assertEquals(0, stack.size());
    }

    @Test
    void sizeGrowsWithEachPush() {
        for (int i = 1; i <= 5; i++) {
            stack.push(i);
            assertEquals(i, stack.size());
        }
    }

    @Test
    void sizeShrinksWithEachPop() {
        for (int i = 0; i < 5; i++) {
            stack.push(i);
        }
        for (int i = 4; i >= 0; i--) {
            stack.pop();
            assertEquals(i, stack.size());
        }
    }

    @Test
    void pushedValueBecomesTop() {
        stack.push(10);
        assertEquals(10, stack.peek());
        stack.push(20);
        assertEquals(20, stack.peek());
    }

    @Test
    void pushAcceptsDuplicates() {
        stack.push(7);
        stack.push(7);
        assertEquals(2, stack.size());
        assertEquals(7, stack.pop());
        assertEquals(7, stack.pop());
    }

    @Test
    void pushManyValues() {
        for (int i = 0; i < 10_000; i++) {
            stack.push(i);
        }
        assertEquals(10_000, stack.size());
        assertEquals(9_999, stack.peek());
    }

    @Test
    void popReturnsValuesInReverseOrder() {
        stack.push(1);
        stack.push(2);
        stack.push(3);
        assertEquals(3, stack.pop());
        assertEquals(2, stack.pop());
        assertEquals(1, stack.pop());
    }

    @Test
    void popOnEmptyStackReturnsNull() {
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void popOnDrainedStackReturnsNullAndSizeStaysZero() {
        stack.push(1);
        stack.pop();
        assertNull(stack.pop());
        assertNull(stack.pop());
        assertEquals(0, stack.size());
    }

    @Test
    void stackIsReusableAfterBeingEmptied() {
        stack.push(1);
        stack.pop();
        stack.push(2);
        assertEquals(1, stack.size());
        assertEquals(2, stack.pop());
    }

    @Test
    void peekReturnsTopWithoutRemovingIt() {
        stack.push(1);
        stack.push(2);
        assertEquals(2, stack.peek());
        assertEquals(2, stack.peek());
        assertEquals(2, stack.size());
    }

    @Test
    void peekOnEmptyStackReturnsNull() {
        assertNull(stack.peek());
        assertEquals(0, stack.size());
    }

    @Test
    void peekAfterPopShowsPreviousElement() {
        stack.push(1);
        stack.push(2);
        stack.pop();
        assertEquals(1, stack.peek());
    }

    @Test
    void worksWithStrings() {
        Stack<String> words = new Stack<>();
        words.push("a");
        words.push("b");
        assertEquals("b", words.pop());
        assertEquals("a", words.peek());
    }
}

