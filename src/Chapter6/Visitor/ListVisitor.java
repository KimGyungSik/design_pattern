package Chapter6.Visitor;

import java.util.Iterator;

public class ListVisitor extends Visitor {
    // 현재 주목하는 디렉터리 이름
    private String currentdir ="";

    // File 방문 시

    @Override
    public void visit(File file) {
        System.out.println(currentdir+"/"+file);
    }
    // Directory 방문 시

    @Override
    public void visit(Directory directory) {
        System.out.println(currentdir+"/"+directory);
        String savedir = currentdir;
        currentdir = currentdir+"/"+directory.getName();
        for(Entry entry: directory) {
            entry.accept(this);
        }
//        Iterator<Entry> it = directory.iterator();
//        while (it.hasNext()) {
//            Entry entry = it.next();
//            entry.accept(this);
//        }
        currentdir = savedir;
    }
}
