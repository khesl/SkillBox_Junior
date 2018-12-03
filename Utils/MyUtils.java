package Utils;

import java.io.File;
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
}
