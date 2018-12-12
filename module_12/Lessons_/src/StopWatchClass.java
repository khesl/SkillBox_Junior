package module_12.Lessons_.src;

import java.text.DateFormat;
import java.util.Date;

public class StopWatchClass extends Thread {
    private Long startTime;
    private Long stopWatchTime = 0L;
    private volatile boolean pause = true;
    private boolean started = false;

    @Override
    public void run(){
        super.run();
        int count = 0;
        while (true){
            if (!isInterrupted()){
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            count++;
        }

    }

    public void startStopWatch(){
        setPause(false);
        started = true;
        stopWatchTime = 0L;
    }
    public void stopStopWatchTime(){
        setPause(true);
        started = false;
        stopWatchTime = 0L;
    }

    public boolean isStarted() {
        return started;
    }

    public Long getStopWatchTime() { return stopWatchTime; }
    public String getFormattedTime(DateFormat dateFormat) {
        return dateFormat.format(new Date(stopWatchTime));
    }

    public boolean isPause() { return pause; }
    public void setPause(boolean pause) {
        this.pause = pause;
        if (!pause) startTime = System.nanoTime();
        else stopWatchTime += System.nanoTime() - startTime;
    }
    public void pause(){
        setPause(!isPause());
    }

}
