package java_.package_;

import java.sql.*;
import java.util.*;

/**
 * 1.java.util包与java.util.jar包有包含关系吗？ 没有
 * 2.独立的类只能有public 和 包访问权限（无）两种访问权限。
 * @author heshuaijun
 * @date 2019/11/24 1:11
 */
public class NestedPackage {

    public static void main (String[] args) {
        // 访问不到子文件的包访问权限的方法。
//        new java_.package_.sub_package.NestedPackage().f();
        // 编译错误，因为不知道使用哪个类。
//        Date d = new Date();
    }
}
