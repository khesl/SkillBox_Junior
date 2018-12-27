package module_12.Lessons_.src;

import Utils.ConsoleColor;

import java.util.ArrayList;
import java.util.List;

public class TreeLinks {
    private int deep;
    private String rootLink;
    private String href;
    private List<TreeLinks> insideLinks = new ArrayList<>();

    public TreeLinks(int deep, String rootLink, String href){
        this.deep = deep;
        this.rootLink = rootLink;
        this.href = href;
    }

    public TreeLinks(int deep, String rootLink, String href, List<TreeLinks> insideLinks){
        this(deep, rootLink, href);
        this.insideLinks = insideLinks;
    }

    public int getDeep() { return deep; }
    public void setDeep(int deep) { this.deep = deep; }

    public String getRootLink() { return rootLink; }
    public void setRootLink(String rootLink) { this.rootLink = rootLink; }

    public String getHref() { return href; }
    public void setHref(String href) { this.href = href; }

    public List<TreeLinks> getInsideLinks() { return insideLinks; }
    public void setInsideLinks(List<TreeLinks> insideLinks) { this.insideLinks = insideLinks; }
    public void addInsideLinks(TreeLinks link) { this.insideLinks.add(link); }
    public boolean isInsideLinksEmpty() { return insideLinks.size() == 0; }

    @Override
    public String toString(){
        String str = "";
        for (int i=0;i<deep;i++) str+= "\t";
        str += ConsoleColor.setColor(href, ConsoleColor.ANSI_BLUE) + ConsoleColor.setColor("\t(child count: " + insideLinks.size()  + ")", ConsoleColor.ANSI_RED);
        return str;
    }
    public String toAllString(){
        String str = toString() + "\n";
        for (TreeLinks childs : insideLinks)
            str += childs.toAllString();
        return str;
    }

}
