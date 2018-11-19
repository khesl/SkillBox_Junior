package module_9;

import module_9.Forms.Form_3;

import javax.swing.*;
import java.awt.*;

public class Lessons {
    private JFrame frame_1;
    private JFrame frame_2;
    private JFrame frame_3;
    private Form_3 form_3;

    public static void main(String[] args) throws Exception {
        Lessons lessons = new Lessons();

        lessons.Lesson_();

    }

    private void Lesson_() throws Exception {
        // - Создать простой работающий интерфейс из трёх экранов без оформления и изображений (только элементы форм):
        //  ввод телефона, ввод кода подтверждения и отображение списка контактов.
        //---
        //      реализовал, 1 окно авторизация по номеру,
        //                  2 окно ввод кода + сделал таймер таймаута, с возможностью запроса нового кода.
        //                  3 окно вывод контактов в список (List) + выбор из списка с заполнением данных контакта.

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); // неплохо, меняет стиль элементов по стилю системы
        form_3 = new Form_3();
        //form_7_2_2 = new Form_7_2_2(this);
        //form_7_2_3 = new Form_7_2_3(this);
        frame_1 = createMainFrame(form_3.getRootPanel(), "Lesson_3 Окно 1", new Dimension(400,300));
        //frame_2 = createMainFrame(form_7_2_2.getRootPanel(), "Lesson_7 Окно 2, Ввод кода", new Dimension(400,300));
        //frame_3 = createMainFrame(form_7_2_3.getRootPanel(), "Lesson_7 Окно 3, Список контактов", new Dimension(400,300));

        //frame_1.setUndecorated(true); // убрать системные бордеры у окна!
        //frame_1.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        frame_1.setVisible(true);
    }



    public JFrame getFrame_1() { return frame_1; }
    public JFrame getFrame_2() { return frame_2; }
    public JFrame getFrame_3() { return frame_3; }

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
}
