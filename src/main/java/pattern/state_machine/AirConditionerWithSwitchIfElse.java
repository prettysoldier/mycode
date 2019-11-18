package pattern.state_machine;



/**
 * 用if/else 或 switch 实现状态机。
 * 缺点：
 *
 * a. 当状态很多的时候，维护起来非常麻烦，容易出错。
 *
 * b. 不容易定位错误，对于状态的理解也不清晰。
 *
 * c. 这段代码没有实现有限状态机和具体事件动作的隔离。
 *
 * @author shuaijunhe
 * @create 2019/11/18
 * @description
 */
public class AirConditionerWithSwitchIfElse {

    public static void main(String[] args) {

        AirConditionerWithSwitchIfElse air = new AirConditionerWithSwitchIfElse();
        air.dispather(Event.CLICK_POWER);
    }
    /**
     * 空调当前状态
     */
    private State currentState = State.OFF;

    public void dispather(Event event) {
        if (currentState == State.OFF) {
            if(event == Event.CLICK_POWER){
                setCurrentState(State.FAN_ONLY);
                doStartFan();
            }
        } else if (currentState == State.FAN_ONLY) {
            if(event == Event.CLICK_POWER){
                setCurrentState(State.OFF);
                doStopFan();
            } else if (event == Event.CLICK_COOL) {
                setCurrentState(State.COOL);
                doStartCool();
            }
        } else if(currentState == State.COOL){
            if(event == Event.CLICK_POWER){
                setCurrentState(State.OFF);
                doStopCool();
            } else if (event == Event.CLICK_COOL) {
                setCurrentState(State.FAN_ONLY);
                doStartFan();
            }
        }
    }

    private void doStartFan(){
        System.out.println("start Fan");
    }
    private void doStopFan(){
        System.out.println("stop Fan");
    }
    private void doStartCool(){
        System.out.println("start Cool");
    }
    private void doStopCool(){
        System.out.println("stop Cool");
    }

    private void setCurrentState(State currentState) {
        this.currentState = currentState;
    }

    /**
     * 空调状态枚举
     */
    public enum State {
        //关闭中状态
        OFF,
        //送风中状态
        FAN_ONLY,
        //制冷中状态
        COOL
    }

    /**
     * 空调事件枚举
     */
    public enum Event {
        //点击电源键
        CLICK_POWER,
        //点击制冷键
        CLICK_COOL
    }
}
