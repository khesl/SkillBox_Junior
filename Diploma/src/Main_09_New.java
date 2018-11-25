package Diploma.src;

import Diploma.src.Forms_09_new.*;
import org.javagram.response.object.UserContact;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.regex.Pattern;

public class Main_09_New implements CoreTelegramContainerInt {
    private JFrame frame_1;
    private StartSignInForm startSignInForm;
    private CodeSignForm codeSignForm;
    private MainForm mainForm;
    private StartSignUpForm startSignUpForm;
    private EditProfileForm editProfileForm;
    private AddContactsForm addContactsForm;
    private EditContactsForm editContactsForm;
    private static DiplomaProj diplomApp = new DiplomaProj();
    private static final boolean IS_DRY_CALL = true; // true - холостой вызов/не стучится в телеграм

    public static void main(String[] args) throws Exception {
        Main_09_New main = new Main_09_New();

        main.StartForm();
    }

    public static Image backgroundImage;
    public static Image background_320x240_85_opacity_Image;
    public static Image logo;
    public static Image hideButton;
    public static Image hideButtonOnCursor;
    public static Image closeButton;
    public static Image closeButtonOnCursor;
    private void StartForm(){
        try {
            backgroundImage = javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\DSC_0351_722x480.jpg"));
            background_320x240_85_opacity_Image = javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\320х240_85_opac.png"));
            logo = javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\GUI Components\\logo.png"));
            hideButton =  javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\GUI Components\\icon-hide.png"));;
            hideButtonOnCursor =  javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\GUI Components\\icon-hide_onCursor.png"));;
            closeButton =  javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\GUI Components\\icon-close.png"));;
            closeButtonOnCursor =  javax.imageio.ImageIO.read(new File(new File("").getAbsolutePath() + "\\SkillBox_Junior\\Diploma\\src\\Image\\GUI Components\\icon-close_onCursor.png"));;
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
        frame_1.setUndecorated(true); // убрать системные бордеры у окна!

        //frame_2 = createMainFrame(codeSignForm.getRootPanel(), "Окно 2, Ввод кода", new Dimension(640,480));
        //frame_3 = createMainFrame(mainForm.getRootPanel(), "Окно 3, Список контактов", new Dimension(640,480));
        //frame_4 = createMainFrame(startSignUpForm.getRootPanel(), "Окно 4, Окно регистрации", new Dimension(640,480));
        //frame_5 = createMainFrame(editProfileForm.getRootPanel(), "Окно 5, Настройки профиля", new Dimension(320,240));
        //frame_6 = createMainFrame(addContactsForm.getRootPanel(), "Окно 6, Настройки профиля", new Dimension(320,240));
        //frame_7 = createMainFrame(editContactsForm.getRootPanel(), "Окно 7, Редактирование контакта", new Dimension(640,480));

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
        frame_1.setVisible(true);
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
    public void hideMainFrame(){
        frame_1.setExtendedState(JFrame.ICONIFIED);// this.getExtendedState());
    }
    public void closeMainFrame(){
        frame_1.dispose(); // освбождает ресурсы, но Апп умирает не сразу.. скорее всего при удалении мусорщиком.
        // предполагаю, что может не закрыть приложение,
        // когда вдруг останутся вызванные и забытые ресурсы, может ли такое быть?
    }

    private Point draggedLocation = new Point(0,0);
    private boolean isTopBarMousePressed = false;
    public void moveMainframe(MouseEvent mouseEvent){
        int deltaX = mouseEvent.getLocationOnScreen().x - draggedLocation.x;
        int deltaY = mouseEvent.getLocationOnScreen().y - draggedLocation.y;
        frame_1.setLocation(deltaX, deltaY);
    }
    public void setTopBarMousePressed(boolean topBarMousePressed, MouseEvent mouseEvent) {
        isTopBarMousePressed = topBarMousePressed;
        if (topBarMousePressed == true) draggedLocation = mouseEvent.getPoint();
    }

    public JFrame getFrame_1() { return frame_1; }
    static public DiplomaProj getDiplomApp() { return diplomApp; }
    public StartSignInForm startSignInForm() { return startSignInForm; }
    public StartSignUpForm startSignUpForm() { return startSignUpForm; }
    public CodeSignForm getCodeSignForm() { return codeSignForm; }
    public MainForm getMainForm() { return mainForm; }
    public DefaultListModel<UserContact> getModel() {
        if (model == null) {
            model = new DefaultListModel<>();
            updateContacts();
        }
        return model;
    }

    public boolean getSmsAuthorisation(){
        try {
            if (!IS_DRY_CALL) getDiplomApp().getSmsAuthorisation();
            switchContentPanes(ContentPanes.codeSign);
            codeSignForm.startTimer(15);
            return true;
        } catch (IOException e1) {
            //e1.printStackTrace();
            System.out.println(e1.getMessage());
            if (e1.getMessage().equals(null)){
                JOptionPane.showMessageDialog(frame_1.getRootPane(), "Тайм Аут ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else if (e1.getMessage().equals("PHONE_NUMBER_INVALID") || e1.getMessage().equals("Please write correct number.")){
                JOptionPane.showMessageDialog(frame_1.getRootPane(), "Неверный номер телефона!", "Ошибка", JOptionPane.ERROR_MESSAGE);
            } else
                JOptionPane.showMessageDialog(frame_1.getRootPane(), "Ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
    public boolean getSmsAuthorisation(String phoneNumber){
        getDiplomApp().setPhone(phoneNumber);
        return getSmsAuthorisation();
    }

    public boolean setVerificationCode(String code){
        if (!Pattern.compile("[0-9]{5}").matcher(String.valueOf(code)).matches()){
            JOptionPane.showMessageDialog(getFrame_1().getRootPane(), "Неверный формат кода.", "Ошибка", JOptionPane.ERROR_MESSAGE);
            return false;
        } else {
            try {
                if (!IS_DRY_CALL) if (!getDiplomApp().authorized(String.valueOf(code))) {
                    JOptionPane.showMessageDialog(getFrame_1().getRootPane(), "Ошибка авторизации, попробуйте позднее.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    //startTimer(10); // для теста
                    return false;
                }
                switchContentPanes(ContentPanes.main);
                return true;
            } catch (IOException e1) {
                //e1.printStackTrace();
                JOptionPane.showMessageDialog(getFrame_1().getRootPane(), "Вероятно код устарел.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
    }

    private DefaultListModel<UserContact> model = null;
    public void updateContacts(){
        try {
            getModel().clear();
            for (UserContact contact : getDiplomApp().getTelegramApiBridge().contactsGetContacts())
                getModel().addElement(contact);
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(frame_1.getRootPane(), "Произошла ошибка при обновлении контактов.", "Ошибка", JOptionPane.ERROR_MESSAGE);
        }
    }

    enum ContentPanes{
        startSignUp,
        startSignIn,
        main,
        editProfile,
        editContacts,
        codeSign,
        addContacts
    }
    /**
     * позаимствовал идёю определения формы. Теперь буду здесь менять наполнение форм.
     * */
    public void switchContentPanes(ContentPanes contentPanes){
        switch (contentPanes){
            case startSignIn: {
                // может быть в случае нового логина, или закрытии текущей сессии, или открытии новой.
                break;
            }
            case startSignUp: {
                // форма регистрации, может быть в случае регистрации нового номера телефона.
                break;
            }
            case main: {
                // поидее основная форма, будет содержать всё. в неё переход идёт после подтверждения кода авторизации,
                // либо сразу при входе в приложении когда есть запущенная активная сессия.
                if(frame_1.getContentPane() == codeSignForm.getRootPanel()) {
                    // какие-то действия
                    if (!IS_DRY_CALL) if (getDiplomApp().isAuthorized()) {
                        updateContacts();
                        mainForm.getContactsList().setModel(getModel());
                    }
                    frame_1.setContentPane(mainForm.getRootPanel());
                    System.out.println("here");
                    break;
                }
            }
            case codeSign: {
                // окно ввода кода. может быть при вводе телефона для подтверждения
                if(frame_1.getContentPane() == startSignInForm.getRootPanel())
                {
                    frame_1.setContentPane(codeSignForm.getRootPanel());
                    codeSignForm.getTelNumLabel().setText(startSignInForm.getCodeNumField().getText() + startSignInForm.getNumField().getText());
                    break;
                }
            }
            case addContacts: {
                // окно добавления контакта, может быть вызвано только из главной формы, при нажатии на добавление.
                if(frame_1.getContentPane() == mainForm.getRootPanel())
                {
                    break;
                }
            }
            case editProfile: {
                // может быть вызвано только из главной формы.
                if(frame_1.getContentPane() == mainForm.getRootPanel())
                {
                    break;
                }
            }
            case editContacts: {
                // может быть вызвано только из главной формы.
                if(frame_1.getContentPane() == mainForm.getRootPanel())
                {
                    break;
                }
            }
            default:
                break;
        }
        frame_1.revalidate();
        frame_1.repaint();
    }
}
