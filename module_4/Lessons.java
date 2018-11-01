package module_4;

import java.util.Scanner;

public class Lessons {
    protected static Scanner scanner;


    public static void main(String[] args) {
        scanner = new Scanner(System.in);

        //Lesson_2();
        //Lesson_3();
        Lesson_4();
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
}
