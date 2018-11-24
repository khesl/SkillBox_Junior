package Diploma.src.Forms;

import Diploma.src.Main;
import Utils.MyUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.IOException;

public class StartSignInForm {
    private static Main main = null;
    private final String CODE_NUM_PATTERN = "+7(___)";
    private final String BODY_NUM_PATTERN = "___-__-__";


    private JPanel rootPanel;
    private JPanel topPanel;
    private JTextField numField;
    private JButton okButton;
    private JLabel numInfoLabel;
    private JLabel labelNumber;
    private JLabel numInfoLabel2;
    private JPanel centerPanel;
    private JTextField codeNumField;

    public StartSignInForm(Main main) {
        this.main = main;

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String number = codeNumField.getText() + numField.getText();
                    System.out.println(number);
                    if (!MyUtils.checkPhoneNumber(number))
                        JOptionPane.showMessageDialog(getRootPanel(), "Неверно введён номер", "Ошибка", JOptionPane.ERROR_MESSAGE);
                    else {
                        main.getDiplomApp().setPhone(MyUtils.getPhoneNumber(number));
                        main.getDiplomApp().getSmsAuthorisation();
                        main.getFrame_1().setVisible(false);
                        main.getFrame_2().setVisible(true);
                        main.getCodeSignForm().startTimer(30);
                    }
                } catch (IOException e1) {
                    //e1.printStackTrace();
                    System.out.println(e1.getMessage());
                    if (e1.getMessage().equals(null)){
                        JOptionPane.showMessageDialog(getRootPanel(), "Тайм Аут ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else if (e1.getMessage().equals("PHONE_NUMBER_INVALID") || e1.getMessage().equals("Please write correct number.")){
                        JOptionPane.showMessageDialog(getRootPanel(), "Неверный номер телефона!", "Ошибка", JOptionPane.ERROR_MESSAGE);
                        return;
                    } else
                    JOptionPane.showMessageDialog(getRootPanel(), "Ошибка соединения с Telegram", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        codeNumField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE)) && !(codeNumField.getText().length()+1 < CODE_NUM_PATTERN.length())) {
                    getRootPanel().getToolkit().beep();
                    e.consume();
                    return;
                }
                codeNumField.setText(getPatternUpdate(codeNumField.getText(), c, CODE_NUM_PATTERN));
                e.consume();

            }
        });
        numField.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                char c = e.getKeyChar();
                if (!((c >= '0') && (c <= '9') ||
                        (c == KeyEvent.VK_BACK_SPACE) ||
                        (c == KeyEvent.VK_DELETE)) && !(numField.getText().length()+1 < BODY_NUM_PATTERN.length())) {
                    getRootPanel().getToolkit().beep();
                    e.consume();
                    return;
                }
                numField.setText(getPatternUpdate(numField.getText(), c, BODY_NUM_PATTERN));
                e.consume();
            }
        });
    }

    public static String getPatternUpdate(String source, char input, String pattern){
        // +7(5__)  || +7(___)
        if (input == KeyEvent.VK_DELETE){
            return pattern;
        } else if (input == KeyEvent.VK_BACK_SPACE){
            return pattern;
        } else if (source.contains("_"))
            source = source.replaceFirst("_", String.valueOf(input));
        return source;
    }

    public JPanel getRootPanel(){ return rootPanel; }

    private void createUIComponents() {
        // TODO: place custom component creation code here
        rootPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.drawImage(main.backgroundImage, 0, 0, null);

                g.setColor(new Color(39,74,112));
                g.drawLine(365, 375, 565, 375);

            }};
    }
}
