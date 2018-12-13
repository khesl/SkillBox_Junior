package module_12.res.Concurrency.src;

/**
 * Created by Danya on 18.02.2016.
 */
public class Counter
{
    private int value = 0;

    /*public synchronized void increment()
    {
            value = value + 1;
    }*/
    /** более низкий аналог первого варианта*/
    /*public void increment()
    {
        synchronized (this){
        value = value + 1;
        }
    }*/
    /** в случае статического класса и методов*/
    /*public void increment()
    {
        synchronized (Counter.class){
            value = value + 1;
        }
    }*/
    /** пример через отдельный объект*/
    /*private Object lock = new Object();
    public void increment()
    {
        synchronized (lock){
            value = value + 1;
        }
    }*/
    /** так не работает, т.к. Immutable классы пересоздают себя при изменении значения и блокировка не срабатывает*/
    /*public void increment()
    {
        synchronized (value){
            value = value + 1;
        }
    }*/
    public void increment()
    {
            value = value + 1;
    }

    public synchronized void decrement()
    {
        value = value - 1;
    }

    public int getValue()
    {
        return value;
    }
}
