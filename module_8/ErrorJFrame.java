package module_8;

import javax.swing.*;
import java.awt.*;

public class ErrorJFrame extends JFrame {
    private ErrorForm errorForm;

    public ErrorJFrame(Lessons lessons){
        super();
        errorForm = new ErrorForm(lessons);

        this.setContentPane(errorForm.getRootPanel());

        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(200, 150);
        this.setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setTitle("ErrorMessage");
    }

    public void setErrorMessage(String errorMessage){
        errorForm.getErrorText().setText(errorMessage);
        this.setVisible(true);

    }
}
