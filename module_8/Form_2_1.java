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
                nameFilled = 1;
                updateProgressBar();
            }
        });
        surnameField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                surnameFilled = 1;
                updateProgressBar();
            }
        });
        patronymicField.addFocusListener(new FocusAdapter() {
            @Override
            public void focusLost(FocusEvent e) {
                super.focusLost(e);
                patronymicFilled = 1;
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

    }

    private void updateProgressBar(){
        progressBar1.setValue((nameFilled + surnameFilled + patronymicFilled) / 3);
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

    public JPanel getRootPanel(){ return rootPanel; }
    public JTextField getNameField() { return nameField; }
    public JTextField getSurnameField() { return surnameField; }
    public JTextField getPatronymicField() { return patronymicField; }
}
