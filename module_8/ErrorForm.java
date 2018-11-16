package module_8;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

import static javax.swing.JComponent.WHEN_IN_FOCUSED_WINDOW;

public class ErrorForm {
    private static Lessons lessons = null;
    private JPanel rootPanel;
    private JButton ok;
    private JLabel errorText;

    public ErrorForm(Lessons lessons){
        this.lessons = lessons;
        ok.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessons.getErrorFrame().dispose();
            }
        });

        InputMap im = getRootPanel().getInputMap(WHEN_IN_FOCUSED_WINDOW);// (WHEN_FOCUSED);
        ActionMap am = getRootPanel().getActionMap();

        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, false), "onEnter");
        im.put(KeyStroke.getKeyStroke(KeyEvent.VK_ENTER, 0, true), "onEnterReleased");

        am.put("onEnter", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ok.getModel().setPressed(true);
            }
        });
        am.put("onEnterReleased", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                lessons.getErrorFrame().dispose();
            }
        });

    }

    public JPanel getRootPanel(){ return rootPanel; }
    public JLabel getErrorText() { return errorText; }

}
