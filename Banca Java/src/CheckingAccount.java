public class CheckingAccount extends  BankAccount {
    public static final int FREE_TRANSACTIONS = 20;
    private static final double TRANSACTION_FEE = 0.5;
    private int trans_utilizzate;

    public CheckingAccount(double bilancio) {
        super(bilancio);
        this.trans_utilizzate = 0;
    }

    public void deposit(double soldi) {
        trans_utilizzate++;
        balance += soldi;
    }

    public void widtdraw(double soldi) {
            balance -= soldi;
            trans_utilizzate++;
    }
    public void deductFees() {
        if (trans_utilizzate >= FREE_TRANSACTIONS) {

            trans_utilizzate = 0;
        } else {
            trans_utilizzate = 0;
        }
    }
    @Override
    public String toString() {
        return "CheckingAccount{" +
                "trans_utilizzate=" + trans_utilizzate +
                ", balance=" + balance +
                '}';
    }
}
