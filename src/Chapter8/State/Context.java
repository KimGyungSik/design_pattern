package Chapter8.State;

public interface Context {
    void setClock(int hour); // 시간 설정
    void changeState(State state);// 상태 전환
    void callSecurityCenter(String msg);// 경비 센터 경비원 호출
    void recordLog(String msg);// 경비 센터 기록
}
