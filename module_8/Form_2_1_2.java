package module_8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class Form_2_1_2 {
    private static Lessons lessons = null;
    private JTextField fioField;
    private JButton jumpBackButton;
    private JPanel rootPanel;


    public Form_2_1_2(Lessons lessons) {
        this.lessons = lessons;

        jumpBackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                callNewForm();
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

    public JTextField getFioField() { return fioField; }
    public JPanel getRootPanel(){ return rootPanel; }
    private void callNewForm(){
        if(!Pattern.compile("[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+\\s[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+\\s" +
                "[А-ЯЁ&&[^ЪЬЫ]]{1}[а-яё]+$").matcher(fioField.getText()).matches())
            lessons.getErrorFrame().setErrorMessage("Неверно введено Ф.И.О.");
        else {
            String[] fullNameArray = fioField.getText().split("\\s");

            lessons.getForm_2_1().getNameField().setText(fullNameArray[0]);
            lessons.getForm_2_1().getSurnameField().setText(fullNameArray[1]);
            lessons.getForm_2_1().getPatronymicField().setText(fullNameArray[2]);

            lessons.getFrame_1().setVisible(true);
            lessons.getFrame_2().setVisible(false);
        }
    }
}
