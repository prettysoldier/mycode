
package java_.java8.call;

/**
 * @author Shuaijun He
 */
interface PersonFactory<P extends Person> {

    P createDefault();

//    P create(String firstName, String lastName);
}
