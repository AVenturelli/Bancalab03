public class BankAccount {
    protected double balance;

    public BankAccount()
    {
        balance = 0;
    }
    public  BankAccount(double bilancio)
    {
        balance = bilancio;
    }
    public void deposit(double soldi)
    {
        balance += soldi;
    }
    public double getBalance() {
        return balance;
    }
    public void transfer(double soldi, BankAccount account)
    {
        if((balance -= soldi) < 0)
        {
            System.out.println("Danaro disponibile insufficiente per il trasferimento");
        }
        else {
            balance -= soldi;
            account.balance += soldi;
        }
    }
    public void withdraw(double soldi)
    {
        if((balance -= soldi) < 0)
        {
            System.out.println("Danaro disponibile insufficiente");
        }
        else
        {
            balance -= soldi;
        }
    }
}
