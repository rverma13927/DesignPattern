package org.example.SolidPrinciple.liskovsubstitution;

/**
 *
 * Why does this follow LSP?
 * Both SavingsAccount and FixedDepositAccount can be used wherever Account is expected without unexpected behavior.
 * If you need an account that allows withdrawals, use WithdrawableAccount.
 * No surprises for the client code, ensuring inheritance is used correctly.
 *
 *
 */
public class With {
}
// Base interface for all accounts
interface Account {
    double getBalance();
}

// Interface for accounts that allow withdrawals
interface WithdrawableAccount extends Account {
    void withdraw(double amount);
}

// Savings account allows withdrawals
class SavingsAccount implements WithdrawableAccount {
    private double balance;

    public SavingsAccount(double balance) {
        this.balance = balance;
    }

    @Override
    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

// Fixed deposit account doesn't allow withdrawals
class FixedDepositAccount1 implements Account {
    private double balance;

    public FixedDepositAccount1(double balance) {
        this.balance = balance;
    }

    @Override
    public double getBalance() {
        return balance;
    }
}

 class Main {
    public static void main(String[] args) {
        WithdrawableAccount savings = new SavingsAccount(1000);
        savings.withdraw(500);
        System.out.println("Savings Account Balance: " + savings.getBalance());  // Output: 500.0

        Account fixedDeposit = new FixedDepositAccount1(2000);
        System.out.println("Fixed Deposit Balance: " + fixedDeposit.getBalance());  // Output: 2000.0
    }
}
