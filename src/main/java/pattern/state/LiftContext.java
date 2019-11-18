package pattern.state;

/**
 * 状态模式：允许对象在内部状态发生改变时改变它的行为，对象看起来好像修改了它的类。
 *
 * 环境(Context)角色，也称上下文：定义客户端所感兴趣的接口，并且保留一个具体状态类的实例。
 * 这个具体状态类的实例给出此环境对象的现有状态。
 *
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:09
 **/
public class LiftContext extends AbstractLiftState {

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

    private AbstractLiftState liftState = LiftContext.closeState;

    static CloseState closeState = new CloseState();
    static OpenState openState = new OpenState();
    static RunState runState = new RunState();
    static StopState stopState = new StopState();



    public AbstractLiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(AbstractLiftState liftState) {
        this.liftState = liftState;
        // 同时，把电梯上下文传给状态。
        this.liftState.setLift(this);
    }

    @Override
    public void open() {
        this.liftState.open();
    }

    @Override
    public void close() {
        this.liftState.close();
    }

    @Override
    public void run() {
        this.liftState.run();
    }

    @Override
    public void stop() {
        this.liftState.stop();
    }
}
