package Chapter2;

import java.io.InputStream;

public class TemplateEX {
    public static void main(String[] args) {
            AbstractDisplay d1 = new CharDisplay('H');
            AbstractDisplay d2 = new StringDisplay("Hello, world");

            // 실제 동작은 CharDisplay나 StringDisplay 클래스에서 정해진다
            d1.display();
            d2.display();
            d1.open();
    }
}
// Template Method 패턴
// => 상위 클래스에서 처리의 뼈대를 결정하고 하위 클래스에서 그 구체적 내용을 결정하는 패턴
// 추상클래스 역 : AbstractDisplay 클래스
// 구현클래스 역 : CharDisplay, StringDisplay 클래스
// The LisKov Substitution Principle(LSP) : 상위 클래스형 변수에 하위 클래스의 인스턴스 중 어느 것을 대입해도 제대로 동작할 수 있게 하는 원칙 (리스코프 치환 원칙)
// @Override 어노테이션을 발견하면, 어떤 책임을 맡은 메소드인지 주의하며 코드를 읽을 것
// 추상 클래스 단계에서 처리 흐름을 형성하는 것이 중요함
abstract class AbstractDisplay {
    // open,print,close는 하위 클래스에 구현을 맡기는 추상 메소드
    public abstract void open();
    public abstract void print();
    public abstract void close();

    // display는 AbstractDisplay에서 구현하는 메서드
    public final void display() {
        open();
        for(int i = 0; i<5; i++) {
            print();
        }
        close();
    }
}

class CharDisplay extends AbstractDisplay {
    private char ch; // 표시해야 하는 문자

    // 생성자
    public CharDisplay(char ch) {
        this.ch = ch;
    }

    @Override
    public void open() {
        // 시작 문자열 "<<"를 표시한다.
        System.out.print("<<");
    }

    @Override
    public void print() {
        // 필드에 저장해 둔 문자를 1회 표시한다
        System.out.print(ch);
    }

    @Override
    public void close() {
        // 종료 문자열 ">>"를 표시한다
        System.out.println(">>");
    }
}

class StringDisplay extends AbstractDisplay {
    private String string; // 표시해야 하는 문자열
    private int width; // 문자열의 길이

    //생성자
    public StringDisplay(String string) {
        this.string = string;
        this.width = string.length();
    }

    @Override
    public void open() {
        printLine();
    }

    @Override
    public void print() {
        System.out.println("|"+string+"|");
    }

    @Override
    public void close() {
        printLine();
    }
    //open과 close에 호출되어 "+----+" 문자열을 표시하는 메서드
    private void printLine() {
        System.out.print("+");
        for(int i=0; i<width; i++) {
            System.out.print("-");
        }
        System.out.println("+");
    }
}
// AbstractDisplay를 인터페이스로 변경했을 떄
interface AbstractDisplay2 {
    // open,print,close는 하위 클래스에 구현을 맡기는 추상 메소드
    public abstract void open();
    public abstract void print();
    public abstract void close();

    // display는 AbstractDisplay에서 구현하는 메서드
     default void display() {
        open();
        for(int i = 0; i<5; i++) {
            print();
        }
        close();
    }
}