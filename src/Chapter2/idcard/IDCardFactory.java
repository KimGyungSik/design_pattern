package Chapter2.idcard;

import Chapter2.framework.Factory;
import Chapter2.framework.Product;

public class IDCardFactory extends Factory {
    private int serial = 100;
    @Override
    protected synchronized Product createProduct(String owner) {
        return new IDCard(owner,serial++);
    }

    @Override
    protected void registerProduct(Product product) {
        System.out.println(product+"을 등록헀습니다.");
    }
}
