import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;


public class Loader
{


    private static JFrame jFrame;
    private static Form1 form1;
    private static Form2 form2;



    public static void main(String[] args) throws Exception {

        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());

        jFrame = new JFrame();

        form1 = new Form1();
        form2 = new Form2();

        jFrame.setContentPane(form1.getRootPanel());

        Dimension dimension = new Dimension(305,229);
        jFrame.setSize(dimension.width, dimension.height);
        jFrame.setMinimumSize(dimension);
        jFrame.setTitle("SimpleGIU");
        jFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        jFrame.setLocationRelativeTo(null);
        jFrame.setVisible(true);


        JButton switchButton1 = form1.getSwitchButton();
        switchButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchContentPanes();
            }
        });

        JButton switchButton2 = form2.getSwitchButton();
        switchButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                switchContentPanes();
            }
        });

        KeyboardFocusManager keyboardFocusManager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
        keyboardFocusManager.addKeyEventDispatcher(new MyDispatcher());
    }

    private static void switchContentPanes()
    {

        if(jFrame.getContentPane() == form1.getRootPanel())
        {
            String[] fio = form1.getFIO();
            if(fio[0].equals("") || fio[1].equals(""))
            {
                JOptionPane.showMessageDialog(
                        form1.getRootPanel(),
                        "Необходимо ввести имя и фамилию",
                        "Ошибка ввода",
                        JOptionPane.ERROR_MESSAGE
                );

                if(fio[0].equals("")) form1.setSurnameFocus();
                else form1.setNameFocus();
                return;
            } else if(fio[2].equals(""))
            {
                int option = JOptionPane.showConfirmDialog(
                        form1.getRootPanel(),
                        "Продолжить без ввода отчества?",
                        "Ввод отчества",
                        JOptionPane.YES_NO_OPTION
                );

                if(option == JOptionPane.NO_OPTION)
                {
                    form1.setPatronymicFocus();
                    return;
                }


            }
            jFrame.setContentPane(form2.getRootPanel());
            form2.setFIO(fio);
            form2.setProgressBarValue();
            form2.setFioFocus();
        }
        else
        {
            jFrame.setContentPane(form1.getRootPanel());
            form1.setFIO(form2.getFIO());
        }

        jFrame.revalidate();
        jFrame.repaint();

    }

    private static class MyDispatcher implements KeyEventDispatcher
    {

        @Override
        public boolean dispatchKeyEvent(KeyEvent e) {
            if(e.getID() == KeyEvent.KEY_RELEASED)
            {
                if(e.getKeyCode()==KeyEvent.VK_ENTER && e.isControlDown())
                {
                    switchContentPanes();
                }
            }

            return false;
        }
    }


}
