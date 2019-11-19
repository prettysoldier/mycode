
package java_.string;

/**
 * @author Shuaijun He
 */
public class StringSplitTest {

    private boolean haha;

    public boolean isHaha() {
        return haha;
    }

    public void setHaha(boolean haha) {
        this.haha = haha;
    }

    public static void main(String[] args) {

//
        String str = "a,b,c,,";
        String[] ary = str.split(",");
        //预期大于3，但结果是3。如果含有空格，也会增加
        System.out.println(ary.length);

        String str1 = "a,b,c, , ";
        String[] ary1 = str.split(",");
        //预期大于3，但结果是3。即使含有空格，也不算！
        System.out.println(ary1.length);
    }
}
