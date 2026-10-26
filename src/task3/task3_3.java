import java.lang.reflect.Array;

public class TestDynArray {

    public static void testGetItem() {
        DynArray arr = generate();

        try {
            arr.getItem(-10);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }

        try {
            arr.getItem(10);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }

        assert arr.getItem(0).equals(0);
        assert arr.getItem(9).equals(9);
        assert arr.getItem(5).equals(5);
    }

    public static void testAppend() {
        DynArray arr = generate();

        for (int i = 0; i < 10; i++) {
            arr.append(i + 10);
        }

        assert arr.capacity == 32;
        assert arr.count == 20;
        assert arr.getItem(10).equals(10);
        assert arr.getItem(19).equals(19);
        assert arr.getItem(0).equals(0);

        try {
            arr.getItem(-10);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }

        try {
            arr.getItem(20);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }
    }

    public static void testInsert() {
        DynArray arr = generate();

        for (int i = 0; i < 10; i++) {
            arr.append(i + 10);
        }

        arr.insert(20, 20);
        assert arr.getItem(20).equals(20);

        arr.insert(100, 0);
        assert arr.getItem(0).equals(100);
        assert arr.getItem(20).equals(19);

        try {
            arr.getItem(-10);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }

        try {
            arr.getItem(22);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }

        assert arr.count == 22;
        assert arr.capacity == 32;
    }

    public static void testRemove() {
        DynArray arr = generate();

        for (int i = 0; i < 10; i++) {
            arr.append(i + 10);
        }

        arr.remove(19);

        try {
            arr.getItem(19);
            assert false : "throwable must be here";
        } catch (Throwable e) {
            assert e.getMessage().equals("index out of range");
        }

        assert arr.getItem(18).equals(18);

        arr.remove(0);
        assert arr.getItem(0).equals(1);
        assert arr.getItem(17).equals(18);
        assert arr.count == 18;
        for (int i = 0; i < 5; i++) {
            arr.remove(i);
        }

        assert arr.count == 13;
        assert arr.capacity == 21;

        for (int i = 0; i < 6; i++) {
            arr.remove(i);
        }

        assert arr.count == 7;
        assert arr.capacity == 16;
    }

    static void runTests() {
        boolean enabled = false;
        assert enabled = true;
        if (!enabled) {
            System.err.println("run with -ea flag");
            System.exit(2);
        }

        testGetItem();
        testAppend();
        testInsert();
        testRemove();

        System.out.println("all dynArray tests clear");
    }

    /**
     * task 3,
     *  5,
     *  remove
     *  O(n)
     */
    public static void main(String[] args) {
        runTests();
    }

    private static DynArray generate() {
        DynArray arr = new DynArray(Integer.class);

        arr.append(0);
        arr.append(1);
        arr.append(2);
        arr.append(3);
        arr.append(4);
        arr.append(5);
        arr.append(6);
        arr.append(7);
        arr.append(8);
        arr.append(9);

        assert arr.getItem(9).equals(9);

        return arr;
    }
}

