package module_10.Lessons_;

import Utils.ConsoleColor;

import java.io.File;
import java.util.Scanner;

public class Lesson_1 {
    private static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        Lesson_1(); // вывод всем папок и подпапок по вводимому пути
    }

    private static void Lesson_1(){
        /**
         String path = "";
         // построчное чтение
         try {
         BufferedReader reader = new BufferedReader(new FileReader(path));
         for (;;) {
         String line = reader.readLine();
         if (line == null) break;
         System.out.println(line);
         }
         } catch (IOException e) {
         e.printStackTrace();
         }
         // посимвольное чтение
         try {
         FileInputStream fis = new FileInputStream(path);
         for (;;){
         int code = fis.read();
         if (code < 0) break;
         System.out.print((char) code);
         }
         } catch (IOException e) {
         e.printStackTrace();
         }
         // чтение целиком
         try {
         String file = new String(Files.readAllBytes(Paths.get(path)));
         System.out.println(file);
         } catch (IOException e) {
         e.printStackTrace();
         }
         */

        // - Написать код, на вход которого подаётся путь к папке, а на выходе печатается дерево из файлов и папок,
        // находящихся в этой папке. При этом у файлов должен быть указан размер в байтах.
        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, what do want? (exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
            File file = new File(in);
            if (!file.exists()) {
                System.out.println(ConsoleColor.setColor("robot# Write path incorrect! please write new path.", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                continue;
            }
            deepIntoTheFile(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior"));
        }
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
