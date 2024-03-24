package Chapter6.Visitor;

import java.util.Iterator;

// Directory클래스 getSize메서드 대신 크기를 얻는 처리를 하는 클래스
public class SizeVisitor extends Visitor{

    private int size; // 파일의 크기

    //File 방문 시
    @Override
    public void visit(File file) {
        size += file.getSize();
    }
    // Directory 방문 시
    @Override
    public void visit(Directory directory) {
        for(Entry entry: directory) {
            entry.accept(this);
        }
    }
    public int getSize() {
        return size;
    }
}
