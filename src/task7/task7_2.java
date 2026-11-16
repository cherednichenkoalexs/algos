import java.util.*;

class MassDeleteOrderedList<T> extends OrderedList<T> {

    public MassDeleteOrderedList(boolean asc) {
        super(asc);
    }

    // task 7,
    // 8
    // mass deletion
    // O(n)
    public void massDelete(T val) {
        Node<T> node = this.head;

        while (node != null) {
            int cmp = compare(node.value, val);

            // дальше только заведомо большие (меньшие) значения
            if ((this.isAscending() && cmp == 1) || (!this.isAscending() && cmp == -1)) {
                return;
            }

            Node<T> next = node.next;
            if (cmp == 0) {
                this.unlink(node);
            }
            node = next;
        }
    }

    // task 7,
    // 8
    // remove duplicates
    // O(n)
    public void removeDuplicates() {
        Node<T> node = this.head;

        while (node != null && node.next != null) {
            if (compare(node.value, node.next.value) == 0) {
                this.unlink(node.next);
            } else {
                node = node.next;
            }
        }
    }

    // task 7,
    // 10
    // find sub list
    // O(n + m)
    public boolean findSubList(OrderedList<T> subList) {
        if (subList.head == null) {
            return true;
        }

        ArrayList<T> pattern = new ArrayList<T>();
        for (Node<T> n = subList.head; n != null; n = n.next) {
            pattern.add(n.value);
        }

        // prefix[i] - длина самого длинного собственного префикса pattern[0..i], который является и его суффиксом
        int[] prefix = new int[pattern.size()];
        for (int i = 1, k = 0; i < pattern.size(); i++) {
            while (k > 0 && compare(pattern.get(i), pattern.get(k)) != 0) {
                k = prefix[k - 1];
            }
            if (compare(pattern.get(i), pattern.get(k)) == 0) {
                k++;
            }
            prefix[i] = k;
        }

        int matched = 0;
        for (Node<T> node = this.head; node != null; node = node.next) {
            while (matched > 0 && compare(node.value, pattern.get(matched)) != 0) {
                matched = prefix[matched - 1];
            }
            if (compare(node.value, pattern.get(matched)) == 0) {
                matched++;
            }
            if (matched == pattern.size()) {
                return true;
            }
        }

        return false;
    }

    // task 7,
    // 11
    // find most frequent
    // O(n)
    public T findMostFrequent() {
        T best = null;
        int bestCount = 0;

        Node<T> node = this.head;
        while (node != null) {
            int run = 1;
            Node<T> next = node.next;
            while (next != null && compare(node.value, next.value) == 0) {
                run++;
                next = next.next;
            }

            if (run > bestCount) {
                bestCount = run;
                best = node.value;
            }
            node = next;
        }

        return best;
    }

    private void unlink(Node<T> node) {
        if (node.prev == null) {
            this.head = node.next;
        } else {
            node.prev.next = node.next;
        }

        if (node.next == null) {
            this.tail = node.prev;
        } else {
            node.next.prev = node.prev;
        }

        this.count--;
    }
}

class ListCombiner {

    // task 7,
    // 9
    // combines lists
    // O(n + m)
    public static <T> OrderedList<T> combine(OrderedList<T> first, OrderedList<T> second) {
        if (first.isAscending() != second.isAscending()) {
            throw new IllegalArgumentException("lists must have the same order");
        }

        OrderedList<T> result = new OrderedList<T>(first.isAscending());
        Node<T> a = first.head;
        Node<T> b = second.head;

        while (a != null || b != null) {
            boolean takeFirst;
            if (b == null) {
                takeFirst = true;
            } else if (a == null) {
                takeFirst = false;
            } else {
                int cmp = result.compare(a.value, b.value);
                takeFirst = first.isAscending() ? cmp <= 0 : cmp >= 0;
            }

            Node<T> n;
            if (takeFirst) {
                n = new Node<T>(a.value);
                a = a.next;
            } else {
                n = new Node<T>(b.value);
                b = b.next;
            }

            if (result.tail == null) {
                result.head = n;
            } else {
                n.prev = result.tail;
                result.tail.next = n;
            }
            result.tail = n;
            result.count++;
        }

        return result;
    }
}

// task 7,
// 12
// поиск индекса за O(log n)
// add() остаётся O(n) из-за сдвига элементов, indexOf() - O(log n)
class EfficientList<T> {

    private final OrderedList<T> comparer;
    private final ArrayList<T> items;

    public EfficientList(boolean asc) {
        this(new OrderedList<T>(asc));
    }

    // comparer нужен только для compare() и порядка, его элементы не используются
    EfficientList(OrderedList<T> comparer) {
        this.comparer = comparer;
        this.items = new ArrayList<T>();
    }

    // O(n): двоичный поиск места за O(log n) и сдвиг хвоста
    public void add(T value) {
        this.items.add(this.upperBound(value), value);
    }

    // индекс первого элемента, равного value, либо -1
    // O(log n)
    public int indexOf(T value) {
        int index = this.lowerBound(value);

        if (index < this.items.size() && this.comparer.compare(this.items.get(index), value) == 0) {
            return index;
        }

        return -1;
    }

    // O(n)
    public void delete(T value) {
        int index = this.indexOf(value);

        if (index != -1) {
            this.items.remove(index);
        }
    }

    // O(1)
    public T get(int index) {
        return this.items.get(index);
    }

    // O(1)
    public int size() {
        return this.items.size();
    }

    private int lowerBound(T value) {
        int low = 0;
        int high = this.items.size();

        while (low < high) {
            int middle = (low + high) >>> 1;
            int cmp = this.comparer.compare(this.items.get(middle), value);

            if (this.comparer.isAscending() ? cmp < 0 : cmp > 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }

        return low;
    }

    private int upperBound(T value) {
        int low = 0;
        int high = this.items.size();

        while (low < high) {
            int middle = (low + high) >>> 1;
            int cmp = this.comparer.compare(this.items.get(middle), value);

            if (this.comparer.isAscending() ? cmp <= 0 : cmp >= 0) {
                low = middle + 1;
            } else {
                high = middle;
            }
        }

        return low;
    }
}

//рефлексия 5.3 совпадает с моей реализацией
//рефлексия 5.4 совпадает с моей реализацией
//рефлексия 5.5 моя реализация за О(n^2), через лист - лучше по сложнотси, но хуже по памяти
//рефлексия 5.6 у меня реализация с одним указателем, в остальном реализация совпадает

