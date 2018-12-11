package test;

/**
 * @Desc 覆盖率样本
 * @Author shuaijunhe
 * @CreateTime 2018/12/11 14:06
 **/
public class CoverageSampleMethods {

    public Boolean testMethod(int a, int b, int c){
        boolean result = false;
        if(a == 1 && b == 2 || c == 3){
            result = true;
        }
        return result;
    }
}
