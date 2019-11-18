package pattern.state;

/**
 * @Desc 抽象状态(State)角色：定义一个接口，用以封装环境（Context）对象的一个特定的状态所对应的行为。
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:05
 **/
public abstract class AbstractLiftState {

    protected LiftContext lift;

    public LiftContext getLift() {
        return lift;
    }

    public void setLift(LiftContext lift) {
        this.lift = lift;
    }


    public abstract void open();
    public abstract void close();
    public abstract void run();
    public abstract void stop();


}

/**
 * 具体状态(ConcreteState)角色：每一个具体状态类都实现了环境（Context）的一个状态所对应的行为。
 */
class StopState extends AbstractLiftState {

    @Override
    public void open() {
        this.getLift().setLiftState(LiftContext.openState);
        LiftContext.openState.open();
    }

    @Override
    public void close() {
        System.out.println("本来就关着呢~~");
    }

    @Override
    public void run() {

        this.getLift().setLiftState(LiftContext.runState);
        LiftContext.runState.run();
    }

    @Override
    public void stop() {
        System.out.println("停止...");
    }
}

class OpenState extends AbstractLiftState {


    @Override
    public void open() {
        System.out.println("开门...");
    }

    @Override
    public void close() {
        this.getLift().setLiftState(LiftContext.closeState);
        LiftContext.closeState.close();
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

class RunState extends AbstractLiftState {


    @Override
    public void open() {
        System.out.println("运行时不允许开门，很危险！");
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
        this.getLift().setLiftState(LiftContext.stopState);
        LiftContext.stopState.stop();
    }
}

class CloseState extends AbstractLiftState {

    @Override
    public void open() {
        this.getLift().setLiftState(LiftContext.openState);
        LiftContext.openState.open();
    }

    @Override
    public void close() {
        System.out.println("关门...");
    }

    @Override
    public void run() {
        this.getLift().setLiftState(LiftContext.runState);
        LiftContext.runState.run();
    }

    @Override
    public void stop() {
        System.out.println("本来就是停着的~~");
    }
}
