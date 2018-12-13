package module_12.res.GUIMultithreading.src;

import javax.swing.*;

/**
 * Created by Danya on 28.10.2015.
 */
public class Main
{
    public static void main(String[] args)
    {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }

        JFrame frame = new JFrame();

        Form form = new Form();
        frame.setContentPane(form.getRootPanel());

        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setTitle("Multithreading");

        frame.setSize(300, 170);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}