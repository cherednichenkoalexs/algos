
import java.util.*;

public class LinkedList2 {

    public Node head;
    public Node tail;

    public LinkedList2() {
        head = null;
        tail = null;
    }

    public void addInTail(Node _item) {
        if (head == null) {
            this.head = _item;
            this.head.next = null;
            this.head.prev = null;
        } else {
            this.tail.next = _item;
            _item.prev = tail;
        }
        this.tail = _item;
    }

    public Node find(int _value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == _value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;

        while (node != null) {
            if (node.value == _value) {
                nodes.addLast(node);
            }

            node = node.next;
        }

        return nodes;
    }

    public boolean remove(int _value) {
        Node node = this.head;

        while (node != null) {
            if (node.value == _value) {
                this.removeNode(node);

                return true;
            }

            node = node.next;
        }

        return false;
    }

    public void removeAll(int _value) {
        Node node = this.head;

        while (node != null) {
            if (node.value == _value) {
                this.removeNode(node);
            }

            node = node.next;
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public int count() {
        int count = 0;

        Node node = this.head;
        while (node != null) {
            count += 1;

            node = node.next;
        }

        return count;
    }

    public void insertAfter(Node _nodeAfter, Node _nodeToInsert) {
        if (_nodeAfter == null) {
            if (this.head == null) {
                this.head = this.tail = _nodeToInsert;
                return;
            }

            if (this.head.next == null) {
                this.tail = this.head;
            }

            _nodeToInsert.next = this.head;
            this.head.prev = _nodeToInsert;
            this.head = _nodeToInsert;

            return;
        }

        Node node = this.head;

        while (node != null) {
            if (node.value == _nodeAfter.value && node.next == _nodeAfter.next) {
                _nodeToInsert.next = node.next;
                _nodeToInsert.prev = node;

                if (_nodeToInsert.next == null) {
                    this.tail = _nodeToInsert;
                } else {
                    _nodeToInsert.next.prev = _nodeToInsert;
                }

                node.next = _nodeToInsert;

                return;
            }

            node = node.next;
        }
    }

    private void removeNode(Node toDelete) {
        if (toDelete.next == null && toDelete.prev == null) {
            this.head = null;
            this.tail = null;

            return;
        }

        if (toDelete.next == null) {
            this.tail = toDelete.prev;
            toDelete.prev.next = null;
        } else {
            toDelete.next.prev = toDelete.prev;
        }

        if (toDelete.prev == null) {
            this.head = toDelete.next;
            toDelete.next.prev = null;
        } else {
            toDelete.prev.next = toDelete.next;
        }
    }

    public String asString() {
        String result = "(";
        Node node = this.head;

        while (node != null) {
            result = result.concat(String.valueOf(node.value));
            if (node.next != null) {
                result = result.concat(",");
            }
            node = node.next;
        }

        return result.concat(")");
    }
}

class Node {

    public int value;
    public Node next;
    public Node prev;

    public Node(int _value) {
        value = _value;
        next = null;
        prev = null;
    }
}

