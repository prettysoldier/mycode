package jvm;

/**
 * @author heshuaijun
 * @date 2020/1/3 0:29
 */
public class Test {



    public int inc(){
        int x;
        try{
            x = 1;
            return x;
        }catch(Exception e){
            x = 2;
            return x;
        }finally {
            x = 3;
        }
    }
}
