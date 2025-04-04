package Chapter9.Flyweight;

import java.util.HashMap;
import java.util.Map;

public class BigCharFactory {
    // 이미 만든 BigChar 인스턴스를 관리
    private final static Map<String, BigChar> pool = new HashMap<>();
    // Singleton 패턴
    private static BigCharFactory singleton = new BigCharFactory();

    private BigCharFactory() {}

    // 유일한 인스턴스 생성
    public static BigCharFactory getInstance() {
        return singleton;
    }

    // BigChar 인스턴스 생성(공유)
    public synchronized BigChar getBigChar(char charname) {
        BigChar bc = pool.get(String.valueOf(charname));
        if(bc==null) {
            // 여기서 BigChar 인스턴스를 생성
            bc = new BigChar(charname);
            pool.put(String.valueOf(charname),bc);
        }
        return bc;
    }
}
