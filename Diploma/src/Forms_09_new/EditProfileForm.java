package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditProfileForm {
    private static Main_09_New main = null;
    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JLabel prifoleEditInfoLabel;
    private JButton logOutButton;
    private JLabel userPhoneLabel;
    private JPanel backPanel;
    private JLabel backIcon;
    private JPanel saveButton;


    public EditProfileForm(Main_09_New main) {
        this.main = main;

        BorderBarForm borderBarForm = new BorderBarForm(this.main);
        rootPanel.add(borderBarForm.getRootPanel(), BorderLayout.NORTH);
        rootPanel.setBackground(new Color(0,0,0,220));
        nameTextField.setBackground(new Color(0,0,0,0));
        surnameTextField.setBackground(new Color(0,0,0,0));

        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                JOptionPane.showMessageDialog(getRootPanel(), "Функция ещё не реализована", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        backPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //обратно к Main фрейму
                main.switchContentPanes(Main_09_New.ContentPanes.main);
            }
        });
    }

    public JPanel getRootPanel(){ return rootPanel; }
    public JLabel getUserPhoneLabel() { return userPhoneLabel; }
}
