package lab01.example.model;

public class SimpleBankAccountWithAtm extends AbstractBankAccount {

    public SimpleBankAccountWithAtm(AccountHolder accountHolder, int balance) {
        super(balance, accountHolder, ()->1);
    }

}
