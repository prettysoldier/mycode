package java_.syntax.keyword.final_;

/**
 * 对象引用不能在构造函数中“逸出”
 *  1.如果构造器中this逸出在final赋值之前，可能会导致获取的对象初始化不完整！
 *  2.如果构造器中this逸出在final赋值之后，获取的对象都是初始化完整的！
 *      原因是final重排序规则：对final域的写入与随后将对象引用赋值给一个引用变量，这两个操作之间不能重排序。
 *
 * 另外一个final重排序规则是：初次读一个包含final域的对象引用，与随后初次读这个final域，两个操作之间不能重排序。
 * @author hsj
 * @create 2020/1/16
 */
public class FinalReferenceEscapeExample {
    final int i;
    static FinalReferenceEscapeExample obj;

    public FinalReferenceEscapeExample() {
        obj = this; // 2 this引用在此"逸出"
        i = 1; // 1写final域
    }

    public static void writer() {
        new FinalReferenceEscapeExample();
    }

    public static int reader() {
        if (obj != null) { // 3
            return obj.i; // 4
        }
        return -1;
    }

    public static void main(String[] args) {
        new Thread(()->{
            while(true){
                writer();
            }
        }).start();
        new Thread(()->{
            while(true){
                int i = reader();
                if(i != 1){
                    System.out.println(i);
                }
            }
        }).start();
    }
}