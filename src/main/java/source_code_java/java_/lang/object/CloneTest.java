
package source_code_java.java_.lang.object;

import java.util.Date;

/**
 * @author Shuaijun He
 */
public class CloneTest {

    public static void main(String[] args)  {

        Object a = new Object();
//        clone方法是protected的
//        Object se = a.clone();
//        System.out.println(a + "|" + se);

        A aa = new A();
        // 如果A不实现Cloneable接口，会报错。
        A aa2 = aa.clone();
        System.out.println(aa + "|" + aa2);

        try{
            CloneTest cloneTest = new CloneTest();
            CloneTest cloneTest2 = (CloneTest)cloneTest.clone();
            System.out.println(cloneTest);
            System.out.println(cloneTest2);
        }catch (Exception e){
            e.printStackTrace();
        }
    }


    static class A implements Cloneable {

        @Override
        protected A clone()  {
            try {
                return (A)super.clone();
            }catch (Exception e){
                e.printStackTrace();
            }
            return null;
        }

    }

    static class B{
        private Date date;

        /**
         * 由于Date 是可变类，最好是返回其克隆对象。这样可以防止 B 对象的 date 值，在不经过setter非法修改！
         * @return
         */
        private Date getDate () {
            return (Date)date.clone();
        }

        private void setDate (Date date) {
            this.date = date;
        }
    }
}
