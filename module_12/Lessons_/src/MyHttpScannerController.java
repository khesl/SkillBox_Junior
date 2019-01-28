package module_12.Lessons_.src;

import Utils.ConsoleColor;
import Utils.MyUtils;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class MyHttpScannerController extends Thread {
    private static TreeLinks rootTreeLinks;
    private static volatile Map<String, TreeLinks> uniqueLinks = new HashMap<>();
    private static volatile Set<String> uniqueLinksProcessed = new HashSet<>();
    private static volatile Set<String> uniqueLinksProcessing = new HashSet<>();
    public static final String TEMP_URL_PATH = "module_12\\Lessons_\\src\\page1.html";
    public static final String TEMP_URL_PATH_BASE = "module_12\\Lessons_\\src\\";
    private static List<MyHttpScanner_2> scanners = new ArrayList<>();
    private int threads_count = 1;
    private static boolean updateLinks = false;

    public MyHttpScannerController(TreeLinks rootTreeLinks){
        this.rootTreeLinks = rootTreeLinks;
    }

    @Override
    public void run(){
        DownloadUrl downloadRootUrl = new DownloadUrl(rootTreeLinks.getHref(), TEMP_URL_PATH);
        downloadRootUrl.setLoaderbefore(true);
        while(!interrupted()){
            if (!downloadRootUrl.isRootDownload()) {
                if (!downloadRootUrl.isRootDownloading()) {
                    System.out.println(": start load page root path...");
                    downloadRootUrl = new DownloadUrl(rootTreeLinks.getHref(), TEMP_URL_PATH);
                    downloadRootUrl.start();
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else{
                    System.out.println(downloadRootUrl.getStatus());
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                MyHttpScanner_2 rootScanner = new MyHttpScanner_2(rootTreeLinks, TEMP_URL_PATH, "baseScan");
                TreeLinks rootChild = null;
                try {
                    rootChild = rootScanner.FillTreeLinks_v2();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                // а теперь добавить реализацию потоковости

                //нужно создать сколько надо потоков.
                for (int i = 0; i < threads_count; i++)
                    scanners.add(new MyHttpScanner_2(rootTreeLinks, TEMP_URL_PATH, "scanThread_" + i));
                System.out.println("Thread counts: " + scanners.size() + ":" + threads_count);
                //
                loader.start();
                //loader.join();

                System.out.print(rootChild.toAllString());
                System.out.println("End of program.");
            }

        }
    }

    private class DownloadUrl extends Thread{
        private boolean isRootDownload = false;
        private boolean isRootDownloading = false;
        private String href;
        private String filePath;
        private boolean isLoaderbefore = false;

        public DownloadUrl(String href, String filePath){
            this.href = href;
            this.filePath = filePath;
        }

        @Override
        public void run(){
            while(!interrupted()){
                if (isLoaderbefore) {
                    isRootDownload = true;
                    isRootDownloading = false;
                    break;
                }
                if (!isRootDownload && !isRootDownloading) {
                    System.out.println(getName() + ": start load page...");
                    isRootDownloading = true;
                    MyUtils.downloadUrl(href, filePath);
                    isRootDownload = true;
                    isRootDownloading = false;
                    System.out.println("...end loading page");
                } else break;
            }
        }

        public String getStatus(){
            if (!isRootDownload) return ConsoleColor.setColor("processing: load file (" + (new File(filePath)).length() + " byte), loading: '" + href + "'", ConsoleColor.ANSI_YELLOW);
            return ConsoleColor.setColor("thread is free.", ConsoleColor.ANSI_YELLOW);
        }

        public boolean isRootDownload() { return isRootDownload; }
        public boolean isRootDownloading() { return isRootDownloading; }

        public void setLoaderbefore(boolean loaderbefore) {
            isLoaderbefore = loaderbefore;
        }
    }

    private LinkLoader loader = new LinkLoader();
    /*public void parseLink() throws IOException, InterruptedException {
        System.out.print("start load root page...");
        MyUtils.downloadUrl(rootTreeLinks.getHref(), TEMP_URL_PATH);
        System.out.println("... end load root page.");

        MyHttpScanner_2 rootScanner = new MyHttpScanner_2(rootTreeLinks, TEMP_URL_PATH, "baseScan");
        TreeLinks rootChild = rootScanner.FillTreeLinks_v2();
        // а теперь добавить реализацию потоковости

        //нужно создать сколько надо потоков.
        for (int i = 0; i < threads_count; i++)
            scanners.add(new MyHttpScanner_2(rootTreeLinks, TEMP_URL_PATH, "scanThread_" + i));

        //
        loader.start();
        //loader.join();

        System.out.print(rootChild.toAllString());
        System.out.println("End of program.");
    }*/

    public LinkLoader getLoader() { return loader; }

    public static MyHttpScanner_2 getFreeScanner() throws NoSuchFieldException {
        for (MyHttpScanner_2 scanner : scanners){
            if (scanner.isFreeScanner()){
                if (!scanner.isFinishResult()) return scanner;
                else {
                    // обновим полученные данные в общем контейнере
                    TreeLinks data = scanner.getTreeLink();
                    putUniqueLinks(data.getHref(), data);
                    uniqueLinksProcessed.add(data.getHref());
                    return null;
                }
            }
        }
        return null;
    }

    public static TreeLinks getNextLink(){
        for (Map.Entry entry : uniqueLinks.entrySet()) {
            //System.out.println(" test getNextLink: " + entry.getValue() + "::: " + uniqueLinksProcessing + uniqueLinksProcessing.contains(((TreeLinks) entry.getValue()).getHref()));
            if (!((TreeLinks) entry.getValue()).getHref().contains(rootTreeLinks.getRootLink())){
                uniqueLinksProcessed.add(((TreeLinks) entry.getValue()).getHref());
                System.out.println("add: " + entry.getValue());
                continue;
            }
            if (!uniqueLinksProcessed.contains(((TreeLinks) entry.getValue()).getHref()) && !uniqueLinksProcessing.contains(((TreeLinks) entry.getValue()).getHref())){
                System.out.println("next:" + entry.getValue());
                return (TreeLinks) entry.getValue();
            }
        }
        return null;
    }

    public static boolean isUpdateLinks() { return updateLinks; }
    public static Map<String, TreeLinks> getUniqueLinks() {
        updateLinks = false;
        return uniqueLinks;
    }
    public static Set<String> getUniqueLinksProcessed() { return uniqueLinksProcessed; }
    public static Set<String> getUniqueLinksProcessing() { return uniqueLinksProcessing; }
    public static void putUniqueLinks(String href, TreeLinks treeLinks) { uniqueLinks.put(href, treeLinks); updateLinks = true; }
    public void setThreads_count(int threads_count) { this.threads_count = threads_count; }
    public static List<MyHttpScanner_2> getScanners() { return scanners; }
}