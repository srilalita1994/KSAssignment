import java.util.Scanner;

public class DigitalBank {

    public static void main(String[] args) {
        DigitalBankAccount account = new DigitalBankAccount(100);
        System.out.println("DEPOSIT has just deposited:" + account.getBalance());
        System.out.println("Current account balance:" + account.getBalance() );
        int inputCustomers;
        Scanner input = new Scanner(System.in);
        System.out.println("Enter max. number of Withdraw: N =  ");
        inputCustomers = input.nextInt() + 1;
        Thread[] bankThread = new Thread[inputCustomers];
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        CustomerMultiThread.initializeCustomerThreads(inputCustomers,bankThread);
        CustomerMultiThread.joiningCustomerThreads(inputCustomers,bankThread);
        System.out.print("Closing balance : "+ account.getBalance() );
    }

}
