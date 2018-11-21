package module_8.src.Example.ActionListener_3.src;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Form2 implements Form{
    private JPanel rootPanel2;
    private JTextField textField1;
    private JButton switchButton;
    private JProgressBar progressBar;

    public Form2() {

        textField1.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                super.keyTyped(e);
                setProgressBarValue();

            }
        });
    }

    public void setFioFocus()
    {
        textField1.requestFocus();
    }

    public JPanel getRootPanel(){
        return rootPanel2;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }

    public String[] getFIO()
    {
        String fioArray[] = textField1.getText().split("[^A-Za-zА-ЯЁа-яё0-9\\-']");
        return fioArray;
    }

    public void setFIO(String[] fio)
    {
        textField1.setText(fio[0] + " " + fio[1] + " " + fio[2]);
    }

    public void setProgressBarValue()
    {
        String fioArray[] = getFIO();

        if(textField1.getText().equals(""))
        {
            progressBar.setValue(0);
            progressBar.setForeground(Color.RED);
        }
        else
        {
            switch (fioArray.length)
            {
                case 1:
                    progressBar.setValue(33);
                    progressBar.setForeground(Color.ORANGE);
                    break;

                case 2:
                    progressBar.setValue(66);
                    progressBar.setForeground(Color.YELLOW);
                    break;

                case 3:
                    progressBar.setValue(100);
                    progressBar.setForeground(Color.GREEN);
                    break;
            }
        }

    }
}
