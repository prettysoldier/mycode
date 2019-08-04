package java_;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 18:10
 **/
public class TryCatchFinally {

    public static void main(String[] args) {

        System.out.println(finallyNotWork());
    }

    /**
     * finally 在return 表达式运行之后执行。
     * 此时 return 的值 已经存到了一个新的局部变量中，
     * 所以finally 并不会修改return 的值。
     *
     * finally 中有return语句，情况就不同了，会再加载一遍temp值返回，
     * 所以【强制】不要在finally中写返回语句
     * @return
     */
    public static int finallyNotWork(){
        int i = 1;
        try {
            throw new Exception();
        } catch (Exception e){
            return ++i;
        }
        finally {
            i = 99;
        }
    }


}
