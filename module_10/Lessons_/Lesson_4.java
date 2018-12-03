package module_10.Lessons_;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;

public class Lesson_4 {

    public static void main(String[] args) throws IOException {
        Lesson_4(); // вытаскивание путей из HTML - jsoup
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

}
