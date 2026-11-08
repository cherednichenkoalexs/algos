
import java.util.LinkedList;

class IsPalindorm {

    // task 6,
    // 4
    // addFront()
    // O(1)
    static boolean IsPalindorm(Deque d) {
        for (int i = 0; i < d.size() / 2; i++) {
            if (!d.removeFront().equals(d.removeTail())) {
                return false;
            }
        }

        return true;
    }
}

class MininalDeque {

    protected LinkedList<Integer> list;
    protected LinkedList<Integer> frontMinimalList;
    protected LinkedList<Integer> tailMinimalList;
    protected int size;

    public MininalDeque() {
        // инициализация внутреннего хранилища
        list = new LinkedList<Integer>();
        frontMinimalList = new LinkedList<Integer>();
        tailMinimalList = new LinkedList<Integer>();
        size = 0;
    }

    public void addFront(Integer item) {
        // добавление в голову
        this.list.addFirst(item);

        this.size++;

        if (this.frontMinimalList.peek() >= item) {
            this.frontMinimalList.push(item);
        }
    }

    public void addTail(Integer item) {
        // добавление в хвост
        this.list.addLast(item);
        this.size++;

        if (this.tailMinimalList.peek() >= item) {
            this.tailMinimalList.push(item);
        }
    }

    public Integer removeFront() {
        // удаление из головы
        if (this.size == 0) {
            return null;
        }

        this.size--;

        Integer result = this.list.pollFirst();
        if (this.frontMinimalList.peek().equals(result)) {
            this.frontMinimalList.poll();
        }

        return result;
    }

    public Integer removeTail() {
        // удаление из хвоста
        if (this.size == 0) {
            return null;
        }

        this.size--;

        Integer result = this.list.pollLast();
        if (this.tailMinimalList.peek().equals(result)) {
            this.tailMinimalList.poll();
        }

        return result;
    }

    public int size() {
        return this.size;
    }

    // task 6,
    // 5
    // возвращает минимальный элемент
    // O(1)
    public Integer getMininal() {
        Integer tail = this.tailMinimalList.peek();
        Integer front = this.frontMinimalList.peek();

        return tail > front ? front : tail;
    }
}

class DequeArray<T> {

    private static final int MIN_CAPACITY = 16;

    private Object[] container;
    private int head;
    private int size;

    public DequeArray() {
        container = new Object[MIN_CAPACITY];
        head = 0;
        size = 0;
    }

    // task 6,
    // 6
    // addFront()
    // амортизированно O(1)
    public void addFront(T item) {
        if (this.size == this.container.length) {
            this.resize(this.container.length * 2);
        }

        this.head = (this.head - 1 + this.container.length) % this.container.length;
        this.container[this.head] = item;
        this.size++;
    }

    // task 6,
    // 6
    // addTail()
    // амортизированно O(1)
    public void addTail(T item) {
        if (this.size == this.container.length) {
            this.resize(this.container.length * 2);
        }

        this.container[(this.head + this.size) % this.container.length] = item;
        this.size++;
    }

    // task 6,
    // 6
    // removeFront()
    // амортизированно O(1)
    public T removeFront() {
        if (this.size == 0) {
            return null;
        }

        T result = (T) this.container[this.head];
        this.container[this.head] = null;
        this.head = (this.head + 1) % this.container.length;
        this.size--;

        this.shrinkIfNeeded();
        return result;
    }

    // task 6,
    // 6
    // removeTail()
    // амортизированно O(1)
    public T removeTail() {
        if (this.size == 0) {
            return null;
        }

        int tail = (this.head + this.size - 1) % this.container.length;
        T result = (T) this.container[tail];
        this.container[tail] = null;
        this.size--;

        this.shrinkIfNeeded();
        return result;
    }

    // task 6,
    // 6
    // size()
    // O(1)
    public int size() {
        return this.size;
    }

    // task 6,
    // 6
    // capacity()
    // O(1)
    public int capacity() {
        return this.container.length;
    }

    // task 6,
    // 6
    // уменьшаем вдвое, когда занято меньше четверти, но не меньше MIN_CAPACITY
    private void shrinkIfNeeded() {
        int capacity = this.container.length;
        if (capacity > MIN_CAPACITY && this.size * 4 < capacity) {
            this.resize(Math.max(MIN_CAPACITY, capacity / 2));
        }
    }

    // task 6,
    // 6
    // resize()
    // O(n), вызывается редко
    private void resize(int newCapacity) {
        Object[] newContainer = new Object[newCapacity];

        // элементы переписываем по порядку от головы, чтобы в новом массиве head стал нулём
        for (int i = 0; i < this.size; i++) {
            newContainer[i] = this.container[(this.head + i) % this.container.length];
        }

        this.container = newContainer;
        this.head = 0;
    }
}

// рефлексия 4.4 4.5 совпадает с моей реализацией кроме словаря скобок, но для 3 скобок он не нужен
// рефлексия 4.6 совпажает с моей реализацией
// рефлексия 4.7 хранить сумму действительно более разумно, у меня перемудрённая логика с хранением именно среднего, но принцип тот же
// рефлексия 4.8 рекомендованный алгоритм совпадает с моим

