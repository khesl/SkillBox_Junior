package module_5;

import Diploma.DiplomaProj;
import Utils.ConsoleColor;
import org.javagram.response.object.UserContact;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Lessons {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);

        //Lesson_2();
        //Lesson_3_1();
        //Lesson_3_2();
        //Lesson_3_3();
        //Lesson_4();
        //Lesson_5_1();
        //Lesson_5_2();
        Lesson_6();
    }

    private static void Lesson_2(){
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

    private static void Lesson_3_1() throws IOException {
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
    private static void Lesson_3_2(){
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

    private static void Lesson_3_3() throws IOException {
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

    private static void Lesson_4(){
        //- Написать программу, в которую можно добавлять через консоль и хранить перечень лекарств,
        // а также распечатывать весь их список командой LIST.
        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, what do want? (LIST, exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        Set<String> medical = new HashSet<>();
        while (!(in = scanner.nextLine()).equals("exit")){
            if (in.equals("LIST")){
                if (medical.size() == 0) {System.out.println(ConsoleColor.setColor("robot# sorry we have no any medicine here.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("robot# Try to read new information. (LIST, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                System.out.println(ConsoleColor.setColor("robot# Hello, now in storage:", ConsoleColor.Color.ANSI_YELLOW));
                for (String str : medical) System.out.println("\t" + ConsoleColor.setColor(str, ConsoleColor.Color.ANSI_RED));
            }
            /*просто ввод*/
            else {
                medical.add(in);
                System.out.println(ConsoleColor.setColor("robot# List has a new medicine: '", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(in, ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor("', new medicine count (", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(String.valueOf(medical.size()), ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor(");", ConsoleColor.Color.ANSI_YELLOW));
            }

            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
        System.out.println(ConsoleColor.setColor("robot# See you later!", ConsoleColor.ANSI_BLUE));
    }

    private static Map<Integer, String> cars = new TreeMap<>();
    private static void Lesson_5_1(){
        //- Написать программу, которая будет выдавать имя владельца автомобиля по его номеру.
        // Программа должна быть умной: если у неё в памяти номера нет, она должна попросить ввести его имя и запомнить.

        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, what do want? (LIST, exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));

        while (!(in = scanner.nextLine()).equals("exit")){
            if (in.equals("LIST")){
                if (cars.size() == 0) {System.out.println(ConsoleColor.setColor("robot# sorry we have no any cars here.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("robot# Try to read new information. (LIST, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                System.out.println(ConsoleColor.setColor("robot# Hello, now in storage:", ConsoleColor.Color.ANSI_YELLOW));
                for (Map.Entry entry : cars.entrySet()) System.out.println("\t" +
                        ConsoleColor.setColor(String.valueOf(entry.getKey() + " : " + entry.getValue()), ConsoleColor.Color.ANSI_RED));
            }
            /*просто ввод*/
            else {
                Pattern p = Pattern.compile("[0-9]");
                Matcher m = p.matcher(in);

                if (!m.find()) {
                    System.out.println(ConsoleColor.setColor("robot# Please use numeric for number.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                int key = Integer.valueOf(in);
                // если есть такой
                if (cars.containsKey(key)) {
                    System.out.print(ConsoleColor.setColor("robot# we have that car number. ", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("It's issurer '", ConsoleColor.Color.ANSI_YELLOW) +
                            ConsoleColor.setColor(cars.get(key), ConsoleColor.Color.ANSI_BLUE) +
                            ConsoleColor.setColor("'", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                System.out.println(ConsoleColor.setColor("robot# Please now set the Name.", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                in = scanner.nextLine();

                // если нет такого
                cars.put(key, in);
                System.out.println(ConsoleColor.setColor("robot# List has a new cars: '", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(in, ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor("', new cars count (", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(String.valueOf(cars.size()), ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor(");", ConsoleColor.Color.ANSI_YELLOW));
            }

            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
        System.out.println(ConsoleColor.setColor("robot# See you later!", ConsoleColor.ANSI_BLUE));
    }

    private static Map<String, String> contacts = new TreeMap<>();
    private static void Lesson_5_2(){
        //- Написать умный эмулятор телефонной книги. Если в неё ввести новое имя, она должна запросить номер телефона.
        // Если в неё ввести новый номер телефона, должна запросить имя. Если введённое имя или номер телефона найдены,
        // должна вывести дополнительную информацию: номер или имя, соответственно.
        // Команда LIST должна выводить всех абонентов в алфавитном порядке с номерами телефонов.

        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, what do want? (LIST, exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));

        while (!(in = scanner.nextLine()).equals("exit")){
            if (in.equals("LIST")){
                if (contacts.size() == 0) {System.out.println(ConsoleColor.setColor("robot# sorry we have no any contact here.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("robot# Try to read new information. (LIST, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                System.out.println(ConsoleColor.setColor("robot# Hello, now in storage:", ConsoleColor.Color.ANSI_YELLOW));
                Set<String> tempSet = new TreeSet<>();
                for (Map.Entry entry : contacts.entrySet())
                    tempSet.add(entry.getValue() + " (" + entry.getKey() + ")");
                // выводим отсортированные по значению. А не по ключу как в TreeMap.
                for (String str : tempSet) System.out.println("\t" + ConsoleColor.setColor(str, ConsoleColor.Color.ANSI_RED));
                /*for (Map.Entry entry : contacts.entrySet())
                    System.out.println("\t" +
                        ConsoleColor.setColor(String.valueOf(entry.getKey() + " : " + entry.getValue()), ConsoleColor.Color.ANSI_RED));*/
            }
            /*просто ввод*/
            else {
                String phone = in;
                String key;
                String value;

                if (in.equals("")){
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }
                // если есть такой по ключу
                if (checkPhoneNumber(phone) ? contacts.containsKey(phone) : contacts.containsValue(phone)) {
                    if (checkPhoneNumber(phone)){
                        key = phone;
                        value = contacts.get(phone);
                    } else {
                        value = phone;
                        key = "";
                        for (Map.Entry entry : contacts.entrySet())
                            if (entry.getValue().equals(value)){
                                key = (String) entry.getKey();
                                break;
                            }
                    }
                    System.out.print(ConsoleColor.setColor("robot# we have that contact number. ", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.println(ConsoleColor.setColor("\t'", ConsoleColor.Color.ANSI_YELLOW) +
                            ConsoleColor.setColor(value, ConsoleColor.Color.ANSI_BLUE) +
                            ConsoleColor.setColor("' ", ConsoleColor.Color.ANSI_YELLOW) +
                            ConsoleColor.setColor(key, ConsoleColor.Color.ANSI_BLUE));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    continue;
                }

                if (checkPhoneNumber(phone)){
                    key = getPhoneNumber(phone);
                    System.out.println(ConsoleColor.setColor("robot# Please now write Name.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    value = scanner.nextLine();
                } else {
                    value = phone;
                    System.out.println(ConsoleColor.setColor("robot# Please now write number.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    key = scanner.nextLine();
                    if (!checkPhoneNumber(key)){
                        System.out.println(ConsoleColor.setColor("robot# wrong number format. Try more.", ConsoleColor.Color.ANSI_YELLOW));
                        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                        key = scanner.nextLine();
                        if (!checkPhoneNumber(key)) continue;
                    }
                }

                // если нет такого
                contacts.put(key, value);
                System.out.println(ConsoleColor.setColor("robot# List has a new contact: '", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(in, ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor("', new contacts count (", ConsoleColor.Color.ANSI_YELLOW) +
                        ConsoleColor.setColor(String.valueOf(contacts.size()), ConsoleColor.Color.ANSI_BLUE) +
                        ConsoleColor.setColor(");", ConsoleColor.Color.ANSI_YELLOW));
            }

            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }
        System.out.println(ConsoleColor.setColor("robot# See you later!", ConsoleColor.ANSI_BLUE));
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
            //System.out.println("Wrong num count! Please write correct number. (" +telNum.replaceAll("[\\s()-]", "") + ")");
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

    private static List<String> carNum = new ArrayList<>();
    private static Set<String> carNumHash = new HashSet<>();
    private static Set<String> carNumTree = new TreeSet<>();
    private static void Lesson_6() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        //- Сделать детектор блатных номеров для ГИБДД (все регионы):
        // сгенерировать список номеров и сделать метод, который будет проверять наличие номера в списке.
        // Программа должна работать через консоль: запрашивать номер, проверять,
        // выдавать результат проверки и снова запрашивать номер. Результат должен выдаваться
        // в таком формате: false (26 ms).

        //- В задаче с детектором блатных номеров сделать дополнительно бинарный поиск,
        // поиск с помощью HashSet и с помощью TreeSet. Измерить и сравнить длительность 4­х
        // видов поиска и написать результат в качестве решения домашнего задания.


        List<Integer> regNums = new ArrayList<>();
        char[] serChar = {'А', 'В', 'Е', 'К', 'М', 'Н', 'О', 'Р', 'С', 'Т', 'У', 'Х'};

        for (int i = 1; i <= 95; i++) regNums.add(i);
        for (int i : new int[] {99, 97, 177, 199, 197, 777, 799, 150, 190, 750, 124, 98, 178, 198, 123, 159, 138, 163, 763, 96, 196, 116, 716})
            regNums.add(i);

        System.out.print("Start generating number...  ");
        long startTime = System.currentTimeMillis();
        for (int reg : regNums)
            for (char ser_1 : serChar)
                for (char ser_2 : serChar)
                    for (char ser_3 : serChar)
                        for (int i = 0; i < 500; i++) /** генерирую только половину номеров*/
                            if ((ser_2 == ser_3) || (i/100 == (i%100)/10 && i/100 == i%10)) {
                                /** изменил правило поиска красивых || вместо && для увеличения количества при тесте времени */
                                String temp = String.valueOf(ser_1) + (i < 10 ? ("00" + String.valueOf(i)) : i < 100 ? "0" + String.valueOf(i) : i) +
                                        String.valueOf(ser_2) + String.valueOf(ser_3) + "_" + (reg < 10 ? "0" + reg : reg);
                                carNum.add(temp);
                                carNumHash.add(temp);
                                carNumTree.add(temp);
                            }
        long generateTime = System.currentTimeMillis() - startTime;
        System.out.println("Generating for spec (thieves') num (" + generateTime + " mls)");
        System.out.println("Total nums: " + carNum.size());
        System.out.println("Случайный блатной номер: '" + carNum.get((int)(Math.random()*carNum.size())) + "'");
        Collections.sort(carNum);

        //fourthSearch("999");

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
            fourthSearch(num);
        }
    }

    /** Последний проверочный вызов программы. Естественно тут выводы что поиск через List.contains слегка быстрее перебора
     * тогда как бинарный поиск или поиск через Hash или Tree линейны и тратят минимальное время.
     *
     * Start generating number...  Generating for spec (thieves') num (33212 mls)
     * Total nums: 9430560
     * Случайный блатной номер: 'О333УС_14'
     * Введите номер автотранспорта: 999
     * Это обычный номер. (1 ms), найден за (24) итераций(ии)
     * Поиск через List.contains(): false (163 ms)
     *
     * classic not found.	 classicSearchTime (153);
     * listSet not found.	 listSearchTime (140);
     * binary not found.	 binarySearchTime (0);
     * hashSet not found.	 hashSetSearchTime (0);
     * treeSet not found.	 treeSetSearchTime (0);
     * */

    private static void fourthSearch(String val){
        System.out.println();
        long startTime = System.currentTimeMillis();
        boolean res = false;
        for (String str : carNum)
            if (str.equals(val)) { res = true; break;}
        if (res) System.out.print("classic found.");
        else System.out.print("classic not found.");
        long classicSearchTime = System.currentTimeMillis() - startTime;
        System.out.println("\t classicSearchTime (" + classicSearchTime + ");");

        startTime = System.currentTimeMillis();
        if (carNum.contains(val)) System.out.print("list found.");
        else System.out.print("listSet not found.");
        long listSearchTime = System.currentTimeMillis() - startTime;
        System.out.println("\t listSearchTime (" + listSearchTime + ");");

        startTime = System.currentTimeMillis();
        int result = Collections.binarySearch(carNum, val);
        if (result != -1) System.out.print("binary found.");
        else System.out.print("binary not found.");
        long binarySearchTime = System.currentTimeMillis() - startTime;
        System.out.println("\t binarySearchTime (" + binarySearchTime + ");");

        startTime = System.currentTimeMillis();
        if (carNumHash.contains(val)) System.out.print("hashSet found.");
        else System.out.print("hashSet not found.");
        long hashSetSearchTime = System.currentTimeMillis() - startTime;
        System.out.println("\t hashSetSearchTime (" + hashSetSearchTime + ");");

        startTime = System.currentTimeMillis();
        if (carNumTree.contains(val)) System.out.print("treeSet found.");
        else System.out.print("treeSet not found.");
        long treeSetSearchTime = System.currentTimeMillis() - startTime;
        System.out.println("\t treeSetSearchTime (" + treeSetSearchTime + ");");
    }

}
