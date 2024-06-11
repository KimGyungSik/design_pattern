package Chapter9.Flyweight;

import Chapter9.Flyweight.BigChar;
import Chapter9.Flyweight.BigCharFactory;

public class BigString {
    // '큰 문자'의 배열
    private BigChar[] bigChars;

    public BigString(String string) {
        BigCharFactory factory = BigCharFactory.getInstance();
        bigChars = new BigChar[string.length()];
        for(int i = 0; i< bigChars.length; i++) {
            bigChars[i] = factory.getBigChar(string.charAt(i));
        }
    }

    // 표시
    public void print() {
        for(BigChar bc : bigChars) {
            bc.print();
        }
    }
}
