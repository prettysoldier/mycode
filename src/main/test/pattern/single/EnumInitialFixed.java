
package main.test.pattern.single;

import java.util.HashMap;
import java.util.Map;

/**
 * Demo的修正版
 *
 * @author Shuaijun He
 */
public class EnumInitialFixed {
    public static void main(String[] args) {
        PowerOfTwo1 i = PowerOfTwo1.fromInt(2);
        System.out.println(i);
    }
}

enum PowerOfTwo1 {
    ONE(1),
    TWO(2),
    FOUR(4),
    EIGHT(8);

    private int value;

    PowerOfTwo1(int value) {
        this.value = value;
    }

    private static final Map<Integer, PowerOfTwo1> map = new HashMap<>();

    @Override
    public String toString() {
        return Integer.toString(this.value);
    }

    public static PowerOfTwo1 fromInt(int i) {
        return PowerOfTwo1.map.get(i);
    }

    static {
        for (PowerOfTwo1 p : PowerOfTwo1.values()) {
            PowerOfTwo1.map.put(p.value, p);
        }
    }

}