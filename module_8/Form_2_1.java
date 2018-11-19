package module_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.regex.Pattern;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class Form_2_1 {
    private static Lessons lessons = null;

    private JPanel rootPanel;
    private JButton button1;
    private JTextField nameField;
    private JTextField textLength;
    private JTextField surnameField;
    private JTextField patronymicField;
    private JProgressBar progressBar1;
    private JTextField robasticField;
    private JLabel robasticLabel;
    private int nameFilled = 0;
    private int surnameFilled = 0;
    private int patronymicFilled = 0;

    public Form_2_1(Lessons lessons) {
        this.lessons = lessons;

        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callNewForm();
            }
        });
        nameField.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                button1.setBackground(Color.GREEN);
            }
        });
        nameField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyTyped(e);
                if (e.getKeyCode() == KeyEvent.VK_ENTER){
                    textLength.setText(String.valueOf(nameField.getText().length()));
                }
                if (e.isControlDown() && e.getKeyCode() == KeyEvent.VK_ENTER){
                    textLength.setText("Cntrl + Enter");
                }
            }
        });
        nameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (nameField.getText().length() > 0) nameFilled = 1;
                else nameFilled = 0;
                updateProgressBar();
            }
        });
        surnameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (surnameField.getText().length() > 0) surnameFilled = 1;
                else surnameFilled = 0;
                updateProgressBar();
            }
        });
        patronymicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                if (patronymicField.getText().length() > 0) patronymicFilled = 1;
                else patronymicFilled = 0;
                updateProgressBar();
            }
        });

        InputMap im = getRootPanel().getInputMap(WHEN_IN_FOCUSED_WINDOW);// (WHEN_FOCUSED);
        ActionMap am = getRootPanel().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, InputEvent.CTRL_DOWN_MASK, false), "onCtrl_Enter");

        am.put("onCtrl_Enter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callNewForm();
            }
        });

        progressBar1.setMaximum(100);
        progressBar1.setMinimum(0);

        robasticField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
            }
        });
        robasticField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                super.keyPressed(e);
                updateProgressBar(robasticChecker(robasticField.getText()), 0, 100);
            }
        });
    }

    private void updateProgressBar(){
        System.out.println("Update Bar " + (int)((nameFilled/3f + surnameFilled/3f + patronymicFilled/3f)*100));
        progressBar1.setForeground(Color.GREEN);
        progressBar1.setValue((int)((nameFilled/3f + surnameFilled/3f + patronymicFilled/3f)*100));
    }

    private void callNewForm(){
        if(!Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+$").matcher(nameField.getText()).matches()){
            //lessons.getErrorFrame().setErrorMessage("Неверно введено Имя");
            JOptionPane.showMessageDialog(getRootPanel(), "Неверно введено Имя", "Ошибка", JOptionPane.WARNING_MESSAGE);
        }
        else if(!Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+$").matcher(surnameField.getText()).matches())
            lessons.getErrorFrame().setErrorMessage("Неверно введена Фамилия");
        else if(!Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+$").matcher(patronymicField.getText()).matches()){
            //lessons.getErrorFrame().setErrorMessage("Неверно введено Отчество");
            int desition = JOptionPane.showConfirmDialog(getRootPanel(), "Отчество не заполнено. Вы уверены?", "", JOptionPane.YES_NO_OPTION);
            /**
             * 0 - yes
             * 1 - no
             * 2 - cancel
             * */
            if (desition == JOptionPane.YES_OPTION) {
                lessons.getForm_2_1_2().getFioField()
                        .setText( nameField.getText() + " " + surnameField.getText() + " " + patronymicField.getText() );
                lessons.getFrame_2().setVisible(true);
                lessons.getFrame_1().setVisible(false);
            }
        } else {
            lessons.getForm_2_1_2().getFioField().setText(
                    nameField.getText() + " " + surnameField.getText() + " " + patronymicField.getText()
            );
            lessons.getFrame_2().setVisible(true);
            lessons.getFrame_1().setVisible(false);
        }
    }

    private int robasticChecker(String text){
        int currentBall = 0;
        int maxBall = 0;

        // а-я 1072-1103
        for (int i = 1072; i <= 1103; i++)
            if (text.contains(String.valueOf((char)i))){
                currentBall++;
                break;
            }
        maxBall++;
        // А-Я 1040-1071
        for (int i = 1040; i <= 1071; i++)
            if (text.contains(String.valueOf((char)i))){
                currentBall++;
                break;
            }
        maxBall++;
        // a-z 97-122
        for (int i = 97; i <= 122; i++)
            if (text.contains(String.valueOf((char)i))){
                currentBall++;
                break;
            }
        maxBall++;
        // A-Z 65-90
        for (int i = 65; i <= 90; i++)
            if (text.contains(String.valueOf((char)i))){
                currentBall++;
                maxBall++;
                break;
            }
        maxBall++;
        // всякие 33-45 64 94 124
        for (int i = 33; i <= 45; i++)
            if (text.contains(String.valueOf((char)i))){
                currentBall++;
            }
        maxBall += 45-33-2;

        // 0-9 48-57
        for (int i = 48; i <= 57; i++)
            if (text.contains(String.valueOf((char)i))){
                currentBall++;
                maxBall++;
                break;
            }
        maxBall++;

        if (text.length() > 8 && text.length() < 12)
            currentBall++;
        else if (text.length() < 16)
            currentBall+=2;
        else currentBall+=3;

        float result = (currentBall * 1f)/maxBall;
        int resultInt = (int)(result * 100);
        System.out.println(" " + currentBall + " " + maxBall + " " + result + " " + resultInt);

        return resultInt;
    }
    private void updateProgressBar(int currentValue, int minValue, int maxValue){
        System.out.print("Update Bar[" + minValue + ".." + maxValue + "] " + currentValue + "/" + maxValue);
        progressBar1.setMinimum(minValue);
        progressBar1.setMaximum(maxValue);
        progressBar1.setValue(currentValue);
        int colorCheck = (int)((currentValue * 1f/(maxValue-minValue))* maxValue);
        System.out.print(":" + colorCheck);

        if (colorCheck < 25)
            progressBar1.setForeground(Color.YELLOW);
        else if (colorCheck < 50)
            progressBar1.setForeground(Color.ORANGE);
        else if (colorCheck < 75)
            progressBar1.setForeground(Color.CYAN);
        else if (colorCheck < 100)
            progressBar1.setForeground(Color.BLUE);
        else progressBar1.setForeground(Color.GREEN);

    }

    public JPanel getRootPanel(){ return rootPanel; }
    public JTextField getNameField() { return nameField; }
    public JTextField getSurnameField() { return surnameField; }
    public JTextField getPatronymicField() { return patronymicField; }
}
