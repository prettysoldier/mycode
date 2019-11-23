package java_.object;

/**
 * @author heshuaijun
 * @date 2019/11/23 23:47
 */
public class CallByValueTest {

    public static void main (String[] args) {

        howToProveJavaIsCallByValue();
    }
    /**
     * 如何证明Java是按值传递的，而不是按引用传递的！
     * 交换逻辑并没有实现。
     */
    private static void howToProveJavaIsCallByValue(){
        A a1 = new A("a1");
        A a2 = new A("a2");
        swap(a1, a2);
        System.out.println(a1 + ", " + a2);
    }

    private static void swap(A a1, A a2){
        A temp = a1;
        a1 = a2;
        a2 = temp;
    }

    static class A{
        String name;

        private A (String name) {
            this.name = name;
        }

        @Override
        public String toString () {
            return this.name;
        }
    }
}
