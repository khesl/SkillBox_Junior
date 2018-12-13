package module_12;

import Utils.MyUtils;
import module_10.Lessons_.src.MyExplorerSimple;
import module_12.Lessons_.src.StopWatch;
import module_12.Lessons_.src.StopWatchClass;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Lessons {

    //private StopWatchClass stopWatchClass = new StopWatchClass();
    public static void main(String[] args) {
        Lessons less = new Lessons();
        /** подзадачи разбиты внутри методов. */

        //System.out.println(less.stopWatchClass.getFormattedTime(new SimpleDateFormat("HH:mm:ss.S")));

        less.Lesson_2();
    }

    private JFrame frame;
    private StopWatch stopWatch;
    private void Lesson_2(){
        // секундомер с интерфейсом, 2 кнопки (start/pause, stop)

        stopWatch = new StopWatch(this);
        frame = MyUtils.createMainFrame(stopWatch.getRootPanel(), "Секундомер", new Dimension(300,140));
        //frame.setUndecorated(true); // убрать системные бордеры у окна!

        frame.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame.setVisible(true);
    }

    private void Lesson_5(){
        //Урок 5. Потокобезопасность
        //
        //- Разработать приложение с графическим интерфейсом, которое будет составлять карту сайта ­ выводить в
        // файл список ссылок на страницы, которые есть на сайте. Формат карты сайта должен быть следующим:
        //
        //http://ucancode.ru/
        //http://ucancode.ru/mobile/
        //http://ucancode.ru/java/
        //http://ucancode.ru/excel/
        //http://ucancode.ru/ios/
        //http://ucancode.ru/mobile/conf.pdf
        //
        //В файле должны быть ссылки на страницы, размещённые на том же домене (в примере ­ http://ucancode.ru/).
        // Ссылки должны быть сдвинуты вправо на такое количество символов табуляции, которое соответствует уровню
        // вложенности страницы относительно главной страницы. В списке не должно быть ссылок на другие сайты.
        //
        //В интерфейсе приложения должны быть:
        //
        //○ Поле ввода адреса сайта (адреса страницы, относительно которой приложение будет составлять карту).
        //
        //○ Поле выбора файла (JFileChooser) для указания имени файла, в который приложение будет записывать ссылки на страницы сайта.
        //
        //○ Поле с информацией о процессе сбора ссылок (сколько страниц программа обошла и сколько времени прошло с момента запуска).
        //
        //○ Кнопки “Start/Stop” и “Pause”.
        //
        //Дополнительные примечания и требования:
        //
        //- Для отладки программы необходимо выбрать сайт, содержащий несколько сотен или тысяч страниц (например, http://www.lenta.ru/).
        //
        //- Приложение должно собирать страницы в многопоточном режиме. Количество потоков должно быть оптимальным
        // (ваша задача ­ определить, каким именно).
        //
        //- При запросе страниц, возможно, нужно будет выдерживать некоторые паузы (с помощью метода “sleep”), чтобы
        // сайт не заблокировал к себе доступ вашего приложения.


    }
}
