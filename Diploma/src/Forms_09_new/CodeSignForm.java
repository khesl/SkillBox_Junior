package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;
import Utils.MyTimer;
import Utils.MyTimerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.regex.Pattern;

public class CodeSignForm implements MyTimerInterface {

    private static Main_09_New main = null;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPasswordField codeField;
    private JButton okButton;
    private JLabel telNumLabel;
    private JProgressBar timerBar;
    private JLabel timerLabel;
    private JLabel timerCountLabel;
    private JButton failTimerButton;
    private JLabel codeInfoLabel1;
    private JLabel codeInfoLabel2;
    private JLabel codeInfoLabel3;
    private JPanel centerPanel;
    private JPanel hidePanel;
    private JPanel closePanel;

    public CodeSignForm(Main_09_New main) {
        this.main = main;
        //codeField.setBackground(new Color(251,251,251,50));


        failTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.getSmsAuthorisation();
                startTimer(30);
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                main.setVerificationCode(String.valueOf(codeField.getPassword()));
            }
        });
        codeField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE))) {
                    getRootPanel().getToolkit().beep();
                    e.consume();
                }
            }
        });
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

    public void startTimer(int timeSec){
        MyTimer timer = new MyTimer(timeSec, this);
        timer.start();
    }

    private void updateProgressBar(int currentValue, int minValue, int maxValue){
        timerBar.setMinimum(minValue);
        timerBar.setMaximum(maxValue);
        timerBar.setValue(currentValue);
        int colorCheck = (int)((currentValue * 1f/(maxValue-minValue))* 100);

        if (colorCheck > 0 && colorCheck < 20)
            timerBar.setForeground(Color.RED);
        else if (colorCheck < 40)
            timerBar.setForeground(Color.ORANGE);
        else if (colorCheck < 60)
            timerBar.setForeground(Color.YELLOW);
        else if (colorCheck < 75)
            timerBar.setForeground(Color.CYAN);
        else timerBar.setForeground(Color.GREEN);
    }

    public JPanel getRootPanel(){ return rootPanel; }

    @Override
    public void timerInit(int currentTimer, int maxTimer) {
        if (currentTimer > 0) {
            updateProgressBar(currentTimer, 0, maxTimer);
            timerCountLabel.setText(currentTimer + "s");
            timerBar.setVisible(true);
            timerCountLabel.setVisible(true);
            failTimerButton.setVisible(false);
            timerLabel.setText("У вас осталось для ввода кода:");
        } else {
            timerBar.setVisible(false);
            timerCountLabel.setVisible(false);
            failTimerButton.setVisible(true);
            timerLabel.setText("Время действия кода вышло."); //У вас осталось для ввода кода:
        }
    }

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


    public JLabel getTelNumLabel() { return telNumLabel; }
}
