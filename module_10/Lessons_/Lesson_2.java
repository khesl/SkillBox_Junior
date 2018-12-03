package module_10.Lessons_;

import Utils.ConsoleColor;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

public class Lesson_2 {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        Lesson_2_1(); // запись текста в файл по введённому пути
        //Lesson_2_2(); // копирование полного содержимого одной директории, в другую
    }

    private static void Lesson_2_1() throws IOException {
        //F:\files\java\JavaExperiments\SkillBox_Junior\module_10\new.txt
        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, write file path (exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
            File file = new File(in);
            if (!file.exists()) {
                System.out.println(ConsoleColor.setColor("robot# Write path incorrect! please write new path.", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                continue;
            }
            String path = in;
            System.out.println(ConsoleColor.setColor("robot# Write text to file.", ConsoleColor.Color.ANSI_YELLOW));
            System.out.print(ConsoleColor.setColor("consoleWr# ", ConsoleColor.Color.ANSI_BLUE));
            // способ 1
            /**while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
             FileOutputStream fos = new FileOutputStream(path);
             fos.write(in.getBytes());

             fos.flush(); // сброс буферизации
             fos.close();
             System.out.println(ConsoleColor.setColor("robot# Write text to file.", ConsoleColor.Color.ANSI_YELLOW));
             System.out.print(ConsoleColor.setColor("consoleWr# ", ConsoleColor.Color.ANSI_BLUE));
             }*/
            while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                PrintWriter pw = new PrintWriter(path);
                pw.write(in);

                pw.flush(); // сброс буферизации
                pw.close();
                System.out.println(ConsoleColor.setColor("robot# Write text to file.", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("consoleWr# ", ConsoleColor.Color.ANSI_BLUE));
            }
            System.out.println(ConsoleColor.setColor("robot# Hello, write file path (exit)", ConsoleColor.Color.ANSI_YELLOW));
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
    }
    private static void Lesson_2_2() throws IOException {
        // - Написать код, который будет копировать указанную папку с файлами с сохранением структуры в другую указанную папку.

        //F:\files\java\JavaExperiments\SkillBox_Junior\module_10\test_folder_1
        //F:\files\java\JavaExperiments\SkillBox_Junior\module_10\test_folder_2

        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, write first file path (exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
            File file = new File(in);
            if (!file.exists()) {
                System.out.println(ConsoleColor.setColor("robot# Write path incorrect! please write new path.", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                continue;
            }
            System.out.println(ConsoleColor.setColor("robot# write second file path (exit)", ConsoleColor.Color.ANSI_YELLOW));
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
            while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                copyFile(file, in);
                System.out.println(ConsoleColor.setColor("robot# Copping completed.", ConsoleColor.Color.ANSI_YELLOW));
                break;
            }
            System.out.println(ConsoleColor.setColor("robot# Hello, write new file path (exit)", ConsoleColor.Color.ANSI_YELLOW));
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
    }
    public static void copyFile(File file, String newFile) throws IOException {
        copyFile(file, newFile, 0);
    }
    private static void copyFile(File file, String newFilePath, int deep) throws IOException {
        String tab = "";
        for (int i = 0; i<deep; i++) tab += "\t";
        System.out.println(tab + file.getName() + ConsoleColor.setColor("\t\t" + file.length(), ConsoleColor.ANSI_BLUE));
        for (File f : file.listFiles()){
            if (f.isDirectory()) {
                new File(newFilePath + "\\" + f.getName()).mkdir();
                copyFile(f, newFilePath + "\\" + f.getName(), deep + 1);
            } else {
                File temp_ = new File(newFilePath + "\\" + f.getName());
                PrintWriter pw = new PrintWriter(temp_.getPath());
                pw.write(new String(Files.readAllBytes(Paths.get(f.getPath()))));
                pw.flush(); // сброс буферизации
                pw.close();
                System.out.println(tab + "\t" + f.getName() + ConsoleColor.setColor("\t\t" + f.length(), ConsoleColor.ANSI_BLUE));
            }
        }
    }

}
