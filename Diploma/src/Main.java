package Diploma.src;

import Diploma.DiplomaProj;
import Diploma.src.Forms.*;
import module_8.*;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Main {

    private JFrame frame_1;
    private JFrame frame_2;
    private JFrame frame_3;
    private JFrame frame_4;
    private JFrame frame_5;
    private JFrame frame_6;
    private JFrame frame_7;
    private StartSignInForm startSignInForm;
    private CodeSignForm codeSignForm;
    private MainForm mainForm;
    private StartSignUpForm startSignUpForm;
    private EditProfileForm editProfileForm;
    private AddContactsForm addContactsForm;
    private EditContactsForm editContactsForm;
    private DiplomaProj diplomApp = new DiplomaProj();

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        main.StartForm();
    }

    public static Image backgroundImage;
    public static Image background_320x240_85_opacity_Image;
    private void StartForm(){
        try {
            backgroundImage = javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\DSC_0351_722x480.jpg"));
            background_320x240_85_opacity_Image = javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\320х240_85_opac.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        startSignInForm = new StartSignInForm(this);
        codeSignForm = new CodeSignForm(this);
        mainForm = new MainForm(this);
        startSignUpForm = new StartSignUpForm(this);
        editProfileForm = new EditProfileForm(this);
        addContactsForm = new AddContactsForm(this);
        editContactsForm = new EditContactsForm(this);
        frame_1 = createMainFrame(startSignInForm.getRootPanel(), "Окно 1, Ввод телефона", new Dimension(640,480));
        frame_2 = createMainFrame(codeSignForm.getRootPanel(), "Окно 2, Ввод кода", new Dimension(640,480));
        frame_3 = createMainFrame(mainForm.getRootPanel(), "Окно 3, Список контактов", new Dimension(640,480));
        frame_4 = createMainFrame(startSignUpForm.getRootPanel(), "Окно 4, Окно регистрации", new Dimension(640,480));
        frame_5 = createMainFrame(editProfileForm.getRootPanel(), "Окно 5, Настройки профиля", new Dimension(320,240));
        frame_6 = createMainFrame(addContactsForm.getRootPanel(), "Окно 6, Настройки профиля", new Dimension(320,240));
        frame_7 = createMainFrame(editContactsForm.getRootPanel(), "Окно 7, Редактирование контакта", new Dimension(640,480));

        /*try {
            final Image backgroundImage = javax.imageio.ImageIO.read(new File("F:/files/java/JavaExperiments/SkillBox_Junior/Diploma/src/Image/DSC_0351_722x480.jpg"));
            frame_1.setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        //frame_1.setUndecorated(true); // убрать системные бордеры у окна!
        frame_1.setResizable(false); // запретить изменение размеров окна (это знал, но всё равно полезно)
        //frame_1.setVisible(true);
        //frame_2.setVisible(true);
        //frame_3.setVisible(true);
        //frame_4.setVisible(true);
        //frame_5.setVisible(true);
        frame_6.setVisible(true);
        frame_7.setVisible(true);
    }




    private static JFrame createMainFrame(JPanel panel, String formName, Dimension dimension){
        JFrame frame = new JFrame();


        frame.setContentPane(panel);
        /*frame.setContentPane(new JPanel(new BorderLayout()) {
                @Override public void paintComponent(Graphics g) {
                    g.drawImage(backgroundImage, 0, 0, null);
                }
            });*/
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
    public JFrame getFrame_4() { return frame_4; }
    public JFrame getFrame_5() { return frame_5; }
    public DiplomaProj getDiplomApp() { return diplomApp; }
    public StartSignInForm startSignInForm() { return startSignInForm; }
    public StartSignUpForm startSignUpForm() { return startSignUpForm; }
    public CodeSignForm getCodeSignForm() { return codeSignForm; }
    public MainForm getMainForm() { return mainForm; }

}
