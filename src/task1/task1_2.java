
/**
 *  task 1,
 *  8,
 *  list_adder
 *  O(n)
 * */
public class ListAdder {

    public LinkedList sumLists(LinkedList l1, LinkedList l2) {
        LinkedList newList = new LinkedList();

        Node n1 = l1.head;
        Node n2 = l2.head;

        while (n1 != null && n2 != null) {
            newList.addInTail(new Node(n1.value + n2.value));
            n1 = n1.next;
            n2 = n2.next;
        }

        if (n1 != n2) {
            return null;
        }

        return newList;
    }
}

