package pattern.state;

/**
 * @Desc 状态模式：允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。
 *
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:23
 **/
public class Client {

    public static void main(String[] args) {

        LiftContext lift = new LiftContext();
        lift.setLiftState(LiftContext.closeState);

        lift.open();
        lift.run();
        lift.close();
        lift.close();
        lift.run();
        lift.stop();
        lift.open();
        lift.close();
    }
}
