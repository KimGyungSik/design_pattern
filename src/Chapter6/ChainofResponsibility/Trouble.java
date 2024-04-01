package Chapter6.ChainofResponsibility;

public class Trouble {
    private int number; // 트러블 번호

    // 트러블 생성
    public Trouble(int number) {
        this.number = number;
    }

    //트러블 번호를 얻는다
    public int getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "[Trouble"+number+"]";
    }
}
