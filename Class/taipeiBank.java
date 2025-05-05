package Class;

class Bank {
    private String name;
    private int balance;
    public Bank(String name, int balance) {
        this.name = name;
        this.balance = 0;
    }
    // 存款
    public void deposit(int amount) {
        balance += amount;
        System.out.println("存款成功，餘額為：" + balance);
    }
    // 提款
    public void withdraw(int amount) {
        if (amount > balance) {
            System.out.println("提款失敗，餘額不足");
        } else {
            balance -= amount;
            System.out.println("提款成功，餘額為：" + balance);
        }
    }
    // 查詢餘額
    public void checkBalance() {
        System.out.println("餘額為：" + balance);
    }
}

public class taipeiBank {
    public static void main(String[] args) {
        Bank A = new Bank("台北銀行", 0);
        System.out.println("台北銀行");
        A.deposit(1000);
        A.withdraw(500);
        A.checkBalance();
        A.withdraw(600);
        A.checkBalance();
        A.deposit(200);
        A.checkBalance();
    }
}