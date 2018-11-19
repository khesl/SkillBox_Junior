package module_8;

/**
 * Класс чтобы выполнять вызов функции timerInit() в переданном классе.
 * */
public class Timer extends Thread implements Runnable{
    private int timer; // время в секундах
    private int maxTimer;
    private Object obj;

    public Timer (int timer, Object obj){
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
