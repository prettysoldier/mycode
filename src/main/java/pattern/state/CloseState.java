package main.test.pattern.state;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:07
 **/
public class CloseState extends LiftState {

    @Override
    public void open() {
        this.getLift().setLiftState(Lift.openState);
        this.getLift().getLiftState().open();
    }

    @Override
    public void close() {
        System.out.println("关门...");
    }

    @Override
    public void run() {
        this.getLift().setLiftState(Lift.runState);
        this.getLift().getLiftState().run();
    }

    @Override
    public void stop() {
        System.out.println("本来就是停着的~~");
    }
}
