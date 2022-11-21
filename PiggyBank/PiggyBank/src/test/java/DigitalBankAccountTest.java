import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class DigitalBankAccountTest {
    private DigitalBankAccount digitalBankAccount;

    @BeforeEach
     void setUp(){
        digitalBankAccount = new DigitalBankAccount(100);
    }

    @Test
     void checkBalNotNull() {
        assertNotNull(digitalBankAccount.getBalance());
    }
    @Test
     void checkBalNull() {
        digitalBankAccount = new DigitalBankAccount(0);
        double newBal = digitalBankAccount.getBalance();
        assertEquals(0,newBal);
    }
    @Test
     void checkBalance() {
        assertEquals(100, digitalBankAccount.getBalance());
    }
    @Test
     void check_WithWithdrawLess() {
        assertEquals(70,digitalBankAccount.withdraw(30));
    }
    @Test
     void check_WithWithdrawMore() {
        double newBal = digitalBankAccount.getBalance();
        assertEquals(100,digitalBankAccount.withdraw(110));
    }
    @Test
     void checkFalse_WithWithdraw() {
        double newBal = digitalBankAccount.getBalance();
        assertFalse(newBal < digitalBankAccount.withdraw(80));
    }
    @Test
     void checkFalse_WithWithdrawBefore() {
        double newWithdraw = digitalBankAccount.withdraw(70);
        double newBal = digitalBankAccount.getBalance();
        assertFalse(newBal > newWithdraw );
    }
    @Test
    void checkTrue_WithWithdrawBefore() {
        double newWithdraw = digitalBankAccount.withdraw(70);
        double newBal = digitalBankAccount.getBalance();
        assertTrue(newBal == 30 );
    }
    @Test
    void check_WithWithdrawBefore() {
        double newWithdraw = digitalBankAccount.withdraw(60);
        double newBal = digitalBankAccount.getBalance();
        assertEquals(40,newBal);
    }

    @Test
     void checkTrue_WithDeposit() {
        double newDeposit = digitalBankAccount.deposit(50);
        double newBal = digitalBankAccount.getBalance();
        assertTrue(newBal==150);
    }
    @Test
     void check_WithDeposit() {
        double newDeposit = digitalBankAccount.deposit(90);
        double newBal = digitalBankAccount.getBalance();
        assertEquals(newBal,newDeposit);
    }
    @Test
     void checkFalse_WithDeposit() {
        double newBal = digitalBankAccount.getBalance();
        assertFalse(newBal > digitalBankAccount.deposit(70));
    }
    @AfterEach
    void tearDown(){
        digitalBankAccount = null;
    }

}