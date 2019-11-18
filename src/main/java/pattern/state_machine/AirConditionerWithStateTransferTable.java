package pattern.state_machine;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static pattern.state_machine.AirConditionerWithStateTransferTable.Event.CLICK_COOL;
import static pattern.state_machine.AirConditionerWithStateTransferTable.Event.CLICK_POWER;
import static pattern.state_machine.AirConditionerWithStateTransferTable.State.*;

/**
 * 用状态迁移表实现，使用数组和函数引用组合实现。
 *
 a. 状态机可读性比较好
 b. 运行时修改状态表非常方便
 c. 维护起来简单
 d. 可以实现多个状态转换表，根据需要加载不同的转换表。
 *
 * @author shuaijunhe
 * @create 2019/11/18
 * @description
 */
public class AirConditionerWithStateTransferTable {

    public static void main(String[] args) {

        AirConditionerWithStateTransferTable air = new AirConditionerWithStateTransferTable();
        air.dispather(Event.CLICK_POWER);
        air.dispather(Event.CLICK_COOL);
        air.dispather(Event.CLICK_COOL);
        air.dispather(Event.CLICK_POWER);

    }

    /**
     * 此处的用法是？
     */
    private List<Transfer> transferTable = new ArrayList<Transfer>() {
        private static final long serialVersionUID = 2679742264102211454L;

        {
            add(Transfer.of(OFF, CLICK_POWER, FAN_ONLY, () -> doStartFan()));
            add(Transfer.of(FAN_ONLY, CLICK_POWER, OFF, () -> doStopFan()));
            add(Transfer.of(FAN_ONLY, CLICK_COOL, COOL, () -> doStartCool()));
            add(Transfer.of(COOL, CLICK_COOL, FAN_ONLY, () -> doStartFan()));
            add(Transfer.of(COOL, CLICK_POWER, OFF, () -> doStopCool()));
        }
    };

    private static class Transfer {

        private State startState;

        private Event event;

        private State nextState;

        private Runnable doAction;

        private static Transfer of(State startState, Event event, State nextState, Runnable doAction) {
            Transfer transfer = new Transfer();
            transfer.startState = startState;
            transfer.nextState = nextState;
            transfer.event = event;
            transfer.doAction = doAction;
            return transfer;
        }
    }

    /**
     * 空调当前状态
     */
    private State currentState = OFF;

    public void dispather(Event event) {
        for (Transfer transfer : transferTable) {
            if (transfer.startState == currentState && transfer.event == event) {
                if (Objects.nonNull(transfer.doAction)) {
                    transfer.doAction.run();
                    setCurrentState(transfer.nextState);
                    return;
                }
            }
        }
    }

    private void doStartFan() {
        System.out.println("start Fan");
    }

    private void doStopFan() {
        System.out.println("stop Fan");
    }

    private void doStartCool() {
        System.out.println("start Cool");
    }

    private void doStopCool() {
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
