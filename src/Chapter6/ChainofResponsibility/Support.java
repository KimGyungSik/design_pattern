package Chapter6.ChainofResponsibility;

import java.util.LinkedList;

public abstract class Support {
    LinkedList a = new LinkedList();
    private String name; // 이 트러블 해결자 이름
    private Support next; // 떠넘길 곳

    public Support(String name) {
        this.name = name;
        this.next =null;
    }

    //떠넘길 곳을 설정한다
    public Support setNext(Support next) {
        this.next = next;
        return next;
    }

    //트러블 해결 절차를 결정한다.
    public void support(Trouble trouble) {
//        if(resolve(trouble)) { // 해결
//            done(trouble);
//        }else if (next!=null) { // 해걀 x 다음 o
//            next.support(trouble);
//        }else { // ㅎㅐ결 x 다음 o
//            fail(trouble);
//        }
        // 재귀적 호출 로직구성시 종료조건이 중요!
        // 1. 종료조건 : 해결
        // 2. 진행 : 해결되지 않고 다음 객체 존재
        // 3. 실패

//        boolean done = false;
//        while (!resolve(trouble) && !done) {
//            if (next != null) {
//                done = next.support(trouble);
//            } else {
//                fail(trouble);
//                break;
//            }
//        }
//
//        if (done) {
//            done(trouble);
//        }

        Support o = this;
        while (true) {
            if (o.resolve(trouble)) {
                o.done(trouble);
                break;
            } else if (o.next == null) {
                o.fail(trouble);
                break;
            }
            o = o.next;
        }

    }

    //트러블 해결자의 문자열 표현

    @Override
    public String toString() {
        return "["+name+"]";
    }

    // 해결하려고 한다
    protected abstract boolean resolve(Trouble trouble);

    // 해결했다
    protected void done(Trouble trouble) {
        System.out.println(trouble+"is resolved by"+this+".");
    }
    // 해결되지 않았따
    protected void fail(Trouble trouble) {
        System.out.println(trouble + "cannot be resolved");
    }

}
