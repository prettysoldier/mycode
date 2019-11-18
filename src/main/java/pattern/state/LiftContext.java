package pattern.state;

/**
 * @Desc 环境(Context)角色，也称上下文：定义客户端所感兴趣的接口，并且保留一个具体状态类的实例。
 * 这个具体状态类的实例给出此环境对象的现有状态。
 *
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:09
 **/
public class LiftContext extends AbstractLiftState {

    private AbstractLiftState liftState;

    static CloseState closeState = new CloseState();
    static OpenState openState = new OpenState();
    static RunState runState = new RunState();
    static StopState stopState = new StopState();



    public AbstractLiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(AbstractLiftState liftState) {
        this.liftState = liftState;
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
