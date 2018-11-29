package module_10;

import Utils.ConsoleColor;
import Utils.MyUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Lessons {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException, ParseException {
        scanner = new Scanner(System.in);

        //System.out.println(new File("F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\new.txt").getPath());
        //System.out.println(new File("F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\new.txt").getAbsolutePath());
        //Lesson_1(); // вывод всем папок и подпапок по вводимому пути
        //Lesson_2_1(); // запись текста в файл по введённому пути
        //Lesson_2_2(); // копирование полного содержимого одной директории, в другую
        //Lesson_3(); // преобразование формата
        //Lesson_4(); // вытаскивание путей из HTML - jsoup
        //Lesson_5_1(); // по видео работа с Json
        //Lesson_5_2(); // апгрейд задания с контактами, 5 модуль
        //Lesson_6(); // выкачивание всех изображений
        Lesson_7();
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

    /** результат также записывается в файл format_3.txt
     * */
    private static void Lesson_3() throws FileNotFoundException {
        // - Написать программу, которая будет преобразовывать файлы формата “Probabilites.txt” в файлы формата “Probabilities ­ formatted.txt”.

        //	Алексей	Константин	Пётр
        //10.04.2014	0,66702672	0,800955536	0,43254686
        //11.04.2014	0,670182476	0,278783766	0,752237268
        //12.04.2014	0,337505115	0,405217108	0,514713717

        // 			    |	Алексей		|	Константин	|	Пётр
        //10.04.2014	|	0,66702672	|	0,800955536	|	0,43254686
        //11.04.2014	|	0,670182476	|	0,278783766	|	0,752237268
        //12.04.2014	|	0,337505115	|	0,405217108	|	0,514713717

        HashMap<Integer, String> columns = new HashMap<>();
        HashMap<Integer, String> rows = new HashMap<>();
        Object[][] objects = new Object[100][100];

        String path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\txt_format\\format_2.txt";
        // построчное чтение
        try {
            String file = new String(Files.readAllBytes(Paths.get(path)));
            String[] lines = file.split("\n");
            String[] colNames = lines[0].split("\t");
            for (int i = 1; i < colNames.length; i++) columns.put(i, colNames[i]);

            for (int i = 1; i < lines.length; i++) {
                String[] values = lines[i].split("\t");
                rows.put(i-1, values[0]);
                for (int j = 1; j < values.length; j++)
                    objects[i-1][j-1] = values[j];
            }
        } catch (IOException e) {
            e.printStackTrace();
        }


        PrintWriter pw = new PrintWriter("F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\txt_format\\format_3.txt");

        // вывод
        String firstRow = "\t\t\t";
        for (Map.Entry entry : columns.entrySet()){
            String data = formatedString(entry.getValue().toString().trim());
            firstRow += "|\t" + data + "\t";
        }
        pw.write(firstRow);
        pw.flush();
        System.out.println(firstRow);
        int rowCount = 0;
        for (Map.Entry entry : rows.entrySet()){
            int columnCount = 0;
            String row = entry.getValue().toString() + "\t";
            for (Map.Entry entry_ : columns.entrySet()){
                String data = formatedString(objects[rowCount][columnCount].toString().trim());
                row += "|\t" + data + "\t";
                columnCount++;
            }
            pw.write("\n");
            pw.write(row);
            pw.flush();
            System.out.println(row);
            rowCount++;
        }
        pw.flush(); // сброс буферизации
        pw.close();
    }

    private static int formatLength = 12;
    public static String formatedString(String text){
        if (formatLength - text.length() < 0) return text;
        while (formatLength > text.length()-1)
            text += " ";
        return text;
    }

    private static void Lesson_4() throws IOException {
        // - Написать код, формирующий список абсолютных путей изображений,
        // использованных на странице курса ­ http://ucancode.ru/java/
        String path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\res\\page.html";
        Document doc = Jsoup.parse(new File(path), "UTF-8");

        Elements elements = doc.select("img");
        for (Element element : elements){
            //System.out.println(element.toString());
            // все пути ко всем картинкам
            System.out.println(element.attr("src"));
        }
    }

    private static void Lesson_5_1() throws ParseException, IOException {
    String path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\res\\data.json";
        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(new String(Files.readAllBytes(Paths.get(path))));

        for (Object key :object.keySet()){
            System.out.println(key);
            JSONObject value = (JSONObject) object.get(key);
            System.out.println("\t" + value.get("name"));
        }
    }

    private static Map<String, String> contacts = new TreeMap<>();
    private static void Lesson_5_2() throws FileNotFoundException {
        // - Реализовать сохранение телефонной книги из 4­го урока 5­го модуля в JSON­ формат
        // (добавить в приложение команду EXPORT и запрос пути к папке, в которую сохранить файл).

        // "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\export\\export_contact.json"

        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, what do want? (LIST, EXPORT, exit)", ConsoleColor.Color.ANSI_YELLOW));
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
            } else if (in.equals("EXPORT")){
                System.out.println(ConsoleColor.setColor("robot# Write path to Export contact data.", ConsoleColor.Color.ANSI_YELLOW));
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                while (!(in = scanner.nextLine()).toLowerCase().equals("exit")){
                    /*File file = new File(in);
                    if (!file.exists()) {
                        System.out.println(ConsoleColor.setColor("robot# Write path incorrect! please write new path.", ConsoleColor.Color.ANSI_YELLOW));
                        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                        continue;
                    }*/
                    String jsonContacts = "{\n";
                    int count = 1;
                    for (Map.Entry entry : contacts.entrySet()){
                        String contact = "\t\"" + count++ + "\":{\n" +
                                "\t\t\"name\":\"" + entry.getValue() + "\",\n" +
                                "\t\t\"phone\":\"" + entry.getKey() + "\"\n" +
                                "\t}";
                        System.out.println("\t" + ConsoleColor.setColor(String.valueOf(entry.getKey() + " : " + entry.getValue()), ConsoleColor.Color.ANSI_RED));
                        jsonContacts += contact + ",\n";
                    }
                    jsonContacts = jsonContacts.substring(0, jsonContacts.length()-2);
                    jsonContacts += "\n}";
                    System.out.println(jsonContacts);

                    // вывод
                    PrintWriter pw = new PrintWriter(in);

                    pw.write(jsonContacts);
                    pw.flush(); // сброс буферизации
                    pw.close();

                    System.out.println(ConsoleColor.setColor("robot# write success. (LIST, EXPORT, exit)", ConsoleColor.Color.ANSI_YELLOW));
                    break;
                }
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
                if (MyUtils.checkPhoneNumber(phone) ? contacts.containsKey(phone) : contacts.containsValue(phone)) {
                    if (MyUtils.checkPhoneNumber(phone)){
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

                if (MyUtils.checkPhoneNumber(phone)){
                    key = MyUtils.getPhoneNumber(phone);
                    System.out.println(ConsoleColor.setColor("robot# Please now write Name.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    value = scanner.nextLine();
                } else {
                    value = phone;
                    System.out.println(ConsoleColor.setColor("robot# Please now write number.", ConsoleColor.Color.ANSI_YELLOW));
                    System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                    key = scanner.nextLine();
                    if (!MyUtils.checkPhoneNumber(key)){
                        System.out.println(ConsoleColor.setColor("robot# wrong number format. Try more.", ConsoleColor.Color.ANSI_YELLOW));
                        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                        key = scanner.nextLine();
                        if (!MyUtils.checkPhoneNumber(key)) continue;
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

    /**
     * неплохо качает отсюда: https://skillbox.ru/
     */
    private static void Lesson_6() throws IOException {
        // - Написать программу, которая будет скачивать в папку все изображения с указанной веб­страницы.
        // https://course.skillbox.ru/webdev/

        String in = "";
        System.out.println(ConsoleColor.setColor("robot# Hello, what do want? (LIST, EXPORT, exit)", ConsoleColor.Color.ANSI_YELLOW));
        System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));

        while (!(in = scanner.nextLine()).equals("exit")) {
            if (in.length() == 0 || (!in.contains("https:") && !in.contains("http:"))) {
                System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
                continue;
            }

            //URL url = new URL("https://course.skillbox.ru/webdev/");
            URL url = new URL(in);


            InputStream stream = url.openStream();
            String path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\export\\page.html";
            FileOutputStream fos = new FileOutputStream(path);

            int code;
            while ((code = stream.read()) > 0) {
                char c = (char) code;
                fos.write(code);
                System.out.print(c);
            }
            System.out.println();

            fos.flush();
            fos.close();

            Document doc = Jsoup.parse(new File(path), "UTF-8");

            Elements elements = doc.select("img");
            for (Element element : elements) {
                try {
                    String src = element.attr("src");
                    if (src.length() == 0) continue;
                    if (!src.contains("https:") && !src.contains("http:")) src = url + src.substring(1);
                    System.out.println(src);

                    URL url_ = new URL(src);
                    InputStream is = new BufferedInputStream(url_.openStream());
                    ByteArrayOutputStream out = new ByteArrayOutputStream();
                    byte[] buf = new byte[1024];
                    int n = 0;
                    while (-1 != (n = is.read(buf))) {
                        out.write(buf, 0, n);
                    }

                    out.close();
                    is.close();
                    byte[] response = out.toByteArray();

                    path = "F:\\files\\java\\JavaExperiments\\SkillBox_Junior\\module_10\\export\\img";
                    fos = new FileOutputStream(path + "\\" + src.split(String.valueOf("/"))[src.split(String.valueOf("/")).length - 1]);

                    fos.write(response);
                    fos.flush();
                    fos.close();
                } catch (IOException e) {
                    continue;
                }

            }
            System.out.print(ConsoleColor.setColor("console# ", ConsoleColor.Color.ANSI_BLUE));
        }


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
