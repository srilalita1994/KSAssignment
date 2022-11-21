import java.util.Random;

public class Depositor extends Thread {
    private final DigitalBankAccount account;
    public Depositor(DigitalBankAccount acct) {

        account = acct;
    }
    /**
     * run method: operation performed by the Depositor thread when it is scheduled and kill after few iterations
     */
    public void run() {
        for(int threadIterations = 0; threadIterations< 15 ; threadIterations++) {
            Random rand = new Random();
            int depositDenomination = rand.ints(1, 10).findAny().getAsInt();
            int depositAmount = 100 * depositDenomination;
            account.deposit(depositAmount);
        }
    }
}
