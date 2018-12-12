package closure;

/**
 * 闭包就是能够读取其他函数内部变量的函数。例如在javascript中，只有函数内部的子函数才能读取局部变量，
 * 所以闭包可以理解成“定义在一个函数内部的函数“。在本质上，闭包是将函数内部和函数外部连接起来的桥梁。
 * @author Shuaijun He
 */
public class JavaClosure {



    protected Inner outer() {
        int n = 10;

        Inner p = ()->{
//          n++;// java 无法完全模拟闭包，编译不过
            System.out.println(n);
        };
        return p;
    }

}
interface Inner {
    void inner();
}



