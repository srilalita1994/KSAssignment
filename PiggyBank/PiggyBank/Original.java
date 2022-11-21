public class Original {
    import java.util.Scanner;

    public class DigitalBank {
        // int inputCount;
        public static void main(String[] args) {
            DigitalBankAccount account = new DigitalBankAccount(100);
            System.out.println("DEPOSIT has just deposited:" + account.getBalance());
            System.out.println("Current account balance:" + account.getBalance() );
            int inputCount;
            Scanner input = new Scanner(System.in);
            System.out.println("Enter max. number of withdrawers: N =  ");
            inputCount = input.nextInt() + 1;
            Thread[] bankThread = new Thread[inputCount];
           // createCustomerThreads(bankThread,inputCount,account );
            for(int i = 0; i < inputCount ; i++) {
                if (i == 0) {
                    bankThread[i] = new Depositor(account);
                    bankThread[i].setName("DEPOSIT");
                    bankThread[i].setPriority(10);
                } else {
                    bankThread[i] = new Withdrawer(account);
                    bankThread[i].setName("WITHDRAW_"+ i);
                }
            }
            //initializeCustomerThreads(inputCount,bankThread);
            for(int i = 0; i < inputCount; i++) {
                bankThread[i].start();
            }
           // joiningCustomerThreads(inputCount,bankThread);
            for(int i = 0; i < inputCount; i++) {
                try {
                    bankThread[i].join();

                } catch(InterruptedException ie) {
                    System.err.println(ie.getMessage());
                } finally {
                    System.out.println("Banker "+ bankThread[i].getName() + " has exited ");
                }
            }
            System.out.print("Closing balance = "+ account.getBalance() );
        }
}
