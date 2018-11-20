package Diploma.src.Forms;

import Diploma.src.Main;

import javax.swing.*;

public class EditContactsForm {
    private static Main main = null;

    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JButton сохранитьButton;
    private JTextField contactFIO;
    private JTextField contactNum;
    private JPanel contactIcon;

    public EditContactsForm(Main main) {
        this.main = main;
    }

    public JPanel getRootPanel(){ return rootPanel; }
}
