package module_12.res.GUIMultithreading.src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by Danya on 28.10.2015.
 */
public class Form
{
    private JPanel rootPanel;
    private JPanel buttons;
    private JButton startButton;
    private JProgressBar progressBar;

    public Form() {
        Form form = this;
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int count = 100000000;
                for(int i = 0; i < count; i++)
                {
                    progressBar.setValue((int) Math.round(i*100./count));
                }
            }
        });
    }

    public JPanel getRootPanel() {
        return rootPanel;
    }
}
