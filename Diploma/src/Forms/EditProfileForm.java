package Diploma.src.Forms;

import Diploma.src.Main;

import javax.swing.*;
import java.awt.*;

public class EditProfileForm {
    private static Main main = null;
    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JTextField имяTextField;
    private JTextField фамилияTextField;
    private JButton button1;
    private JLabel prifoleEditInfoLabel;
    private JLabel backButton;
    private JButton выйтиButton;
    private JLabel userPhoneLabel;


    public EditProfileForm(Main main) {
        this.main = main;
        //rootPanel.setBackground(new Color(0,0,0,150));


    }

    public JPanel getRootPanel(){ return rootPanel; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(main.background_320x240_85_opacity_Image, 0, 0, null);
            }};
    }
}
