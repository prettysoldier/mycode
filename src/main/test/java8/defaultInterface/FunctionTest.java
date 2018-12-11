
package main.test.java8.defaultInterface;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import main.test.java8.call.Person;

/**
 * @author Shuaijun He
 */
public class FunctionTest {

    public static void main(String[] args) {

        Predicate<String> predicate = (s) -> {
            if (s == null) {
                return false;
            }
            return s.length() > 0;
        };

        System.out.println(predicate.test("foo"));
        System.out.println(predicate.test(null));
        predicate.negate().test("foo");     // false
        System.out.println(predicate.negate().test("foo"));
        Predicate<String> isEmpty = String::isEmpty;
        System.out.println(isEmpty.negate().test(""));

        Function<String, Integer> toInteger = Integer::valueOf;
        Function<String, String> backToString = toInteger
            .andThen(String::valueOf);
        backToString.apply("123");     // "123"

        Supplier<A> personSupplier = A::new;
        personSupplier.get();   // new Person

        Consumer<Person> greeter = (p) -> System.out.println("Hello, "
            + p.getFirstName());
        greeter.accept(new Person("Luke", "Skywalker"));

        Comparator<Person> comparator = (p1, p2) -> p1.getFirstName()
            .compareTo(p2.getFirstName());
        Person p1 = new Person("John", "Doe");
        Person p2 = new Person("Alice", "Wonderland");
        comparator.compare(p1, p2);             // > 0
        comparator.reversed().compare(p1, p2);  // < 0

        Optional<String> optional = Optional.of("bam");
        optional.isPresent();           // true
        optional.get();                 // "bam"
        optional.orElse("fallback");    // "bam"
        // 如果s不等于null, 执行一个Consumer
        optional.ifPresent((s) -> System.out.println(s.charAt(0)));     // "b"

//        Optional<String> optional1 = Optional.of(null);
//        System.out.println(optional1.isPresent());           // true

        List<String> stringCollection = new ArrayList<>();
        stringCollection.add("ddd2");
        stringCollection.add("aaa2");
        stringCollection.add("bbb1");
        stringCollection.add("aaa1");
        stringCollection.add("bbb3");
        stringCollection.add("ccc");
        stringCollection.add("bbb2");
        stringCollection.add("ddd1");

        stringCollection.stream().filter((s) -> s.startsWith("a"))
            .forEach(System.out::println);

        stringCollection.stream().sorted().forEach(System.out::println);

        stringCollection.stream().map(String::toUpperCase)
            .forEach(System.out::println);
        System.out.println();

        boolean isMatch = stringCollection.stream().anyMatch(
            s -> s.startsWith("a"));
        System.out.println(isMatch);

        boolean allMatch = stringCollection.stream().allMatch(
            s -> s.startsWith("a"));
        System.out.println(allMatch);

        System.out.println();
        long startsWithB = stringCollection.stream()
            .filter((s) -> s.startsWith("b")).count();
        System.out.println(startsWithB);    // 3

        Optional<String> reduced = stringCollection.stream().reduce(
            (s1, s2) -> s1 + s2);
        reduced.ifPresent(System.out::println);
    }
}
