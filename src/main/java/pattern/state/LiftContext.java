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
public class LiftContext extends State {

    public static void main(String[] args) {
        LiftContext lift = new LiftContext();
        lift.setCurState(LiftContext.closeState);

        lift.open();
        lift.run();
        lift.close();
        lift.close();
        lift.run();
        lift.stop();
        lift.open();
        lift.close();
    }

    static final CloseState closeState = new CloseState();
    static final OpenState openState = new OpenState();
    static final RunState runState = new RunState();
    static final StopState stopState = new StopState();

    private State curState = LiftContext.closeState;

    public void setCurState (State curState) {
        this.curState = curState;
        // 同时，把电梯上下文传给状态。
        this.curState.setLift(this);
    }

    @Override
    public void open() {
        this.curState.open();
    }

    @Override
    public void close() {
        this.curState.close();
    }

    @Override
    public void run() {
        this.curState.run();
    }

    @Override
    public void stop() {
        this.curState.stop();
    }
}
