package main.test.pattern.state;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:07
 **/
public class OpenState extends LiftState {


    @Override
    public void open() {
        System.out.println("开门...");
    }

    @Override
    public void close() {
        this.getLift().setLiftState(Lift.closeState);
        this.getLift().getLiftState().close();
    }

    @Override
    public void run() {
        System.out.println("开着电梯门运行？！");
    }

    @Override
    public void stop() {
        System.out.println("本来也没走啊~");
    }
}
