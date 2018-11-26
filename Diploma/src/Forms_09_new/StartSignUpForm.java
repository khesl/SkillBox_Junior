package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class StartSignUpForm {
    private static Main_09_New main = null;
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JTextField signUpNameField;
    private JTextField signUpSurNameField;
    private JButton confirmSignUpButton;
    private JLabel signUpInfoLabel1;
    private JLabel signUpInfoLabel2;

    public StartSignUpForm(Main_09_New main) {
        this.main = main;
        BorderBarForm borderBarForm = new BorderBarForm(this.main);
        rootPanel.add(borderBarForm.getRootPanel(), BorderLayout.NORTH);

    }

    public JPanel getRootPanel(){ return rootPanel; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(main.backgroundImage, 0, 23, null);
                g.drawImage(main.logo, 390, 50, null);
            }};
    }
}
