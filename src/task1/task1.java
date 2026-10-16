import java.util.*;

public class LinkedList {

    public Node head;
    public Node tail;

    public LinkedList() {
        head = null;
        tail = null;
    }

    public void addInTail(Node item) {
        if (this.head == null) {
            this.head = item;
        } else {
            this.tail.next = item;
        }
        this.tail = item;
    }

    public Node find(int value) {
        Node node = this.head;
        while (node != null) {
            if (node.value == value) {
                return node;
            }
            node = node.next;
        }
        return null;
    }

    public boolean remove(int _value) {
        Node node = this.head;
        Node prev = null;

        while (node != null) {
            if(node.value == _value) {
                this.removeNode(prev, node);
                
                return  true;
            } else {
                prev = node;
            }

            node = node.next;
        }

        return false;
    }

    public void removeAll(int _value) {
        Node node = this.head;
        Node prev = null;

        while (node != null) {
            if(node.value == _value) {
                this.removeNode(prev, node);
            } else {
                prev = node;
            }

            node = node.next;
        }
    }

    public void clear() {
        this.head = null;
        this.tail = null;
    }

    public ArrayList<Node> findAll(int _value) {
        ArrayList<Node> nodes = new ArrayList<Node>();
        Node node = this.head;

        while (node != null) {
            if(node.value == _value) {
                nodes.addLast(node);
            } 

            node = node.next;
        } 

        return nodes;
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
            if (this.head == null || this.head.next == null) {
                this.tail = _nodeToInsert;
            }
            _nodeToInsert.next = this.head;
            this.head = _nodeToInsert;

            return;
        }

        Node node = this.head;

        while (node != null) {
            if(node.value == _nodeAfter.value && node.next == _nodeAfter.next) {
                _nodeToInsert.next = node.next;
                node.next = _nodeToInsert;

                if (_nodeToInsert.next == null) {
                    this.tail = _nodeToInsert;
                }

                return;
            }

            node = node.next;
        }   
    }

    private void removeNode(Node prev, Node toDelete) {
        if (toDelete.next == null) {
            this.tail = prev;
        }
        
        if(prev == null) {
            this.head = toDelete.next;

            return;
        }

        prev.next = toDelete.next;
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

    public Node(int _value) {
        value = _value;
        next = null;
    }
}

