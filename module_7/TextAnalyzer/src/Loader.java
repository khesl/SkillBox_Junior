package module_7.TextAnalyzer.src;

import java.nio.file.Files;
import java.nio.file.Paths;

public class Loader {
    private static final String PATH = "S:\\my folder\\programming\\IntelliJ_IDEAProjects\\JavaExperiments\\SkillBox_Junior\\module_7\\TextAnalyzer\\res\\text_01.txt";

    public static void main(String[] args) throws Exception {
        //Reading file to the String
        String text = new String(Files.readAllBytes(Paths.get(PATH)));

        TextAnalyzer analyzer = new TextAnalyzer(text);
        System.out.println("Most frequent word: " + analyzer.getMostFrequentWord());

        System.out.println("Arrays: " + analyzer.getWords().toString());
    }


}