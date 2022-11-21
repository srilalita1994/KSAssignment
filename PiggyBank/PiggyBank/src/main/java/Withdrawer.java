import java.util.Random;

public class Withdrawer extends Thread {
    private final DigitalBankAccount account;
    public Withdrawer(DigitalBankAccount acct) {
        super();
        account = acct;
    }

    /**
     *run method: operation performed by N Withdrawer threads and killed after few iterations
     */
    public void run() {
        for(int threadIterations = 0; threadIterations < 15 ; threadIterations++) {
            Random rand = new Random();
            int withdrawDenomination = rand.ints(1, 10).findFirst().getAsInt();
            int withdrawAmt = 10* withdrawDenomination;
            account.withdraw(withdrawAmt);
        }
    }

}
