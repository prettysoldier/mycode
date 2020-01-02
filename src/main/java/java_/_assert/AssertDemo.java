package java_._assert;

/**
 *
 *
 * 什么时候用 assert？
 *
 * 答：assertion(断言)在软件开发中是一种常用的调试方式。一般来说，assertion 用于保证程序最基本、关键的正确性。
 * 通常在开发和测试时开启。为了提高性能，在软件发布后，assertion 检查通常是关闭的。
 * 另外，断言不应该以任何方式改变程序的状态，即不依赖断言实现任何逻辑。
 * 启动和禁用断言是类加载器的功能！当断言被禁止时，类加载器跳过断言代码。
 * 默认情况下，断言是禁用的。可使用-ea -enableassertions 打开。
 * 断言可以用于验证传递给私有方法的参数。
 *      （不过，断言不应该用于验证传递给公有方法的参数，因为不管是否启用了断言，公有方法都必须检查其参数。
 *      不过，既可以在公有方法中，也可以在非公有方法中利用断言测试后置条件。）
 *
 *
 * 断言语法：
 * 在实现中，断言是一个包含布尔表达式的语句， 在执行这个语句时假定该表达式为 true；如果表达式计算为 false，
 * 那么系统会报告一个 Assertionerror。
 * 断言可以有两种形式：
 * assert Expression1 ;
 * assert Expression1 : msg ;
 * Expression1 应该总是产生一个布尔值。 msg 用于生成显示更多调试信息的 String 消息。
 *
 * 要在编译时启用断言，需使用  source 1.4 标记 ： javac -source 1.4 Test.java
 * 要在运行时启用断言，可使用 -enableassertions 或者 -ea 标记。
 * - 要在运行时选择禁用断言，可使用 -da 或者 -disableassertions 标记。
 * - 要在系统类中启用断言，可使用 -esa 或者 -dsa 标记。
 * - 还可以在类或者包的级别上启用或者禁用断言：
 *      - 要使一个包中的所有子包中的断言能够有效或无效，在包名后加上三个点：-ea/-da:java_._assert...
 *      - 类：-ea:java_._assert.AssertDemo
 *

 *
 * @author heshuaijun
 * @date 2019/11/15 23:03
 */
public class AssertDemo {
    public static void main (String[] args) {
        assert 1 > 0;

        System.out.println("大师傅");

        assert false : "asdf";
        System.out.println("--");
    }
}
