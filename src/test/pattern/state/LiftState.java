package test.pattern.state;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:05
 **/
public abstract class LiftState {
    protected Lift lift;

    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();


    public Lift getLift() {
        return lift;
    }

    public void setLift(Lift lift) {
        this.lift = lift;
    }

}
