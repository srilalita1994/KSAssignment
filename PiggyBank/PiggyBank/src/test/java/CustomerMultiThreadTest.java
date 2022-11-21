import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomerMultiThreadTest {
    int inputCustomers = 4;
    Thread[] bankThread = new Thread[inputCustomers];
    DigitalBankAccount account = new DigitalBankAccount(100);

    @Test
    void createCustomerThreads_checkDepositor() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        assertEquals("DEPOSIT", bankThread[0].getName() );
    }
    @Test
    void createCustomerThreads_checkWithdrawer_Start() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        assertEquals("WITHDRAW_1", bankThread[1].getName());
    }
    @Test
    void createCustomerThreads_checkWithdrawer_End() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        assertEquals("WITHDRAW_3", bankThread[3].getName());
    }
    @Test
    void initializeCustomerThreads_CheckDepositorAlive() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        CustomerMultiThread.initializeCustomerThreads(inputCustomers,bankThread);
        assertTrue(bankThread[0].isAlive());
    }
    @Test
    void initializeCustomerThreads_CheckWithdrawerInterrupted() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        CustomerMultiThread.initializeCustomerThreads(inputCustomers,bankThread);
        assertFalse(bankThread[2].isInterrupted());
    }
    @Test
    void joiningCustomerThreads_CheckDepositorInterrupted() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        CustomerMultiThread.joiningCustomerThreads(inputCustomers,bankThread);
        assertFalse(bankThread[0].isInterrupted());
    }
    @Test
    void joiningCustomerThreads_CheckWithdrawerAlive() {
        CustomerMultiThread.createCustomerThreads(bankThread,inputCustomers,account );
        CustomerMultiThread.joiningCustomerThreads(inputCustomers,bankThread);
        assertFalse(bankThread[1].isAlive());
    }
}