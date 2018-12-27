package module_12.Lessons_.src;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MyHttpHandler extends DefaultHandler {
    private static HashMap<String, TreeLinks> links = new HashMap<>();
    private List<TreeLinks> insideLinks = new ArrayList<>();
    private int deep;
    private String rootLink;

    public MyHttpHandler(int deep, String rootLink){
        this.deep = deep;
        this.rootLink = rootLink;
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        if (qName.equals("a")){
            String link = attributes.getValue("href");
            if (link != null){
                TreeLinks treeLink = new TreeLinks(deep, rootLink, link, null);
                insideLinks.add(treeLink);
                if (links.containsKey(link)){
                    if (treeLink.getInsideLinks().size() > links.get(link).getInsideLinks().size())
                        links.put(link, treeLink);
                } else links.put(link, treeLink);
            }
        }
    }

    public List<TreeLinks> getInsideLinks() {
        return insideLinks;
    }

    public static HashMap<String, TreeLinks> getLinks(){
        return links;
    }
}
