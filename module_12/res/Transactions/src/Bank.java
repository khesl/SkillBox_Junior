package module_12.res.Transactions.src;

import java.util.HashMap;
import java.util.Random;

/**
 * Created by Danya on 18.02.2016.
 */
public class Bank {
    private static long startAccountNum = 1000000000;
    private long nextAccountNum;
    private String serial;
    private int workPlace;

    private HashMap<String, Account> accounts;
    private HashMap<String, Account> lockAccounts;
    private final Random random = new Random();
    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException {
        Thread.sleep(1000);
        boolean decision = random.nextBoolean();
        if (!decision) {
            stashLockAccounts(accounts.get(fromAccountNum));
            stashLockAccounts(accounts.get(toAccountNum));
        }
        return decision;
    }

    public Bank (String serial, int workPlace){
        this.serial = serial;
        this.workPlace = workPlace;
        accounts = new HashMap<>();
        lockAccounts = new HashMap<>();
    }

    /**
     * TODO: реализовать метод. Метод переводит деньги между счетами.
     * Если сумма транзакции > 50000, то после совершения транзакции,
     * она отправляется на проверку Службе Безопасности – вызывается
     * метод isFraud. Если возвращается true, то делается блокировка
     * счетов (как – на ваше усмотрение)
     */
    private static long transferLimit = 50000;
    public void transfer(String fromAccountNum, String toAccountNum, long amount) throws InterruptedException, UnsupportedOperationException {
        Account fromAccountNumAcc = accounts.get(fromAccountNum);
        Account toAccountNumAcc = accounts.get(toAccountNum);
        if (fromAccountNumAcc.isLock()) throw new UnsupportedOperationException("Account '" + fromAccountNum + "' is locked.");
        if (toAccountNumAcc.isLock()) throw new UnsupportedOperationException("Account '" + toAccountNum + "' is locked.");
        if (amount > transferLimit) {
            if (!isFraud(fromAccountNum, toAccountNum, amount))
                    throw new UnsupportedOperationException("Transaction was banned! Accounts is locked.");
        }
        if (amount > getBalance(fromAccountNum))
                throw new ArithmeticException("There is not enough money in the account '" + fromAccountNumAcc.getAccNumber() + "'");
        if (fromAccountNumAcc.withdrawBalance(amount))
            toAccountNumAcc.topUpBalance(amount);
    }

    /**
     * TODO: реализовать метод. Возвращает остаток на счёте.
     */
    public long getBalance(String accountNum) {
        Account account = accounts.get(accountNum);
        if (!account.isLock()) return accounts.get(accountNum).getMoney();
        throw new UnsupportedOperationException("Account '" + accountNum + "' is locked.");
    }

    public synchronized void stashLockAccounts(Account account){
        lockAccounts.put(account.getAccNumber(), account);
        account.setLock(true);
    }

    public synchronized long getNextAccountNum() {
        /*long tempId = nextAccountNum;
        String newAccountNum = serial;
        while (tempId < 1000000000) {
            newAccountNum += "0";
            tempId *= 10;
        }*/
        return startAccountNum + nextAccountNum++;
    }
    public synchronized long getCurrentAccountNum() {
        return startAccountNum + nextAccountNum;
    }

    public synchronized void newClient(Account account){
        accounts.put(account.getAccNumber(), account);
    }
    public synchronized void newClient(long money){
        String accNumber = String.valueOf(getNextAccountNum());
        accounts.put(accNumber, new Account(accNumber, money));
    }

    public int clientCount(){ return accounts.size(); }

    public Account getAccount(String accountNum) {
        return accounts.get(accountNum);
    }
    public Account getAccount(long number) {
        return accounts.get(String.valueOf(number));
    }
    public int getWorkPlace(){ return workPlace; }

    public Account getRandomClient(){
        return getAccount(startAccountNum + (long)((getCurrentAccountNum()-startAccountNum) * Math.random()));
    }

}
