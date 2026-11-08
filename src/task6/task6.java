
import java.util.*;

public class Deque<T> {

    protected LinkedList<T> list;
    protected int size;

    public Deque() {
        // инициализация внутреннего хранилища
        list = new LinkedList<T>();
        size = 0;
    }

    // task 6,
    // 1
    // addFront()
    // O(1)
    public void addFront(T item) {
        // добавление в голову
        this.list.addFirst(item);
        this.size++;
    }

    // task 6,
    // 1
    // addTail()
    // O(1)
    public void addTail(T item) {
        // добавление в хвост
        this.list.addLast(item);
        this.size++;
    }

    // task 6,
    // 1
    // removeFront()
    // O(1)
    public T removeFront() {
        // удаление из головы
        if (this.size == 0) {
            return null;
        }

        this.size--;

        return this.list.pollFirst();
    }

    // task 6,
    // 1
    // removeTail()
    // O(1)
    public T removeTail() {
        // удаление из хвоста
        if (this.size == 0) {
            return null;
        }

        this.size--;

        return this.list.pollLast();
    }

    // task 6,
    // 1
    // size()
    // O(1)
    public int size() {
        return this.size;
    }
}

// task 6,
// 2
// связный список, которым я очередь и реализовал, очевидно,
// в этой реализации даже минусы списков из-за подходов к организации памяти не будут добавлять проблем,
// так как очередь обычно не перебирают

