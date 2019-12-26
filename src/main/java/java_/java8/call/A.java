
package java_.java8.call;

/**
 * @author Shuaijun He
 */
public class A {

    public static void main(String[] args) {

        Test t = a -> a;

        Test t2 = a -> {
            int w = a * 2;
            return w * w;
        };

        System.out.println(t.f(200));
        System.out.println(t2.f(200));
        // 静态方法
        Converter<String, Integer> converter = Integer::valueOf;
        Integer converted = converter.convert("123");
        System.out.println(converted);   // 123
        // 对象方法
        String something = "dfgdfJava215";
        Converter<String, Boolean> converter1 = something::startsWith;
        Boolean converted1 = converter1.convert("Java");
        System.out.println(converted1);    // "J"
        // 构造函数
        PersonFactory<Person> personFactory = Person::new;
//        Person person = personFactory.create("帅军", "何");
        Person person = personFactory.createDefault();
        System.out.println(person);
    }
}
