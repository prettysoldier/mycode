
package pattern.single;

/***
 * 这种方式是《Effective Java》作者Josh Bloch提倡的方式，
 * 它不仅能避免多线程同步问题，而且还能防止反序列化重新创建新的对象
 *
 * @author Shuaijun He
 */
public enum Singleton4Enum {

    INSTANCE("wangfabo", 1);

    private final int id;
    private final String name;

    Singleton4Enum(String n, int i) {
        this.id = i;
        this.name = n;
    }

    public void leaveTheBuilding() {
        System.out.println("name:" + this.name + " id:" + this.id);
    }

    public static Singleton4Enum getInstance() {
        return INSTANCE;
    }

    /**
     * test
     * 
     * @param args
     */
    public static void main(String[] args) {
        Singleton4Enum.INSTANCE.leaveTheBuilding();
    }
}