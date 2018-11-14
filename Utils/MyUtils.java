package Utils;

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
}
