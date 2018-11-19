package module_8;

import Utils.MyUtils;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class Form_7_2_1 {
    private static Lessons lessons = null;

    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField numField;
    private JButton okButton;
    private JLabel numInfoLabel;

    public Form_7_2_1(Lessons lessons) {
        this.lessons = lessons;
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(numField.getText());
                    if (!MyUtils.checkPhoneNumber(numField.getText()))
                        JOptionPane.showMessageDialog(getRootPanel(), "Неверно введён номер", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    else {
                    lessons.getDiplomApp().setPhone(MyUtils.getPhoneNumber(numField.getText()));
                    lessons.getDiplomApp().getSmsAuthorisation();
                    lessons.getFrame_1().setVisible(false);
                    lessons.getFrame_2().setVisible(true);
                    lessons.getForm_7_2_2().startTimer(30);
                    }
                } catch (IOException e1) {
                    //e1.printStackTrace();
                    System.out.println(e1.getMessage());
                    if (e1.getMessage().equals(null)){
                        JOptionPane.showMessageDialog(getRootPanel(), "Тайм Аут ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (e1.getMessage().equals("PHONE_NUMBER_INVALID") || e1.getMessage().equals("Please write correct number.")){
                        JOptionPane.showMessageDialog(getRootPanel(), "Неверный номер телефона!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else
                    JOptionPane.showMessageDialog(getRootPanel(), "Ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public JPanel getRootPanel(){ return rootPanel; }
}
