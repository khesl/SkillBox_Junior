package Diploma.src;

import Diploma.DiplomaProj;
import Diploma.src.Forms.StartSignInForm;
import module_8.*;

import javax.swing.*;
import java.awt.*;

public class Main {

    private JFrame frame_1;
    private JFrame frame_2;
    private JFrame frame_3;
    private StartSignInForm startSignInForm;
    private Form_7_2_2 form_7_2_2;
    private Form_7_2_3 form_7_2_3;
    private DiplomaProj diplomApp = new DiplomaProj();

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.StartForm();
    }


    private void StartForm(){

        startSignInForm = new StartSignInForm(this);
        //form_7_2_2 = new Form_7_2_2(this);
        //form_7_2_3 = new Form_7_2_3(this);
        frame_1 = createMainFrame(startSignInForm.getRootPanel(), "Lesson_7 Окно 1, Ввод телефона", new Dimension(400,300));
        //frame_2 = createMainFrame(form_7_2_2.getRootPanel(), "Lesson_7 Окно 2, Ввод кода", new Dimension(400,300));
        //frame_3 = createMainFrame(form_7_2_3.getRootPanel(), "Lesson_7 Окно 3, Список контактов", new Dimension(400,300));

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
    public DiplomaProj getDiplomApp() { return diplomApp; }
    public StartSignInForm startSignInForm() { return startSignInForm; }
    public Form_7_2_2 getForm_7_2_2() { return form_7_2_2; }
    public Form_7_2_3 getForm_7_2_3() { return form_7_2_3; }

}
