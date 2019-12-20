
package java_.lang.object;

import java.util.Date;

/**
 * @author Shuaijun He
 */
public class CloneTest {

    public static void main(String[] args) throws CloneNotSupportedException {

//        Object a = new Object();
//        Object se = a.clone();
//        System.out.println(a + "|" + se);

        A aa = new A();
        // 如果A不实现Cloneable接口，会报错。
        A aa2 = (A) aa.clone();
        System.out.println(aa + "|" + aa2);
    }


    static class A implements Cloneable {
        /*
         * (non-Javadoc)
         * @see java.lang.Object#clone()
         */
        @Override
        protected Object clone() throws CloneNotSupportedException {
            return super.clone();
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
