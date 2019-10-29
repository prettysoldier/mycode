package java_.lang;

import java.io.PrintStream;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2019/1/8 11:28
 **/
public class SystemOut_Demo {
    public static void main(String[] args) throws Exception {

        overrideSout();
        System.out.println("atest");
        System.out.println("btest");
        System.out.println("ctest");
    }

    /**
     * 流重写
     */
    private static void overrideSout(){

        PrintStream stream = new PrintStream(System.out){
            @Override
            public void println(String x) {
                if(x.startsWith("a")){
                    super.println(x + "a");
                }else if(x.startsWith("b")){
                    super.println(x + "b");
                }
                else{
                    super.println(x);
                }
            }
        };
        System.setOut(stream);
    }


}
