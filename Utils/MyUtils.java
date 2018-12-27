package Utils;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MyUtils {

    private static int[] vowels = {1072,1077,1080,1086,1091,1099,1101,1102,1103};
    private static int[] ban = {1098, 1100, 1104};
    public static String getRamdonName(){
        int length =  3 + (int) (Math.random()*3);
        String name = "";
        for (int i =0; i < length; i++){
            char letter;
            boolean isVowelsPrev;
            boolean isVowelsCur;
            do {
                isVowelsPrev = false;
                isVowelsCur = false;
                letter = (char)(1072 + (int)(Math.random()*31));
                boolean banned = false;
                do {
                    banned = false;
                    for (int val : ban)
                        if ((int) letter == val)
                            banned = true;
                    if (banned){
                        letter = (char)(1072 + (int)(Math.random()*31));
                    }
                } while (banned);
                for (int val : vowels){
                    if (i == 0)
                        isVowelsPrev = true;
                    else if (name.charAt(i-1) == val)
                        isVowelsPrev = true;
                    if ((int)letter == val)
                        isVowelsCur = true;
                }
            } while (isVowelsPrev == isVowelsCur);
            name += letter;
            if (i == 0) name = name.toUpperCase();
        }
        return name;
    }

    // подбор шаблона телефона
    public static String getPhoneNumber(String telNum){
        if (checkPhoneNumber(telNum))
            return telNum.replaceAll("[\\s()-]", "");
        throw new IllegalArgumentException("Please write correct number.");
    }
    public static boolean checkPhoneNumber(String telNum){
        // https://www.freeformatter.com/java-regex-tester.html#ad-output

        // проверяем верно ли количество цифр ********** x10
        if (!Pattern.compile("^(\\+7|8)[0-9]{10}").matcher(telNum.replaceAll("[\\s()-]", "")).matches())
            return false;

        // p = Pattern.compile("^[(][0-9]{3}[)][0-9]{7}$");  // (***)*******
        // p = Pattern.compile("^.[0-9]{3}.[0-9]{7}$");  // (***)*******
        //  +*(***)*******|*(***)*******|+*(***)*** ** **|* *** *** ** **|+*-***-***-**-** и другие... да, я писал это сам
        if (!Pattern.compile("\\+?[7,8]{1}[\\s]*[(\\s-]?[0-9]{3}[\\s]*[)\\s-]?[\\s]*[0-9]{2,3}[\\s-]?[0-9]{2,3}[\\s-]?[0-9]{2,3}")
                .matcher(telNum).matches())
            return false;

        //System.out.println("telephone format undetected here, please try another format!");
        return true;
    }

    public static String getPatternUpdate(String source, char input, String pattern){
        // +7(5__)  || +7(___)
        if (source.contains("_"))
            source = source.replaceFirst("_", String.valueOf(input));
        return source;
    }

    public static void deepIntoTheFile(File file){
        deepIntoTheFile(file, 0);
    }
    private static void deepIntoTheFile(File file, int deep){
        String tab = "";
        for (int i = 0; i<deep; i++) tab += "\t";
        System.out.println(tab + file.getName() + ConsoleColor.setColor("\t\t" + file.length(), ConsoleColor.ANSI_BLUE));
        for (File f : file.listFiles()){
            if (f.isDirectory()) deepIntoTheFile(f, deep + 1);
            else System.out.println(tab + "\t" + f.getName() + ConsoleColor.setColor("\t\t" + f.length(), ConsoleColor.ANSI_BLUE));
        }
    }

    public static JFrame createMainFrame(JPanel panel, String formName, Dimension dimension){
        JFrame frame = new JFrame();

        frame.setContentPane(panel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width, dimension.height);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(formName);

        return frame;
    }

    /** выкачать код страницы по ссылке в указанный файл
     *
     * @param link  - url ссылка на сайт
     * @param path  - путь загрузки кода страницы
     * */
    public static boolean downloadUrl(String link, String path) {
        try {
            URL url = new URL(link);

            /*
            URL obj = new URL(url);
            HttpURLConnection conn = (HttpURLConnection) obj.openConnection();
            conn.setInstanceFollowRedirects(true);  //you still need to handle redirect manully.
            HttpURLConnection.setFollowRedirects(true);
            */

            InputStream stream = url.openStream();
            FileOutputStream fos = new FileOutputStream(path);

            int code;
            while ((code = stream.read()) > 0) {
                fos.write(code);
            }

            fos.flush();
            fos.close();

        } catch (Exception e){
            //throw new IOException("trouble with: '" + link + "'");
            return false;
        }
        return true;
    }

    /** выкачать ресурс по ссылке в указанный файл
     *
     * @param link  - url ссылка на ресурс
     * @param path  - путь загрузки кода страницы (пример: "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\export\\img")
     * */
    public static boolean downloadResource(String link, String path) throws IOException {
        // загрузка image в папку
        URL url_ = new URL(link);
        InputStream is = new BufferedInputStream(url_.openStream());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        int n = 0;
        while (-1 != (n = is.read(buf))) { out.write(buf, 0, n);}

        out.close();
        is.close();
        byte[] response = out.toByteArray();

        FileOutputStream fos = new FileOutputStream(path + "\\" + link.split(String.valueOf("/"))[link.split(String.valueOf("/")).length - 1]);

        fos.write(response);
        fos.flush();
        fos.close();
        return true;
    }
}
