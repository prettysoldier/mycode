
package main.test.java.finalize;

public class TestNoFinalize {

    public static void main(String[] args) {
        while (true) {
            TestNoFinalize heap = new TestNoFinalize();
//            System.out.println("memory address=" + heap);
        }
    }

//    @Override
//    protected void finalize() throws Throwable {
//        super.finalize();
//        System.out.println("finalize.");
//    }
}
