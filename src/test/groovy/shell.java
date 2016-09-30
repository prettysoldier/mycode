/**
 * Copyright(c) 2011-2016 by YouCredit Inc.
 * All Rights Reserved
 */
package test.groovy;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;
import groovy.util.GroovyScriptEngine;
import groovy.util.ResourceException;
import groovy.util.ScriptException;

import java.io.IOException;

/**
 * @author Shuaijun He
 */
public class shell {

    public static void main(String[] args) throws IOException,
            ResourceException, ScriptException {

        Binding bind = new Binding();

        bind.setVariable("a", "sdfgsfdg");
        bind.setVariable("b", "111111");
        GroovyShell shell = new GroovyShell(bind);
        Object obj = shell.evaluate("a + b");
        System.out.println(obj);

        GroovyScriptEngine engine = new GroovyScriptEngine("");

        Object re = engine.run("src/test/groovy/Test.groovy", bind);

        System.out.println(re);
    }
}
