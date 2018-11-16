package test.java;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 18:10
 **/
public class TryCatchFinally {

    public static void main(String[] args) {

        System.out.println(test());
    }

    public static String test(){
        try {
            System.out.println("1");
            System.out.println(1/0);
            return "inner";
        }catch (Exception e){
            System.out.println("catch1");
//            return "catch1";
        }finally {
            System.out.println("finally1");
//            return "finally";
        }
        return "outer";

    }
}
