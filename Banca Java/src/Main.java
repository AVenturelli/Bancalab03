public class Main {
    public static void main(String[] args)
    {
        BankAccount alberto = new BankAccount(121213);
        double soldo = alberto.getBalance();
        System.out.println(soldo);
        alberto.withdraw(10213);
        soldo = alberto.getBalance();
        System.out.println(soldo);

    }
}
