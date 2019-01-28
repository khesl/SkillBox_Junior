package module_12.Lessons_.src;

import Utils.ConsoleColor;
import Utils.MyUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyHttpScanner_2 extends Thread{
    private String rootLink;
    private TreeLinks treeLink;
    private String filePath;
    private volatile boolean freeScanner = true; // занят ли поток
    private volatile boolean finishResult = false; // выполнен и есть готовые данные
    private volatile Map<Integer, TreeLinks> links = new HashMap<>();
    private String scannerName;
    private boolean isDebugMode = false;

    public MyHttpScanner_2(TreeLinks treeLink, String filePath, String scannerName){
        this.treeLink = treeLink;
        this.filePath = filePath;
        this.rootLink = treeLink.getRootLink();
        finishResult = false;
        this.scannerName = scannerName;
    }

    public void resetData(TreeLinks treeLink, String filePath) {
        this.treeLink = treeLink;
        this.filePath = filePath;
        this.rootLink = treeLink.getRootLink();
        finishResult = false;
    }

    @Override
    public void run(){
        while(!interrupted()){
            //if (treeLink.isInsideLinksEmpty()){
            if (freeScanner && !finishResult){
                try {
                    freeScanner = false;
                    long start = System.currentTimeMillis();
                    MyUtils.downloadUrl(treeLink.getHref(), filePath);
                    //treeLink = parseHttpCode(filePath, treeLink);
                    treeLink = FillTreeLinks_v2();

                    finishResult = true;
                    System.out.print("download time (" + (System.currentTimeMillis() - start) + " ms)");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public String getStatus(){
        if (!freeScanner) return ConsoleColor.setColor("processing: load file (" + (new File(filePath)).length() + " byte), loading: '" + treeLink.getHref() + "'", ConsoleColor.ANSI_YELLOW);
        return ConsoleColor.setColor("thread is free.", ConsoleColor.ANSI_YELLOW);
    }

    private static TreeLinks parseHttpCode(String path, TreeLinks treeLink){
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

    public TreeLinks parseHttpCode(){
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser parser = factory.newSAXParser();
            MyHttpHandler handler = new MyHttpHandler(treeLink.getDeep(), treeLink.getHref());
            parser.parse(new File(filePath), handler);
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

    public TreeLinks FillTreeLinks_v2() throws IOException {
        int deep = treeLink.getDeep();
        String link = treeLink.getHref();
        if (MyHttpScannerController.getUniqueLinksProcessed().contains(link)) {
            freeScanner = true;
            return MyHttpScannerController.getUniqueLinks().get(link);
        }

        System.out.print(deep + ":" + "start deep (" + deep + ") ");
        // https://course.skillbox.ru/webdev/
        TreeLinks treeLinks = new TreeLinks(deep, rootLink, link);
        System.out.print(ConsoleColor.setColor(treeLinks.getHref(), ConsoleColor.ANSI_BLUE));

        Document doc = Jsoup.parse(new File(filePath), "UTF-8");
        Elements elements = doc.select("a");
        System.out.println(" count child: " + elements.size());
        for (Element element : elements) {
            // для каждого элемента будем пробегать внутрь
            String src = element.attr("href");
            if (isDebugMode) System.out.println(deep + ":" + "parent: '" + link + "'");
            if (isDebugMode) System.out.print("\telement.attr(\"href\"): '" + src + "'");

            //была ли уже такая ссылка?
            if (MyHttpScannerController.getUniqueLinks().containsKey(src)){
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                child.setInsideLinks(MyHttpScannerController.getUniqueLinks().get(src).getInsideLinks());
                if (isDebugMode) System.out.println(deep + ":" + "link " + child + " was inspected before.");
                treeLinks.addInsideLinks(child);
                continue;
            }

            if (src.equals("/") || src.equals("#") || src.equals("index.html")) { if (isDebugMode)  System.out.println(deep + ":" + ConsoleColor.setColor("\titself ", ConsoleColor.ANSI_RED)); continue; }
            if (src.length() == 0) continue;
            if (Pattern.compile("^#{1}.+$").matcher(src).matches()) {if (isDebugMode) System.out.println(deep + ":" + ConsoleColor.setColor("\tmenu links ", ConsoleColor.ANSI_RED)); continue; }
            if (Pattern.compile("^\\.\\./.+$").matcher(src).matches()) {
                if (isDebugMode) System.out.println(deep + ":" + ConsoleColor.setColor("\tback links ", ConsoleColor.ANSI_RED));
                String link_ = link;
                if (link_.contains(".html") || link_.contains(".htm")){
                    Matcher m = Pattern.compile("(^http[s]?://)(.+/)").matcher(link);
                    while (m.find()) { link_ = m.group(0); }
                }
                if (!src.contains("https:") && !src.contains("http:")){
                    if (src.substring(0, 1).equals("/")) src = rootLink + src;
                    else src = link + src;
                }
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                //links.put(getLinksKey(), child);
                MyHttpScannerController.putUniqueLinks(child.getHref(), child);
                if (isDebugMode) System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
                treeLinks.addInsideLinks(child);
                continue;
            }
            //if (!Pattern.compile("^.+/{1}$").matcher(link).matches() && !Pattern.compile("^/{1}.+$").matcher(src).matches()) src = "/" + src;
            if ((link.contains(".html") || link.contains(".htm")) && (src.contains(".html") || src.contains(".htm"))) {
                // переход внутри?
                if (isDebugMode)  System.out.println(deep + ":" + link + "|||" + src);
                Matcher m = Pattern.compile("(^http[s]?://)(.+/)").matcher(link);
                while (m.find()) { link = m.group(0); }
                if (isDebugMode)  System.out.println(deep + ":" + "new link: '" + link + "'");
                //если проход во внуть, то нужно вернуться?
                // но сначала добавить в карту
                if (!src.contains("https:") && !src.contains("http:")){
                    if (src.substring(0, 1).equals("/")) src = rootLink + src;
                    else src = link + src;
                }
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                //links.put(getLinksKey(), child);
                MyHttpScannerController.putUniqueLinks(child.getHref(), child);
                if (isDebugMode) System.out.println("add link, deep \t(" + (deep + 1) + ")" + child);
                treeLinks.addInsideLinks(child);
                continue;
            }
            if (isDebugMode) System.out.println("");
            if (src.contains(".html") || src.contains(".htm")) {
                // вкладка?
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                //links.put(getLinksKey(), child);
                MyHttpScannerController.putUniqueLinks(child.getHref(), child);
                if (isDebugMode) System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
                continue;
            }

            if (!src.contains("https:") && !src.contains("http:")){
                if (src.substring(0, 1).equals("/")) {
                    if (rootLink.substring(rootLink.length()-1, rootLink.length()).equals("/"))
                        src = rootLink.substring(0, rootLink.length()-1) + src;
                    else src = rootLink + src;
                } else src = link + src;
            }

            // теперь проверить на параметризацию
            // TODO: доработать паттерн! чтобы выявлять такие https://docs.oracle.com/en/solutions/solutions.html?type=design и другие зацикленные штуки
            if (Pattern.compile("(^http[s]?://(.+/)+)(.+[?]){1}(.+)").matcher(src).matches()) {
                // "^http[s]?://(.+/)+(.+[?])?((.+=.+)([&])?)+"
                Matcher m = Pattern.compile("(^http[s]?://(.+/)+)(.+[?]){1}(.+)").matcher(src);
                boolean restExist = false;
                while (m.find()) {
                    String temp = m.group(1);
                    String temp_ = "";
                    if (temp.substring(temp.length()-1, temp.length()).equals("/")) temp_ = temp.substring(0, temp.length()-1);
                    if (isDebugMode) System.out.println(deep + ":" + "src :" + src);
                    if (isDebugMode) System.out.println(deep + ":" + "temp:" + temp + "\t" + temp_);
                    if (isDebugMode) System.out.println(deep + ":" + "link:" + link);
                    if (link.equals(temp) || link.equals(temp_)) restExist  = true;
                }
                if (restExist) { if (isDebugMode) System.out.println(ConsoleColor.setColor(deep + ":" + "\trest itself", ConsoleColor.ANSI_RED)); continue; }
            }

            if (isDebugMode) System.out.println(deep + ":" + "next resource: '" + src + "'");

            if (isLinkContain(src)) continue;

            //int key = getLinksKey();
            TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
            //links.put(key, child);
            MyHttpScannerController.putUniqueLinks(child.getHref(), child);
            if (isDebugMode) System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
            // не заходим внутрь если не из базового домена
            if (isDebugMode) System.out.println(deep + ":" + "update link, deep \t(" + (deep + 1) + ")" + child);
            treeLinks.addInsideLinks(child);
        }
        System.out.println(deep + ":" + "and back ^ to (" + (deep-1) + ")");
        return treeLinks;
    }

    private boolean isLinkContain(String src){
        for (Map.Entry entry : links.entrySet()) {
            if (((TreeLinks)entry.getValue()).getHref().equals(src)) {
                // находим уже обработанный объект
                System.out.println(ConsoleColor.setColor("already contain: ", ConsoleColor.ANSI_RED)
                        + ConsoleColor.setColor(src, ConsoleColor.ANSI_YELLOW));
                return true;
            }
        }
        return false;
    }

    private static boolean isSiteHttpLink(String link){
        if (link.contains(".css"))  return false;
        if (link.contains(".ico"))  return false;
        if (link.contains("#"))     return false;
        if (link.contains("../"))   return false;
        return true;
    }

    public TreeLinks getTreeLink() throws NoSuchFieldException {
        if (!finishResult) throw new NoSuchFieldException("Finish data is not present!");
        finishResult = false;
        freeScanner = true;
        return treeLink;
    }

    public boolean isFreeScanner(){ return freeScanner; }
    public boolean isFinishResult() { return finishResult; }

    public String getScannerName() {
        return scannerName;
    }

    public String toString(){
        return "ScanName:'" + getScannerName() + "', isFreeScanner:'" + isFreeScanner() + "', isFinishResult:'" + isFinishResult() + "'";
    }

}
