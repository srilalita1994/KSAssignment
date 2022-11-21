public class DigitalBankAccount {
    private double balance;
    public DigitalBankAccount(double bal) {
        balance = bal;
    }

    /**
     * getBalance method:
     * @return : balance
     */
    public synchronized double getBalance() {
        return balance;
    }

    /**
     * deposit method: calculates the account balance after deposit
     * @param depositAmount : Amount deposited by Depositor
     * @return balance after adding deposit amount
     */
    public synchronized double deposit(double depositAmount) {
        double temp = balance;
        temp = temp + depositAmount;
        try {
            Thread.sleep(3000); // simulate production time
        } catch (InterruptedException ie) {
            System.err.println(ie.getMessage());
        }
        System.out.println(Thread.currentThread().getName()+ "  has just deposited : "+depositAmount);
        System.out.println("Current account balance : " + temp);
        balance = temp;
        notify();
        return balance;
    }

    /**
     * withdraw method: calculates the account balance after withdrawal
     * @param withdrawAmt: Amount withdrawn by Withdrawer
     * @return balance after subtracting withdraw amount
     */
    public synchronized double withdraw(double withdrawAmt) {
        if (balance < withdrawAmt) {
            System.out.println(Thread.currentThread().getName() +" request to withdraw: "+ withdrawAmt +" failed - insufficient balance in the account");
        }else {
            double temp = balance;
            temp = temp - withdrawAmt;
            System.out.println(Thread.currentThread().getName() + " has just withdrawn " + withdrawAmt);
            System.out.println("Current account balance :" + temp);
            balance = temp;
        }
        return balance;
    }
}
