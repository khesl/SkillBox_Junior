package module_9;

import javax.swing.*;
import java.awt.*;

public class Lessons {

    public static void main(String[] args){
        Lessons lessons = new Lessons();

        lessons.Lesson_();
    }

    private void Lesson_() {
        /**
         * 9 модуль ставит задачу сделать интерфейсы для диплома как на скринах.
         * Переношу все файлы и наработки в пакет Diploma/
         * */
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
}
