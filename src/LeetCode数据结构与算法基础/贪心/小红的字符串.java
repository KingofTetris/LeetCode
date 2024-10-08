package LeetCode数据结构与算法基础.贪心;

import java.util.Scanner;


/**
 * 题目描述
 * 小红有 x 个字母 a，y 个字母 b，小红不喜欢在串中出现连续超过 k 个相同的字符。
 * 如果存在用完所有字母组成的字符串，找出其中字典序最大的串，否则输出-1。
 * 输入描述
 * 一行三个整数 x，y，k（1 <= x,y <= 10^5, 1 <= k <= 100），表示小红有 x 个字母 a，y 个字母 b，
 * 小红不喜欢在串中出现连续超过 k 个相同的字符。
 * 输出描述
 * 如果存在，输出字典序最大的串，否则输出 -1。
 * 输入示例
 * 3 5 2
 * 输出示例
 * bbabbaba
 */
public class 小红的字符串 {


    /**
     * 贪心思路
     *
     * 为了保证字典序最大，我们优先放置字母 b，然后再放置字母 a。在放置字符时，我们还需注意不能超过连续 k 次相同字符：
     *
     * 如果当前已经连续放置了 k 次相同字符，必须切换到另一个字符。
     * 每次放置字符后，相应的字符数量减少，同时更新当前字符的连续计数。
     * 实现步骤：
     *
     * 初始化：根据输入的 x, y, k 值，检查是否有可能构造出满足条件的字符串。初始化结果字符串的大小，并设置初始计数器。
     * 循环放置字符：
     * 优先放置字符 b，如果 b 的数量已经足够，或者已经放置了 k 次字符 b，则放置字符 a。
     * 如果已经放置了 k 次相同字符，则强制切换到另一个字符。

     * @param args
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int countA = scanner.nextInt();
        int countB = scanner.nextInt();
        int k = scanner.nextInt();

        // 检查是否有可能生成满足条件的字符串
        /**
         * 假设countA 可以连续K 着放 N次， 那么countA >= N * k 剩下的countA - N*k 小于 k次
         * 那么至少需要  k 个 B才能插入到这个N次里面，保证字符串合法。
         *
         * 如果 countA - k 还大于 k*B 那不管你怎么插，A一定连续
         * 反过来B也一样
         */
        if (countA > (countB + 1) * k || countB > (countA + 1) * k) {
            System.out.println(-1);
            return;
        }

        //给定初始容量 节省空间
        StringBuilder result = new StringBuilder(countA + countB);

        int currentA = 0; // 当前连续 'a' 和 'b' 的计数
        int currentB = 0;
        int pos = 0;  // 当前填充位置

        //如果能凑成合法的字符串，就把所有字符用完。
        while (countA > 0 || countB > 0) {
            // 当可以继续添加 'a' 或 'b' 且没有超过最大连续限制时
            if (currentA < k && currentB < k) {
                //先插入'b' 保证字典序最大
                //为什么条件是countA <= countB * k
                if (countA <= countB * k) {
                    result.append('b');
                    countB--;
                    currentB++;
                    currentA = 0;
                }
                else {
                    result.append('a');
                    countA--;
                    currentA++;
                    currentB = 0;
                }
                pos++;
            }
            // 当 当前字符pos 达到最大连续限制时，切换到另一个字符
            if (currentA == k || currentB == k) {
                if (result.charAt(pos - 1) == 'a' && countB > 0) {
                    result.append('b');
                    countB--;
                    currentB = 1;
                    currentA = 0;
                }
                else if (result.charAt(pos - 1) == 'b' && countA > 0){
                    result.append('a');
                    countA--;
                    currentA = 1;
                    currentB = 0;
                }
                pos++;
            }
        }
        System.out.println(result);
    }
}
