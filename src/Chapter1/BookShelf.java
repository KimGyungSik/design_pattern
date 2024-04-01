package Chapter1;

import java.util.ArrayList;
import java.util.Iterator;

public class  BookShelf implements Iterable<Book> {
//    private Book[] books;
    private ArrayList<Book> arrayList;
//    private int last = 0;

    public BookShelf(int maxsize) {
        this.arrayList = new ArrayList<Book>(maxsize);
    }

    public Book getBookAt(int index) {
        return arrayList.get(index);
    }

    public void appendBook(Book book) {
        this.arrayList.add(book);
    }

    public int getLength() {
        return arrayList.size();
    }

    @Override
    public Iterator<Book> iterator() {
        return new BookShelfIterator(this);
    }
    // 내가 가진 요소를 차례로 검색해 주는 사람
}
