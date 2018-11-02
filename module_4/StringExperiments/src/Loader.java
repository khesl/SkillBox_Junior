package module_4.StringExperiments.src;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Loader{
    protected static Scanner scanner;

    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";

        System.out.println(text);

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
        String names_ = scanner.nextLine();
        String[] fio = names_.split(" ");
        System.out.print("Фамилия: " + fio[0] + "\nИмя: " + fio[1] + "\nОтчество: " + fio[2]);

        // https://www.freeformatter.com/java-regex-tester.html#ad-output
        text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println("\n" + text);

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
}