package Diploma.src.Forms;

import Diploma.src.Main;

import javax.swing.*;
import java.awt.*;

public class StartSignUpForm {
    private static Main main = null;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField signUpNameField;
    private JTextField signUpSurNameField;
    private JButton confirmSignUpButton;
    private JLabel signUpInfoLabel1;
    private JLabel signUpInfoLabel2;

    public StartSignUpForm(Main main) {
        this.main = main;
    }

    public JPanel getRootPanel(){ return rootPanel; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(main.backgroundImage, 0, 0, null);
            }};
    }
}
