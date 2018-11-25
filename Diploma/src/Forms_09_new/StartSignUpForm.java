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
    private JPanel topPanel;
    private JPanel centerPanel;
    private JTextField signUpNameField;
    private JTextField signUpSurNameField;
    private JButton confirmSignUpButton;
    private JLabel signUpInfoLabel1;
    private JLabel signUpInfoLabel2;
    private JPanel hidePanel;
    private JPanel closePanel;

    public StartSignUpForm(Main_09_New main) {
        this.main = main;


        hidePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                hidePanel.getGraphics().drawImage(main.hideButtonOnCursor, 0, 0, null);
            }
        });
        hidePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                hidePanel.getGraphics().drawImage(main.hideButton, 0, 0, null);
            }
        });
        hidePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                main.hideMainFrame();
            }
        });
        closePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent mouseEvent) {
                super.mouseEntered(mouseEvent);
                closePanel.getGraphics().drawImage(main.closeButtonOnCursor, 0, 0, null);
            }
        });
        closePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent mouseEvent) {
                super.mouseExited(mouseEvent);
                closePanel.getGraphics().drawImage(main.closeButton, 0, 0, null);
            }
        });
        closePanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                super.mouseClicked(mouseEvent);
                main.closeMainFrame();
            }
        });
        topPanel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent mouseEvent) {
                super.mouseDragged(mouseEvent);
                main.moveMainframe(mouseEvent);
            }
        });
        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent mouseEvent) {
                super.mousePressed(mouseEvent);
                main.setTopBarMousePressed(true, mouseEvent);
            }
        });
        topPanel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent mouseEvent) {
                super.mouseReleased(mouseEvent);
                main.setTopBarMousePressed(false, mouseEvent);
            }
        });
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
        hidePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(main.hideButton, 0, 0, null);
            }};
        closePanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(main.closeButton, 0, 0, null);
            }};
    }
}
