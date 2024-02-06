package Chapter3.framework;

public interface Product extends Cloneable {
    void use(String s); // '사용'하기 위한 메소드
    Product createCopy(); // 인스턴스를 복제하기 위한 메서드
}
