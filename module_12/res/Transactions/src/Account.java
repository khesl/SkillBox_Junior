package module_12.res.Transactions.src;

/**
 * Created by Danya on 18.02.2016.
 */
public class Account{

    private long money;
    private String accNumber;
    private boolean lock;

    public Account(String accNumber, long money){
        this.accNumber = accNumber;
        this.money = money;
        lock = false;
    }

    public long getMoney() { return money; }
    public void setMoney(long money) {
        this.money = money;
    }

    public String getAccNumber() {
        return accNumber;
    }

    public boolean isLock() {
        return lock;
    }
    public void setLock(boolean lock) {
        this.lock = lock;
    }

    /** списать деньги, если они есть в наличии */
    public synchronized boolean withdrawBalance(long amount) throws ArithmeticException{
        if (money < amount) throw new ArithmeticException("There is not enough money in the account '" + this.getAccNumber() + "' for withdrow operation");
        money -= amount;
        return true;
    }

    public synchronized void topUpBalance(long amount){
        money += amount;
    }

}
