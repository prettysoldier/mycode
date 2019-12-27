package algorithm.priorityqueue;

/**
 * BinaryHeap 二叉堆是一种特殊的完全二叉树，它的每个节点的key值要大于它的父亲节点。所以最小的节点永远是根节点。
 * 又由于它是一棵完全二叉树，因此用一维数组来实现。
 * 数组的下标能够映射出节点间的父子关系，其中节点i的父节点的下标为floor(i / 2), i的左右孩子节点分别为2i与2i+1。
 * 为了映射出这种关系，在实现一维数组的时候数组的第一个元素为空，因为下标0将打破这种映射关系。
 * @see <a href="https://blog.csdn.net/gaobohello1987/article/details/43018995">PriorityQueue的BinaryHeap实现</a>
 *
 * @author Shuaijun He
 * @param <T>
 */
public class MyBinaryHeap<T extends Comparable<? super T>> {

    public static void main(String[] args) {
        Integer[] arr = { 3, 7, 1, 5, 2, 4, 8 };
        // 通过数组，创建二进制堆
        MyBinaryHeap<Integer> heap = new MyBinaryHeap<>(arr, false);
        int i = arr.length;
        while (i-- > 0) {
            System.out.print(heap.deleteTop() + ", ");
        }
    }

    private T[] array;
    private int currentSize;
    private boolean bigTop;

    public MyBinaryHeap(T[] items) {
        this(items, false);
    }

    public MyBinaryHeap(T[] items, boolean bigTop) {
        this.currentSize = items.length;
        this.bigTop = bigTop;
        this.array = (T[]) new Comparable[this.currentSize + 1];
        // 注意这里是从1开始，为了便于后面的索引计算
        int i = 1;
        for (T item : items) {
            this.array[i++] = item;
        }
        this.buildHeap();
    }



    public T deleteTop() {
        if (this.isEmpty()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        T min = this.array[1];
        this.array[1] = this.array[this.currentSize--];
        this.percolateDown(1);
        return min;
    }


    /**
     * 构建堆
     *
     * @author Shuaijun He
     */
    private void buildHeap() {
        /**
         * this.currentSize / 2 :拥有子节点的最后一个父节点
         * 只有非叶子节点才需要移动调整
         */
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
        // 当前节点作为父节点
        T parent = this.array[hole];

        for (; hole * 2 <= this.currentSize;) {
            // hole * 2 是左子节点
            child = hole * 2;
            /**
             * 左子节点不是最后一个，且右子节点比左子节点小，就把child加1，变成右子节点的下标
             * 也就是找出子节点中较小的节点
             */
            if (child != this.currentSize
                    && (!bigTop && this.array[child + 1].compareTo(this.array[child]) < 0
                    || bigTop && this.array[child + 1].compareTo(this.array[child]) > 0 )) {
                child++;
            }
            /**
             * 如果子节点中较小的节点比父节点还小，就把子节点赋值给父节点
             */
            if (!bigTop && this.array[child].compareTo(parent) < 0
                    || bigTop && this.array[child].compareTo(parent) > 0 ) {
                this.array[hole] = this.array[child];
                /**
                 * hole指向较小的节点，继续向下判断，直至达到总大小
                 */
                hole = child;
            } else {
                /**
                 * 父节点比子节点小就直接退出
                 */
                break;
            }
        }
        this.array[hole] = parent;
    }

    private boolean isEmpty() {
        return this.currentSize <= 0;
    }


}
