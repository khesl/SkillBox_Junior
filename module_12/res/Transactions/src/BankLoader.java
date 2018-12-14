package module_12.res.Transactions.src;

import Utils.ConsoleColor;

import java.util.ArrayList;

public class BankLoader {
    private static Bank bank;
    public static int clientCount = 50000;
    public static long minMoney = 5000;
    public static long maxMoney = 100000;

    public static void main(String[] args) {
        init();
    }

    public static void init(){
        bank = new Bank("CN", 5);
        generateAccaunts(clientCount, minMoney, maxMoney);
        System.out.println("Generate success.");
        System.out.println(bank.getCurrentAccountNum());


        ArrayList<Thread> threads = new ArrayList<Thread>();
        for (int i = 0;i<bank.getWorkPlace(); i++){
            threads.add(new BankWorkPlace(i));
        }

        //Starting threads
        for(Thread thread : threads) {
            thread.start();
        }
    }

    private static void generateAccaunts(int count, long minMoney, long maxMoney){
        for (int i= 0;i <= count; i++){
            long money = minMoney + (long)((maxMoney-minMoney)*Math.random());
            String accNumber = String.valueOf(bank.getNextAccountNum());
            bank.newClient(new Account(accNumber, money));
        }
    }

    public static Bank getBank(){ return bank; }

    public static void logger(int workPlace, String message){
        System.out.println("workPlace number (" + ConsoleColor.setColor(String.valueOf(workPlace), ConsoleColor.Color.ANSI_BLUE) +")\t-\t" + message);
    }
}
