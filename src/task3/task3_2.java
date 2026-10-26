import java.util.*;
import java.lang.reflect.Array;

    /**
     * task 3,
     *  6,
     *  dynamic array with bank method
     *  -
     */
 class BankDynArray<T> {

    public T[] array;
    public int count;
    public int capacity;
    public int credit;
    Class clazz;

    public BankDynArray(Class clz) {
        clazz = clz;
        // new DynArray<Integer>(Integer.class);
        credit = 16;
        count = 0;
        makeArray(16);
    }

    /**
     * O(1)
     */
    private boolean isCreditEnoughForeAllocation(int newCapacity) {
        return credit >= Integer.highestOneBit(newCapacity);
    }

    /**
     * O(n)
     */
    public void makeArray(int new_capacity) {
        T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);

        if (this.array != null) {
            System.arraycopy(this.array, 0, newArray, 0, this.count);
        }

        this.array = newArray;
        this.capacity = new_capacity;
        this.credit = this.credit - (Integer.highestOneBit(new_capacity));
    }

    /**
     * O(1)
     */
    public T getItem(int index) throws Throwable {
        if (index < 0 || index >= this.count) {
            throw new Throwable("index out of range");
        }

        return this.array[index];
    }

    public void append(T itm) {
        int newCapacity = this.capacity * 2;
        if (this.count == this.capacity) {
            makeArray(newCapacity);
        }

        this.array[this.count] = itm;
        this.count++;
        credit += 3;

        newCapacity = this.capacity * 2;
        if (this.isCreditEnoughForeAllocation(newCapacity)) {
            makeArray(newCapacity);
        }
    }

    /**
     * O(n) worst O(1) avg
     */
    public void insert(T itm, int index) throws Throwable {
        if (index < 0 || index > count) {
            throw new Throwable("index out of range");
        }

        credit += 3;
        int newCapacity = this.capacity * 2;

        if (this.count == this.capacity) {
            T[] newArray = (T[]) Array.newInstance(this.clazz, newCapacity);
            if (index == 0) {
                newArray[index] = itm;
                System.arraycopy(this.array, 0, newArray, 1, this.count);
            } else {
                System.arraycopy(this.array, 0, newArray, 0, index);
                newArray[index] = itm;
                System.arraycopy(this.array, index, newArray, index + 1, this.count - index);
            }
            this.array = newArray;
            this.capacity = newCapacity;
            this.count++;

            this.credit = this.credit - (Integer.highestOneBit(newCapacity));

            return;
        }

        this.count++;
        T movedItm = null;

        for (int i = 0; i < this.count; i++) {
            if (i < index) {
                continue;
            }

            if (i == index) {
                movedItm = this.array[i];
                this.array[i] = itm;
                continue;
            }

            T helper = this.array[i];
            this.array[i] = movedItm;
            movedItm = helper;
        }

        newCapacity = this.capacity * 2;

        if (this.isCreditEnoughForeAllocation(newCapacity)) {
            makeArray(newCapacity);
        }
    }

    /**
     * O(n)
     */
    public void remove(int index) throws Throwable {
        if (index < 0 || index > count - 1) {
            throw new Throwable("index out of range");
        }
        credit += 3;

        int newCapacity = (int) (this.capacity / 1.5);
        newCapacity = newCapacity >= 16 ? newCapacity : 16;

        if (this.capacity != 16 && (this.count - 1) * 2 < this.capacity && this.isCreditEnoughForeAllocation(newCapacity)) {
            T[] newArray = (T[]) Array.newInstance(this.clazz, newCapacity);
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index + 1, newArray, index, this.count - (index + 1));
            this.array = newArray;
            this.capacity = newCapacity;
            this.credit = this.credit - (Integer.highestOneBit(newCapacity));
        } else {
            for (int i = 0; i < this.count - 1; i++) {
                if (i < index) {
                    continue;
                }

                T movedItm = this.array[i + 1];
                this.array[i] = movedItm;
            }
        }

        this.count--;
    }

}

    /**
     * task 3,
     *  7,
     *  dynamik array with multiple dimensions
     *  -
     */
 class MultiDynArray<T> {

    private int[] sizes;
    private DynArray<?> root;
    Class clazz;

    /**
     * O(n + d), where d - num fo dymensions
     */
    public MultiDynArray(Class clz, int... dimensions) throws Throwable {
        if (dimensions == null || dimensions.length == 0) {
            throw new Throwable("at least one dimension required");
        }

        for (int d : dimensions) {
            if (d < 0) {
                throw new Throwable("dimension size must be >= 0");
            }
        }

        this.clazz = clz;
        this.sizes = Arrays.copyOf(dimensions, dimensions.length);
        this.root = newDimension(0);
        fill(this.root, this.sizes[0]);
    }

    /**
     * O(d)
     */
    public T getItem(int... ind) throws Throwable {
        checkInd(ind);
        for (int k = 0; k < ind.length; k++) {
            if (ind[k] < 0 || ind[k] >= sizes[k]) {
                throw new Throwable("index out of range");
            }
        }

        DynArray<?> node = root;
        for (int k = 0; k < ind.length; k++) {
            if (ind[k] >= node.count) {
                return null;
            }
            Object item = node.array[ind[k]];
            if (k == ind.length - 1) {
                return (T) item;
            }
            if (item == null) {
                return null;
            }
            node = (DynArray<?>) item;
        }
        return null;
    }

    /**
     * O(d)
     */
    public void setItem(T value, int... ind) throws Throwable {
        checkInd(ind);
        for (int index : ind) {
            if (index < 0) {
                throw new Throwable("index out of range");
            }
        }

        for (int k = 0; k < ind.length; k++) {
            if (ind[k] >= sizes[k]) {
                sizes[k] = ind[k] + 1;
            }
        }

        DynArray<?> node = root;
        for (int k = 0; k < ind.length; k++) {
            fill(node, ind[k] + 1);

            if (k == ind.length - 1) {
                ((Object[]) node.array)[ind[k]] = value;
                return;
            }

            Object child = node.array[ind[k]];
            if (child == null) {
                child = newDimension(k + 1);
                ((Object[]) node.array)[ind[k]] = child;
            }
            node = (DynArray<?>) child;
        }
    }

    /**
     * O(1)
     */
    private void checkInd(int[] ind) throws Throwable {
        if (ind == null || ind.length != sizes.length) {
            throw new Throwable("wrong number of indexes");
        }
    }

    /**
     * O(1)
     */
    private DynArray<?> newDimension(int level) {
        boolean isLeaf = level == sizes.length - 1;
        return new DynArray<Object>(isLeaf ? clazz : Object.class);
    }

    /**
     * O(n)
     */
    private static void fill(DynArray<?> node, int minCount) {
        DynArray<Object> n = (DynArray<Object>) node;
        while (n.count < minCount) {
            n.append(null);
        }
    }
}

// Рефлексия о задаче 1.8 : предложенный метод эффективнее по утилизации ресурсов, чем тот,
//  * что предложил я, однако мой метод быстрее, за счёт того, что count() не
//  * вызывается вовсе и происходит переобход только меньшего из листов при этом
//  * сложность в любом варианте растёт линейно, просто с разным угловым
//  * коэффициентом. Чтобы взять лучшее из двух миров можно в объект добавить
//  * свойство int count и сразу сравнивать их, не переобходя каждый раз список

