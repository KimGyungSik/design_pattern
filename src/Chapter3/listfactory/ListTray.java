package Chapter3.listfactory;

import Chapter3.factory.Item;
import Chapter3.factory.Tray;

public class ListTray extends Tray {
    public ListTray(String caption) {
        super(caption);
    }

    @Override
    public String makeHTML() {
        StringBuffer sb = new StringBuffer();
        sb.append("<li>\n");
        sb.append(caption);
        sb.append("\n<ul>\n");
        for(Item item: tray) {
            sb.append(item.makeHTML());
        }
        sb.append("\n<ul>\n");
        sb.append("<li>\n");
        return sb.toString();
    }
}
