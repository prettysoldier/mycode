package source_code_java.java_.syntax.overload;

/**
 * 重写：多态，动态单分配
 *
 * @author Shuaijun He
 */
public class DynamicDispatch {

    static class Human {
        public void sayHello() {
            System.out.println("human say hello");
        }
    }

    static class Man extends Human {

        @Override
        public void sayHello() {
            // TODO Auto-generated method stub
            System.out.println("man say hello");
        }
    }

    static class Woman extends Human {
        @Override
        public void sayHello() {
            // TODO Auto-generated method stub
            System.out.println("woman say hello");
        }
    }

    public static void main(String[] args) {
        Human man = new Man();
        Human woman = new Woman();
        man.sayHello();
        woman.sayHello();
        man = new Woman();
        man.sayHello();
    }
}
