package module_8;

import Diploma.src.DiplomaProj;

import javax.swing.*;
import java.awt.*;

public class Lessons {

    public static void main(String[] args) throws Exception {
        Lessons lessons = new Lessons();

        //Lesson_1();
        //lessons.Lesson_2(); // уроки 2-6 - каждый улучшает проект
        //lessons.Lesson_2_4();
        //Lesson_7_1();
        lessons.Lesson_7_2();
    }

    /**
     * - Создать форму с полями: “Фамилия”, “Имя”, “Отчество”, “Дата рождения” и “Город проживания”.
     * ---
     *      Создано на форме ->
     * */
    private static void Lesson_1(){
        Form form = new Form();
        JFrame frame_1 = createMainFrame(form.getRootPanel(), "Lesson_1", new Dimension(400,300));

        frame_1.setVisible(true);
        //frame_1.setResizable(false); // как решение проблемы с разъезжанием элементов
        frame_1.pack();
    }

    /** пример кода без UI конструктора
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        JButton button = new JButton();
        button.setText("Click");
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.getContentPane().setBackground(Color.red);
            }
        });

        frame.add(button);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(400, 300);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("TestFrame");
        frame.setVisible(true);*/

    private JFrame frame_1;
    private JFrame frame_2;
    private ErrorJFrame errorFrame;
    private Form_2_1 form_2_1;
    private Form_2_1_2 form_2_1_2;
    private void Lesson_2(){
        //- Создать две формы: первую ­ с трема полями ­ “Фамилия”, “Имя” и “Отчество” и кнопой,
        //  и вторую с одним полем “Ф.И.О.” и кнопкой. При нажатии на кнопку первая форма должна подменяться второй,
        //  и наоборот. При этом поля формы должны заполняться автоматически: если в первой форме были введены данные,
        //  они должны оказаться во второй форме, и наоборот.
        //
        //- Самостоятельно изучить, как обрабатывать сочетания клавиш.
        //
        //- Усложнённое задание: сделать так, чтобы переключение между двумя формами в обе стороны работало
        // также при нажатии на <Ctrl+Enter>.
        //----------
        // Сделаны все задания + сделано также Окно при возникновении Ошибки ввода ФИО, закрытие окна ошибки при нажатии Enter.
        //-------
        // до 3 урока не знал о классе JOptionPane, поэтому ранее создал свой класс по отображению окна,
        //      далее переделал на вызов JOptionPane
        // https://docs.oracle.com/javase/tutorial/uiswing/components/dialog.html

        //- В созданном приложении, если не заполнено поле с фамилией или именем, предупреждать, что нужно заполнить.
        // Если не заполнено отчество, спрашивать, уверены ли Вы в том, что не хотите установить отчество.
        //
        //- Усложнённое задание: убрать кнопку “Cancel” в диалоговом окне из трёх кнопок
        // (найти эту информацию и разобраться самостоятельно).
        //----  устанавливается добавлением параметра JOptionPane.YES_NO_OPTION
        // JOptionPane.showConfirmDialog(getRootPanel(), "Отчество не заполнено. Вы уверены?", "", JOptionPane.YES_NO_OPTION);

        //- В созданной форме сделать так, чтобы элементы не разъезжались при изменении размера окна.
        //----  изначально это сделал, добавляя элементы способом GridLayoutManager

        //- Добавить в созданное приложение во вторую форму JProgressBar и отображать на нём,
        // в какой мере форма заполнена (максимальная длина ­ на Ваше усмотрение).

        form_2_1 = new Form_2_1(this);
        form_2_1_2 = new Form_2_1_2(this);
        errorFrame = new ErrorJFrame(this);
        frame_1 = createMainFrame(form_2_1.getRootPanel(), "Lesson_2_1 Окно 1", new Dimension(400,300));
        frame_2 = createMainFrame(form_2_1_2.getRootPanel(), "Lesson_2_1 Окно 2", new Dimension(400,300));

        frame_1.setVisible(true);
    }

    private void Lesson_2_4() throws Exception{
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // неплохо, меняет стиль элементов по стилю системы

        form_2_1 = new Form_2_1(this);
        form_2_1_2 = new Form_2_1_2(this);
        errorFrame = new ErrorJFrame(this);
        frame_1 = createMainFrame(form_2_1.getRootPanel(), "Lesson_2_1 Окно 1", new Dimension(400,300));

        frame_1.setUndecorated(true); // убрать системные бордеры у окна!
        frame_1.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)

        frame_2 = createMainFrame(form_2_1_2.getRootPanel(), "Lesson_2_1 Окно 2", new Dimension(400,300));

        frame_1.setVisible(true);
    }

    private static void Lesson_7_1(){
        //- По нерассмотренным в видео экранам расписать, из чего они состоят. Нарисовать схему на бумаге,
        // отсканировать или сфотографировать и прислать.
        /**
         Опишу тут структуру скринов:
            2 - ввод кода подтверждения. Элементы:
                Image - на фоне
                Image - с иконкой приложения
                JLabel - печатает номер телефона
                JLabel - текст сообщения: "На данный номер... "
                JPasswordField - поле для ввода кода
                JButton - кнопка для перехода на след экран
                + строка состояния, кнопки свернуть, закрыть
            3 - регистрация. Элементы:
                Image - на фоне
                Image - с иконкой приложения
                JLabel - текст сообщения: "Введите ваши... "
                JTextField - ввод имени
                JTextField - ввод фамилии
                JButton - кнопка для перехода на след экран
                + строка состояния, кнопки свернуть, закрыть
            4 - основной экран приложения. Элементы:
                JPanel - с BorderLayuot
                    Jpanel - в TOP части, меню приложения
                        Image - иконка приложения
                        JPanel - инфо пользователя
                            Image - мини аватар
                            JLabel - Имя фамилия
                            JButton - кнопка меню
                    JPanel - в LEFT части
                        JTextField - строка поиска
                        JPanel - контейнер объектов JPanel (инфо пользователя, описание выше)
                        JButton - кнопка добавления контакта
                    JPanel - в CENTER части
                        JPanel - в TOP части
                            JPanel - инфо собеседника
                            JButton - кнопка edit
                        JPanel - CENTER части
                            JPanel - контейнер сообщений []
                                JLabel - текст сообщения
                                JLabel - time_stamp
                            JPanel - строка ввода сообщения
                                JTextField - ввод сообщения
                                JButton - кнопка отправки
                + строка состояния, кнопки свернуть, закрыть
            5 - настройки профиля. Элементы:
                Image - прозрачный фон
                JLabel - текст сообщения: "Настройки профиля"
                JTextField - ввод имени
                JTextField - ввод фамилии
                JButton - кнопка "Сохранить" для перехода на след экран
                + строва возврата + текст телефона, кнопка выход
            6 - добавление контакта. Элементы:
                Image - прозрачный фон
                JLabel - текст сообщения: "Добавление контакта"
                JLabel - текст сообщения: "Введите код страны..."
                JTextField - ввод номера
                JTextField - ввод имени
                JTextField - ввод фамилии
                JButton - кнопка "добавить" для перехода на след экран
                + строва возврата
            7 - Редактирование контакта. Элементы:
                Image - прозрачный фон
                JLabel - текст сообщения: "Редактирование контакта"
                Image - аватар контакта
                JTextField - ввод имя + фамилия
                JTextField - ввод номер
                JButton - кнопка удаления пользователя
                JButton - кнопка "сохранить" для перехода на след экран
                + строва возврата
         * */
        System.out.println("\n" +
                "         Опишу тут структуру скринов:\n" +
                "            2 - ввод кода подтверждения. Элементы:\n" +
                "                Image - на фоне\n" +
                "                Image - с иконкой приложения\n" +
                "                JLabel - печатает номер телефона\n" +
                "                JLabel - текст сообщения: \"На данный номер... \"\n" +
                "                JPasswordField - поле для ввода кода\n" +
                "                JButton - кнопка для перехода на след экран\n" +
                "                + строка состояния, кнопки свернуть, закрыть\n" +
                "            3 - регистрация. Элементы:\n" +
                "                Image - на фоне\n" +
                "                Image - с иконкой приложения\n" +
                "                JLabel - текст сообщения: \"Введите ваши... \"\n" +
                "                JTextField - ввод имени\n" +
                "                JTextField - ввод фамилии\n" +
                "                JButton - кнопка для перехода на след экран\n" +
                "                + строка состояния, кнопки свернуть, закрыть\n" +
                "            4 - основной экран приложения. Элементы:\n" +
                "                JPanel - с BorderLayuot\n" +
                "                    Jpanel - в TOP части, меню приложения\n" +
                "                        Image - иконка приложения\n" +
                "                        JPanel - инфо пользователя\n" +
                "                            Image - мини аватар\n" +
                "                            JLabel - Имя фамилия\n" +
                "                            JButton - кнопка меню\n" +
                "                    JPanel - в LEFT части\n" +
                "                        JTextField - строка поиска\n" +
                "                        JPanel - контейнер объектов JPanel (инфо пользователя, описание выше)\n" +
                "                        JButton - кнопка добавления контакта\n" +
                "                    JPanel - в CENTER части\n" +
                "                        JPanel - в TOP части\n" +
                "                            JPanel - инфо собеседника\n" +
                "                            JButton - кнопка edit\n" +
                "                        JPanel - CENTER части\n" +
                "                            JPanel - контейнер сообщений []\n" +
                "                                JLabel - текст сообщения\n" +
                "                                JLabel - time_stamp\n" +
                "                            JPanel - строка ввода сообщения\n" +
                "                                JTextField - ввод сообщения\n" +
                "                                JButton - кнопка отправки\n" +
                "                + строка состояния, кнопки свернуть, закрыть\n" +
                "            5 - настройки профиля. Элементы:\n" +
                "                Image - прозрачный фон\n" +
                "                JLabel - текст сообщения: \"Настройки профиля\"\n" +
                "                JTextField - ввод имени\n" +
                "                JTextField - ввод фамилии\n" +
                "                JButton - кнопка \"Сохранить\" для перехода на след экран\n" +
                "                + строва возврата + текст телефона, кнопка выход\n" +
                "            6 - добавление контакта. Элементы:\n" +
                "                Image - прозрачный фон\n" +
                "                JLabel - текст сообщения: \"Добавление контакта\"\n" +
                "                JLabel - текст сообщения: \"Введите код страны...\"\n" +
                "                JTextField - ввод номера\n" +
                "                JTextField - ввод имени\n" +
                "                JTextField - ввод фамилии\n" +
                "                JButton - кнопка \"добавить\" для перехода на след экран\n" +
                "                + строва возврата\n" +
                "            7 - Редактирование контакта. Элементы:\n" +
                "                Image - прозрачный фон\n" +
                "                JLabel - текст сообщения: \"Редактирование контакта\"\n" +
                "                Image - аватар контакта\n" +
                "                JTextField - ввод имя + фамилия\n" +
                "                JTextField - ввод номер\n" +
                "                JButton - кнопка удаления пользователя\n" +
                "                JButton - кнопка \"сохранить\" для перехода на след экран\n" +
                "                + строва возврата");
    }

    private JFrame frame_3;
    private Form_7_2_1 form_7_2_1;
    private Form_7_2_2 form_7_2_2;
    private Form_7_2_3 form_7_2_3;
    private DiplomaProj diplomApp = new DiplomaProj();
    private void Lesson_7_2(){
        // - Создать простой работающий интерфейс из трёх экранов без оформления и изображений (только элементы форм):
        //  ввод телефона, ввод кода подтверждения и отображение списка контактов.
        //---
        //      реализовал, 1 окно авторизация по номеру,
        //                  2 окно ввод кода + сделал таймер таймаута, с возможностью запроса нового кода.
        //                  3 окно вывод контактов в список (List) + выбор из списка с заполнением данных контакта.

        form_7_2_1 = new Form_7_2_1(this);
        form_7_2_2 = new Form_7_2_2(this);
        form_7_2_3 = new Form_7_2_3(this);
        errorFrame = new ErrorJFrame(this);
        frame_1 = createMainFrame(form_7_2_1.getRootPanel(), "Lesson_7 Окно 1, Ввод телефона", new Dimension(400,300));
        frame_2 = createMainFrame(form_7_2_2.getRootPanel(), "Lesson_7 Окно 2, Ввод кода", new Dimension(400,300));
        frame_3 = createMainFrame(form_7_2_3.getRootPanel(), "Lesson_7 Окно 3, Список контактов", new Dimension(400,300));

        //frame_1.setUndecorated(true); // убрать системные бордеры у окна!
        frame_1.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame_1.setVisible(true);
    }




    private static JFrame createMainFrame(JPanel panel, String formName, Dimension dimension){
        JFrame frame = new JFrame();

        frame.setContentPane(panel);

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        frame.setSize(dimension.width, dimension.height);
        frame.setLocation(dim.width/2-frame.getSize().width/2, dim.height/2-frame.getSize().height/2);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle(formName);

        return frame;
    }

    public JFrame getFrame_1() { return frame_1; }
    public JFrame getFrame_2() { return frame_2; }
    public JFrame getFrame_3() { return frame_3; }
    public Form_2_1 getForm_2_1() { return form_2_1; }
    public Form_2_1_2 getForm_2_1_2() { return form_2_1_2; }
    public ErrorJFrame getErrorFrame() { return errorFrame; }
    public DiplomaProj getDiplomApp() { return diplomApp; }
    public Form_7_2_1 getForm_7_2_1() { return form_7_2_1; }
    public Form_7_2_2 getForm_7_2_2() { return form_7_2_2; }
    public Form_7_2_3 getForm_7_2_3() { return form_7_2_3; }


}
