package module_8.src.Example.SimpleGUI_2.src;

import module_8.src.Example.SimpleGUI_2.src.Form;

import javax.swing.*;
import java.awt.*;

public class Loader
{
    public static void main(String[] args)
    {
        JFrame jFrame = new JFrame();

        Form form = new Form();
        jFrame.setContentPane(form.getRootPanel());


        Dimension dimension = new Dimension(305,229);
        jFrame.setSize(dimension.width, dimension.height);
        jFrame.setMinimumSize(dimension);
        jFrame.setTitle("SimpleGIU");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);
    }
}
