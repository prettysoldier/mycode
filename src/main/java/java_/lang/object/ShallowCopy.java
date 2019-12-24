package java_.lang.object;

/**
 * 浅拷贝，如果是field是不可变对象（比如String、Integer等包装类），是否达到深拷贝一样的效果？
 * 是可以的
 *
 * @author hsj
 * @create 2019/12/24
 */
public class ShallowCopy implements Cloneable{
    String name = "zhangsan";
    Integer age = 1;

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    public static void main(String[] args) throws Exception{
        ShallowCopy shallowCopy = new ShallowCopy();
        ShallowCopy copy = (ShallowCopy)shallowCopy.clone();
        System.out.println(shallowCopy.name == copy.name);
        System.out.println(shallowCopy.age == copy.age);
        copy.name = "李四";
        copy.age = 2;

        System.out.println(shallowCopy);
        System.out.println(copy);
    }

    @Override
    public String toString() {
        return "ShallowCopy{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
