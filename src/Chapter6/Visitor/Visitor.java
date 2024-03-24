package Chapter6.Visitor;

public abstract class Visitor {

        public abstract void visit(File file); // File클래스가 호출하는 메서드
        public abstract void visit(Directory directory); // Directory클래스가 호출하는 메서드

}
