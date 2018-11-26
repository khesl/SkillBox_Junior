package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EditContactsForm {
    private static Main_09_New main = null;

    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JTextField contactFIO;
    private JTextField contactNum;
    private JPanel contactIcon;
    private JPanel contactDataPanel;
    private JPanel contactData2Panel;
    private JPanel contactPanel;
    private JPanel centerPanel;
    private JPanel mainPanel;
    private JLabel backIcon;
    private JPanel backButton;
    private JPanel deleteContactButton;
    private JLabel deleteLabel;
    private JLabel titleLabel;
    private JPanel saveButton;
    private JLabel iconLabel;

    public EditContactsForm(Main_09_New main) {
        this.main = main;
        BorderBarForm borderBarForm = new BorderBarForm(this.main);
        rootPanel.add(borderBarForm.getRootPanel(), BorderLayout.NORTH);

        rootPanel.setBackground(new Color(0,0,0,220));
        contactNum.setBackground(new Color(0,0,0,0));
        contactFIO.setBackground(new Color(0,0,0,0));
        deleteContactButton.setBackground(new Color(0,0,0,0));
        saveButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                JOptionPane.showMessageDialog(getRootPanel(), "Функция ещё не реализована", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        backButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                //обратно к Main фрейму
                main.switchContentPanes(Main_09_New.ContentPanes.main);
            }
        });
        deleteContactButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                JOptionPane.showMessageDialog(getRootPanel(), "Функция ещё не реализована", "", JOptionPane.INFORMATION_MESSAGE);
            }
        });
    }

    public JPanel getRootPanel(){ return rootPanel; }
    public JTextField getContactFIO() { return contactFIO; }
    public JTextField getContactNum() { return contactNum; }
    public JPanel getContactIcon() { return contactIcon; }
}
