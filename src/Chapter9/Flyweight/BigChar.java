package Chapter9.Flyweight;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class BigChar {
    // 문자의 이름
    private char charname;
    // 큰 문자를 표현하는 문자열
    private String fontdata;

    public BigChar(char charname) {
        this.charname = charname;
        try {
            String filename = "big"+ charname + ".txt";
            StringBuilder sb = new StringBuilder();
            for (String line: Files.readAllLines(Path.of(filename))) {
                sb.append(line);
                sb.append("\n");
            }
            this.fontdata = sb.toString();
        }catch (IOException e) {
            this.fontdata = charname + "?";
        }
    }

    // 큰 문자를 표시한다.
    public void print() {
        System.out.print(fontdata);
    }
}
