package module_12.Lessons_;

import Utils.ConsoleColor;
import Utils.MyUtils;
import module_12.Lessons_.src.MyHttpScanner;
import module_12.Lessons_.src.MyHttpScannerController;
import module_12.Lessons_.src.TreeLinks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/** 12 модуль */
public class Lesson_5 {

    public static void main(String[] args) throws IOException, NoSuchFieldException, InterruptedException {
        //Lesson_5 less = new Lesson_5();
        ////TreeLinks links = less.FillTreeLinks("https://docs.oracle.com/en/");
        ////TreeLinks links = less.FillTreeLinks("https://lenta.ru/");
        //TreeLinks links = less.FillTreeLinks_v2(0,"https://lenta.ru/", "https://lenta.ru/");
        //System.out.println("loaded http map:\n" + links.toAllString());

        //MyHttpScannerController mhsc = new MyHttpScannerController(new TreeLinks(0, "https://lenta.ru/","https://lenta.ru/"));
        //
    }

    private volatile Map<Integer, TreeLinks> links = new HashMap<>();
    private volatile Map<String, TreeLinks> uniqueLinks = new HashMap<>();
    private volatile int key = 0;
    private int getLinksKey(){ return key++; }
    private static final String TEMP_URL_PATH = "module_12\\Lessons_\\src\\page.html";
    private TreeLinks FillTreeLinks(String link) throws IOException {
        String rootLink = link;
        Matcher m = Pattern.compile("(^http[s]?://)([^/]+/)").matcher(link);
        while (m.find()) { rootLink = m.group(0); }
        System.out.println("rootPath(" + rootLink + ")");
        return FillTreeLinks(0, rootLink, link);
    }
    /** на основе module_10.Lessons_.Lesson_6 */
    private TreeLinks FillTreeLinks(int deep, String rootLink, String link) throws IOException {
        System.out.print(deep + ":" + "start deep (" + deep + ") ");
        // - Написать программу, которая будет скачивать в папку все изображения с указанной веб­страницы.
        // https://course.skillbox.ru/webdev/
        TreeLinks treeLinks = new TreeLinks(deep, rootLink, link);
        System.out.print(ConsoleColor.setColor(treeLinks.getHref(), ConsoleColor.ANSI_BLUE));

        if (link.length() == 0 || (!link.contains("https:") && !link.contains("http:"))) {
            System.out.print("некорректная ссылка. Потом можно ловить эксепшн");
            return null;
        }
        System.out.print(" sleep..");
        try {
            Thread.sleep(Double.valueOf(2000 * Math.random()).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".wake up, ");
        boolean load = MyUtils.downloadUrl(link, TEMP_URL_PATH); // загрузили код
        if (!load) {
            /** проверяем корректность ссылки при наличии/отсутствии '/' в конце */
            if (link.substring(link.length()-1, link.length()).equals("/"))
                load = MyUtils.downloadUrl(link.substring(0, link.length() - 1), TEMP_URL_PATH); // загрузили код
            else
                load = MyUtils.downloadUrl(link + "/", TEMP_URL_PATH); // загрузили код
            if (!load) {
                System.out.println("Error with load URL '" + link + "'");
                return new TreeLinks(deep + 1, rootLink, link + "(Error spectate page)");
            }
        }
        System.out.print(", http loaded, ");

        Document doc = Jsoup.parse(new File(TEMP_URL_PATH), "UTF-8");
        Elements elements = doc.select("a");
        System.out.println("count child: " + elements.size());
        for (Element element : elements) {
            // для каждого элемента будем пробегать внутрь
            String src = element.attr("href");
            System.out.println(deep + ":" + "parent: '" + link + "'");
            System.out.print("\telement.attr(\"href\"): '" + src + "'");

            if (src.equals("/") || src.equals("#") || src.equals("index.html")) { System.out.println(deep + ":" + ConsoleColor.setColor("\titself ", ConsoleColor.ANSI_RED)); continue; }
            if (src.length() == 0) continue;
            if (Pattern.compile("^#{1}.+$").matcher(src).matches()) { System.out.println(deep + ":" + ConsoleColor.setColor("\tmenu links ", ConsoleColor.ANSI_RED)); continue; }
            if (Pattern.compile("^\\.\\./.+$").matcher(src).matches()) {
                System.out.println(deep + ":" + ConsoleColor.setColor("\tback links ", ConsoleColor.ANSI_RED));
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
                links.put(getLinksKey(), child);
                System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
                treeLinks.addInsideLinks(child);
                continue;
            }
            //if (!Pattern.compile("^.+/{1}$").matcher(link).matches() && !Pattern.compile("^/{1}.+$").matcher(src).matches()) src = "/" + src;
            if ((link.contains(".html") || link.contains(".htm")) && (src.contains(".html") || src.contains(".htm"))) {
                // переход внутри?
                System.out.println(deep + ":" + link + "|||" + src);
                Matcher m = Pattern.compile("(^http[s]?://)(.+/)").matcher(link);
                while (m.find()) { link = m.group(0); }
                System.out.println(deep + ":" + "new link: '" + link + "'");
                //если проход во внуть, то нужно вернуться?
                // но сначала добавить в карту
                if (!src.contains("https:") && !src.contains("http:")){
                    if (src.substring(0, 1).equals("/")) src = rootLink + src;
                    else src = link + src;
                }
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                links.put(getLinksKey(), child);
                System.out.println("add link, deep \t(" + (deep + 1) + ")" + child);
                treeLinks.addInsideLinks(child);
                continue;
            }
            System.out.println("");
            if (src.contains(".html") || src.contains(".htm")) {
                // вкладка?
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                links.put(getLinksKey(), child);
                System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
                continue;
            }

            if (!src.contains("https:") && !src.contains("http:")){
                if (src.substring(0, 1).equals("/")) src = rootLink + src;
                else src = link + src;
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
                    System.out.println(deep + ":" + "src :" + src);
                    System.out.println(deep + ":" + "temp:" + temp + "\t" + temp_);
                    System.out.println(deep + ":" + "link:" + link);
                    if (link.equals(temp) || link.equals(temp_)) restExist  = true;
                }
                if (restExist) {  System.out.println(ConsoleColor.setColor(deep + ":" + "\trest itself", ConsoleColor.ANSI_RED)); continue; }
            }

            System.out.println(deep + ":" + "next resource: '" + src + "'");

            if (isLinkContain(src)) continue;

            int key = getLinksKey();
            TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
            links.put(key, child);
            System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
            // не заходим внутрь если не из базового домена
            if (src.contains(rootLink) && isSiteHttpLink(src)){
                child = FillTreeLinks(deep + 1, rootLink, src);
                links.put(key, child); // перезаписываем если было изменение внутри
            }
            System.out.println(deep + ":" + "update link, deep \t(" + (deep + 1) + ")" + child);
            treeLinks.addInsideLinks(child);
        }
        System.out.println(deep + ":" + "and back ^ to (" + (deep-1) + ")");
        return treeLinks;
    }


    /** так, допустим это последняя ревизия проекта. на тот момент примерно 01.10.2019
     * мне нужно переделать эту функцию под поток. убрать рекурсивность, она просто собирает в главный set ссылки
     * и возвращает текущий список по сайту. Далее управление перехватит Main функция и раскидает этот список
     * по доступным потокам. да, возможно будет while, чтобы идти в глубь, либо ограничиться 4-5 (n) уровнями
     * вложенности. */
    private TreeLinks FillTreeLinks_v2(int deep, String rootLink, String link) throws IOException {
        System.out.print(deep + ":" + "start deep (" + deep + ") ");
        // https://course.skillbox.ru/webdev/
        TreeLinks treeLinks = new TreeLinks(deep, rootLink, link);
        System.out.print(ConsoleColor.setColor(treeLinks.getHref(), ConsoleColor.ANSI_BLUE));

        if (link.length() == 0 || (!link.contains("https:") && !link.contains("http:"))) {
            System.out.print("некорректная ссылка. Потом можно ловить эксепшн");
            return null;
        }
        System.out.print(" sleep..");
        try {
            Thread.sleep(Double.valueOf(2000 * Math.random()).intValue());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.print(".wake up, ");
        boolean load = MyUtils.downloadUrl(link, TEMP_URL_PATH); // загрузили код
        if (!load) {
            /** проверяем корректность ссылки при наличии/отсутствии '/' в конце */
            if (link.substring(link.length()-1, link.length()).equals("/"))
                load = MyUtils.downloadUrl(link.substring(0, link.length() - 1), TEMP_URL_PATH); // загрузили код
            else
                load = MyUtils.downloadUrl(link + "/", TEMP_URL_PATH); // загрузили код
            if (!load) {
                System.out.println("Error with load URL '" + link + "'");
                return new TreeLinks(deep + 1, rootLink, link + "(Error spectate page)");
            }
        }
        System.out.print(", http loaded, ");

        Document doc = Jsoup.parse(new File(TEMP_URL_PATH), "UTF-8");
        Elements elements = doc.select("a");
        System.out.println("count child: " + elements.size());
        for (Element element : elements) {
            // для каждого элемента будем пробегать внутрь
            String src = element.attr("href");
            System.out.println(deep + ":" + "parent: '" + link + "'");
            System.out.print("\telement.attr(\"href\"): '" + src + "'");

            //была ли уже такая ссылка?
            if (uniqueLinks.containsKey(src)){
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                child.setInsideLinks(uniqueLinks.get(src).getInsideLinks());
                System.out.println(deep + ":" + "link " + child + " was inspected before.");
                treeLinks.addInsideLinks(child);
                continue;
            }

            if (src.equals("/") || src.equals("#") || src.equals("index.html")) { System.out.println(deep + ":" + ConsoleColor.setColor("\titself ", ConsoleColor.ANSI_RED)); continue; }
            if (src.length() == 0) continue;
            if (Pattern.compile("^#{1}.+$").matcher(src).matches()) { System.out.println(deep + ":" + ConsoleColor.setColor("\tmenu links ", ConsoleColor.ANSI_RED)); continue; }
            if (Pattern.compile("^\\.\\./.+$").matcher(src).matches()) {
                System.out.println(deep + ":" + ConsoleColor.setColor("\tback links ", ConsoleColor.ANSI_RED));
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
                links.put(getLinksKey(), child);
                uniqueLinks.put(child.getHref(), child);
                System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
                treeLinks.addInsideLinks(child);
                continue;
            }
            //if (!Pattern.compile("^.+/{1}$").matcher(link).matches() && !Pattern.compile("^/{1}.+$").matcher(src).matches()) src = "/" + src;
            if ((link.contains(".html") || link.contains(".htm")) && (src.contains(".html") || src.contains(".htm"))) {
                // переход внутри?
                System.out.println(deep + ":" + link + "|||" + src);
                Matcher m = Pattern.compile("(^http[s]?://)(.+/)").matcher(link);
                while (m.find()) { link = m.group(0); }
                System.out.println(deep + ":" + "new link: '" + link + "'");
                //если проход во внуть, то нужно вернуться?
                // но сначала добавить в карту
                if (!src.contains("https:") && !src.contains("http:")){
                    if (src.substring(0, 1).equals("/")) src = rootLink + src;
                    else src = link + src;
                }
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                links.put(getLinksKey(), child);
                uniqueLinks.put(child.getHref(), child);
                System.out.println("add link, deep \t(" + (deep + 1) + ")" + child);
                treeLinks.addInsideLinks(child);
                continue;
            }
            System.out.println("");
            if (src.contains(".html") || src.contains(".htm")) {
                // вкладка?
                TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
                links.put(getLinksKey(), child);
                uniqueLinks.put(child.getHref(), child);
                System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
                continue;
            }

            if (!src.contains("https:") && !src.contains("http:")){
                if (src.substring(0, 1).equals("/")) src = rootLink + src;
                else src = link + src;
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
                    System.out.println(deep + ":" + "src :" + src);
                    System.out.println(deep + ":" + "temp:" + temp + "\t" + temp_);
                    System.out.println(deep + ":" + "link:" + link);
                    if (link.equals(temp) || link.equals(temp_)) restExist  = true;
                }
                if (restExist) {  System.out.println(ConsoleColor.setColor(deep + ":" + "\trest itself", ConsoleColor.ANSI_RED)); continue; }
            }

            System.out.println(deep + ":" + "next resource: '" + src + "'");

            if (isLinkContain(src)) continue;

            int key = getLinksKey();
            TreeLinks child = new TreeLinks(deep + 1, rootLink, src);
            links.put(key, child);
            uniqueLinks.put(child.getHref(), child);
            System.out.println(deep + ":" + "add link, deep \t(" + (deep + 1) + ")" + child);
            // не заходим внутрь если не из базового домена
            if (src.contains(rootLink) && isSiteHttpLink(src)){
                //child = FillTreeLinks(deep + 1, rootLink, src);
                links.put(key, child); // перезаписываем если было изменение внутри
            }
            System.out.println(deep + ":" + "update link, deep \t(" + (deep + 1) + ")" + child);
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

    /** собираем готовую ссылку если в дочерней встречается фрагмент рутовой */
    private static String getValidLink(String rootLink, String childLink){
        if (childLink.matches("^http[s]?://.+")) return childLink;
        // 	https://docs.oracle.com/en/cloud/
        // marketplace/index.html  |  /en/cloud/cloud-at-customer/index.html

        /** выделяем фрагменты рутовской ссылки */
        List<String> rootPathParts = new ArrayList<>();
        Matcher m = Pattern.compile("(^http[s]?://)(.+/)").matcher(rootLink);
        String rootParts_ = "";
        while (m.find()) {
            rootPathParts.add(m.group(1));
            rootParts_ = m.group(2);
        }
        m = Pattern.compile("([^/]+)/?").matcher(rootParts_);
        while (m.find()) {
            rootPathParts.add(m.group(1));
        }

        /** отделяем параметры вызова если это REST запрос */
        m = Pattern.compile("([^?]+)([?](.+))?").matcher(childLink);
        String childParts_ = "", restParams = "";
        while (m.find()) { childParts_ = m.group(1); restParams = m.group(2); }

        /** выделяем фрагменты дочерней ссылки*/
        List<String> childPathParts = new ArrayList<>();
        m = Pattern.compile("([^/]+)/?").matcher(childParts_);
        while (m.find()) {
            childPathParts.add(m.group(1));
        }

        /** определяем если ли в дочерней полный фрагмент родительской ссылки */
        for (int i = 0; i< rootPathParts.size(); i++) {
            if (rootPathParts.get(i).equals(childPathParts.get(0))){
                if (fullMatch(i, rootPathParts, childPathParts)){
                    String link = rootPathParts.get(0);
                    int rootCount = 0;
                    for (int j = 1; j <= i;j++){
                        link += rootPathParts.get(j) + "/";
                        rootCount++;
                    }
                    for (int j = rootCount - 1; j < childPathParts.size();j++) link += childPathParts.get(j) + "/";
                    return link;
                }
            }
        }
        String link;
        if (rootLink.substring(rootLink.length()-1, rootLink.length()).equals("/")){
            if (childLink.substring(0, 1).equals("/")) link = rootLink.substring(0, rootLink.length()-1) + childLink;
            else link = rootLink + childLink;
        } else {
            if (childLink.substring(0, 1).equals("/")) link = rootLink + childLink;
            else link = rootLink + childLink.substring(1, childLink.length());
        }
        return link;
    }
    private static boolean fullMatch(int startIter, List<String> rootPathParts, List<String> childPathParts){
        for (int i = startIter; i < rootPathParts.size(); i++){
            if (!rootPathParts.get(i).equals(childPathParts.get(i-startIter))) return false;
        }
        return true;
    }

}
