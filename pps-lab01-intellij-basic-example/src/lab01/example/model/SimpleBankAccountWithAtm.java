package lab01.example.model;

public class SimpleBankAccountWithAtm implements BankAccount {
    private double balance;
    private final AccountHolder accountHolder;
    private final static int FEE = 1;

    public SimpleBankAccountWithAtm(AccountHolder accountHolder, int balance) {
        this.accountHolder = accountHolder;
        this.balance = balance;
    }

    @Override
    public AccountHolder getHolder() {
        return this.accountHolder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(int userID, double amount) {
        if (checkUser(userID)) {
            this.balance = this.balance + amount - FEE;
        }
    }

    @Override
    public void withdraw(int userID, double amount) {
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount - FEE;
        }
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= (amount + FEE);
    }

    private boolean checkUser(final int id) {
        return this.accountHolder.getId() == id;
    }
}
