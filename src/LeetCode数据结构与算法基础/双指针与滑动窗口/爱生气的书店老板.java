package LeetCode数据结构与算法基础.双指针与滑动窗口;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 爱生气的书店老板 {

    public int maxSatisfied(int[] customers, int[] grumpy, int minutes) {
        int ans = 0;
        // 先计算出 在没有使用抑制情绪下 最多能使多少客户满意
        for (int i = 0; i < customers.length; i++) {
            if (grumpy[i] == 0) {
                ans += customers[i];
            }
        }
        // 通过滑动窗口思想计算出,在 哪个区间使用抑制情绪能挽回最多的顾客
        int left = 0;
        int max = 0;
        int sum = 0;
        for (int right = 0; right < grumpy.length; right++) {
            // 流失的客户数
            if (grumpy[right] == 1) {
                sum += customers[right];
            }
            // 抑制情绪区间
            if ((right - left + 1) == minutes) {
                // 计算最大挽回数
                max = Math.max(sum, max);
                // 滑动窗口
                if (grumpy[left] == 1) {
                    sum -= customers[left];
                }
                left++;
            }
        }
        // 在没有使用抑制情绪下客户满意数 + 抑制情绪最多能挽回的客户数
        return ans + max;
    }
}
