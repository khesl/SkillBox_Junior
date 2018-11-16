package module_8;

import javax.swing.*;
import java.awt.*;

public class Form {
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JTextField textField4;
    private JTextField textField5;
    private JButton кнопка_2Button;
    private JPanel rightPanel;
    private JPanel downPanel;
    private JPanel leftPanel;
    private JPanel topPanel;

    public Form(){
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
    }

    public JPanel getRootPanel(){ return rootPanel; }

        //rootPanel.setLayout(new BoxLayout(rootPanel, BoxLayout.Y_AXIS));
    private void createUIComponents() {
        // TODO: place custom component creation code here
    }
}
