package Chapter8.State;

public class NightState implements State {
    private static NightState singleton = new NightState();

    private NightState(){}

    public static State getInstance() {
        return singleton;
    }

    @Override
    public void doClock(Context context, int hour) {
        if( hour<9 || 17 <= hour)
            context.changeState(DayState.getInstance());
    }

    @Override
    public void doUse(Context context) {
        context.callSecurityCenter("비상 : 야간 금고 사용!");
    }

    @Override
    public void doAlarm(Context context) {
        context.callSecurityCenter("비상벨(야간)");
    }

    @Override
    public void doPhone(Context context) {
        context.callSecurityCenter("야간 통화 녹음");
    }

    public String toString() {
        return "[야간]";
    }
}
