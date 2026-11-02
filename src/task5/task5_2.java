import java.util.Stack;

// task 5,
// 4
// очередь из двух стеков
// O(1)
class QueueStacked<T> {

    Stack<T> stack1;
    Stack<T> stack2;
    protected int size;

    QueueStacked() {
        stack1 = new Stack<T>();
        stack2 = new Stack<T>();
        size = 0;
    }

    // task 5,
    // 3
    // вращает на n жлементов
    // O(n)
    void Rotate(int n) {
        if (this.size == 0) {
            return;
        }

        for (int i = 0; i < n; i++) {
            this.enqueue(this.dequeue());
        }
    }

    // task 5,
    // 4
    // enqueue()
    // O(1)
    public void enqueue(T item) {

        this.stack1.push(item);
        this.size++;
    }

    // task 5,
    // 4
    // dequeue()
    // O(n)
    public T dequeue() {
        // выдача из головы
        if (this.size == 0) {
            return null; // null если очередь пустая
        }

        this.size--;

        if (!this.stack2.empty()) {
            return this.stack2.pop();
        }

        int size = this.stack1.size();
        for (int i = 0; i < size; i++) {
            this.stack2.push(this.stack1.pop());
        }

        return this.stack2.pop();
    }

    // task 5,
    // 4
    // size()
    // O(1)
    public int size() {
        return this.size;
    }
}

class QueueExtended<T> extends Queue<T> {

    // task 5,
    // 5
    // меняет порядок на обратный
    // O(n)
    void reverse() {
        for (int i = 0; i < this.size; i++) {
            this.list.add(i, this.list.removeLast());
        }
    }
}

// task 5,  
// 6
// круговая очередь
class QueueFixed<T> {

    int capacity;
    int size;
    int headPosition;
    Object[] container;

    QueueFixed(int _capacity) {
        capacity = _capacity;
        size = 0;
        headPosition = 0;
        container = new Object[capacity];
    }

    // task 5,
    // 1
    // enqueue()
    // O(1)
    public void enqueue(T item) {
        if (this.size == this.capacity) {
            return;
        }

        int newPosition = (this.headPosition + this.size) % this.capacity;
        this.size++;

        this.container[newPosition] = item;
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

        T result = (T) this.container[this.headPosition];

        this.headPosition = (this.headPosition + 1) % this.capacity;

        this.size--;
        return result;
    }

    // task 5,
    // 6
    // size()
    // O(1)
    public int size() {
        return this.size;
    }

    // task 5,  
    // 6
    // capacity()
    // O(1)
    public int capacity() {
        return this.capacity;
    }
}


// рефлексия 3.6 Моя реализация стремится к нулевому балансу, предложенная непонятно. Чего мы добъёмся, если увеличивать размер будем только при необходимости, как-будто никакой разницы с обычной реализации тут нет. Не списывать бюджет при уменьшении тоже непонятно какой смысл несёт. Мне кажется стремление к нулевому балансу лучше усредняет сложность.
// рефлексия 3.7 Это очень хитрая реализация, я не додумался и навертел переусложнённый массив массивов. Приму во внимание.

