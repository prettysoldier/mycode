package java_.syntax.exception._extends;

/**
 * 子类方法抛出的异常范围应该更小！更精确！
 * 父类抛出已检查异常，子类可以不抛出任何已检查异常！
 * 父类方法不抛出已检查异常，子类也不能抛出已检查异常！
 *
 * 以上三点，我认为都是为了子类自由地向上转型！！
 * @author hsj
 * @create 2019/12/26
 */
public class Child extends Parent {

    @Override
    void f() {

    }
}
