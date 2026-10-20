
import java.util.*;

public class TestLinkedList2 {

    static void testAsString() {
        LinkedList2 list = generateList();
        assert list.asString().equals("(0,1,2,3,4,0,1,2,3,4)");
    }

    static void testRemove() {
        LinkedList2 list = generateList();
        list.remove(1);
        assert list.asString().equals("(0,2,3,4,0,1,2,3,4)");

        list.remove(1);
        assert list.asString().equals("(0,2,3,4,0,2,3,4)");

        list.remove(0);
        assert list.asString().equals("(2,3,4,0,2,3,4)");
        assert list.head.value == 2;

        list.remove(4);
        list.remove(4);
        assert list.asString().equals("(2,3,0,2,3)");
        assert list.tail.value == 3;
        assert list.tail.next == null;

        list.remove(5);
        assert list.asString().equals("(2,3,0,2,3)");
        assert list.tail.value == 3;
        assert list.tail.next == null;

        LinkedList2 list1 = new LinkedList2();

        list1.addInTail(new Node(1));
        list1.removeAll(1);
        assert list1.head == null;
        assert list1.tail == null;
        assert list1.asString().equals("()");
    }

    static void testRemoveAll() {
        LinkedList2 list = generateList();
        list.removeAll(1);
        assert list.asString().equals("(0,2,3,4,0,2,3,4)");

        list.removeAll(0);
        assert list.asString().equals("(2,3,4,2,3,4)");
        assert list.head.value == 2;

        list.removeAll(4);
        assert list.asString().equals("(2,3,2,3)");
        assert list.tail.value == 3;

        list.removeAll(5);
        assert list.asString().equals("(2,3,2,3)");
        assert list.tail.value == 3;

        LinkedList2 list1 = new LinkedList2();

        list1.addInTail(new Node(1));
        list1.addInTail(new Node(1));
        list1.removeAll(1);
        assert list1.head == null;
        assert list1.tail == null;
        assert list1.asString().equals("()");

    }

    static void testClear() {
        LinkedList2 list = generateList();
        list.clear();
        assert list.count() == 0;
        assert list.head == null;
        assert list.tail == null;
        assert list.asString().equals("()");
    }

    static void testFindAll() {
        LinkedList2 list = generateList();

        assert list.findAll(2).size() == 2;
    }

    static void testCount() {
        LinkedList2 list = generateList();
        assert list.count() == 10;

        list.removeAll(1);
        assert list.count() == 8;

        list.removeAll(1);
        assert list.count() == 8;

        list.clear();
        assert list.count() == 0;
    }

    static void testInsertAfter() {
        LinkedList2 list = generateList();
        ArrayList<Node> nodes = list.findAll(4);

        list.insertAfter(nodes.get(0), new Node(10));
        assert list.count() == 11;
        assert list.asString().equals("(0,1,2,3,4,10,0,1,2,3,4)");

        list.insertAfter(nodes.get(0), new Node(11));
        assert list.count() == 12;
        assert list.asString().equals("(0,1,2,3,4,11,10,0,1,2,3,4)");

        list.insertAfter(nodes.get(1), new Node(20));
        assert list.count() == 13;
        assert list.asString().equals("(0,1,2,3,4,11,10,0,1,2,3,4,20)");

        list.insertAfter(nodes.get(1), new Node(22));
        assert list.count() == 14;
        assert list.asString().equals("(0,1,2,3,4,11,10,0,1,2,3,4,22,20)");

        LinkedList2 newList = new LinkedList2();
        newList.insertAfter(null, new Node(2));
        assert newList.head == newList.tail;
        assert newList.head.prev == newList.tail.prev;
        assert newList.head.next == newList.tail.next;
        assert newList.asString().equals("(2)");

    }

    static void runTests() {
        boolean enabled = false;
        assert enabled = true;
        if (!enabled) {
            System.err.println("run with -ea flag");
            System.exit(2);
        }

        testAsString();
        testRemove();
        testRemoveAll();
        testClear();
        testFindAll();
        testCount();
        testInsertAfter();
        System.out.println("all LinkedList2 tests clear");
    }

    public static void main(String[] args) {
        runTests();
    }

    static LinkedList2 generateList() {
        LinkedList2 list = new LinkedList2();
        for (int i = 0; i < 2; i++) {
            for (int b = 0; b < 5; b++) {
                Node node = new Node(b);
                list.addInTail(node);
            }
        }

        return list;
    }

}

