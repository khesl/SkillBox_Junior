package module_8;

import javax.swing.*;
import java.awt.*;

public class Lessons {

    public static void main(String[] args) {
        Lessons lessons = new Lessons();

        Lesson_1();
        //lessons.Lesson_2();
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
    public Form_2_1 getForm_2_1() { return form_2_1; }
    public Form_2_1_2 getForm_2_1_2() { return form_2_1_2; }
    public ErrorJFrame getErrorFrame() { return errorFrame; }


}
