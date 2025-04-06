package HW;

import java.util.Scanner;

public class twoDimensionalArrayMultiplication {
    public static void main(String[] args) {
        int[][] num1 = new int[3][3]; // 第一個3x3矩陣
        int[][] num2 = new int[3][3]; // 第二個3x3矩陣

        if (args.length == 18) {
            // 若命令列參數為18個，直接使用
            for (int i = 0; i < 9; i++) {
                num1[i / 3][i % 3] = Integer.parseInt(args[i]);
            }
            for (int i = 9; i < 18; i++) {
                num2[(i - 9) / 3][(i - 9) % 3] = Integer.parseInt(args[i]);
            }
        } else {
            // 否則，提示用戶輸入
            Scanner scanner = new Scanner(System.in);
            System.out.println("請輸入第一個3x3矩陣的9個整數：");
            for (int i = 0; i < 9; i++) {
                num1[i / 3][i % 3] = scanner.nextInt();
            }
            System.out.println("請輸入第二個3x3矩陣的9個整數：");
            for (int i = 0; i < 9; i++) {
                num2[i / 3][i % 3] = scanner.nextInt();
            }
            scanner.close();
        }

        int[][] result = new int[3][3]; // 儲存相乘結果的矩陣

        // 矩陣相乘運算
        for (int i = 0; i < 3; i++) { // 遍歷結果矩陣的每一列
            for (int j = 0; j < 3; j++) { // 遍歷結果矩陣的每一行
                result[i][j] = 0; // 初始化結果元素
                for (int k = 0; k < 3; k++) { // 計算對應的乘積和
                    result[i][j] += num1[i][k] * num2[k][j];
                }
            }
        }

        // 輸出結果矩陣
        System.out.println("兩個矩陣相乘的結果為：");
        for (int i = 0; i < 3; i++) { // 遍歷每一列
            for (int j = 0; j < 3; j++) { // 遍歷每一行
                System.out.print(result[i][j] + "\t"); // 輸出元素，使用tab分隔
            }
            System.out.println(); // 換行
        }
    }
}
