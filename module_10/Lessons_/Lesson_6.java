package module_10.Lessons_;

import Utils.ConsoleColor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.URL;
import java.util.Scanner;

public class Lesson_6 {
    private static Scanner scanner;

    public static void main(String[] args) throws IOException {
        scanner = new Scanner(System.in);
        Lesson_6(); // выкачивание всех изображений
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

}
