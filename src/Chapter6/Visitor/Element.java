package Chapter6.Visitor;

public interface Element {
    // 방문자를 받아들이는 인터페이스
    void accept(Visitor v); // accept : 받아들인다
}
