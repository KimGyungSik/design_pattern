package Part1;
// Iterator패턴 : 무엇인가 많이 모여 있을 떄 이를 순서대로 가리키며 전체를 검색하고 처리를 반복하는 것
// Iterator : 무엇인가를 '반복하다'
// Iterable<E> : 집합체를 나타내는 인터페이스 -> public abstract Iterator<E> iterator(); 라는 추상메서드를 가지고 있음
// Iterator<E> : 하나하나의 요소처리르 반복하기 위한 것 (루프 변수와 같은 역할)
// hasNext() : 다음에 next메서드를 호출해도 괜찮은지 알아보는 메소드
// next() : 현재 요소를 반환하고 다음 위치로 진행

// Iterator 패턴의 등장인물
// 반복자 역할 : Iterator<E>
// 구체적인 반복자 역할 : BookShelfIterator
// 집합체 역할 : Iterator를 만들어 내는 인터페이스(API) -> Iterable<E>
// 구체적인 집합체 역할 : BookShelf

// "추상 클래스나 인터페이스르 사용하여 프로그래밍한다"
import java.util.Iterator;
import java.util.NoSuchElementException;

public class IteratorEx {
    public static void main(String[] args) {
        BookShelf bookShelf = new BookShelf(4); // 책장의 크기는 4
        bookShelf.appendBook(new Book("Around the World in 80 Days"));
        bookShelf.appendBook(new Book("Bible"));
        bookShelf.appendBook(new Book("Cinderella"));
        bookShelf.appendBook(new Book("Daddy-Long-Legs"));

        //명시적으로 Iterator를 사용하는 방법 -> 변경사항이 생겨도 이 while문은 변경 x
        Iterator<Book> it = bookShelf.iterator();
        while (it.hasNext()) {
            Book book = it.next();
            System.out.println(book.getName());
        }
        System.out.println();

        // 확장 for문을 사용하는 방법
        for(Book book : bookShelf) {
            System.out.println(book.getName());
        }
        System.out.println();
    }
}


