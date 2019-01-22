package module_12.Lessons_.src;

import Utils.MyUtils;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

public class MyHttpScanner extends Thread{
    private String rootLink;
    private TreeLinks treeLink;
    private String filePath;
    private volatile boolean freeScanner = true; // занят ли поток
    private volatile boolean finishResult = false; // выполнен и есть готовые данные

    public MyHttpScanner(TreeLinks treeLink, String filePath){
        this.treeLink = treeLink;
        this.filePath = filePath;
        finishResult = false;
    }

    @Override
    public void run(){
        while(!interrupted()){
            //if (treeLink.isInsideLinksEmpty()){
            if (freeScanner && !finishResult){
                freeScanner = false;
                long start = System.currentTimeMillis();
                MyUtils.downloadUrl(treeLink.getHref(), filePath);
                treeLink = parseHttpCode(filePath, treeLink);
                finishResult = true;
                System.out.print("download time (" + (System.currentTimeMillis() - start) + " ms)");
            }
        }
    }

    public static TreeLinks parseHttpCode(String path, TreeLinks treeLink){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MyHttpHandler handler = new MyHttpHandler(treeLink.getDeep(), treeLink.getHref());
            parser.parse(new File(path), handler);
            treeLink.setInsideLinks(handler.getInsideLinks());
            System.out.println("new InsideLinks (" + treeLink.getInsideLinks().size() + ") for: " + treeLink);
            return treeLink;
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return null;
    }

    public TreeLinks getTreeLink() throws NoSuchFieldException {
        if (!finishResult) throw new NoSuchFieldException("Finish data is not present!");
        finishResult = false;
        freeScanner = true;
        return treeLink;
    }
}
