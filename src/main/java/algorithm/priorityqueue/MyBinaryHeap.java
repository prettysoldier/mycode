package algorithm.priorityqueue;

/**
 * @author Shuaijun He
 * @param <T>
 */
public class MyBinaryHeap<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        Integer[] arr = { 3, 7, 1, 5, 2, 4, 8 };
        MyBinaryHeap<Integer> heap = new MyBinaryHeap<>(arr);
        int i = arr.length;
        while (i-- > 0) {
            System.out.print(heap.deleteMin() + ", ");
        }
    }

    @SuppressWarnings("unused")
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;
    private int currentSize;

    public MyBinaryHeap() {
        // TODO
    }

    public MyBinaryHeap(int capacity) {
        // TODO
    }

    @SuppressWarnings("unchecked")
    public MyBinaryHeap(T[] items) {
        this.currentSize = items.length;
        this.array = (T[]) new Comparable[(this.currentSize + 2) * 11 / 10];
        int i = 1;
        for (T item : items) {
            this.array[i++] = item;
        }
        this.buildHeap();
    }

    /**
     * 构建堆
     *
     * @author Shuaijun He
     */
    private void buildHeap() {
        // 拥有子节点的最后一个父节点
        for (int i = this.currentSize / 2; i > 0; i--) {
            this.percolateDown(i);
        }
    }

    /**
     * 下虑
     *
     * @author Shuaijun He
     * @param hole
     */
    private void percolateDown(int hole) {
        int child;
        T tmp = this.array[hole];
        for (; hole * 2 <= this.currentSize; hole = child) {
            child = hole * 2;
            if (child != this.currentSize
                && this.array[child + 1].compareTo(this.array[child]) < 0) {
                child++;
            }
            if (this.array[child].compareTo(tmp) < 0) {
                this.array[hole] = this.array[child];
            } else {
                break;
            }
        }
        this.array[hole] = tmp;
    }

    public T deleteMin() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T min = this.findMin();
        this.array[1] = this.array[this.currentSize--];
        this.percolateDown(1);
        return min;
    }

    private T findMin() {
        return this.array[1];
    }

    private boolean isEmpty() {
        return this.currentSize <= 0;
    }


}
