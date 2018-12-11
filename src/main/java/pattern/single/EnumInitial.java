
package pattern.single;

import java.util.HashMap;
import java.util.Map;

/**
 * 枚举的初始化问题
 *
 * @author Shuaijun He
 */
public class EnumInitial {
    public static void main(String[] args) {
        PowerOfTwo i = PowerOfTwo.fromInt(2);
        System.out.println(i);
    }
}

/**
 * Java语言规范第三版8.9规定了enum里的构造器、初始化器和初始化块中不得引用该enum中非编译时常量的静态成员域。
 * Java枚举类型中的枚举成员是静态成员，它们会首先被静态初始化；其它成员都只能在枚举成员之后声明，如果通过初始化器（如上例）来初始化的话，
 * 则开始初始化RED时静态变量colorMap尚未被赋值。于是遇到NullPointerException。
 * <B>以下是一种隐藏的错误！！</B>
 *
 * @author Shuaijun He
 */
enum PowerOfTwo {
    ONE(1),
    TWO(2),
    FOUR(4),
    EIGHT(8);

    private int value;

    PowerOfTwo(int value) {
        this.value = value;
        this.registerValue(); //map.put(value, this);
    }

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    private void registerValue() {
        PowerOfTwo.map.put(this.value, this);
    }

    public static PowerOfTwo fromInt(int i) {
        return PowerOfTwo.map.get(i);
    }

    private static final Map<Integer, PowerOfTwo> map = new HashMap<>();
}