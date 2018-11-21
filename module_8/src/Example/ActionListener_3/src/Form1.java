package module_8.src.Example.ActionListener_3.src;

import javax.swing.*;

public class Form1 implements Form {
    private JPanel rootPanel1;
    private JTextField textField1;
    private JTextField textField2;
    private JTextField textField3;
    private JButton switchButton;


    public JPanel getRootPanel() {
        return rootPanel1;
    }

    public JButton getSwitchButton() {
        return switchButton;
    }

    public void setSurnameFocus()
    {
        textField1.requestFocus();
    }

    public void setNameFocus()
    {
        textField2.requestFocus();
    }

    public void setPatronymicFocus()
    {
        textField3.requestFocus();
    }

    public String[] getFIO()
    {
        String fioArray[] = new String[3];

        fioArray[0] = textField1.getText().trim();
        fioArray[1] = textField2.getText().trim();
        fioArray[2] = textField3.getText().trim();

        return fioArray;

    }

    public void setFIO(String[] fio)
    {
        textField1.setText("");
        textField2.setText("");
        textField3.setText("");

        try
        {
            textField1.setText(fio[0]);
            textField2.setText(fio[1]);
            textField3.setText(fio[2]);
        } catch (ArrayIndexOutOfBoundsException e)
        {

        }
    }

}
