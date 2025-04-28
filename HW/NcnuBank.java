package HW;

public class NcnuBank {
    private long account;
    private double balance;

    public NcnuBank(long account, double initialBalance) {
        this.account = account;
        this.balance = initialBalance;
    }

    // 存款（不印訊息）
    public void saveMoney(double amount) {
        if (amount > 0) {
            balance += amount;
        }
    }

    // 提款，成功回傳 true，失敗回傳 false
    public boolean withdraw_money(double amount) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // 印出帳戶與餘額
    public void printInfo() {
        System.out.printf("帳戶  : %d,  餘額  : %.0f%n", account, balance);
    }

    public static void main(String[] args) {
        NcnuBank myAccount = new NcnuBank(10000001L, 0.0);

        // 1. 初始狀態
        myAccount.printInfo();        // 帳戶  : 10000001,  餘額  : 0

        // 2. 存款 100
        myAccount.saveMoney(100);
        System.out.println("存款成功");
        myAccount.printInfo();        // 帳戶  : 10000001,  餘額  : 100

        // 3. 提款 90
        boolean ok = myAccount.withdraw_money(90);
        System.out.println(ok ? "提款成功" : "提款失敗");
        myAccount.printInfo();        // 帳戶  : 10000001,  餘額  : 10

        // 4. 再提款 20
        ok = myAccount.withdraw_money(20);
        System.out.println(ok ? "提款成功" : "提款失敗");
        myAccount.printInfo();        // 帳戶  : 10000001,  餘額  : 10
    }
}
