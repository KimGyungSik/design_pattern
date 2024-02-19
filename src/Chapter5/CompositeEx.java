package Chapter5;
// 디렉터리와 파일은 서로 다르지만, 둘 다 '디렉터리 안에 넣을 수 있는 것'
// 디렉터리와 파일을 합쳐서 '디렉터리 엔트리'
// '디렉터리 엔트리'라는 이름으로 디렉터리와 파일을 같은 종류로 간주하는(동일시하는)것입니다
// Composite 패턴 : 그릇과 내용물을 동일시하여 재귀적인 구조를 만드는 패턴
// 복수와 단수의 동일시라고도 불림 즉, 여러 개를 모아서 마치 하나의 것처럼 취급하는 것
// Leaf역 : 내용물을 뜻함, File클래스
// Composite역 : 그릇을 뜻함, Leaf나 Composite역을 넣을 수 있음, Directory클래스
// Component역 : Leaf역과 Composite역을 동일시하기 위한 역할, Entry 클래스
// Client 역 : Composite패턴의 사용자, main클래스


import java.util.ArrayList;
import java.util.List;

public class CompositeEx {
    public static void main(String[] args) {
        System.out.println("Making root entries,,,");
        Directory rootdir = new Directory("root");
        Directory bindir = new Directory("bin");
        Directory tmpdir = new Directory("tmp");
        Directory usrdir = new Directory("usr");
        rootdir.add(bindir);
        rootdir.add(tmpdir);
        rootdir.add(usrdir);
        bindir.add(new File("vi",10000));
        bindir.add(new File("latex",20000));
        rootdir.printList();
        System.out.println();
        System.out.println("Making user entries,,,");
        Directory youngin = new Directory("youngjun");
        Directory gildong = new Directory("gildong");
        Directory dojun = new Directory("dojun");
        usrdir.add(youngin);
        usrdir.add(gildong);
        usrdir.add(dojun);
        youngin.add(new File("diary.html",100));
        youngin.add(new File("Composite.java",200));
        gildong.add(new File("memo.tex",300));
        dojun.add(new File("game.doc",400));
        dojun.add(new File("junk.mail",500));
        rootdir.printList();
    }
}
abstract class Entry {
    // 이름을 얻는다
    public abstract String getName();

    // 크기를 얻는다
    public abstract int getSize();

    // 목록을 표시한다
    public void printList() {
        printList("");
    }
    //prefix를 앞에 붙여서 목록을 표시한다
    protected abstract void printList(String prefix);

    // 문자열 표시
    public String toString() {
        return getName()+" ("+getSize()+")";
    }
}

class File extends Entry {
    private String name;
    private int size;

    public File(String name,int size) {
        this.name = name;
        this.size= size;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix+"/"+this);
    }
}

class Directory extends Entry {
    private String name;
    private List<Entry> directory = new ArrayList<>();

    public Directory(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getSize() {
        int size = 0;
        for(Entry entry : directory) {
            size += entry.getSize();
        }
        return size;
    }

    @Override
    protected void printList(String prefix) {
        System.out.println(prefix+"/"+this);
        for(Entry entry:directory) {
            entry.printList(prefix+"/"+name);
        }
    }

    //디렉터리 엔트리를 디렉터리에 추가한다
    public Entry add(Entry entry) {
        directory.add(entry);
        return this;
    }
}



















