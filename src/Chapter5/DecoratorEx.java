package Chapter5;

import java.util.ArrayList;
import java.util.List;

//Decorator 패턴 : 객체에 점점 장식을 더해 가는 디자인 패턴
// Decorator 패턴은 장식틀과 내용물을 동일시한다. Border클래스(및 그 하위 클래스들)는 내용을 나타내는 Display클래스와 같은 인터페이스를 가짐
// 장식틀이 감싼 내용물이 실제로는 다른 것의 장식틀이 되는 구조
// 내용물을 변경하지 않고 기능을 추가할 수 있음
// Decorator에서 사용되는 위임은 클래스 사이를 동적으로 결합한다. 그러므로 프레임워크의 소스를 변경하지 않고 객체의 관계를 변경한 새로운 객체를 만들 수 있음
//
//Component 역 : 기능을 추가할 떄 핵심이 되는 역할 ( 장식하기 전의 스펀지 케이크), Display클래스
//ConcreteComponent역 : Component의 인터페이스를 구현하는 구체적인 스펀지 케이크, StringDisplay클래스
//Decorator 역 : Component와 동일한 인터페이스를 가지며 Decorator가 장식할 대상이 되는 Component도 가지고 있습니다, 자신이 장식할 대상을 아는 셈 , Border클래스
// ConcreteDecorator 역 : 구제척인 Decorator, SideBorder클래스와 FullBorder클래스
public class DecoratorEx {
    public static void main(String[] args) {
        Display b1 = new StringDisplay("Hello, world");
        Display b2 = new UpDownBorder(b1,'-');
        Display b3 = new SideBorder(b2,'*');
        b1.show();
        b2.show();
        b3.show();
        Display b4 = new SideBorder(
                new FullBorder(
                        new FullBorder(
                                new SideBorder(
                                        new FullBorder(
                                                new StringDisplay("Hello, world")
                                        ),
                                        '*'
                                )
                        )
                ),
                '/'
        );
        b4.show();
    }
}
abstract class Display{
    public abstract int getColumns(); // 가로 문자 수를 얻는다
    public abstract int getRows(); // 세로 행수를 얻는다
    public abstract String getRowText(int row); // row행째 문자열을 얻는다

    //모든 행을 표시한다, template Method패턴 활용
    public void show() {
        for(int i=0; i<getRows(); i++) {
            System.out.println(getRowText(i));
        }
    }
}
class StringDisplay extends Display {
    private String string; // 표시 문자열
    public StringDisplay(String string) {
        this.string = string;
    }

    @Override
    public int getColumns() {
        return string.length();
    }

    @Override
    public int getRows() {
        return 1; // 행수는 1
    }

    @Override
    public String getRowText(int row) {
        if(row!=0) {
            throw new IndexOutOfBoundsException();
        }
        return string;
    }
}
abstract class Border extends Display {
    protected Display display; // 이 장식틀이 감싸는 '내용물', Display추상클래스를 구현한 클래스의 인스턴스가 들어올것임
    protected Border(Display display) { //인스턴스 생성 시 '내용물'을 인수로 지정
        this.display = display;
    }
}

class SideBorder extends Border {
    private char borderChar; // 장식 문자

    //내용물이 될 Display와 장식 문자를 지정
    public SideBorder(Display display,char ch) {
        super(display);
        this.borderChar = ch;
    }

    @Override
    public int getColumns() {
        //문자 수는 내용물의 양쪽에 장식 문자만큼 더한 것
        return 1+ display.getColumns()+1;
    }

    @Override
    public int getRows() {
        //행수는 내용물의 행수와 같다
        return display.getRows();
    }

    @Override
    public String getRowText(int row) {
        // 지정 행의 내용은 내용물의 지정 행 양쪽에 장식 문자를 붙인 것
        return borderChar+ display.getRowText(row)+borderChar;
    }
}

class FullBorder extends Border {
    public FullBorder(Display display) {
        super(display);
    }

    @Override
    public int getColumns() {
        // 문자 수는 내용물 양쪽에 좌우 장식 문자만큼 더한 것
        return 1+ display.getRows()+1;
    }

    @Override
    public int getRows() {
        //행수는 내용물의 행수에 상하 장식 문자만큼 더한 것
        return 1+ display.getRows()+1;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {// 상단테두리
            return "+" + makeLine('-', display.getColumns()) + "+";
        } else if (row== display.getRows()+1) { // 하단 테두리
            return "+" + makeLine('-', display.getColumns()) + "+";
        }else { // 기타
            return "|"+display.getRowText(row-1)+"|";
        }
    }

    //문자 ch로 count 수만큼 연속한 문자열을 만든다
    private String makeLine(char ch,int count) {
        StringBuilder line = new StringBuilder();
        for(int i=0; i<count; i++) {
            line.append(ch);
        }
        return line.toString();
    }
}

// 위아래만 장식 문자가 붙는 클래스
class UpDownBorder extends Border {
    private char borderChar; // 장식 문자

    //내용물이 될 Display와 장식 문자를 지정
    public UpDownBorder(Display display,char ch) {
        super(display);
        this.borderChar = ch;
    }

    @Override
    public int getColumns() {
        //문자 수는 내용물의 양쪽에 장식 문자만큼 더한 것
        return display.getColumns();
    }

    @Override
    public int getRows() {
        //행수는 내용물의 행수와 같다
        return 1+display.getRows()+1;
    }

    @Override
    public String getRowText(int row) {
        if (row == 0) {// 상단테두리
            return makeLine(borderChar, display.getColumns()) ;
        } else if (row== display.getRows()+1) { // 하단 테두리
            return makeLine(borderChar, display.getColumns()) ;
        }else { // 기타
            return display.getRowText(row-1);
        }
    }

    //문자 ch로 count 수만큼 연속한 문자열을 만든다
    private String makeLine(char ch,int count) {
        StringBuilder line = new StringBuilder();
        for(int i=0; i<count; i++) {
            line.append(ch);
        }
        return line.toString();
    }
}