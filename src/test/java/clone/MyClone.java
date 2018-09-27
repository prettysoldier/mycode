package test.java.clone;

/**
 * 可以得到如下结论：如果在拷贝一个对象时，要想让这个拷贝的对象和源对象完全彼此独立，那么在引用链上的每一级对象都要被显式的拷贝。
 * 所以创建彻底的深拷贝是非常麻烦的，尤其是在引用关系非常复杂的情况下， 或者在引用链的某一级上引用了一个第三方的对象， 而这个对象没有实现clone方法，
 * 那么在它之后的所有引用的对象都是被共享的。
 *
 * @author Shuaijun He
 */
public class MyClone {

    public static void main(String[] args) throws CloneNotSupportedException {
        Body body = new Body(new Head());

        Body body1 = (Body) body.clone();

        System.out.println("body == body1 : " + (body == body1));

        System.out.println("body.head == body1.head : "
            + (body.head == body1.head));
    }
}

class Body implements Cloneable {
    public Head head;

    public Body() {
    }

    public Body(Head head) {
        this.head = head;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {

        Body body = (Body) super.clone();
        //由此可见， body和body1内的head引用指向了不同的Head对象， 也就是说在clone Body对象的同时， 也拷贝了它所引用的Head对象， 进行了深拷贝。
        body.head = (Head) this.head.clone();
        return body;
    }

}

class Head implements Cloneable {

    /*
     * (non-Javadoc)
     * @see java.lang.Object#clone()
     */
    @Override
    protected Object clone() throws CloneNotSupportedException {
        // TODO Auto-generated method stub
        return super.clone();
    }
}
