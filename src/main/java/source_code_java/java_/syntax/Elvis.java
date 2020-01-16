package source_code_java.java_.syntax;

/**
 * @author heshuaijun
 * @date 2020/1/12 23:59
 */
public enum Elvis {
    INSTANCE("wangfabo", 1);

    private final int id;
    private final String name;
    Elvis(String n, int i) {
        id=i;
        name=n;
    }
    public void leaveTheBuilding() {
        System.out.println("name:"+name+" id:"+id);
    }
    public Elvis getInstance(){
        return INSTANCE;
    }

    public static void main (String[] args) {
        Elvis elvis = INSTANCE.getInstance();
        System.out.println(elvis);
        elvis.leaveTheBuilding();
    }
}