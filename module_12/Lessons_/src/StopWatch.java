package module_12.Lessons_.src;

import module_12.Lessons;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

public class StopWatch {
    private static Lessons main = null;
    private JPanel rootPanel;
    private JPanel centerPanel;
    private JPanel clockPanel;
    private JPanel rightPanel;
    private JButton startButton;
    private JButton stopButton;
    private JLabel stopWatchTime;

    private StopWatchClass stopWatchClass = new StopWatchClass();

    public StopWatch(Lessons main){
        this.main = main;
        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (stopWatchClass.isPause()){
                    // сейчас на паузе
                    if (!stopWatchClass.isStarted())
                        stopWatchClass.startStopWatch();
                    else stopWatchClass.pause();
                    startButton.setText("pause");
                    stopButton.setText("stop");
                } else {
                    // должно идти время
                    stopWatchClass.pause();
                    startButton.setText("play");
                }
            }
        });
        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (stopWatchClass.isStarted()){
                    stopWatchClass.setPause(true);
                    stopButton.setText("clear");
                } else {
                    stopWatchClass.stopStopWatchTime();
                    stopButton.setText("stop");
                }


            }
        });
    }

    public JPanel getRootPanel(){ return rootPanel; }

    private void createUIComponents() {
        // TODO: place custom component creation code here

        //stopWatchClass.start();

        stopWatchTime = new JLabel(){
            @Override
            public void repaint(){
                super.repaint();
                //this.setText(stopWatchClass.getFormattedTime(new SimpleDateFormat("HH:mm:ss.S")));
            }
        };
    }
}
