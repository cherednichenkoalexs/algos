import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class OrderedListTest {

    private OrderedList<Integer> asc;
    private OrderedList<Integer> desc;

    // список, который считает вызовы compare(), нужен для проверки раннего выхода из find()
    private static class CountingList extends OrderedList<Integer> {
        int comparisons = 0;

        CountingList(boolean asc) {
            super(asc);
        }

        @Override
        public int compare(Integer a, Integer b) {
            comparisons++;
            return super.compare(a, b);
        }
    }

    @BeforeEach
    void setUp() {
        asc = new OrderedList<>(true);
        desc = new OrderedList<>(false);
    }

    private static List<Integer> values(OrderedList<Integer> list) {
        List<Integer> result = new ArrayList<>();
        for (Node<Integer> node : list.getAll()) {
            result.add(node.value);
        }
        return result;
    }

    // head, tail, prev, next и count должны быть согласованы
    private static void assertLinksAreValid(OrderedList<Integer> list) {
        Node<Integer> prev = null;
        Node<Integer> node = list.head;
        int counted = 0;
        while (node != null) {
            assertTrue(node.prev == prev);
            prev = node;
            node = node.next;
            counted++;
        }
        assertTrue(list.tail == prev);
        assertEquals(counted, list.count());
    }

    // ---------- add ----------

    @Test
    void addAscendingKeepsOrder() {
        for (int x : new int[]{5, 3, 8, 1, 4}) {
            asc.add(x);
        }
        assertEquals(List.of(1, 3, 4, 5, 8), values(asc));
        assertEquals(5, asc.count());
        assertLinksAreValid(asc);
    }

    @Test
    void addDescendingKeepsOrder() {
        for (int x : new int[]{5, 3, 8, 1, 4}) {
            desc.add(x);
        }
        assertEquals(List.of(8, 5, 4, 3, 1), values(desc));
        assertEquals(5, desc.count());
        assertLinksAreValid(desc);
    }

    @Test
    void addKeepsDuplicates() {
        for (int x : new int[]{4, 1, 7, 1}) {
            asc.add(x);
            desc.add(x);
        }
        assertEquals(List.of(1, 1, 4, 7), values(asc));
        assertEquals(List.of(7, 4, 1, 1), values(desc));
    }

    @Test
    void addManyValues() {
        for (int i = 0; i < 1000; i++) {
            asc.add((i * 37) % 1000);
        }
        List<Integer> result = values(asc);
        assertEquals(1000, result.size());
        for (int i = 1; i < result.size(); i++) {
            assertTrue(result.get(i - 1) <= result.get(i));
        }
        assertLinksAreValid(asc);
    }

    // ---------- find ----------

    @Test
    void findInEmptyListReturnsNull() {
        assertNull(asc.find(5));
        assertNull(desc.find(5));
    }

    @Test
    void findAscending() {
        for (int x : new int[]{1, 3, 5, 7, 9}) {
            asc.add(x);
        }
        for (int x : new int[]{1, 3, 5, 7, 9}) {
            assertEquals(x, asc.find(x).value);
        }
        assertNull(asc.find(0));
        assertNull(asc.find(4));
        assertNull(asc.find(10));
    }

    @Test
    void findDescending() {
        for (int x : new int[]{1, 3, 5, 7, 9}) {
            desc.add(x);
        }
        for (int x : new int[]{1, 3, 5, 7, 9}) {
            assertEquals(x, desc.find(x).value);
        }
        assertNull(desc.find(0));
        assertNull(desc.find(4));
        assertNull(desc.find(10));
    }

    @Test
    void findComparesByValueNotByReference() {
        asc.add(1000);
        assertEquals(1000, asc.find(1000).value);
    }

    @Test
    void findAscendingStopsEarlyWhenValueIsMissing() {
        CountingList list = new CountingList(true);
        for (int i = 0; i < 1000; i++) {
            list.add(i * 2);
        }
        list.comparisons = 0;
        assertNull(list.find(3));
        assertTrue(list.comparisons < 10);
    }

    @Test
    void findDescendingStopsEarlyWhenValueIsMissing() {
        CountingList list = new CountingList(false);
        for (int i = 0; i < 1000; i++) {
            list.add(i * 2);
        }
        list.comparisons = 0;
        assertNull(list.find(1995));
        assertTrue(list.comparisons < 10);
    }

    // ---------- delete ----------

    @Test
    void deleteFromTheMiddle() {
        for (int x : new int[]{1, 3, 5, 7, 9}) {
            asc.add(x);
            desc.add(x);
        }
        asc.delete(5);
        desc.delete(5);
        assertEquals(List.of(1, 3, 7, 9), values(asc));
        assertEquals(List.of(9, 7, 3, 1), values(desc));
        assertLinksAreValid(asc);
        assertLinksAreValid(desc);
    }

    @Test
    void deleteTheHeadAndTheTail() {
        for (int x : new int[]{1, 3, 5}) {
            asc.add(x);
            desc.add(x);
        }
        asc.delete(1);
        asc.delete(5);
        desc.delete(5);
        desc.delete(1);
        assertEquals(List.of(3), values(asc));
        assertEquals(List.of(3), values(desc));
        assertLinksAreValid(asc);
        assertLinksAreValid(desc);
    }

    @Test
    void deleteTheOnlyElement() {
        asc.add(4);
        asc.delete(4);
        assertEquals(0, asc.count());
        assertNull(asc.head);
        assertNull(asc.tail);
    }

    @Test
    void deleteMissingValueChangesNothing() {
        for (int x : new int[]{1, 3, 5}) {
            asc.add(x);
        }
        asc.delete(4);
        assertEquals(List.of(1, 3, 5), values(asc));
        assertEquals(3, asc.count());

        new OrderedList<Integer>(true).delete(1);
    }

    @Test
    void deleteRemovesOnlyOneOfDuplicates() {
        for (int x : new int[]{2, 2, 2}) {
            asc.add(x);
        }
        asc.delete(2);
        assertEquals(List.of(2, 2), values(asc));
        assertLinksAreValid(asc);
    }

    // ---------- clear ----------

    @Test
    void clearEmptiesTheListAndCanChangeTheOrder() {
        asc.add(1);
        asc.add(2);
        asc.clear(false);
        assertEquals(0, asc.count());
        assertNull(asc.head);
        asc.add(1);
        asc.add(2);
        assertEquals(List.of(2, 1), values(asc));
    }
}

class OrderedListStringsTest {

    @Test
    void compareIgnoresSpacesAtTheEdges() {
        OrderedList<String> strings = new OrderedList<>(true);
        assertEquals(0, strings.compare("  abc ", "abc"));
        assertEquals(-1, strings.compare("a", " b"));
        assertEquals(1, strings.compare("b ", "a"));
    }

    @Test
    void addSortsStringsInBothOrders() {
        OrderedList<String> asc = new OrderedList<>(true);
        OrderedList<String> desc = new OrderedList<>(false);
        for (String s : new String[]{"pear", "fig", " apple ", "cherry"}) {
            asc.add(s);
            desc.add(s);
        }
        assertEquals("apple", asc.head.value.trim());
        assertEquals("pear", asc.tail.value);
        assertEquals("pear", desc.head.value);
        assertEquals("apple", desc.tail.value.trim());
    }

    @Test
    void findAndDeleteIgnoreSpacesAtTheEdges() {
        OrderedList<String> list = new OrderedList<>(true);
        list.add("a");
        list.add("b");
        list.add("c");
        assertEquals("b", list.find("  b ").value);
        assertNull(list.find("d"));
        list.delete(" b ");
        assertEquals(2, list.count());
        assertNull(list.find("b"));
    }
}

