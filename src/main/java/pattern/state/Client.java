package pattern.state;

/**
 * @Desc
 * @Author shuaijunhe
 * @CreateTime 2018/11/14 20:23
 **/
public class Client {

    public static void main(String[] args) {
        Lift lift = new Lift();
        lift.setLiftState(Lift.closeState);

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
