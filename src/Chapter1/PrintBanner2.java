package Chapter1;

public class PrintBanner2 extends Print2 {
    private Banner banner;
    public PrintBanner2(String string) {
        banner = new Banner(string);
    }

    @Override
    public void printWeak() {
        banner.showWithParen();
    }

    @Override
    public void printStrong() {
        banner.showWithAster();
    }
}
