/**
 * 
 */
package java.lock;

/**
 * @author shuaijunhe
 *
 */
public class ZZ {

    ZZ() {
        ZZ a1 = this;
        ZZ a2 = this;
        synchronized (a1) {
            try {
                a2.wait();
                System.out.println("Hello");
            } catch (InterruptedException e) {
                System.out.println("Good Bye");
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println("Wrong");
            }
            finally {
                System.out.println("finally");
            }

        }
        System.out.println("Out");
    }

    public static void main(String[] args) {
        new ZZ();
    }
}
