package Diploma.src.Forms_09_new;

import Diploma.src.Main_09_New;
import Utils.MyUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class StartSignInForm {
    private static Main_09_New main = null;
    private final String CODE_NUM_PATTERN = "+7(___)";
    private final String BODY_NUM_PATTERN = "___-__-__";


    private JPanel rootPanel;
    private JTextField numField;
    private JButton okButton;
    private JLabel numInfoLabel;
    private JLabel labelNumber;
    private JLabel numInfoLabel2;
    private JPanel centerPanel;
    private JTextField codeNumField;

    public StartSignInForm(Main_09_New main) {
        this.main = main;
        BorderBarForm borderBarForm = new BorderBarForm(this.main);
        rootPanel.add(borderBarForm.getRootPanel(), BorderLayout.NORTH);

        okButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String number = codeNumField.getText() + numField.getText();
                System.out.println(number);
                if (!MyUtils.checkPhoneNumber(number))
                    JOptionPane.showMessageDialog(getRootPanel(), "Неверно введён номер", "Ошибка", JOptionPane.ERROR_MESSAGE);
                else {
                    main.getSmsAuthorisation(MyUtils.getPhoneNumber(number));
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
                g.drawImage(main.backgroundImage, 0, 23, null);
                g.drawImage(main.logo, 390, 50, null);

                g.setColor(new Color(39,74,112));
                g.drawLine(370, 396, 570, 396);
            }
        };
    }


    public JTextField getNumField() { return numField; }
    public JTextField getCodeNumField() { return codeNumField; }
}
