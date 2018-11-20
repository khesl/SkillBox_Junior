package Diploma.src.Forms;

import Diploma.src.Main;
import Utils.MyTimer;
import Utils.MyTimerInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.regex.Pattern;

public class CodeSignForm implements MyTimerInterface {
    private static Main main = null;
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

    public CodeSignForm(Main main) {
        this.main = main;
        //codeField.setBackground(new Color(251,251,251,50));


        failTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    main.getDiplomApp().getSmsAuthorisation();
                } catch (Exception e1) {
                    JOptionPane.showMessageDialog(getRootPanel(), "Ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    //e1.printStackTrace();
                }
            }
        });
        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!Pattern.compile("[0-9]{5}").matcher(String.valueOf(codeField.getPassword())).matches())
                    JOptionPane.showMessageDialog(getRootPanel(), "Неверный формат кода.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                else {
                    try {
                        if (!main.getDiplomApp().authorized(String.valueOf(codeField.getPassword()))) {
                            JOptionPane.showMessageDialog(getRootPanel(), "Ошибка авторизации, попробуйте позднее.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                            //main.getFrame_2().setVisible(false);
                            //main.getFrame_1().setVisible(true);
                            startTimer(10); // для теста
                            return;
                        }
                        main.getFrame_2().setVisible(false);
                        main.getFrame_3().setVisible(true);
                        if (main.getDiplomApp().isAuthorized()) main.getMainForm().updateContacts();
                    } catch (IOException e1) {
                        //e1.printStackTrace();
                        JOptionPane.showMessageDialog(getRootPanel(), "Вероятно код устарел.", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    }
                }
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
                g.drawImage(main.backgroundImage, 0, 0, null);
            }};
    }
}
