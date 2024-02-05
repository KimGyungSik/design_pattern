package Chapter3;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class SingletonTest {
    public static void main(String[] args) {
        System.out.println("Start");
        Triple a1 = Triple.getInstance("ALPHA");
        Triple a2 = Triple.getInstance("ALPHA");
        Triple b1 = Triple.getInstance("BETA");
        Triple b2 = Triple.getInstance("BETA");
        Triple c1= Triple.getInstance("GAMMA");
        Triple c2 = Triple.getInstance("GAMMA");
        if(a1==a2) System.out.println("a1==a2");
        if(b1==b2) System.out.println("b1==b2");
        if(c1==c2) System.out.println("c1==c2");
    }
}
class Triple {
    private static Map<String,Triple> map = new HashMap<>();
    static  {
        String[] names = {"ALPHA","BETA","GAMMA"};
        Arrays.stream(names).forEach(s->map.put(s,new Triple(s)));
    }
    private String name;

    private Triple(String name) {
        System.out.println("The instance "+name+"is created");
        this.name = name;
    }
    public static Triple getInstance(String name) {
        return map.get(name);
    }

    @Override
    public String toString() {
        return this.name;
    }
}
// enum을 사용한 방법
//enum Triple {
//    ALPHA,BETA,GAMMA;
//    String name;
//    private Triple() {
//    }
//    Triple getInstance(String name) {
//        return valueOf(name);
//    }
//}