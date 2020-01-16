package source_code_java.java_.syntax.enum_;

/**
 * @author heshuaijun
 * @date 2020/1/17 0:52
 */
public enum MyEnum {
    ONE(1, "SUCC"),
    SECOND(2, "FAIL");

    private int code;
    private String desc;

    MyEnum (int code, String desc) {
        this.code = code;
        this.desc = desc;
    }


}
