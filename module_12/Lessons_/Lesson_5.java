package module_12.Lessons_;

import Utils.ConsoleColor;
import Utils.MyUtils;
import module_12.Lessons_.src.TreeLinks;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Pattern;

public class Lesson_5 {

    public static void main(String[] args) throws IOException {
        Lesson_5 less = new Lesson_5();
        TreeLinks links = less.FillTreeLinks(0, "https://docs.oracle.com/en/solutions/", "https://docs.oracle.com/en/solutions/");
        System.out.println("loaded http map:\n" + links.toAllString());
    }

    private volatile Map<Integer, TreeLinks> links = new HashMap<>();
    private volatile int key = 0;
    private int getLinksKey(){ return key++; }
    private static final String TEMP_URL_PATH = "module_12\\Lessons_\\src\\page.html";
    /** на основе module_10.Lessons_.Lesson_6 */
    private TreeLinks FillTreeLinks(int deep, String rootLink, String link) throws IOException {
        System.out.print("start deep (" + deep + ") ");
        // - Написать программу, которая будет скачивать в папку все изображения с указанной веб­страницы.
        // https://course.skillbox.ru/webdev/
        TreeLinks treeLinks = new TreeLinks(deep, rootLink, link);
        System.out.print(treeLinks);

        if (link.length() == 0 || (!link.contains("https:") && !link.contains("http:"))) {
            System.out.print("некорректная ссылка. Потом можно ловить эксепшн");
            return null;
        }

        boolean load = MyUtils.downloadUrl(link, TEMP_URL_PATH); // загрузили код
        if (!load) {
            System.out.println("Error with load URL '" + link + "'");
            return new TreeLinks(deep + 1, rootLink, link + "(Error spectate page)");
        }
        System.out.print(", http loaded, ");

        Document doc = Jsoup.parse(new File(TEMP_URL_PATH), "UTF-8");
        Elements elements = doc.select("a");
        System.out.println("count child: " + elements.size());
        for (Element element : elements) {
            // для каждого элемента будем пробегать внутрь
            String src = element.attr("href");
            System.out.println("element.attr(\"href\"): '" + src + "'");

            if (src.equals("/") || src.equals("#") || src.equals("index.html")) { System.out.println(ConsoleColor.setColor("\titself ", ConsoleColor.ANSI_RED)); continue; }
            if (src.length() == 0) continue;
            if (Pattern.compile("^#{1}.+$").matcher(src).matches()) { System.out.println(ConsoleColor.setColor("\tmenu links ", ConsoleColor.ANSI_RED)); continue; }
            if (!Pattern.compile("^.+/{1}$").matcher(link).matches() && !Pattern.compile("^/{1}.+$").matcher(src).matches()) src = "/" + src;
            if (!src.contains("https:") && !src.contains("http:")) src = link + src;

            // теперь проверить на параметризацию
            // TODO: доработать паттерн! чтобы выявлять такие https://docs.oracle.com/en/solutions/solutions.html?type=design и другие зацикленные штуки
            Pattern.compile("^http[s]?://(.+/)+(.+[?])?((.+=.+)([&])?)+").matcher(src).matches();

            //System.out.println("next resource: '" + src + "'");

            boolean exist = false;
            for (Map.Entry entry : links.entrySet()) {
                if (((TreeLinks)entry.getValue()).getHref().equals(src)) {
                    // находим уже обработанный объект
                    System.out.println("already contain");
                    treeLinks.addInsideLinks((TreeLinks)entry);
                    exist = true;
                    break;
                    // попробуем найти это дерево и добавить?
                }
            }
            if (exist) continue;

            TreeLinks child;
            // не заходим внутрь если не из базового домена
            if (src.contains(rootLink) && isSiteHttpLink(src)) child = FillTreeLinks(deep + 1, rootLink, src);
            else child = new TreeLinks(deep + 1, rootLink, src);
            System.out.println(child);
            links.put(getLinksKey(), child);
            treeLinks.addInsideLinks(child);
        }
        return treeLinks;
    }

    private boolean isSiteHttpLink(String link){
        if (link.contains(".css"))  return false;
        if (link.contains(".ico"))  return false;
        if (link.contains("#"))     return false;
        if (link.contains("../"))   return false;
        return true;
    }


}
