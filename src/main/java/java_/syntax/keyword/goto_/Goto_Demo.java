package java_.syntax.keyword.goto_;

/**
 * 1.goto 语句可以用在if语句。或者块语句
 * @author hsj
 * @create 2019/11/22
 */
public class Goto_Demo {

    public static void main(String[] args) {

        inIfBlock(true);
        System.out.println("----------分隔符----");
        inBlock(true);
    }

    private static void inIfBlock(boolean bool){

        结束:
        if(bool){

            System.out.println("in ");
            if(bool){
                break 结束;
            }
            System.out.println("after ");
        } 

        System.out.println("out ");
    }

    private static void inBlock(boolean bool){

        结束:
        {
            System.out.println("in ");
            if(bool){
                break 结束;
            }
            System.out.println("after ");
        }

        System.out.println("out ");
    }
}
