package module_12;

import Utils.MyUtils;
import module_10.Lessons_.src.MyExplorerSimple;
import module_12.Lessons_.src.SiteMapBuilderGUI;
import module_12.Lessons_.src.StopWatch;
import module_12.Lessons_.src.StopWatchClass;
import module_12.res.Transactions.src.BankLoader;

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

        //less.Lesson_2();
        Lesson_6();
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

    private SiteMapBuilderGUI siteMapBuilderGUI;
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
        //      В интерфейсе приложения должны быть:
        //○ Поле ввода адреса сайта (адреса страницы, относительно которой приложение будет составлять карту).
        //○ Поле выбора файла (JFileChooser) для указания имени файла, в который приложение будет записывать ссылки на страницы сайта.
        //○ Поле с информацией о процессе сбора ссылок (сколько страниц программа обошла и сколько времени прошло с момента запуска).
        //○ Кнопки “Start/Stop” и “Pause”.
        //
        //      Дополнительные примечания и требования:
        //- Для отладки программы необходимо выбрать сайт, содержащий несколько сотен или тысяч страниц (например, http://www.lenta.ru/).
        //- Приложение должно собирать страницы в многопоточном режиме. Количество потоков должно быть оптимальным
        // (ваша задача ­ определить, каким именно).
        //- При запросе страниц, возможно, нужно будет выдерживать некоторые паузы (с помощью метода “sleep”), чтобы
        // сайт не заблокировал к себе доступ вашего приложения.

        siteMapBuilderGUI = new SiteMapBuilderGUI(this);
        frame = MyUtils.createMainFrame(siteMapBuilderGUI.getRootPanel(), "Генератор карты сайтов", new Dimension(640,480));
        //frame.setUndecorated(true); // убрать системные бордеры у окна!

        frame.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame.setVisible(true);

    }

    private static void Lesson_6(){
        //Урок 6. Взаимная блокировка
        //
        //- Проект Transactions. Есть банк, представленный классом Bank, в котором есть счета (класс Account) с
        // двумя полями ­ money и accNumber. Все счета хранятся внутри банка. Клиенты банка (их много) могут
        // делать переводы между счетами, запрашивать баланс по своему счету. Все это происходит в highly
        // concurrent (многопоточной) среде. При этом, транзакции на крупные суммы (> 50000) отправляются
        // на проверку Службе Безопасности. Можно считать, что таких транзакций не более 5% от всех. За эту
        // проверку отвечает отдельный и уже реализованный кем­то метод Bank.isFraud. Служба безопасности в Банке всего одна, работает медленно и не может обрабатывать более одной транзакции одновременно. Проверка транзакции занимает у них 1000 мс. Если проверка выявила мошенничество, то необходимо заблокировать оба счета, т.е. запретить любые изменения остатков.
        //
        //Нужно реализовать:
        //
        //- Метод “transfer” класса Bank, который переводит деньги с одного счета на другой.
        // Если сумма транзакции > 50000, то после совершения транзакции, она отправляется на проверку
        // Службе Безопасности – вызывается метод isFraud. Если возвращается true, то делается блокировка
        // счетов (как – на ваше усмотрение).
        //
        //- Метод getBalance класса Bank, который возвращает остаток на счете. Классы Account и Bank можно
        // дорабатывать как угодно для решения задачи. Кроме того, необходимо реализовать адекватный тест
        // (или набор тестов) для эмуляции реальной работы этих двух классов и проверки системы.

        /** задание выполнено, всё в доп классах {@link module_12.res.Transactions.src.BankLoader}*/
        BankLoader bankLoader = new BankLoader();
        bankLoader.init();
    }
}
