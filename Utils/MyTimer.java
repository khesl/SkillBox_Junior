package Utils;

import module_8.MyTimerInterface;

/**
 * Класс чтобы выполнять вызов функции timerInit() в переданном классе.
 * */
public class MyTimer extends Thread implements Runnable{
    private int timer; // время в секундах
    private int maxTimer;
    private Object obj;

    public MyTimer(int timer, Object obj){
        this.timer = timer;
        this.maxTimer = timer;
        this.obj = obj;
    }

    @Override
    public void run() {
        while (!isInterrupted())
            while (timer > -1) {
                ((MyTimerInterface)obj).timerInit(timer, maxTimer);

                try {
                    this.sleep(1000);
                    timer--;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
    }
}
