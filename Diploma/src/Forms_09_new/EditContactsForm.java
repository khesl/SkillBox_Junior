package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;

import javax.swing.*;

public class EditContactsForm {
    private static Main_09_New main = null;

    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JButton сохранитьButton;
    private JTextField contactFIO;
    private JTextField contactNum;
    private JPanel contactIcon;

    public EditContactsForm(Main_09_New main) {
        this.main = main;
    }

    public JPanel getRootPanel(){ return rootPanel; }
}
