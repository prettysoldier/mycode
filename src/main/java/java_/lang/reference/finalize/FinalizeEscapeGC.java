package java_.lang.reference.finalize;

/**
 * 深入理解jvm:对象的生存还是死亡
 * 即使在可达性分析算法中不可达的对象，也并非是“非死不可”的，这时候它们暂时处于“缓刑”阶段，要真正宣告一个对象死亡，
 * 至少要经历两次标记过程:如果对象在进行可达性分析后发现没有与GC Roots 相连接的引用链，那它将会被第一次标记并且进行一次筛选，
 * 筛选的条件是此对象是否有必要执行finalize() 方法。
 *    当对象没有覆盖finalize() 方法，或者finalize() 方法已经被虚拟机调用过，虚拟机将这两种情况都视为“没有必要执行”。
 * 如果这个对象被判定为有必要执行finalize()方法，JVM会生成一个 new Finalizer(obj) 包裹这个对象，把这个 Finalizer 对象放到pending-queue 的队列之中，
 * 由Reference.ReferenceHandler 线程从 pending-queue 中将 Finalizer 放到 每个Finalizer对象的queue里，这个queue是最终是Finalizer的静态变量，只有一个。
 * 由Finalizer.FinalizerThread 线程不断从这个queue中获取，如果队列为空，就会阻塞。获取 Finalizer 后，获取其引用的对象，调用对象finalize()。
 *
 * finalize() 方法是对象逃脱死亡命运的最后一次机会，如果对象要在finalize()中成功扬救自己一只要重新与引用链上的任何- 一个对象建立关联即可，譬如把自己(this 关键字)
 * 赋值给某个类变量或者对象的成员变量，那在第二次标记时它将被移除出“即将回收”的集合: 如果对象这时候还没有逃脱，那基本上它就真的被回收了。
 * 从代码清单3-2 中我们可以看到一个对象的finalize()被执行，但是它仍然可以存活。代码清单3-2一次对象自我拯救的演示。
 *
 * 此代码演示了两点
 * 对象可以在GC时自我拯救
 * 这种自救只会有一次，因为一个对象的finalize方法只会被自动调用一次
 */
public class FinalizeEscapeGC {
    public static FinalizeEscapeGC SAVE_HOOK = null;

    public void isAlive() {
        System.out.println("yes我还活着");
    }

    @Override
    public void finalize() throws Throwable {
        super.finalize();
        System.out.println("执行finalize方法");
        //自救
        FinalizeEscapeGC.SAVE_HOOK = this;
    }

    public static void main(String[] args) throws InterruptedException {
        SAVE_HOOK = new FinalizeEscapeGC();
        //对象的第一次回收
        SAVE_HOOK = null;
        // gc后，SAVE_HOOK可能不为空！
        System.gc();
        //因为finalize方法的优先级很低所以暂停0.5秒等它
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no我死了");
        }
        //下面的代码和上面的一样，但是这次自救却失败了
        //对象的第一次回收
        SAVE_HOOK = null;
        System.gc();
        Thread.sleep(500);
        if (SAVE_HOOK != null) {
            SAVE_HOOK.isAlive();
        } else {
            System.out.println("no我死了");
        }
    }
}
