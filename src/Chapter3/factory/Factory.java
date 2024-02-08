package Chapter3.factory;

public abstract class Factory {
    // getFactory로 공장을 만들고 변수 factory에 대입 이후에 factory를 사용해 Link와 Tray를 만들고, Tray 안에 Link나 Tray를 넣은 후 마지막에
    // Page를 만들어 output을 실행
    public static Factory getFactory(String classname) {
        Factory factory = null;
        try {
            factory = (Factory) Class.forName(classname).getDeclaredConstructor().newInstance();
        }catch (ClassNotFoundException e) {
            System.out.println(classname+"클래스가 발견되지 않았습니다");
        }catch (Exception e) {
            e.printStackTrace();
        }
        return factory;
    }

    public abstract Link createLink(String caption,String url);
    public abstract Tray createTray(String caption);
    public abstract Page createPage(String title,String author);
}
