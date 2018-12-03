package module_10.Lessons_;

import Utils.ConsoleColor;
import Utils.MyUtils;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class Lesson_5 {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException, ParseException {
        scanner = new Scanner(System.in);
        Lesson_5_1(); // по видео работа с Json
        //Lesson_5_2(); // апгрейд задания с контактами, 5 модуль
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


}
