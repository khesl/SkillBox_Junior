package module_10.Lessons_;

import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.Scanner;

public class Lesson_7 {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        Lesson_7();
    }

    /** шикарная штука, ранее я придумывал собственный парсер файлов в xml формате, в котором хранил нужные мне данные..
     * */
    private static void Lesson_7() throws IOException {
        String path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\res\\config.ini";

        // создали файл
        /*Properties props = new Properties();
        props.setProperty("size", "57");
        props.setProperty("debug_mode", "false");

        props.store(new FileOutputStream(path), "comments");
        */

        // меняем свойства
        Properties props2 = new Properties();
        props2.load(new FileReader(path));

        String val = String.valueOf(Integer.valueOf(props2.get("size").toString())+1);
        props2.setProperty("size", val);
        System.out.println(props2.get("debug_mode"));
        if (Boolean.valueOf(props2.get("debug_mode").toString()))
            props2.setProperty("debug_mode", "false");
        else
            props2.setProperty("debug_mode", "true");
        System.out.println(props2.get("debug_mode"));

        props2.store(new FileOutputStream(path), "changeMode");


    }

}
