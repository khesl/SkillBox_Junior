package Diploma.src.Forms;

import Diploma.src.Main;
import Utils.MyUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

public class StartSignInForm {
    private static Main main = null;


    private JPanel rootPanel = new JPanel(){
        @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(main.backgroundImage, 0, 0, null);
    }};
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField numField;
    private JButton okButton;
    private JLabel numInfoLabel;

    public StartSignInForm(Main main) {
        this.main = main;

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    System.out.println(numField.getText());
                    if (!MyUtils.checkPhoneNumber(numField.getText()))
                        JOptionPane.showMessageDialog(getRootPanel(), "Неверно введён номер", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    else {
                        main.getDiplomApp().setPhone(MyUtils.getPhoneNumber(numField.getText()));
                        main.getDiplomApp().getSmsAuthorisation();
                        main.getFrame_1().setVisible(false);
                        main.getFrame_2().setVisible(true);
                        main.getCodeSignForm().startTimer(30);
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
