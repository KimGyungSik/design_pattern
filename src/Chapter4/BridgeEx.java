package Chapter4;

// Bridge 패턴 : 기능의 클래스 계층과 구현의 클래스 계층을 연결하는 다리
// 기능의 클래스 계층 : 상위 클래스(기본적인 기능)-> 하위 클래스(새로운 기능을 추가)
// 새로운 기능을 추가하고 싶을떄 = 클래스 계층 안에서 자신의 목적과 가까운 클래스를 찾아 그 하위 클래스를 만들고,원하는 기능을 추가한 새로운 클래스를 만든다
// 구현의 클래스 계층 : 상위 클래스(추상메서드로 인터페이스를 규정) -> 하위 클래스(구상 메서드로 그를 구 인터페이스현)
// 하위 클래스를 만들고자 할때 기능을 추가하려는가? 아님 구현하려고 하는가?를 고려해야함
// 두 개의 독립된 클래스 계층으로 나뉜걸 이어주는 패턴이 Bridge패턴
// 두 개의 독립된 클래스 계층으로 나누면 각각의 클래스 계층을 독립적으로 확장할 수 있다 (장점)
// 상속은 강한 결합(소스 코드를 다시 쓰지 않는 한 바꿀 수 없는것)
// 위임은 약한 결합(포함관계)

public class BridgeEx {
    public static void main(String[] args) {
        Display d1 = new Display(new StringDisplayImpl("Hello, Korea"));
        Display d2 = new CountDisplay(new StringDisplayImpl("Hello, world"));
        CountDisplay d3 = new CountDisplay(new StringDisplayImpl("Hello, Universe"));
        RandomDisplay d4 = new RandomDisplay(new StringDisplayImpl("Hi, faker"));
        CharDisplay d5 = new CharDisplay(new CharDisplayImpl('<','*','>'),1);
        d1.display();
        d2.display();
        d3.multiDisplay(5);
        d4.randomDisplay(7);
        d5.chardisplay(4);
    }
}
class Display {
    private DisplayImpl impl;

    public Display(DisplayImpl impl) {
        this.impl = impl;
    }
    public void open() {
        impl.rawOpen();
    }
    public void print() {
        impl.rawPrint();
    }
    public void close() {
        impl.rawClose();
    }
    public final void display() {
        open();
        print();
        close();
    }
}
class CountDisplay extends Display {
    public CountDisplay(DisplayImpl impl) {
        super(impl);
    }
    public void multiDisplay(int times) {
        open();
        for(int i=0; i<times; i++) {
            print();
        }
        close();
    }
}
class RandomDisplay extends Display {
    public RandomDisplay(DisplayImpl impl) {
        super(impl);
    }
    public void randomDisplay(int times) {
        int randomtime = (int) Math.random()*times+1;
        open();
        for(int i=0; i<randomtime; i++) {
            print();
        }
        close();
    }
}
class CharDisplay extends CountDisplay {
    int step;
    public CharDisplay(DisplayImpl impl,int step) {
        super(impl);
        this.step = step;
    }
    public void chardisplay(int level) {
        int cnt = 0;
        for(int i=0; i<level; i++) {
            multiDisplay(cnt);
            cnt += step;
        }
    }
}
abstract class DisplayImpl {
    public abstract void rawOpen();
    public abstract void rawPrint();
    public abstract void rawClose();
}
class StringDisplayImpl extends DisplayImpl {
    private String string;
    private int width;

    public StringDisplayImpl(String string) {
        this.string = string;
        this.width = string.length();
    }

    @Override
    public void rawOpen() {
        printLine();
    }

    @Override
    public void rawPrint() {
        System.out.println("|"+string+"|");
    }

    @Override
    public void rawClose() {
        printLine();
    }
    private void printLine() {
        System.out.print("+");
        for(int i=0; i<width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
class CharDisplayImpl extends DisplayImpl {
    private char start;
    private char print;
    private char end;

    public CharDisplayImpl(char start,char print,char end) {
        this.start = start;
        this.print = print;
        this.end = end;
    }

    @Override
    public void rawOpen() {
        System.out.print(start);
    }

    @Override
    public void rawPrint() {
        System.out.print(print);
    }

    @Override
    public void rawClose() {
        System.out.println(end);
    }

}