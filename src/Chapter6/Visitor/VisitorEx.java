package Chapter6.Visitor;

// Visitor패턴  : 데이터 구조와 처리를 분리
// 데이터 구조 안을 돌아다니는 주체인 '방문자'를 나타내느 클래스를 준비하고 그 클래스에 처리를 맡김
// 새로운 처리를 추가하고 싶을 때는 새로운 '방문자'를 만들면 된다. 데이터 구조 쪽에서는 문을 두드리는 '방문자'를 받아 주면 된다.
// Visitor 패턴의 목적은 처리를 데이터 구조와 분리하는 것, File클래스나 Directory클래스의 부품으로서의 독립성을 높여 준다.
// The Open-Closed Principle - 확장에 대해서는 열고, 수정에 대해서는 닫는다.
// => 기존 클래스를 수정하지 않고 확장할 수 있게 하는것
// 확장에 대해서는 열려 있고 수정에 대해서는 닫혀 있는 클래스가 부품으로서의 재사용성이 높은 클래스다
// 디자인 패턴의 목적, 객체지향의 목적은 바로 그런 클래스를 만드는 구조를 제공하는 것
import java.util.Iterator;

public class VisitorEx {
    public static void main(String[] args) {
        System.out.println("Making root entries...");
        Directory rootdir = new Directory("root");
        Directory bindir = new Directory("bin");
        Directory tmpdir = new Directory("tmp");
        Directory usrdir = new Directory("usr");
        rootdir.add(bindir);
        rootdir.add(tmpdir);
        rootdir.add(usrdir);
        bindir.add(new File("vi",10000));
        bindir.add(new File("latex",20000));
        rootdir.accept(new ListVisitor());
        System.out.println();

        System.out.println("Making user entries...");
        Directory youngin = new Directory("youngjun");
        Directory gildong = new Directory("gildong");
        Directory dojun = new Directory("dojun");
        usrdir.add(youngin);
        usrdir.add(gildong);
        usrdir.add(dojun);
        youngin.add(new File("diary.html",100));
        youngin.add(new File("Composite.java",200));
        gildong.add(new File("memo.tex",300));
        gildong.add(new File("index.html",350));
        dojun.add(new File("game.doc",400));
        dojun.add(new File("junk.mail",500));
//        rootdir.accept(new ListVisitor());

        FileFindVisitor ffv = new FileFindVisitor(".html");
        rootdir.accept(ffv);
        System.out.println("HTML files are..");
        for(File file : ffv.getFoundFiles()) {
            System.out.println(file);
        }
    }
}



