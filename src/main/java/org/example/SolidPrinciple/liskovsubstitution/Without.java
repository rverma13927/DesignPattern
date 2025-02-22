package org.example.SolidPrinciple.liskovsubstitution;

public class Without {
}

class BankAccount {
    protected double balance;

    public BankAccount(double balance) {
        this.balance = balance;
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
        } else {
            throw new IllegalArgumentException("Insufficient funds");
        }
    }

    public double getBalance() {
        return balance;
    }
}

class FixedDepositAccount extends BankAccount {
    public FixedDepositAccount(double balance) {
        super(balance);
    }

    @Override
    public void withdraw(double amount) {
        throw new UnsupportedOperationException("Cannot withdraw from Fixed Deposit Account");
    }
}
class Main33 {
    public static void main(String[] args) {
        BankAccount account = new FixedDepositAccount(1000);
        account.withdraw(500);  // Throws exception unexpectedly
    }
}
