package Chapter3;

public class SingletonTest2 extends Thread{
    public static void main(String[] args) {
        System.out.println("Start");
        new SingletonTest2("A").start();
        new SingletonTest2("B").start();
        new SingletonTest2("C").start();
        System.out.println("End");
    }
    public void run() {
        Singleton2 obj = Singleton2.getInstance();
        System.out.println(getName()+": obj="+obj);
    }
    SingletonTest2(String name) {
        super(name);
    }
}
class Singleton2 {
    private static Singleton2 singleton2 = null;

    private Singleton2() {
        System.out.println("인스턴스가 생성되었습니다");
        slowdown();
    }
    public static synchronized Singleton2 getInstance() {
        if(singleton2 ==null) {
            singleton2 = new Singleton2();
        }
        return singleton2;
    }
    private void slowdown() {
        try{
            Thread.sleep(1000);
        }catch (InterruptedException e) {}
    }
}
// 실행결과 (스레드 세이프하기전)
//    Start
//    End
//    인스턴스가 생성되었습니다
//    인스턴스가 생성되었습니다
//    인스턴스가 생성되었습니다
//C: obj=Chapter3.Singleton2@ef0e276
//B: obj=Chapter3.Singleton2@2cacaea8
//A: obj=Chapter3.Singleton2@6f71457b
// 실행결과 (스레드 세이프한후)
//Start
//End
//인스턴스가 생성되었습니다
//C: obj=Chapter3.Singleton2@6e64614d
//A: obj=Chapter3.Singleton2@6e64614d
//B: obj=Chapter3.Singleton2@6e64614d
