package Chapter1;

public class Book {
    private String name;

    public Book(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
class Book2 extends Book {

    public Book2(String name) {
        super(name);
    }
}