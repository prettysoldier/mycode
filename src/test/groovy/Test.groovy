
package test.groovy

/**
 * @author Shuaijun He
 *
 */
class Test {

    int a = 1

    String b = "sdfg"

    static void main (){

        def s = "sadfg";

        println s
        s.metaClass.haha = { d.toUpperCase(); }
        println s.haha()
        GroovyShell shell =  new   GroovyShell(s);

        Object o = shell.evaluate("s.haha()");
    }
}
