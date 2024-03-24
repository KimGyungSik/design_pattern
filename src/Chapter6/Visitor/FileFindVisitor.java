package Chapter6.Visitor;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileFindVisitor extends Visitor{

    // 지정된 확장자로 된 파일을 모으는 클래스

    private String prefix; // 확장자 이름
    // File 을 모으는 List
    private List<File> fileList = new ArrayList<>();
    public FileFindVisitor(String prefix) {
        this.prefix = prefix;
    }

    // List목록에 대한 접근을 위한 Iterator
    public List<File> getFoundFiles() {
        return fileList;
    }
    // File 방문 시
    @Override
    public void visit(File file) {
        //해당 prefix를 이름으로 갖고있는지 확인
        if(file.getName().contains(prefix)) {
            // 갖고있으면 list에 넣어주기
            fileList.add(file);
        }
    }

    // Directory 방문 시
    @Override
    public void visit(Directory directory) {
        for(Entry entry: directory) {
            entry.accept(this);
        }
    }
}
