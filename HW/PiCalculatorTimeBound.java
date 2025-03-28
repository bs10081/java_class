package HW;
import java.util.concurrent.TimeUnit; 

public class PiCalculatorTimeBound {

    // --- 設定 ---
    private static final double DEFAULT_DURATION_SECONDS = 10.0; // 預設執行時間（秒）
    // 在總執行時間內，要報告 Pi 近似值的次數 (例如 10 次，即大約每 10% 時間報告一次)
    private static final int REPORT_COUNT = 10;
    private static final int PROGRESS_BAR_WIDTH = 50;     // 進度條寬度

    // 解釋為啥和題目不一樣：
    // 因為我想做進度條，但是現在的 CPU 都太快了，所以進度條根本看不到，
    // 所以我就改成時間限制，同時限制 CPU 速度在給定的時間內完成，
    // 所以理論上每次執行回報的都會不一樣，也比較有趣


    public static void main(String[] args) {
        double targetDurationSeconds = DEFAULT_DURATION_SECONDS; // 初始化為預設值

        // --- 解析命令列參數 ---
        if (args.length > 0) {
            try {
                targetDurationSeconds = Double.parseDouble(args[0]);
                // 檢查秒數是否為正數
                if (targetDurationSeconds <= 0) {
                    System.out.println("警告：執行時間必須是正數。將使用預設值：" + DEFAULT_DURATION_SECONDS + " 秒");
                    targetDurationSeconds = DEFAULT_DURATION_SECONDS;
                }
            } catch (NumberFormatException e) {
                // 如果參數無法轉換為數字，顯示錯誤並使用預設值
                System.err.println("錯誤：無效的執行時間參數 '" + args[0] + "'。必須是數字。");
                System.err.println("將使用預設執行時間：" + DEFAULT_DURATION_SECONDS + " 秒");
                targetDurationSeconds = DEFAULT_DURATION_SECONDS;
            }
        }

        // 將目標時間轉換為毫秒，方便比較
        long targetDurationMillis = (long) (targetDurationSeconds * 1000.0);

        // --- UI 顯示 ---
        System.out.println("===========================================");
        System.out.println("   使用萊布尼茲公式計算圓周率 (π) - 時間限制版");
        System.out.println("===========================================");
        System.out.printf("目標執行時間: %.2f 秒\n", targetDurationSeconds);
        System.out.println("期間報告次數: " + REPORT_COUNT);
        System.out.println("-------------------------------------------");
        System.out.println("開始計算...");

        // --- 計算 ---
        double sum = 0.0;        // 累加萊布尼茲級數的項
        long iteration = 0;      // 迭代計數器，使用 long 以防數字過大
        int reportsMade = 0;     // 已輸出的報告次數
        double finalPi = 0.0;    // 儲存最終計算的 Pi 值

        long startTime = System.currentTimeMillis(); // 記錄開始時間
        long currentTime;
        long elapsedTime;

        // 使用無限迴圈，內部透過時間判斷來跳出
        while (true) {
            iteration++; // 增加迭代次數

            // 計算萊布尼茲級數的第 i 項: (-1)^(i+1) / (2i - 1)
            double term = Math.pow(-1, iteration + 1) / (2.0 * iteration - 1.0);
            sum += term; // 累加

            // 獲取目前時間並計算已用時間
            currentTime = System.currentTimeMillis();
            elapsedTime = currentTime - startTime;

            // --- 檢查是否達到目標時間 ---
            if (elapsedTime >= targetDurationMillis) {
                // 確保進度條顯示為 100% 和最終狀態
                printTimeProgressBar(targetDurationMillis, targetDurationMillis, iteration, PROGRESS_BAR_WIDTH);
                // 計算最終 Pi 值
                finalPi = 4.0 * sum;
                // 如果最後一次報告點剛好不是時間結束點，補上最終報告
                 if (reportsMade < REPORT_COUNT || REPORT_COUNT == 0) {
                    clearCurrentLine(PROGRESS_BAR_WIDTH + 60); // 清除進度條行
                    System.out.printf("時間 %5.1fs (迭代 %,d 次): π ≈ %.15f (最終)\n",
                                      targetDurationMillis / 1000.0, iteration, finalPi);
                 }
                break; // 跳出 while 迴圈
            }

            // --- 進度條更新 ---
            // 定期更新進度條以獲得平滑效果 (例如每 5000 次迭代或每 50 毫秒)
            // 這裡選擇迭代次數，但也可以用 elapsedTime % 50 == 0 之類的時間判斷
            if (iteration % 5000 == 0) {
                 // 只有在還沒到下個報告點時才畫，避免閃爍
                 double currentProgressRatio = (double) elapsedTime / targetDurationMillis;
                 int nextReportThreshold = reportsMade + 1;
                 if (currentProgressRatio * REPORT_COUNT < nextReportThreshold) {
                    printTimeProgressBar(elapsedTime, targetDurationMillis, iteration, PROGRESS_BAR_WIDTH);
                 }
            }

            // --- 根據時間間隔報告 Pi ---
            // 計算目前進度比例應該對應到第幾個報告點
            double currentProgressRatio = (double) elapsedTime / targetDurationMillis;
            int targetReportNum = (int) Math.floor(currentProgressRatio * REPORT_COUNT);

            // 如果目前進度應該達到的報告點數量 > 已經報告的數量，就輸出報告
            if (targetReportNum > reportsMade && reportsMade < REPORT_COUNT) {
                double currentPi = 4.0 * sum;
                clearCurrentLine(PROGRESS_BAR_WIDTH + 60); // 清除目前的進度條行
                System.out.printf("時間 %5.1fs (迭代 %,d 次): π ≈ %.15f\n",
                                  elapsedTime / 1000.0, iteration, currentPi);
                reportsMade++; // 增加已報告次數

                // 報告後立即重繪進度條，顯示當前狀態
                printTimeProgressBar(elapsedTime, targetDurationMillis, iteration, PROGRESS_BAR_WIDTH);
            }
        } // --- while 迴圈結束 ---

        // --- 結束 UI 顯示 ---
        System.out.println(); // 確保游標在新的一行
        System.out.println("-------------------------------------------");
        System.out.println("計算完成！");
        System.out.printf("實際執行時間約: %.3f 秒\n", (System.currentTimeMillis() - startTime) / 1000.0);
        System.out.printf("總迭代次數: %,d\n", iteration);
        // 再次顯示最終計算結果 (即使上面可能已作為最後報告輸出過)
        System.out.printf("最終 Pi 近似值: %.15f\n", finalPi);
        System.out.println("===========================================");
    }

    /**
     * 在命令列印出基於時間的進度條。
     * @param elapsedMillis 已過毫秒數
     * @param totalMillis   目標總毫秒數
     * @param currentIteration 目前的迭代次數
     * @param width         進度條的字元寬度
     */
    private static void printTimeProgressBar(long elapsedMillis, long totalMillis, long currentIteration, int width) {
        // 防止 totalMillis 為 0 導致除法錯誤
        if (totalMillis <= 0) totalMillis = 1;
        // 確保 elapsedTime 不會超過 totalMillis，避免進度超過 100%
        elapsedMillis = Math.min(elapsedMillis, totalMillis);

        // 計算完成百分比
        double percentage = (double) elapsedMillis / totalMillis;
        int filledWidth = (int) (width * percentage);
        int emptyWidth = width - filledWidth;

        // 建構進度條字串
        StringBuilder bar = new StringBuilder();
        bar.append('[');
        for (int j = 0; j < filledWidth; j++) bar.append('=');
        if (filledWidth < width && elapsedMillis > 0) bar.append('>'); // 進行中標示
        for (int j = 0; j < emptyWidth; j++) bar.append(' ');
        bar.append(']');

        // 格式化時間顯示 (秒)
        double elapsedSeconds = elapsedMillis / 1000.0;
        double totalSeconds = totalMillis / 1000.0;

        // 格式化迭代次數，增加千分位分隔符
        String iterationStr = String.format("%,d", currentIteration);

        // 使用 '\r' 回到行首，並格式化輸出
        System.out.printf("\r計算中: %s %.1f%% (時間 %.1fs/%.1fs, 迭代 %s)",
                          bar.toString(), percentage * 100.0, elapsedSeconds, totalSeconds, iterationStr);
        System.out.flush(); // 強制刷新輸出
    }

    /**
     * 清除命令列目前游標所在的行。
     * @param length 要清除的字元長度 (應足夠覆蓋之前的輸出)
     */
    private static void clearCurrentLine(int length) {
         // 回到行首，印出足夠多的空白，再回到行首
         System.out.print("\r" + " ".repeat(length) + "\r");
         System.out.flush(); // 強制刷新
    }
}