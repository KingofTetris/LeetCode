package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2024/9/10
 */
public class 单调递增的数字 {

    /**
     * 当且仅当每个相邻位数上的数字 x 和 y 满足 x <= y 时 (x下标 < y下标)，我们称这个整数是单调递增的。
     * <p>
     * 给定一个整数 n ，返回 小于或等于 n 的最大数字，且数字呈 单调递增 。
     * <p>
     * <p>
     * <p>
     * 示例 1:
     * <p>
     * 输入: n = 10
     * 输出: 9
     * 示例 2:
     * <p>
     * 输入: n = 1234
     * 输出: 1234
     * 示例 3:
     * <p>
     * 输入: n = 332
     * 输出: 299
     *
     * @param n
     * @return
     */

    @Test
    public void test() {
        int digits = monotoneIncreasingDigits(332);
        System.out.println(digits);
    }

    public int monotoneIncreasingDigits(int n) {
        //数字拆分不一般都是把数字化成字符串
        String s = String.valueOf(n);
        char[] chars = s.toCharArray();
        int start = s.length();
        for (int i = s.length() - 2; i >= 0; i--) {
            //如果前面比后面大，就不符合要求了。
            if (chars[i] > chars[i + 1]) {
                //chars[i]--
                chars[i]--;
                //记录要从哪开始改成9
                start = i + 1;
            }
        }
        for (int i = start; i < s.length(); i++) {
            chars[i] = '9';
        }
        return Integer.parseInt(String.valueOf(chars));
    }


    //暴力法肯定超时的。
    public boolean isMonotone(int n) {
        LinkedList<Integer> list = new LinkedList<>();
        while (n != 0) {
            int gw = n % 10;
            if (list.size() != 0 && gw > list.getLast()) {
                return false;
            }
            list.add(gw);
            n = n / 10;
        }
        return true;
    }
}
