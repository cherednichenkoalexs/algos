
import java.util.*;

public class Queue<T> {

    protected LinkedList<T> list;
    protected int size;

    public Queue() {
        list = new LinkedList<T>();
        size = 0;
    }

    // task 5,
    // 1
    // enqueue()
    // O(1)
    public void enqueue(T item) {
        // вставка в хвост
        this.list.addLast(item);
        this.size++;
    }

    // task 5,
    // 1
    // dequeue()
    // O(1)
    public T dequeue() {
        // выдача из головы
        if (this.size == 0) {
            return null; // null если очередь пустая
        }

        this.size--;
        return this.list.pollFirst();
    }

    // task 5,
    // 1
    // size()
    // O(1)
    public int size() {
        return this.size;
    }

}

