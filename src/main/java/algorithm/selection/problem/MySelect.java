package algorithm.selection.problem;

/**
 * @author Shuaijun He
 */
public abstract class MySelect {

    protected abstract <T extends Comparable<? super T>> T select(T[] a, int k);

    public <T extends Comparable<? super T>> void selectWithLog(T[] a, int k) {

        if (Client.log) {
            for (T i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }

        long start = System.currentTimeMillis();
        T t = this.select(a, k);
        if (Client.log) {
            System.out.println("第" + k + "小元素是：" + t);
        }
        long spent = System.currentTimeMillis() - start;
        System.out.println(this.getClass() + "花费：" + spent + "ms");

        if (Client.log) {
            for (T i : a) {
                System.out.print(i + ", ");
            }
            System.out.println();
        }
    }

}
