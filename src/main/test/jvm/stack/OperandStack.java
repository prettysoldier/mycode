package main.test.jvm.stack;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/29 11:08
 **/
public class OperandStack {
    private int i;
    OperandStack(int i){
        this.i = i;
    }
    public void simpleMethod(){
        OperandStack ref = new OperandStack(7);
        int a = 1;
    }
}
