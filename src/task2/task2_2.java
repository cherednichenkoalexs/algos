/**
 *  task 2,
 *  9,
 *  flip_list
 *  O(n)
 * */
public class LisFlipper {
    public static void flipp(LinkedList2 list) {
        Node node = list.head;
        Node tmp = null;
        while (node != null) {
            tmp = node.next;
            node.next = node.prev;
            node.prev = tmp; 
            node = tmp;
        }
        tmp = list.head;
        list.head = list.tail;
        list.tail = tmp;
    }
}

/**
 *  task 2,
 *  10,
 *  find_loop
 *  O(n)
 * */
public class FindLoop {
    public static boolean isLooped(LinkedList2 list) {
      if (list.head.prev == list.tail || list.tail.next == list.head) {
        return true;
      }

      Node node = list.head;
      
      while (node != null && node.next != null){
        if(node.next.prev != node) {
            return true;
        }
        node = node.next;
      }

      return false;
    }
}

/**
 *  task 2,
 *  11,
 *  sort
 *  O(n^2)
 * */
public class SortList {
    public static void sort(LinkedList2 list) {
        Node step = list.head;
      
      while (step != null){
        Node node = step;
        while (node.prev != null && node.prev.value > node.value) {
            int tmpValue = node.value;
            node.value = node.prev.value;
            node.prev.value = tmpValue;
            node = node.prev;
        }
        step = step.next;
      }
    }
}

/**
 *  task 2,
 *  12,
 *  merge sorted
 *  O(n^2)
 * */
public class MergeSortedLists {
    public static LinkedList2 sortAndMerge(LinkedList2 list1, LinkedList2 list2) {
        SortList.sort(list1);
        SortList.sort(list2);

        Node pointer1 = list1.head;
        Node pointer2 = list2.head;

        LinkedList2 result = new LinkedList2();

        while (pointer1 != null && pointer2 != null) {
            if(pointer1.value >= pointer2.value) {
                result.addInTail(new Node(pointer2.value));
                pointer2 = pointer2.next;
            } else {
                result.addInTail(new Node(pointer1.value));
                pointer1 = pointer1.next;
            }
        }

        while (pointer1 != null) {
            result.addInTail(new Node(pointer1.value));
            pointer1 = pointer1.next;
        }

        while (pointer2 != null) {
            result.addInTail(new Node(pointer2.value));
            pointer2 = pointer2.next;
        }

        return result;
    }
}
/**
 *  task 2,
 *  13,
 *  dummy node
 *  -
 * */
public class DummyNode{
    public int value;
    public Node next;
    public Node prev;

    public DummyNode(int _value) {
        value = _value;
        next = null;
        prev = null;
    }

    public boolean isDummy() {
        return true;
    }
}

