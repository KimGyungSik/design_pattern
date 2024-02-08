package Chapter3;

// 추상적인 공장에서는 추상적인 부품을 조합하여 추상적인 제품을 만들어낸다
// 부품의 구체적인 구현에는 주목하지 않고 인터페이스에 주목한다 그리고 그 인터페이스만 사용해서 부품을 조립하고 제품으로 완성하는 것
// 하위 클래스에서 구체적인 공장이 등장하고 구체적인 부품을 조립하여 구체적인 제품을 만든다
// 클래스나 생성자와 같은 프로그램의 구성 요소를 (컴파일러가 다루지 않고) 프로그램 자신이 다루고 있는 것을 리플렉션(reflection)이라고 함
// 추상적인 제품 : Link, Tray, Page
// 추상적인 공장 : Factory
// Main클래스는 추상적인 공장과 제품만을 사용해 작업한다. 구체적인 부품이나 공장에 관해선 몰라도된다.
// 구체적인 제품 : LinkList,ListTray,Listpage
// 구체적인 공장 : ListFactory

import Chapter3.factory.Factory;
import Chapter3.factory.Link;
import Chapter3.factory.Page;
import Chapter3.factory.Tray;

public class AbstractFactoryEx {
    public static void main(String[] args) {
        if(args.length!=2) {
            System.out.println("Usage: java Main filename.html class.name.of.ConcreteFactory");
            System.out.println("Example 1:java Mian list.html listfactory.ListFactory");
            System.out.println("Example 2:java Mian list.html divfactory.DivFactory");
        }
        String filename = args[0];
        String classname = args[1];

        Factory factory = Factory.getFactory(classname);

        //Blog
        Link blog1 = factory.createLink("Blog 1","https://example.com/blog1");
        Link blog2 = factory.createLink("Blog 2","https://example.com/blog2");
        Link blog3 = factory.createLink("Blog 3","https://example.com/blog3");

        Tray blogTray = factory.createTray("Blog Site");
        blogTray.add(blog1);
        blogTray.add(blog2);
        blogTray.add(blog3);

        //News
        Link news1 = factory.createLink("News 1", "https://example.com/news1");
        Link news2 = factory.createLink("News 2", "https://example.com/news2");
        Tray news3 = factory.createTray("News3");
        news3.add(factory.createLink("News 3 (US)","https://example.com/news3us"));
        news3.add(factory.createLink("News 3 (Korea)","https://example.com/news3korea"));

        Tray newsTray = factory.createTray("News Site");
        newsTray.add(news1);
        newsTray.add(news2);
        newsTray.add(news3);

        //Page
        Page page = factory.createPage("Blog and News","Youngjin.com");
        page.add(blogTray);
        page.add(newsTray);

        page.output(filename);
    }
}
