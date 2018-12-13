package module_12.Lessons_.src;

import module_12.Lessons;

import javax.swing.*;
import java.awt.*;
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

    private StopWatchClass stopWatchClass;

    public StopWatch(Lessons main){
        this.main = main;
        stopWatchClass = new StopWatchClass(stopWatchTime, clockPanel);
        stopWatchClass.start();

        startButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (!stopWatchClass.isStarted()) {
                    stopWatchClass.startStopWatch();
                    startButton.setText("pause");
                } else {
                    if (stopWatchClass.isPause()){
                        // сейчас на паузе
                        stopWatchClass.pause();
                        startButton.setText("pause");
                    } else {
                        // должно идти время
                        stopWatchClass.pause();
                        startButton.setText("play");
                    }
                }
            }
        });
        stopButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                if (stopWatchClass.isStarted()){
                    stopWatchClass.stopStopWatchTime();
                    startButton.setText("start");
                }
            }
        });
    }

    public JPanel getRootPanel(){ return rootPanel; }


    private void createUIComponents() {
        // TODO: place custom component creation code here

        clockPanel = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                g.setColor(Color.BLUE);

                if (stopWatchClass!= null){
                int minutes = Integer.parseInt(stopWatchClass.getFormattedTime(new SimpleDateFormat("ss")));
                //System.out.println (minutes);
                double angle = Math.toRadians(minutes*(360/60));
                //System.out.print(angle + ": ");
                Point center = new Point(clockPanel.getWidth()/2, clockPanel.getHeight()/2);
                double radius = 0.85*(clockPanel.getWidth()/2);
                Point newPoint = new Point((int) (radius * Math.sin(angle)), (int) (radius * Math.cos(angle)));
                //System.out.println(center.x + " " + center.y + " " + (center.x - newPoint.x) + " " + (center.y - newPoint.y));

                g.drawLine(center.x, center.y, center.x + newPoint.x,center.y - newPoint.y);
}
            }
        };
    }
}
