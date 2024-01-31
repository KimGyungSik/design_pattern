package Chapter1;

public class AdapterEx {
    public static void main(String[] args) {
        Print p = new PrintBanner("Hello");
        p.printWeak();
        p.printStrong();

        Print p2 = new PrintBanner("Hello");
        p2.printWeak();
        p2.printStrong();
    }
}
// adapter : 제공된 것과 필요한 것 상에 들어가서 그사이를 채우는 것
// Wrapper : 감싸는 것을 의미
// 무엇인가를 포장해서 다른 용도로 사용할 수 있도록 변환해 주는 것이 래퍼이자 어댑터
// 1. 클래스에 의한 Adapter 패턴 (상속을 이용한 패턴)
//          제공된 것 : Banner 클래스 ( showWithParen, showWithAster)
//          변환 장치 : PrintBanner 클래스
//          필요한 것 : Print 인터페이스 ( printWeak, printStrong)
// 2. 인스턴스에 의한 Adapter 패턴 ( 위임을 사용한 패턴) / 위임 : 누군가에게 맡긴다라는 의미 ( java에서는 어떤 메서드의 실제 처리를 다른 인스턴스의 메소드에게 맡기는 것)
//         Banner클래스를 이용하여 Print 클래스와 같은 메소드를 갖는 클래스를 실현하려는 것
//         PrintBanner클래스에 Banner클래스의 참조변수를 선언하고 생성자로 객체 만들어줌
// 정리
// target ( 필요한 대상 ) : Print 인터페이스, Print 추상클래스
// Client ( 의뢰자 역할 ) : AdapterEx클래스 (main메서드)
// Adaptee ( 적응 대상자 역) : Banner클래스 (제공된 것)
// Adapter (적응자 역) : PrintBanner클래스
//      클래스에 의한 Adapter패턴은 Adaptee를 상속
//      인스턴스에 의한Adapter패턴은 Adaptee를 위임
// 상속 < 위임 : 위임을 사용하는 편이 문제가 적음
// =========================================서로 다른 두 개의 인터페이스(API) 사이에서 그 차이를 메우는 Adapter 패턴======================================