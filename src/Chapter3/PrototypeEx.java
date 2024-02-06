package Chapter3;

import Chapter3.framework.Manager;
import Chapter3.framework.Product;
// Prototype 패턴 : 인스턴스를 복사해서 새 인스턴스를 만드는 것
// 1. 종류가 너무 많아 클래스로 정리할 수 없는 경우
// 2. 클래스로부터 인스턴스 생성이 어려운 경우
// 3. 프레임워크와 생성하는 인스턴스를 분리하고 싶은 경우
// framework패키지와 Product인터페이스를 상속한 두 클래스는 밀접한 관련이 없음
// 원형 역 : Product 인터페이스 (인스턴스를 복사하여 새로운 인스턴스를 만들기 위한 메서드를 결정)
// 구체적인 원형 역 : MessageBox와 UnderlinePen클래스(인스턴스를 복사하여 새로운 인스턴스를 만드는 메서드를 구현)
// 이용자 역 : Manager클래스(인스턴스를 복사하는 메서드를 이용해 새로운 인스턴스를 만듬)
// 소스 코드 안에 이용할 클래스 이름이 쓰여 있으면, 그 클래스와 분리해서 재사용할 수 없게 된다.
// 부품으로서의 재사용 => 소스파일(.java)이 없어도 재사용할 수 있느냐? 클래스 파일(.class)만 있어도 그 클래스를 재사용할 수 있는지가 중요
// clone메서드는 필드 내용을 그대로 복사하는 것( 얕은 복사 )
public class PrototypeEx {
    public static void main(String[] args) {
        //준비
        Manager manager = new Manager();
        UnderlinePen upen = new UnderlinePen('-');
        MessageBox mbox = new MessageBox('*');
        MessageBox sbox = new MessageBox('/');
        //등록
        manager.register("strong message",upen);
        manager.register("warning box",mbox);
        manager.register("slash box",sbox);
        //생성과 사용
        Product p2 = manager.showcase.get("warning box");
        Product p1 = manager.create("warning box");
        MessageBox m = (MessageBox) p1;
        MessageBox m2 = (MessageBox) p2;
        m.arr[0] = 3;
        m2.arr[0] = 4;
        System.out.println(m.arr[0]);
        System.out.println(p1==p2);
//        System.out.println(m.arr[0]);
//        Product p2=manager.create("warning box");
//        MessageBox m2 = (MessageBox) p2;
//        m2.arr[0] = 4;
//        System.out.println(m2.arr[0]);
//        System.out.println(m.arr[0]);
//        System.out.println(m==m2);
//        m2.i = 3;
//        p2.use("hi");
//        System.out.println(p1==p2);
//        System.out.println(m.i);
//        Product p2 = manager.create("warning box");
//        p2.use("Hello, world");
//        Product p3 = manager.create("slash box");
//        p3.use("Hello, world");
//        Product p4 = manager.create("strong message");
//        p4.use("Hello, world");
//        Product p5 = manager.create("warning box");
//        p5.use("Hello, world");
//        Product p6 = manager.create("slash box");
//        p6.use("Hello, world");
    }
}
class MessageBox implements Product {
    private char decochar;
    int i = 5;
    int[] arr= new int[] {1,2,3};

    public MessageBox(char decochar) {
        this.decochar = decochar;
    }

    @Override
    public void use(String s) {
        int decolen = 1+ s.length()+1;
        for(int i=0; i<decolen; i++) {
            System.out.print(decochar);
        }
        System.out.println();
        System.out.println(decochar+s+decochar);
        for(int i=0; i<decolen; i++) {
            System.out.print(decochar);
        }
        System.out.println();
    }

    @Override
    public Product createCopy() {
        Product p = null;
        try {
            p=(Product) clone();
            }catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
class UnderlinePen implements Product {
    private char ulchar;

    public UnderlinePen(char ulchar) {
        this.ulchar = ulchar;
    }

    @Override
    public void use(String s) {
        int ulen = s.length();
        System.out.println(s);
        for(int i=0; i<ulen; i++) {
            System.out.print(ulchar);
        }
        System.out.println();
    }

    @Override
    public Product createCopy() {
        Product p = null;
        try {
            p=(Product) clone();
        }catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return p;
    }
}