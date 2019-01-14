package module_12.Lessons_.src;

import java.util.HashMap;
import java.util.Map;

public class MyHttpScannerController {
    private String rootLink;
    private TreeLinks rootTreeLinks;
    private static volatile Map<String, TreeLinks> uniqueLinks = new HashMap<>();
    public static final String TEMP_URL_PATH = "module_12\\Lessons_\\src\\page.html";

    public MyHttpScannerController(TreeLinks rootTreeLinks){
        this.rootTreeLinks = rootTreeLinks;
    }

    public void parseLink(TreeLinks treeLinks){

    }

    public static Map<String, TreeLinks> getUniqueLinks() {
        return uniqueLinks;
    }
}
