package module_12.res.Transactions.src;

import Utils.ConsoleColor;

public class BankWorkPlace extends Thread {
    private int number;

    public BankWorkPlace(int number){
        this.number = number;
    }

    @Override
    public void run(){
        super.run();
        while (true){
            if (!isInterrupted()){
                if ((int)(Math.random()*100) < 75){
                    //транзакция
                    Account acc_1 = BankLoader.getBank().getRandomClient();
                    Account acc_2 = BankLoader.getBank().getRandomClient();
                    long amount = 5000 * (int)(20*Math.random());
                    try {
                        BankLoader.getBank().transfer(acc_1.getAccNumber(), acc_2.getAccNumber(), amount);
                    } catch (Exception e) {
                        //e.printStackTrace();
                        BankLoader.logger(number, "An error has occurred: "
                                + ConsoleColor.setColor(e.getMessage(), ConsoleColor.Color.ANSI_RED)
                                + " Transaction info: " + "from '"
                                + acc_1.getAccNumber() + "' to '"
                                + acc_2.getAccNumber() + "' amount '" + amount + "'.");
                    }
                    BankLoader.logger(number, ConsoleColor.setColor("was created new transaction: " + "from '" + acc_1.getAccNumber()
                            + "' to '" + acc_2.getAccNumber() + "' amount '" + amount + "'.",  ConsoleColor.Color.ANSI_BLUE) );
                } else {
                    //новый клиент
                    long money = BankLoader.minMoney + (long)((BankLoader.maxMoney-BankLoader.minMoney)*Math.random());
                    String accNumber = String.valueOf(BankLoader.getBank().getNextAccountNum());
                    Account account = new Account(accNumber, money);
                    BankLoader.getBank().newClient(account);
                    BankLoader.logger(number, ConsoleColor.setColor("was created new client: ", ConsoleColor.Color.ANSI_YELLOW)
                            + ConsoleColor.setColor(account.toString(), ConsoleColor.Color.ANSI_PURPLE) );
                }
                try {
                    sleep(500 + (int)(1000*Math.random()));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
