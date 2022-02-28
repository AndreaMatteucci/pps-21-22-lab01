package lab01.example.model;

public abstract class AbstractBankAccount implements BankAccount {
    protected final AccountHolder accountHolder;
    protected double balance;
    protected FeeStrategy feeStrategy;

    public AbstractBankAccount(int balance, AccountHolder accountHolder, FeeStrategy feeStrategy) {
        this.balance = balance;
        this.accountHolder = accountHolder;
        this.feeStrategy = feeStrategy;
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
            this.balance = this.balance + amount - this.feeStrategy.computeFee();
        }
    }

    @Override
    public void withdraw(int userID, double amount) {
        if (checkUser(userID) && isWithdrawAllowed(amount)) {
            this.balance = this.balance - amount - this.feeStrategy.computeFee();
        }
    }

    private boolean isWithdrawAllowed(final double amount) {
        return this.balance >= (amount + this.feeStrategy.computeFee());
    }

    private boolean checkUser(final int id) {
        return this.accountHolder.getId() == id;
    }
}
