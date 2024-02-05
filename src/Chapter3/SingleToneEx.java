package Chapter3;

public class SingleToneEx {
    public static void main(String[] args) {
        System.out.println("Start");
        Singleton obj1 = Singleton.getInstance(); // 프로그램 실행 후 처음 getInstance메소드를 호출할 떄 Singleton클래스가 초기화된다.
        Singleton obj2 = Singleton.getInstance();
        if(obj1==obj2) {
            System.out.println("obj1과 obj2는 같은 인스턴스입니다");
        }else {
            System.out.println("obj1과 obj2는 같은 인스턴스가 아닙니다");
        }
        System.out.println("End");
    }
}
// Singleton 패턴 : 인스턴스가 하나만 존재하는 것을보증하는 패턴
// Singleton 클래스의 생성자는 private : Singleton클래스 외부에서 생성자 호출을 금지하기 위해서

class Singleton {
    private static Singleton singleton = new Singleton();
    // Singleton클래스를 로드할떄 한번만 실행됨

    private Singleton() {
        System.out.println("인스턴스를 생성했습니다");
    }
    public static Singleton getInstance() {
        return singleton;
    }
}