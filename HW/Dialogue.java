package HW;
import java.util.Scanner; 

public class Dialogue {

    public static void main(String[] args) {
        // 建立一個 Scanner 物件來讀取標準輸入 (鍵盤)
        Scanner scanner = new Scanner(System.in);
        String input; // 用來儲存使用者輸入的字串

        // 使用一個無限迴圈來持續進行對話，直到使用者輸入 'q'
        while (true) {
            System.out.println("輸入你的姓名、學號");
            System.out.print("輸入 q 可以結束對話 = ");
            
            input = scanner.nextLine();

            // 檢查使用者輸入是否為 "q" 
            if (input.equals("q")) {
                // 如果輸入是 "q"，則輸出 "q" 並跳出迴圈，結束程式
                System.out.println(input); 
                break;
            } else {
                // 如果輸入不是 "q"，則將使用者輸入的內容原樣輸出
                System.out.println(input);
                System.out.println(); 
            }
        }

        // 關閉 Scanner 物件，釋放資源
        scanner.close();
    }
}