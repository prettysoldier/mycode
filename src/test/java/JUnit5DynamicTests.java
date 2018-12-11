import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DynamicTest;
import org.junit.jupiter.api.TestFactory;
import org.junit.jupiter.api.function.Executable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/12/11 16:46
 **/
public class JUnit5DynamicTests {
    @TestFactory
    Collection<DynamicTest> dynamicTests() {
        return Arrays.asList(
                DynamicTest.dynamicTest("simple dynamic test",
                        () -> Assertions.assertTrue(true)),
                DynamicTest.dynamicTest("My Executable Class", new MyExecutable()),
//                DynamicTest.dynamicTest("Exception Executable",
//                        () -> {throw new Exception("Exception Example");}),
                DynamicTest.dynamicTest("simple dynamic test-2",
                        () -> Assertions.assertTrue(true))
        );
    }

    @TestFactory
    Stream<DynamicTest> dynamicTestsExample() {
        List<Integer> input1List = Arrays.asList(1,2,3);
        List<Integer> input2List = Arrays.asList(10,20,30);

        List<DynamicTest> dynamicTests = new ArrayList<>();

        for(int i=0; i < input1List.size(); i++) {
            int x = input1List.get(i);
            int y = input2List.get(i);
            DynamicTest dynamicTest = DynamicTest.dynamicTest("Dynamic Test for MyUtils.add("+x+","+y+")",
                    () ->Assertions.assertEquals(x+y,MyUtils.add(x,y)));
            dynamicTests.add(dynamicTest);
        }

        return dynamicTests.stream();
    }
}

class MyExecutable implements Executable {

    @Override
    public void execute() throws Throwable {
        System.out.println("Hello World!");
    }

}

class MyUtils {

    public static int add(int x, int y) {
        return x+y;
    }
}