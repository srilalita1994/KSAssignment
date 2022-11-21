
public class CustomerMultiThread extends DigitalBank {
    /**
     * createCustomerThreads method: creates 1 depositor and N withdrawers
     * @param bankThread : create threads
     * @param inputCustomers : input taken from keyboard
     * @param account : balance of DigitalBankAccount
     */
    public static void createCustomerThreads(Thread[] bankThread, int inputCustomers, DigitalBankAccount account) {
        for(int customers = 0; customers < inputCustomers ; customers++) {
            if (customers == 0) {
                bankThread[customers] = new Depositor(account);
                bankThread[customers].setName("DEPOSIT");
                bankThread[customers].setPriority(10);
            } else {
                bankThread[customers] = new Withdrawer(account);
                bankThread[customers].setName("WITHDRAW_"+ customers);
            }
        }
    }
    /**
     * initializeCustomerThreads method : Start the  bank threads
     * @param inputCustomers: input taken from keyboard
     * @param bankThread : has Withdrawers and Depositors
     */
    public static void initializeCustomerThreads(int inputCustomers, Thread[] bankThread) {
        for(int customers = 0; customers < inputCustomers; customers++) {
            bankThread[customers].start();
        }
    }

    /**
     * joiningCustomerThreads method: execution of threads based on priority
     * @param inputCustomers : input taken from keyboard
     * @param bankThread: has Withdrawers and Depositors
     */
    public static void joiningCustomerThreads(int inputCustomers, Thread[] bankThread) {
        for(int customers = 0; customers < inputCustomers; customers++) {
            try {
                bankThread[customers].join();
            } catch(InterruptedException ie) {
                System.err.println(ie.getMessage());
            } finally {
                System.out.println("Banker "+ bankThread[customers].getName() + " has exited ");
            }
        }
    }
}
