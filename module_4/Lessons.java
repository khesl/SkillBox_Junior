package module_4;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lessons {
    protected static Scanner scanner;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        //Lesson_2();
        //Lesson_3();
        //Lesson_4();
        //Lesson_6_1();
        //Lesson_6_2();
        Lesson_6_3();
    }

    private static void Lesson_2(){

        System.out.println(24.0*0.1);

        //short   16 бит    от -32768 до 32767
        //long    64 бит	от -9223372036854775808L до 9223372036854775807L
        //float   32 бит	от -1.4e-45f до 3.4e+38f
        //double  64 бит	от -4.9e-324 до 1.7e+308
    }

    private static void Lesson_3(){
        String countStr = scanner.nextLine();

        // way 1
        int countBox = Integer.parseInt(countStr);
        int countContainer = 1 + countBox/27;
        int countTrack = 1 + countContainer/12;
        System.out.println(countBox + " " + countContainer + " " + countTrack);

        // way 2
        System.out.println("-------------");
        int cur = 0;
        int countContainer_ = 1;
        int countTrack_ = 1;
        System.out.println("Грузовик " + countTrack_ + ":");
        System.out.println("\tКонтейнер " + countContainer_ + ":");
        while (++cur <= countBox){
            System.out.println("\t\tЯщик " + cur);
            if (cur%27 == 0){
                countContainer_++;
                if (countContainer_%12 == 0){ countTrack_++;
                    System.out.println("Грузовик " + countTrack_ + ":");
                }
                System.out.println("\tКонтейнер " + countContainer_ + ":");
            }
        }
        System.out.println("total: box = " + countBox + ", container = " + countContainer_ + ", track = " + countTrack_);
    }

    private static void Lesson_4(){
        for (int i = 0; i <= 512; i++){
            System.out.print((char) i + "|");
            if (i%100 == 0) System.out.println();
        }
        System.out.println("\n");
        System.out.println("а:" + (int) 'а' + " - я:" + (int) 'я');
        System.out.println("А:" + (int) 'А' + " - Я:" + (int) 'Я');
        System.out.println("Коды русских букв в промежутках: " + (int) 'а' + "-" + (int) 'я' + " и "
                                                               + (int) 'А' + "-" + (int) 'Я');

        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println("\n" + text);
        double firstAmount = Double.parseDouble(text.substring(text.indexOf("Вася заработал")
                + "Вася заработал".length(), text.indexOf("рублей, Петя")).trim());
        double secondAmount = Double.parseDouble(text.substring(text.indexOf("рублей, Петя -")
                + "рублей, Петя -".length(), text.indexOf("рубля, а Маша")).trim());
        double thirdAmount = Double.parseDouble(text.substring(text.indexOf("рубля, а Маша -")
                + "рубля, а Маша -".length(), text.lastIndexOf("рублей")).trim());
        System.out.println("Зарплаты: " + firstAmount + ", " + secondAmount + ", "+ thirdAmount);
        System.out.println("Сумма: " + (firstAmount + secondAmount + thirdAmount));

        System.out.print("\nА теперь введите ФИО: ");
        String names = scanner.nextLine();
        String[] fio = names.split(" ");
        System.out.print("Фамилия: " + fio[0] + "\nИмя: " + fio[1] + "\nОтчество: " + fio[2]);
    }

    private static void Lesson_6_1(){
        // https://www.freeformatter.com/java-regex-tester.html#ad-output
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println(text);

        //Pattern p = Pattern.compile("^[a-z0-9_-]{3,15}$");
        Pattern p = Pattern.compile("([А-Я]{1}[а-я]*)[а-я\\s-]*(([0-9]{3,15})\\sруб(лей|ля|ль))");
        Matcher m = p.matcher(text);
        List<String> names = new ArrayList<String>();
        List<Double> summs = new ArrayList<Double>();
        int count = 0;
        while (m.find()) {
            names.add(m.group(1));
            summs.add(Double.parseDouble(m.group(3)));
            //System.out.println("" + m.group(1) + " " + m.group(2) + ", summ(" + m.group(3) + ")");
        }

        for (int i = 0; i < names.size(); i++){
            System.out.println("Человек по имени '" + names.get(i) + "' зарабатывает '" + summs.get(i) + "'");
        }
        double total = 0;
        for (double summ : summs) total +=summ;
        System.out.println("Общая сумма заработка '" + total + "'");
    }

    private static void Lesson_6_2(){
        String engl_text = "Splits the given input sequence around matches of this pattern.\n" +
                "The array returned by this method contains each substring of the input sequence that is terminated by another subsequence that matches this pattern or is terminated by the end of the input sequence. The substrings in the array are in the order in which they occur in the input. If this pattern does not match any subsequence of the input then the resulting array has just one element, namely the input sequence in string form.\n" +
                "\n" +
                "The limit parameter controls the number of times the pattern is applied and therefore affects the length of the resulting array. If the limit n is greater than zero then the pattern will be applied at most n - 1 times, the array's length will be no greater than n, and the array's last entry will contain all input beyond the last matched delimiter. If n is non-positive then the pattern will be applied as many times as possible and the array can have any length. If n is zero then the pattern will be applied as many times as possible, the array can have any length, and trailing empty strings will be discarded.";

        Pattern p = Pattern.compile("\\s?[A-Za-z']*\\s?");
        Matcher m = p.matcher(engl_text);
        List<String> words = new ArrayList<String>();
        while (m.find()) {
            String temp = m.group(0);
            temp = temp.trim();
            if (!temp.equals("")) words.add(temp);
        }
        for (String str: words){
            System.out.print("\'" + str + "\', ");
        }

    }

    private static void Lesson_6_3(){
        System.out.print("\nВведите ФИО: ");
        String names = scanner.nextLine();
        String[] fio = names.split(" ");
        //System.out.print("Фамилия: " + fio[0] + "\nИмя: " + fio[1] + "\nОтчество: " + fio[2]);

        Pattern p = Pattern.compile("([А-Яа-я]?[а-я]*\\s?)");
        Matcher m = p.matcher(names);

        List<String> names_ = new ArrayList<String>();
        while (m.find()) {
            names_.add(m.group(0));
        }
        System.out.print("Фамилия: " + names_.get(0) + "\nИмя: " + names_.get(1) + "\nОтчество: " + names_.get(2));
    }

    private static void Lesson_6_4(){

    }
}
