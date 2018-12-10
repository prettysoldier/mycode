
package test.java;

/**
 * @author Shuaijun He
 */
public class PassValueTest {
    public static void changeInt(int i) {// 改变int型变量的函数
        i = 100;
    }

    public static void changeString(String s) {// 改变String型变量的函数
        s = "changeString";
    }

    public static void changeModel(Model model) {// 改变Model型变量的函数
        model = new Model();
        model.i = 1;
        model.s = "changeModel";
    }

    public static void changeModel2(Model model) {// 改变Model型变量的成员的函数
        model.i = 1;
        model.s = "changeModel";
    }

    public static Model changeModel3(Model model) {// 改变Model型变量的成员的函数
        model = new Model();
        model.i = 1;
        model.s = "changeModel";
        return model;
    }

    // 测试程序
    public static void main(String[] args) {

        int i = 0;
        PassValueTest.changeInt(i);
        System.out.println("i=" + i);

        String s = "hello";
        PassValueTest.changeString(s);
        System.out.println("s=" + s);

        Model model = new Model();
        Model model2 = new Model();
        Model model3 = new Model();



        PassValueTest.changeModel(model);
        System.out.println("model:" + model.s);
        PassValueTest.changeModel2(model2);
        System.out.println("model2:" + model2.s);
        Model model4 = PassValueTest.changeModel3(model3);
        System.out.println("model3:" + model3.s);
        System.out.println("model4:" + model4.s);
    }
}

//类Model

class Model {
    public int i;
    public String s;
}
