import java.lang.reflect.Array;

public class DynArray<T> {

    public T[] array;
    public int count;
    public int capacity;
    Class clazz;

    public DynArray(Class clz) {
        clazz = clz; // нужен для безопасного приведения типов
        // new DynArray<Integer>(Integer.class);

        count = 0;
        makeArray(16);
    }

    /**
     * task 3,
     *  1,nt
     *  makeArray
     *  O(1)
     */
    public void makeArray(int new_capacity) {
        T[] newArray = (T[]) Array.newInstance(this.clazz, new_capacity);

        if (this.array != null) {
            System.arraycopy(this.array, 0, newArray, 0, this.count);
        }

        this.array = newArray;
        this.capacity = new_capacity;
    }

    /**
     * task 3,
     *  1,
     *  getItem
     *  O(1)
     */
    public T getItem(int index) {
        if (index < 0 || index > count - 1) {
            throw new IndexOutOfBoundsException("index out of range");
        }

        return this.array[index];
    }

    /**
     * task 3,
     *  1,
     *  append
     *  O(1)
     */
    public void append(T itm) {
        if (this.count == this.capacity) {
            makeArray(this.capacity * 2);
        }

        this.array[this.count] = itm;
        this.count++;
    }

    /**
     * task 3,
     *  2,
     *  insert
     *  O(n)
     */
    public void insert(T itm, int index) {
        if (index < 0 || index > count) {
            throw new IndexOutOfBoundsException("index out of range");
        }

        if (this.count == this.capacity) {
            int newCapacity = this.capacity * 2;
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
    }

    /**
     * task 3,
     *  3,
     *  remove
     *  O(n)
     */
    public void remove(int index) {
        if (index < 0 || index > count - 1) {
            throw new IndexOutOfBoundsException("index out of range");
        }
        

        if (this.capacity != 16 && (this.count - 1) * 2 < this.capacity) {
            int newCapacity = (int) (this.capacity / 1.5);
            newCapacity = newCapacity >= 16 ? newCapacity : 16;

            T[] newArray = (T[]) Array.newInstance(this.clazz, newCapacity);
            System.arraycopy(this.array, 0, newArray, 0, index);
            System.arraycopy(this.array, index + 1, newArray, index, this.count - (index + 1));
            this.array = newArray;
            this.capacity = newCapacity;
        } else {
            T movedItm = null;

            for (int i = 0; i < this.count - 1; i++) {
                if (i < index) {
                    continue;
                }

                movedItm = this.array[i + 1];
                this.array[i] = movedItm;
            }
        }

        this.count--;
    }

}
