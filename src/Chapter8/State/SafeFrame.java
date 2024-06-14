package Chapter8.State;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafeFrame extends Frame implements ActionListener,Context {
    private TextField textClock = new TextField(60); // 현재 시간 표시
    private TextArea textScreen = new TextArea(10,60);// 경비 센터 출력
    private Button buttonUse = new Button("금고 사용");// 금고 사용 버튼
    private Button buttonAlarm = new Button("비상벨");// 비상벨 버튼
    private Button buttonPhone = new Button("일반 통화");// 일반 통화 버튼
    private Button buttonExit = new Button("종료");// 종료 버튼

    private State state = DayState.getInstance();// 현재 상태

    public SafeFrame(String title) {
        super(title);
        setBackground(Color.lightGray);
        setLayout(new BorderLayout());
        add(textClock,BorderLayout.NORTH);
        textClock.setEditable(false);
        add(textScreen,BorderLayout.CENTER);
        textScreen.setEditable(false);
        Panel panel = new Panel();
        panel.add(buttonUse);
        panel.add(buttonAlarm);
        panel.add(buttonPhone);
        panel.add(buttonExit);
        add(panel,BorderLayout.SOUTH);
        pack();
        setVisible(true);
        buttonUse.addActionListener(this);
        buttonAlarm.addActionListener(this);
        buttonPhone.addActionListener(this);
        buttonExit.addActionListener(this);
    }

    // 버튼이 눌리면 여기로 온다
    public void actionPerformed(ActionEvent e) {
        System.out.println(e.toString());
        if(e.getSource() == buttonUse) {
            state.doUse(this);
        } else if(e.getSource() == buttonAlarm) {
            state.doAlarm(this);
        } else if (e.getSource() == buttonPhone) {
            state.doPhone(this);
        } else if (e.getSource() == buttonExit) {
            System.exit(0);
        } else {
            System.out.println("?");
        }
    }

    // 시간 설정
    @Override
    public void setClock(int hour) {
        String clockstring = String.format("현재 시간은 %02d:00",hour);
        System.out.println(clockstring);
        textClock.setText(clockstring);
        state.doClock(this,hour);
    }

    // 상태 전환
    public void changeState(State state) {
        System.out.println(this.state + "에서" + state + "으로 상태가 변화했습니다");
        this.state = state;
    }

    // 경비 센터 경비원 호출
    public void callSecurityCenter(String msg) {
        textScreen.append("call! "+ msg + "\n");
    }

    // 경비 센터 기록
    public void recordLog(String msg) {
        textScreen.append("record...." + msg + "\n");
    }
}
