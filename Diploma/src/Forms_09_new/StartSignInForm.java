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
    private JPanel topPanel;
    private JTextField numField;
    private JButton okButton;
    private JLabel numInfoLabel;
    private JLabel labelNumber;
    private JLabel numInfoLabel2;
    private JPanel centerPanel;
    private JTextField codeNumField;
    private JPanel hidePanel;
    private JPanel closePanel;

    public StartSignInForm(Main_09_New main) {
        this.main = main;

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
                g.drawLine(365, 393, 565, 393);
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


    public JTextField getNumField() { return numField; }
    public JTextField getCodeNumField() { return codeNumField; }
}
