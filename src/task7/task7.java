
import java.util.*;

class Node<T> {

    public T value;
    public Node<T> next, prev;

    public Node(T _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

public class OrderedList<T> {

    public Node<T> head, tail;
    private boolean _ascending;
    protected int count;

    // task 7,
    // 1
    // new order option
    // O(1)
    public OrderedList(boolean asc) {
        head = null;
        tail = null;
        _ascending = asc;
    }

    public int compare(T v1, T v2) {

        // -1 если v1 < v2
        // 0 если v1 == v2
        // +1 если v1 > v2
        // task 7,
        // 2
        // compare ints
        // O(1)
        if (v1 instanceof Integer && v2 instanceof Integer) {
            return Integer.compare((int) v1, (int) v2);
        }

        // task 7,
        // 5
        // compare strings
        // O(1)
        if (v1 instanceof String && v2 instanceof String) {
            int res = ((String) v1).trim().compareTo(((String) v2).trim());
            return res < 0 ? -1 : (res > 0 ? 1 : 0);
        }

        return 0;
    }

    // task 7,
    // 3
    // add()
    // O(n)
    public void add(T value) {
        // автоматическая вставка value 
        // в нужную позицию

        if (this.head == null) {
            Node n = new Node(value);

            this.head = n;
            this.tail = n;
            count++;
            return;
        }

        if ((_ascending && compare(value, this.head.value) == -1) || (!_ascending && compare(value, this.head.value) == 1)) {
            Node n = new Node(value);
            n.prev = null;
            n.next = this.head;
            this.head.prev = n;
            this.head = n;

            this.count++;
            return;
        }

        if ((_ascending && compare(value, this.tail.value) == 1) || (!_ascending && compare(value, this.tail.value) == -1)) {
            Node n = new Node(value);
            n.prev = this.tail;
            n.next = null;
            this.tail.next = n;
            this.tail = n;
            this.count++;
            return;
        }

        Node<T> left = this.head.next;
        Node<T> right = this.tail;

        for (int i = 0; i <= this.count / 2; i++) {
            if (left != null) {
                int cl = compare(value, left.value);
                if ((_ascending && cl == -1) || (!_ascending && cl == 1)) {
                    Node<T> newN = new Node<T>(value);
                    newN.prev = left.prev;
                    newN.next = left;
                    left.prev.next = newN;
                    left.prev = newN;

                    this.count++;
                    return;
                }
                left = left.next;
            }

            int cr = compare(value, right.value);
            if ((_ascending && cr != -1) || (!_ascending && cr != 1)) {
                Node<T> newN = new Node<T>(value);
                newN.prev = right;
                newN.next = right.next;
                if (right.next != null) {
                    right.next.prev = newN;
                } else {
                    this.tail = newN;
                }
                right.next = newN;

                this.count++;
                return;
            }
            right = right.prev;
        }
    }

    // task 7,
    // 6
    // find()
    // O(n)
    public Node<T> find(T val) {
        if (count == 0) {
            return null;
        }

        if ((_ascending && compare(val, this.tail.value) == 1)
                || (_ascending && compare(val, this.head.value) == -1)) {
            return null;
        }

        if ((!_ascending && compare(val, this.head.value) == 1)
                || (!_ascending && compare(val, this.tail.value) == -1)) {
            return null;
        }

        Node<T> node = this.head;

        for (int i = 0; i < this.count(); i++) {
            int cmp = compare(node.value, val);
            if (cmp == 0) {
                return node;
            }

            if ((_ascending && cmp == 1) || (!_ascending && cmp == -1)) {
                return null;
            }

            node = node.next;
        }

        return null;
    }

    // task 7,
    // 4
    // delete()
    // O(n)
    public void delete(T val) {
        Node<T> n = this.find(val);
        if (n == null) {
            return;
        }

        if (n.prev == null) {
            this.head = n.next;
        } else {
            n.prev.next = n.next;
        }

        if (n.next == null) {
            this.tail = n.prev;
        } else {
            n.next.prev = n.prev;
        }

        this.count--;
    }

    public void clear(boolean asc) {
        _ascending = asc;
        // здесь будет ваш код
        this.head = null;
        this.tail = null;
        this.count = 0;
    }

    public int count() {
        return count;
    }

    ArrayList<Node<T>> getAll() // выдать все элементы упорядоченного 
    // списка в виде стандартного списка
    {
        ArrayList<Node<T>> r = new ArrayList<Node<T>>();
        Node<T> node = head;
        while (node != null) {
            r.add(node);
            node = node.next;
        }
        return r;
    }

    public boolean isAscending() {
        return _ascending;
    }
}

