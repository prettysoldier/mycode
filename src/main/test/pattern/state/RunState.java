package main.test.pattern.state;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:07
 **/
public class RunState extends LiftState {


    @Override
    public void open() {
        System.out.println("很危险！");
    }

    @Override
    public void close() {
        System.out.println("本来就是关着的~~");
    }

    @Override
    public void run() {
        System.out.println("运行...");
    }

    @Override
    public void stop() {
        this.getLift().setLiftState(Lift.stopState);
        this.getLift().getLiftState().stop();
    }
}
