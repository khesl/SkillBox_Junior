package Diploma.src.Forms_09_new;

import Diploma.src.Main;
import Diploma.src.Main_09_New;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/**
 * 6я форма
 * */
public class AddContactsForm {
    private final String CODE_NUM_PATTERN = "+7(___)";
    private final String BODY_NUM_PATTERN = "___-__-__";

    private static Main_09_New main = null;
    private JPanel rootPanel;
    private JPanel bottomPanel;
    private JPanel centerPanel;
    private JLabel mainLabel;
    private JLabel labelNumber;
    private JTextField codeNumField;
    private JTextField numField;
    private JTextField nameTextField;
    private JTextField surnameTextField;
    private JButton addContactButton;
    private JLabel backButton;

    public AddContactsForm(Main_09_New main) {
        this.main = main;

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
            }};
    }
}
