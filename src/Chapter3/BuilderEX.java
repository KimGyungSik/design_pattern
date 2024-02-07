package Chapter3;
// Builder 패턴 : 구조를 가진 인스턴스를 만들어 가는 패턴
// Builder 클래스 : 문서를 구성하기 위한 메소드를 규정한 추상 클래스
// Director 클래스 : 하나의 문서를 만드는 클래스
// TextBuiler 클래스 : 텍스트(일반 문자열)을 이용하여 문서를 만드는 클래스
// HTMLBuiler클래스 : HTML 파일을 이용하여 문서를 만드는 클래스
// Director는 실제로 동작하는 것이 TextBuilder인지 HTMLBuiler인지 의식하지 않는다
// why? Director는 Builer의 메서드만 사용하기 때문에
// 객체지향 프로그래밍에서는 '누가 무엇을 알고 있는가?가 매우 중요
// 즉 어느 클래스가 어느 메소드를 사용할 수 있는지(사용해도 좋은지)에 주의하여 프로그래밍할 필요가 있음
// Director 클래스가 자신이 이용하는 Builer클래스의 하위 클래스를 모르는 것은 매우 좋은일
// why? 모르기에 교체할 수 있기 때문임 => 부품으로서의 가치가 상승 => 교체 가능성을 클래스 설계자는 항상 염두에 둘 필요가 있음
// 의존성 주입(Dependancy Injection, DI ) : '소스코드에는 쓰여져 있지 않지만, 실제로는 이 인스턴스를 이용해(의존해) 동작해 주세요'라는 의미를 담아 인스턴스를 건네는 방법
// Builder가 필기구 , TextBuilder가 연필이나 볼펜 => 구체적인 필기구를 전달하는 것이 의존성 주입
public class BuilderEX {
    public static void main(String[] args) {
        TextBuilder textBuilder = new TextBuilder();
        Director director = new Director(textBuilder);
        director.construct();
        String result = textBuilder.getTextResult();
        System.out.println(result);
        HTMLBuilder htmlBuilder = new HTMLBuilder();
        Director director1 = new Director(htmlBuilder);
        director1.construct();
        String result2 = htmlBuilder.getHTMLresult();
        System.out.println(result2);
    }
}

abstract class Builder {
    public abstract void makeTitle(String title);
    public abstract void makeString(String str);
    public abstract void makeItems(String[] items);
    public abstract void close();
}

class Director {
    private Builder builder;

    // 생성자
    public Director(Builder builder) {
        this.builder = builder;
    }
    //문서를 만드는 메서드
    public void construct() {
        builder.makeTitle("Grreting");
        builder.makeString("일반적인 인사");
        builder.makeItems(new String[] {"How are you?","Hello","Hi"});
        builder.makeString("시간대별 인사");
        builder.makeItems(new String[] {
                "Good morning",
                "Good afternoon",
                "Good evening"
        });
        builder.close();
    }
}

class TextBuilder extends Builder {
    private StringBuffer sb = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        sb.append("===========================\n");
        sb.append("[");
        sb.append(title);
        sb.append("]\n\n");
    }

    @Override
    public void makeString(String str) {
        sb.append("ㅁ");
        sb.append(str);
        sb.append("]\n\n");
    }

    @Override
    public void makeItems(String[] items) {
        for(String s : items) {
            sb.append(" .");
            sb.append(s);
            sb.append("\n");
        }
        sb.append("\n");
    }

    @Override
    public void close() {
        sb.append("==============================\n");
    }
    public String getTextResult() {
        return sb.toString();
    }
}

class HTMLBuilder extends Builder {
    private String filename = "untitled.html";
    private StringBuffer sb = new StringBuffer();

    @Override
    public void makeTitle(String title) {
        filename = title+".html";
    }

    @Override
    public void makeString(String str) {

    }

    @Override
    public void makeItems(String[] items) {

    }

    @Override
    public void close() {

    }
    public String getHTMLresult() {
        return filename;
    }
}