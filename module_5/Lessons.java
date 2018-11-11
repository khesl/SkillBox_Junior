package module_5;

import Diploma.DiplomaProj;
import Utils.ConsoleColor;
import org.javagram.response.object.UserContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;


public class Lessons {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);

        //Lesson_1();
        //Lesson_2_1();
        //Lesson_2_2();
        Lesson2_3();
    }

    private static void Lesson_1(){
        System.out.println("\t\t--- First task ---");
        //- Повторить создание массива и заполнение цветами радуги, как показано в видео, а затем написать код, переворачивающий этот массив.
        String[] rainbow = {"Красный", "Оранжевый", "Желтый", "Зеленый", "Голубой", "Синий", "Фиолетовый"};

        System.out.print("Straight rainbow: \t");
        for (String str : rainbow)
            System.out.print(str + ", ");
        System.out.println();
        System.out.print("Reverse rainbow: \t");
        for (int i = rainbow.length-1; i > 0; i--)
            System.out.print(rainbow[i] + ", ");
        System.out.println();
        System.out.print("New Reverse rainbow: \t");
        String[] back_rainbow = new String[rainbow.length];
        for (int i = 0; i < rainbow.length; i++)
            back_rainbow[rainbow.length-1-i] = rainbow[i];
        for (String str : back_rainbow)
            System.out.print(str + ", ");

        System.out.println();
        System.out.println("\t\t--- Second task ---");
        //- Создать и распечатать массив серий паспортов гражданина РФ.
            // PS. я не гражданин РФ, поэтому не знаком с пинципами генерации Паспорта, номера брал с первого примера.
        String[] pasport = new String[10];
        for (int i = 0; i< pasport.length; i++) {
            //4507...
            int issNum = (int)(30 + 20 * Math.random());
            int issYear = (int)(18 * Math.random());
            int passNum = (int)(10000 + 970000 * Math.random());
            pasport[i] = String.valueOf(issNum) + " " + (issYear < 10 ? "0" + String.valueOf(issYear): String.valueOf(issYear)) + " " + String.valueOf(passNum);
        }
        for (String str : pasport)
            System.out.println("Passport num: " + str);

        System.out.println();
        System.out.println("\t\t--- Third task ---");
        //- Создать массив массивов клеток на шахматной доске и распечатать так,
            // чтобы в консоли названия клеток были напечатаны в форме шахматной доски.
        String[][] chessDesk = new String[8][8];
        for (int i = 0; i < chessDesk.length; i++){
            for (int j = 0; j < chessDesk[i].length; j++)
                if ((i+j)%2 == 0) chessDesk[i][j] = "[x]";
                else chessDesk[i][j] = "[ ]";
        }
        System.out.println("Chess desk ([x] - black, [ ] - white)");
        String st = "\t";
        for (int i = 1; i< 9; i++)
            st += " " +i + " ";
        System.out.println(st);
        //for (String[] strs : chessDesk) { for (String str : strs) System.out.print(str); System.out.println(); }
        for (int i = 0; i < chessDesk.length; i++) {
            System.out.print("  " +(char)(97+i) + "\t");
            for (String str : chessDesk[i])
                System.out.print(str);
            System.out.println();
        }
    }

    private static void Lesson_2_1() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<String> visitorNames = new ArrayList<>();
        //for (;;)
        /*{
            String name = reader.readLine().trim();
            visitorNames.add(name);
            if (visitorNames.size() > 10) {
                String nextVisitor = visitorNames.remove(0);
                System.out.println(nextVisitor);
            }
        }*/

        //- Сделать детектор блатных номеров для ГИБДД (все регионы):
        // сгенерировать список номеров и сделать метод, который будет проверять наличие номера в списке.
        // Программа должна работать через консоль: запрашивать номер, проверять,
        // выдавать результат проверки и снова запрашивать номер. Результат должен выдаваться
        // в таком формате: false (26 ms).
        List<String> carNum = new ArrayList<>();
        List<Integer> regNums = new ArrayList<>();
        char[] serChar = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

        for (int i = 1; i <= 95; i++) regNums.add(i);
        for (int i : new int[] {99, 97, 177, 199, 197, 777, 799, 150, 190, 750, 124, 98, 178, 198, 123, 159, 138, 163, 763, 96, 196, 116, 716})
            regNums.add(i);

        long startTime = System.currentTimeMillis();
        for (int reg : regNums)
            for (char ser_1 : serChar)
                for (char ser_2 : serChar)
                    for (char ser_3 : serChar)
                        for (int i = 0; i < 999; i++)
                            if ((ser_2 == ser_3) && (i/100 == (i%100)/10 && i/100 == i%10))
                                carNum.add(String.valueOf(ser_1) + (i < 10 ? ("00" + String.valueOf(i)) : i < 100 ? "0" + String.valueOf(i) : i) +
                                    String.valueOf(ser_2) + String.valueOf(ser_3) + "_" + (reg < 10 ? "0" + reg : reg));
        long generateTime = System.currentTimeMillis() - startTime;
                            System.out.println("Generating for spec (thieves') num (" + generateTime + " mls)");
        System.out.println("Total nums: " + carNum.size());
        System.out.println("Случайный блатной номер: '" + carNum.get((int)(Math.random()*carNum.size())) + "'");
        Collections.sort(carNum);

        for (;;) {
            System.out.print("Введите номер автотранспорта: ");
            String num = reader.readLine().trim();

            startTime = System.currentTimeMillis();
            iterCount = 0;
            if (search(carNum, num)) System.out.print("Это блатной номер!");
            else System.out.print("Это обычный номер.");
            long searchTime = System.currentTimeMillis() - startTime;
            startTime = System.currentTimeMillis();
            boolean standartSearch = carNum.contains(num); // другой способ поиска
            long searchTime_2 = System.currentTimeMillis() - startTime;
            System.out.println (ConsoleColor.setColor(" ("+ searchTime +" ms), найден за (" + iterCount + ") итераций(ии)", ConsoleColor.Color.ANSI_CYAN));
            System.out.println (ConsoleColor.setColor("Поиск через List.contains(): " + standartSearch + " ("+ searchTime_2 +" ms)", ConsoleColor.ANSI_YELLOW));

        }
    }
    private static int iterCount = 0;

    /** помоему это получилась бинарная сортировка, реализовывал по памяти (она мне ещё и приснилась..) */
    public static boolean search(List<String> col, String key){
        iterCount++;
        //System.out.print(col.size() + " ");
        int middle = col.size()/2;
        if (col.size() == 0) return false;
        if (col.get(middle).equals(key)) return true;
        if (col.size() <= 1) return false;
        //System.out.println("\tCompare " + key + ":" + col.get(middle));
        if (less(key, col.get(middle)))
            return search(col.subList(0, middle), key);
        else return search(col.subList(middle+1, col.size()), key);
    }
    private static boolean less(Comparable v, Comparable w)
    { return v.compareTo(w) < 0; }

    private static List<String> flyStack = new ArrayList<>();
    private static void Lesson_2_2(){
        //- Реализовать эмулятор стоянки самолётов-стэк. Размер стоянки ­ 5- мест.
        // Работать должно быть следующим образом: вводим в консоль бортовые номера самолётов,
        // и программа их запоминает (“ставит” их на стоянку). При вводе команды “exitAll”
        // программа должна распечатать номера самолётов в порядке покидания стоянки (и удалить их все из памяти).
        // При вводе “exitLast” ­ должна распечатать и удалить из памяти только тот самолёт, который сейчас выезжает.
        // Если стоянка заполнена полностью, следующему самолёту должно быть отказано во въезде.
        String in = "";
        System.out.println(ConsoleColor.setColor("supervisor# Hello, what do want? (viewAll, exitAll, exitLast, exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        while (!(in = scanner.nextLine()).equals("exit")){
            if (in.equals("viewAll")){
                if (flyStack.size() == 0) {System.out.println(ConsoleColor.setColor("supervisor# sorry we have no any fly here.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("supervisor# Try to read new information. (viewAll, exitAll, exitLast, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                System.out.println(ConsoleColor.setColor("supervisor# Hello, now in parking:", ConsoleColor.Color.ANSI_YELLOW));
                for (String str : flyStack) System.out.println("\t" + ConsoleColor.setColor(str, ConsoleColor.Color.ANSI_RED));
            }
            else if (in.equals("exitLast")){
                if (flyStack.size() == 0) {System.out.println(ConsoleColor.setColor("supervisor# sorry we have no any fly here.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("supervisor# Try to read new information. (viewAll, exitAll, exitLast, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                System.out.print(ConsoleColor.setColor("supervisor# Remove last fly: ", ConsoleColor.Color.ANSI_YELLOW));
                System.out.println(" " + ConsoleColor.setColor(flyStack.remove(flyStack.size() - 1), ConsoleColor.Color.ANSI_RED));
            }
            else if (in.equals("exitAll")){
                System.out.println(ConsoleColor.setColor("supervisor# GET OUT OF HERE: ", ConsoleColor.Color.ANSI_YELLOW));
                while (flyStack.size() > 0)
                    System.out.println("\t" + ConsoleColor.setColor(flyStack.remove(flyStack.size() - 1), ConsoleColor.Color.ANSI_RED));
            }
            /*просто ввод*/
            else {
                if (flyStack.size() > 4) {System.out.println(ConsoleColor.setColor("supervisor# sorry we have no more place!", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("supervisor# Try to read new information. (viewAll, exitAll, exitLast, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                flyStack.add(in);
                System.out.println(ConsoleColor.setColor("supervisor# Parking has a new fly with Number: '", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(in, ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor("', new fly count (", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(String.valueOf(flyStack.size()), ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor(");", ConsoleColor.Color.ANSI_YELLOW));
            }

            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
        System.out.println(ConsoleColor.setColor("supervisor# See you later!", ConsoleColor.ANSI_BLUE));
    }

    private static void Lesson2_3() throws IOException {
        //- По диплому: после авторизации вывести список всех своих контактов с телефонами.
        DiplomaProj proj = new DiplomaProj();

        String phone = "";
        while (phone.length() < 11 ){
            System.out.print(ConsoleColor.setColor("Write your phone number: ", ConsoleColor.ANSI_YELLOW));
            phone = String.valueOf(scanner.nextLine()).trim();
        }
        proj.setPhone(phone);
        proj.getSmsAuthorisation();

        String code = "";
        while (code.length() < 4 ){
            System.out.print(ConsoleColor.setColor("Write your sms code: ", ConsoleColor.ANSI_YELLOW));
            code = String.valueOf(scanner.nextLine()).trim();
        }
        proj.authorized(code);

        for (UserContact contact : proj.getTelegramApiBridge().contactsGetContacts())
            System.out.println(ConsoleColor.setColor(contact.getFirstName() + " "
                    + contact.getLastName() + " " + contact.getPhone(), ConsoleColor.ANSI_BLUE));

    }

}
