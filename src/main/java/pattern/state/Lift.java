package main.test.pattern.state;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:09
 **/
public class Lift extends LiftState {

    private LiftState liftState;

    static CloseState closeState = new CloseState();
    static OpenState openState = new OpenState();
    static RunState runState = new RunState();
    static StopState stopState = new StopState();



    public LiftState getLiftState() {
        return liftState;
    }

    public void setLiftState(LiftState liftState) {
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
