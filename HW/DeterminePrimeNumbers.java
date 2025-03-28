package HW;
import java.util.Scanner;

public class DeterminePrimeNumbers {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.print("請輸入大於 1 的整數做質數測試： ");

        int number = scanner.nextInt();
        scanner.close(); // 關閉 scanner 以釋放資源

        if (number <= 1) {
            System.out.println("輸入的數字必須大於 1。");
        } else if (isPrime(number)) {
            System.out.println(number + " 是質數");
        } else {
            System.out.println(number + " 不是質數");
        }
    }

    /**
     * 檢查一個數字是否為質數
     * @param num 要檢查的數字
     * @return 如果是質數返回 true，否則返回 false
     */
    public static boolean isPrime(int num) {
        // 質數定義為大於 1 的自然數，除了 1 和它本身以外不再有其他因數
        if (num <= 1) {
            return false; // 小於等於 1 的數不是質數
        }
        // 從 2 開始檢查到 num 的平方根
        // 如果在這之間找到任何可以整除 num 的數，那 num 就不是質數
        for (int i = 2; i * i <= num; i++) {
            if (num % i == 0) {
                return false; // 找到因數，不是質數
            }
        }
        // 如果迴圈結束都沒有找到因數，那就是質數
        return true;
    }
}