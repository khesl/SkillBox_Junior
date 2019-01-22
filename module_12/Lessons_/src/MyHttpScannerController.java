package module_12.Lessons_.src;

import java.io.IOException;
import java.util.*;

public class MyHttpScannerController {
    private String rootLink;
    private TreeLinks rootTreeLinks;
    private static volatile Map<String, TreeLinks> uniqueLinks = new HashMap<>();
    private static volatile Set<String> uniqueLinksProcessed = new HashSet<>();
    public static final String TEMP_URL_PATH = "module_12\\Lessons_\\src\\page.html";
    public static final String TEMP_URL_PATH_BASE = "module_12\\Lessons_\\src\\";
    private List<MyHttpScanner_2> scanners = new ArrayList<>();
    private int threads_count = 5;

    public MyHttpScannerController(TreeLinks rootTreeLinks){
        this.rootTreeLinks = rootTreeLinks;
    }

    public void parseLink() throws IOException, NoSuchFieldException, InterruptedException {
        MyHttpScanner_2 rootScanner = new MyHttpScanner_2(rootTreeLinks, TEMP_URL_PATH, "baseScan");
        TreeLinks rootChild = rootScanner.FillTreeLinks_v2();
        // а теперь добавить реализацию потоковости

        //нужно создать сколько надо потоков.

        //
        while (uniqueLinksProcessed.size() < uniqueLinks.size()) {
            MyHttpScanner_2 mhs_2 = getFreeScanner();
            if (mhs_2 == null) continue;
            TreeLinks nextLink = getNextLink();
            if (nextLink == null) break;
            mhs_2.resetData(nextLink, TEMP_URL_PATH_BASE + mhs_2.getScannerName());
            mhs_2.start();
            mhs_2.join();
        }

        System.out.print(rootChild.toAllString());
        System.out.println("End of program.");
    }

    private MyHttpScanner_2 getFreeScanner() throws NoSuchFieldException {
        for (MyHttpScanner_2 scanner : scanners){
            if (scanner.isFreeScanner()){
                if (scanner.isFinishResult()) return scanner;
                else {
                    // обновим полученные данные в общем контейнере
                    TreeLinks data = scanner.getTreeLink();
                    uniqueLinks.put(data.getHref(), data);
                    uniqueLinksProcessed.add(data.getHref());
                    return null;
                }
            }
        }
        return null;
    }

    private TreeLinks getNextLink(){
        for (Map.Entry entry : uniqueLinks.entrySet()) {
            if (!uniqueLinksProcessed.contains(entry.getValue())) return (TreeLinks) entry.getValue();
        }
        return null;
    }

    public static Map<String, TreeLinks> getUniqueLinks() { return uniqueLinks; }
    public static Set<String> getUniqueLinksProcessed() { return uniqueLinksProcessed; }
    public static void putUniqueLinks(String href, TreeLinks treeLinks) { uniqueLinks.put(href, treeLinks); }
}
