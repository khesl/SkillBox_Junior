package module_4.StringExperiments.src;

import java.util.Scanner;

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
        String names = scanner.nextLine();
        String[] fio = names.split(" ");
        System.out.print("Фамилия: " + fio[0] + "\nИмя: " + fio[1] + "\nОтчество: " + fio[2]);
    }
}