package source_code_java.java_.syntax.keyword.switch_;

import source_code_java.java_.syntax.enum_.MyEnum;

/**
 * @author heshuaijun
 * @date 2020/1/17 0:58
 */
public class MySwitch {

    public static void main (String[] args) {
        /**
         * java7中字符串可以用于switch
         */
        String s = "asd";
        switch (s){
            case "qqq" :
                System.out.println("qqq");
                break;
            case "as" :
                System.out.println("as");
                break;
            case "asd" :
                System.out.println("asd");
                break;
        }


    }

    public static void f () {

        MyEnum v = MyEnum.ONE;
        switch (v){
            case ONE:
                System.out.println("ONE");
                break;
            case SECOND :
                System.out.println("SECOND");
                break;
        }


    }
}
