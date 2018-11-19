package module_8;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.regex.Pattern;

public class Form_7_2_2 implements MyTimerInterface{
    private static Lessons lessons = null;
    private JPanel rootPanel;
    private JPanel topPanel;
    private JPanel centerPanel;
    private JPasswordField codeField;
    private JButton okButton;
    private JLabel telNumLabel;
    private JTextPane codeInfoTextPane;
    private JProgressBar timerBar;
    private JLabel timerLabel;
    private JLabel timerCountLabel;
    private JButton failTimerButton;

    public Form_7_2_2(Lessons lessons) {
        this.lessons = lessons;
        failTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    lessons.getDiplomApp().getSmsAuthorisation();
                } catch (IOException e1) {
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
                        lessons.getDiplomApp().authorized(String.valueOf(codeField.getPassword()));
                        lessons.getFrame_2().setVisible(false);
                        lessons.getFrame_3().setVisible(true);
                        if (lessons.getDiplomApp().isAuthorized()) lessons.getForm_7_2_3().updateContacts();
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
        Timer timer = new Timer(timeSec, this);
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
            failTimerButton.setVisible(false);
            timerLabel.setText("У вас осталось для ввода кода:");
        } else {
            timerBar.setVisible(false);
            failTimerButton.setVisible(true);
            timerLabel.setText("Время действия кода вышло."); //У вас осталось для ввода кода:
        }
    }
}
