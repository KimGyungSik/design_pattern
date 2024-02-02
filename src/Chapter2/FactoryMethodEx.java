package Chapter2;

import Chapter2.framework.Factory;
import Chapter2.framework.Product;
import Chapter2.idcard.IDCardFactory;

public class FactoryMethodEx {
    public static void main(String[] args) {
        Factory factory = new IDCardFactory();
        Product card1 = factory.create("Youngjun Kim");
        Product card2 = factory.create("Heungmin Son");
        Product card3 = factory.create("Kane");
        Product card4 = factory.create("messi");
        card1.use();
        card2.use();
        card3.use();
        card4.use();
    }
}
// Factory Method 패턴 : 'Fatory'= 공장, 인스턴스를 생성하는 공장을 Template Method패턴으로 구성한 것
// 인스턴스를 생성하는 프레임워크 쪽(framework 패키지)
// 구체적인 내용을 구현하는 쪽(idcard 패키지)
// 클래스 목록 : Product(추상 메소드 use만 정의한 추상클래스), Factory(메소드 create를 구현한 추상 클래스)
//              IDCard(메소드 use를 구현한 클래스), IDCardFactory(메소드 createProduct, registerProduct를 구현한 클래스)
// Product -> IDCard
// Factory -> IDCardFactory
// Factory는 인스턴스를 생성하는 메서드 createProduct를 호출하여 Product를 생성한다.
// 이것은 new를 사용해 실제 인스턴스를 생성하는 대신에, 인스턴스를 생성하는 메소드를 호출함으로써 구체적인 클래스 이름에 의한 속박에서 상위 클래스를 자유롭게 한다
// framework 패키지 내용을 수정하지 않고도 전혀 다른 제품과 공장을 만들 수 있는 것!
// 'framework패키지는 idcard 패키지에 의존하지 않는다'
// Template 패턴과 Factory패턴 모두 뼈대를 먼저 이해하고 추상메서드가 무엇인지 파악한뒤 추상메서드를 구현한 클래스의 소스 코드를 살펴볼것
