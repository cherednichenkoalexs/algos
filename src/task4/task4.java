
import java.util.*;

public class Stack<T> {

    public LinkedList<T> list;
    public int size;

    // task 4,
    // 1/
    // size()
    // O(1)
    public Stack() {
        // инициализация внутреннего хранилища стека
        this.list = new LinkedList();
        this.size = 0;
    }

    // task 4,
    // 1/
    // size()
    // O(1)
    public int size() {
        // размер текущего стека    
        return this.size;
    }

    // task 4,
    // 1
    // pop()
    // O(1)
    public T pop() {
        // ваш код
        if (this.size == 0) {
            return null;// если стек пустой
        }

        this.size--;
        return (T) this.list.removeLast();
    }

    // task 4,
    // 1
    // push()
    // O(1)
    public void push(T val) {
        this.list.addLast(val);
        this.size++;
    }

    // task 4,
    // 1
    // peek()
    // O(1)
    public T peek() {
        return (T) this.list.peekLast();
    }

    // task 4,
    // 2
    // popFirst()
    // O(1)
    public T popFirst() {
        // ваш код
        if (this.size == 0) {
            return null;// если стек пустой
        }

        this.size--;
        return (T) this.list.pop();
    }

    // task 4,
    // 2
    // pushFirst()
    // O(1)
    public void pushFirst(T val) {
        this.list.push(val);
        this.size++;
    }

    // task 4,
    // 2
    // peekFirst()
    // O(1)
    public T peekFirst() {
        return (T) this.list.peek();
    }

    // task 4,
    // 3
    // опустошит весь стек
}

