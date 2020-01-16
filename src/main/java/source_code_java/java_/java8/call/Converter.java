
package source_code_java.java_.java8.call;

/**
 * @author Shuaijun He
 */
@FunctionalInterface
interface Converter<F, T> {
    T convert(F from);
}