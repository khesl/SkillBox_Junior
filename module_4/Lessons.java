package module_4;

import Utils.ConsoleColor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lessons {
    protected static Scanner scanner;

    public static void main(String[] args) throws IOException, ParseException {
        scanner = new Scanner(System.in);

        //Lesson_3(); // дополнил
        //Lesson_4(); // дополнил
        //Lesson_5(); // дополнил
        //Lesson_7_1(); // не требуется
        //Lesson_7_2(); // не требуется
        //Lesson_7_3(); // дополнил
        //Lesson_7_4(); // не требуется
        Lesson_8(); // дополнил
    }

    /**
     * Вам осталось прокомментировать результат  24.0 * 0.1 подробнее
     *
     * ---
     * описал внутри метода.
     * */
    private static void Lesson_3(){

        System.out.println("24.0*0.1=" + 24.0*0.1); // 2.4000000000000004
        System.out.println("24.0f*0.1f=" + 24.0f*0.1f); // 2.4
        System.out.println("24.0d*0.1d=" + 24.0d*0.1d); // 2.4

        /**
         * данный результат появляется т.к. без явного указания типа он принимается как Double - вещественное число с плавающей точкой,
         * артефакты появляются при вычислениях в большой точности, т.к. компьютер работает не в десятичной системе исчисления,
         * а в двоичной - операции с дробными числами выполняются другими методами.
         * */

        System.out.println("Short: " + Short.MIN_VALUE  + "<->" + Short.MAX_VALUE);
        //short   16 бит    от -32768 до 32767
        System.out.println("Long: " + Long.MIN_VALUE + "<->" + Long.MAX_VALUE);
        //long    64 бит	от -9223372036854775808L до 9223372036854775807L
        System.out.println("Float: " + Float.MIN_VALUE + "<->" + Float.MAX_VALUE);
        //float   32 бит	от -1.4e-45f до 3.4e+38f
        System.out.println("Double: " + Double.MIN_VALUE + "<->" + Double.MAX_VALUE);
        //double  64 бит	от -4.9e-324 до 1.7e+308
    }

    /** Назовите, пожалуйста, переменные таким образом, чтобы было понятно их назначение.
     * Проинициализируйте их в условии. Затем необходимо через консоль запросить у пользователя
     * количество ящиков и в зависимости от этого производить расчет. Необходимо переделать.
     *
     * ---
     * Не вижу проблем с названием переменных, но всё равно переименовал, что может быть более говорящим чем 'countBox' или 'currentBox'?
     * Расчет данных и так происходит после ввода пользователя.. Теперь более пользователе ориентированно
     * */
    private static void Lesson_4(){
        System.out.println(ConsoleColor.setColor("robot# Введите количество коннтейнеров.", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        String countStr = scanner.nextLine();

        // way 1
        int countBox = Integer.parseInt(countStr);
        int countContainer = 1 + countBox/27;
        int countTrack = 1 + countContainer/12;
        System.out.println(countBox + " " + countContainer + " " + countTrack);

        // way 2
        System.out.println("-------------");
        int currentBox = 0;
        int currentContainer = 1;
        int currentTrack = 1;
        System.out.println("Грузовик " + currentTrack + ":");
        System.out.println("\tКонтейнер " + currentContainer + ":");
        while (++currentBox <= countBox){
            System.out.println("\t\tЯщик " + currentBox);
            if (currentBox%27 == 0){
                currentContainer++;
                if (currentContainer%12 == 0){ currentTrack++;
                    System.out.println("Грузовик " + currentTrack + ":");
                }
                System.out.println("\tКонтейнер " + currentContainer + ":");
            }
        }
        System.out.println("total: box = " + countBox + ", container = " + currentContainer + ", track = " + currentTrack);
    }

    /**
     *    1) Принято
     *    2) Попробуйте акцентрироваться именно на пробелах. Имена искать не нужно.
     *    3) Все верно. Принято.
     *
     * ---
     * Не совсем понятно требуется ли от меня что-то? Задача выполнена теперь другим способом,
     * будет работать для любого количества 'Зарплат' тексте
     * */
    private static void Lesson_5(){
        for (int i = 0; i <= 512; i++){
            System.out.print((char) i + "|");
            if (i%100 == 0) System.out.println();
        }
        System.out.println("\n");
        System.out.println("а:" + (int) 'а' + " - я:" + (int) 'я');
        System.out.println("А:" + (int) 'А' + " - Я:" + (int) 'Я');
        System.out.println("Коды русских букв в промежутках: " + (int) 'а' + "-" + (int) 'я' + " и "
                                                               + (int) 'А' + "-" + (int) 'Я');

        //- С помощью методов indexOf(), lastIndexOf(), substring() и trim() написать код
        // в проекте StringExperiments, который считает сумму заработка Васи и Маши.
        String text = "Вася заработал 5000 рублей, Петя - 7563 рубля, а Маша - 30000 рублей";
        System.out.println("\n" + text);
        // 1 way
       /* double firstAmount = Double.parseDouble(text.substring(text.indexOf("Вася заработал")
                + "Вася заработал".length(), text.indexOf("рублей, Петя")).trim());
        double secondAmount = Double.parseDouble(text.substring(text.indexOf("рублей, Петя -")
                + "рублей, Петя -".length(), text.indexOf("рубля, а Маша")).trim());
        double thirdAmount = Double.parseDouble(text.substring(text.indexOf("рубля, а Маша -")
                + "рубля, а Маша -".length(), text.lastIndexOf("рублей")).trim());
        System.out.println("Зарплаты: " + firstAmount + ", " + secondAmount + ", "+ thirdAmount);
        System.out.println("Сумма: " + (firstAmount + secondAmount + thirdAmount));*/
        // 2 way
        List<Double> salarys = new ArrayList<>();
        String temp = text;
        while (temp.contains(" ")){
            String word = temp.substring(0, temp.indexOf(" "));
            System.out.print("word: '" + word + "', ");
            try { salarys.add(Double.valueOf(word)); } catch (Exception e){}
            temp = temp.substring(temp.indexOf(" ")+1, temp.length());
            System.out.println("'" + temp + "',");
        }
        System.out.print("Зарплаты: ");
        double summ = 0;
        for (double salary : salarys) {
            summ += salary;
            System.out.print(salary + ", ");
        }
        System.out.println("Сумма: " + summ);


        System.out.print("\nА теперь введите ФИО: ");
        String names = scanner.nextLine();
        String[] fio = names.split(" ");
        System.out.print("Фамилия: " + fio[0] + "\nИмя: " + fio[1] + "\nОтчество: " + fio[2]);
    }

    private static void Lesson_7_1(){
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

    private static void Lesson_7_2(){
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

    /**3) Есть более эффективное решение. Попробуйте изменить код, используя
     *
     * ---
     * Действительно так эффективнее, в вашем коде нет 2х лишних выделений памяти под объекты,
     * String[] также легче чем List<>, при этом очень просто проходит проверка на некорректность ввода.
     */
    private static void Lesson_7_3(){
        System.out.println(ConsoleColor.setColor("robot# Введите Ф.И.О.", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        String names = scanner.nextLine();

        // 1 way
        /*String[] fio = names.split(" ");
        System.out.print("Фамилия: " + fio[0] + "\nИмя: " + fio[1] + "\nОтчество: " + fio[2]);*/

        // 2 way
        /*Pattern p = Pattern.compile("([А-Яа-я]?[а-я]*\\s?)");
        Matcher m = p.matcher(names);
        List<String> names_ = new ArrayList<String>();
        while (m.find()) names_.add(m.group(0));
        System.out.println("Фамилия: " + names_.get(0) + "\nИмя: " + names_.get(1) + "\nОтчество: " + names_.get(2));*/

        // 3 way
        /**
         *  при вводе сразу проверяем по шаблону, где текст должен состоять из 3х слов, разделённых \\s (' '),
         *  первая буква которых в верхнем регистре и не содержит 'Ъ, Ь, Ы', остальные буквы слова - кириллица в малом регистре.
         *  Если ввод соответствует, по парсим текст по символу пробела и определяем Ф.И.О.
         * */
        if(!Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+\\s[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+\\s" +
                "[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+$").matcher(names).matches())
            System.out.println("Неверно введено Ф.И.О.");
        else {
            String[] fullNameArray=names.split("\\s");
        System.out.println("Фамилия: " + fullNameArray[0] + "\nИмя: " + fullNameArray[1] + "\nОтчество: " + fullNameArray[2]);
        }
    }

    private static void Lesson_7_4(){
        System.out.print("\nВведите номер телефона: ");
        String phone = scanner.nextLine();

        System.out.println("result: " + getPhoneNumber(phone));
    }

    // подбор шаблона телефона
    private static String getPhoneNumber(String telNum){
        if (checkPhoneNumber(telNum))
            return telNum.replaceAll("[\\s()-]", "");
        throw new IllegalArgumentException("Please write correct number.");
    }
    private static boolean checkPhoneNumber(String telNum){
        // https://www.freeformatter.com/java-regex-tester.html#ad-output

        // проверяем верно ли количество цифр
        Pattern p = Pattern.compile("^(\\+7|8)[0-9]{10}");  // ********** x10
        Matcher m = p.matcher(telNum.replaceAll("[\\s()-]", ""));
        if (!m.matches()) {
            System.out.println("Wrong num count! Please write correct number. (" +telNum.replaceAll("[\\s()-]", "") + ")");
            return false;
        }
        // p = Pattern.compile("^[(][0-9]{3}[)][0-9]{7}$");  // (***)*******
        // p = Pattern.compile("^.[0-9]{3}.[0-9]{7}$");  // (***)*******
        p = Pattern.compile("\\+?[7,8]{1}[\\s]*[(\\s-]?[0-9]{3}[\\s]*[)\\s-]?[\\s]*[0-9]{2,3}[\\s-]?[0-9]{2,3}[\\s-]?[0-9]{2,3}");
        //  +*(***)*******|*(***)*******|+*(***)*** ** **|* *** *** ** **|+*-***-***-**-** и другие... да, я писал это сам
        m = p.matcher(telNum);
        if (m.matches()) {
            return true;
        }

        System.out.println("telephone format undetected here, please try another format!");
        return false;
    }

    /**
     * Вы решили задачу неэффективным способом. Попробуйте использовать следующие инструкции,
     *
     * ---
     * Не сказал бы что это не эффиктивный способ, т.к. в задаче не ставится ввод с консоли, проверка на корректность и пр.
     * "- Написать программу, которая будет распечатывать Ваш возраст,
     * соответствующий ему день рождения и день недели. до текущего момента"
     * Хотя при стоящей задаче ввода, моё решение действительно было бы менее эффективно..
     *
     * Интегрировал Ваш способ ввода данных к своей логике задания.
     *
     * */
    private static void Lesson_8() throws IOException, ParseException {

        /*Calendar cal = Calendar.getInstance();
        cal.set(1994, Calendar.JULY, 22);
        //cal.add(Calendar.DAY_OF_MONTH, 1);
        Date date = cal.getTime();
        System.out.println(date + " " + cal.get(Calendar.DAY_OF_WEEK));

        int count = 0;
        do {
            DateFormat format = new SimpleDateFormat("dd.MM.yyyy - EEEE");
            System.out.println("Time of life (" + count++ + "):\t" + format.format(cal.getTime()));

            cal.add(Calendar.YEAR, 1);
        } while (cal.getTimeInMillis() < Calendar.getInstance().getTimeInMillis());
        */

        // update
        /**
         * Каким способом по вашему мнению эффективнее запрашивать данные?
         * Во-первых относительно пост/пред условного цикла,
         * во-вторых со стороны выделения памяти и количества произведённых операций (время выполнения)
         *      date = scanner.nextLine())
         *      date = (new BufferedReader(new InputStreamReader(System.in))).readLine();
         *

         String date = "";
         while (!(date = scanner.nextLine()).equals("exit")) {
         }

         или

         do {
            date = (new BufferedReader(new InputStreamReader(System.in))).readLine();
         } while (!date.equals("exit"));

         * */
        String date = "";
        do {
            System.out.println(ConsoleColor.setColor("robot# Введите дату. (01.01.1800)", ConsoleColor.Color.ANSI_YELLOW));
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));

            date = (new BufferedReader(new InputStreamReader(System.in))).readLine();

            if(!Pattern.compile("([0-3][0-9])[/.](0[1-9]|1[0-2])[/.](18|19|20)[0-9]{2}").matcher(date).matches())
                System.out.println("Неверно введена дата");
            else {
                Calendar cal = Calendar.getInstance();
                cal.setTime(new SimpleDateFormat("dd.MM.yyyy").parse(date));
                int count = 0;
                do {
                    System.out.println("Time of life (" + count++ + "):\t"
                            + new SimpleDateFormat("dd.MM.yyyy - EEEE").format(cal.getTime()));
                    cal.add(Calendar.YEAR, 1);
                } while (cal.getTimeInMillis() < Calendar.getInstance().getTimeInMillis());
            }
        } while (!date.equals("exit"));
    }
}
