package module_12.Lessons_.src;

import javax.swing.*;
import java.awt.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

public class StopWatchClass extends Thread {
    private Long startTime;
    private Long stopWatchTime = 0L;
    private volatile boolean pause = true;
    private boolean started = false;
    private JLabel stopWatchTimeLabel;
    private JPanel clockPanel;
    private Long updateTime;

    public StopWatchClass(JLabel stopWatchTimeLabel){
        this.stopWatchTimeLabel = stopWatchTimeLabel;
        startTime = System.currentTimeMillis();
        updateTime = System.currentTimeMillis();
    }
    public StopWatchClass(JLabel stopWatchTimeLabel, JPanel clockPanel){
        this(stopWatchTimeLabel);
        this.clockPanel = clockPanel;
    }

    @Override
    public void run(){
        super.run();
        while (true){
            if (!isInterrupted()){
                if (!isPause()){
                    stopWatchTime = System.currentTimeMillis() - startTime;

                    String time = getFormattedTime(new SimpleDateFormat("HH:mm:ss.S"));
                    stopWatchTimeLabel.setText(time);
                    //System.out.println(time + "\t" + getStopWatchTime());
                    //stopWatchTimeLabel.repaint();
                    //sleep(1000);
                    //if (System.currentTimeMillis() - updateTime > 1000)
                        //updateClock();
                    clockPanel.repaint();
                }
            }
        }

    }

    public void updateClock(){
        if (clockPanel != null){
            updateTime = System.currentTimeMillis();
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date(stopWatchTime));
            calendar.setTimeZone(TimeZone.getTimeZone("GMT0"));
            int minutes = Integer.parseInt(getFormattedTime(new SimpleDateFormat("ss")));
            System.out.println (minutes);
            double angle = Math.toRadians(minutes*(360/60));
            System.out.print(angle + ": ");
            Point center = new Point(clockPanel.getWidth()/2, clockPanel.getHeight()/2);
            double radius = 0.85*(clockPanel.getWidth()/2);
            Point newPoint = new Point((int) (radius * Math.sin(angle)), (int) (radius * Math.cos(angle)));
            System.out.println(center.x + " " + center.y + " " + (center.x - newPoint.x) + " " + (center.y - newPoint.y));

            clockPanel.getGraphics().drawLine(center.x, center.y, center.x + newPoint.x,center.y - newPoint.y);

            clockPanel.repaint();
        }
    }

    public void startStopWatch(){
        started = true;
        setPause(false);
        startTime = System.currentTimeMillis();
    }
    public void stopStopWatchTime(){
        setPause(true);
        started = false;
        stopWatchTime = 0L;
        stopWatchTimeLabel.setText(getFormattedTime(new SimpleDateFormat("HH:mm:ss.S")));
        stopWatchTimeLabel.repaint();
    }

    public boolean isStarted() {
        return started;
    }

    public Long getStopWatchTime() { return stopWatchTime; }
    public String getFormattedTime(DateFormat dateFormat) {
        //Calendar calendar = Calendar.getInstance();
        //calendar.setTime(new Date(stopWatchTime));
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT0"));
        return dateFormat.format(new Date(stopWatchTime));
    }

    public boolean isPause() { return pause; }
    public void setPause(boolean pause) {
        if (!isStarted()) throw new NullPointerException("StopWatch not Stared!");
        this.pause = pause;
    }
    public void pause(){
        setPause(!isPause());
    }

}
