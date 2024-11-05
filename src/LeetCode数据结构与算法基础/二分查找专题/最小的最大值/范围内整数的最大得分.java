package LeetCode数据结构与算法基础.二分查找专题.最小的最大值;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */
public class 范围内整数的最大得分 {

    public int maxPossibleScore(int[] start, int d) {
        Arrays.sort(start);
        int n = start.length;
        int left = 0;
        int right = (start[n - 1] + d - start[0]) / (n - 1) + 1;
        while (left + 1 < right) {
            int mid = (left + right) >>> 1;
            if (check(start, d, mid)) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return left;
    }

    private boolean check(int[] start, int d, int score) {
        long x = Long.MIN_VALUE;
        for (int s : start) {
            x = Math.max(x + score, s); // x 必须 >= 区间左端点 s
            if (x > s + d) {
                return false;
            }
        }
        return true;
    }

/*    作者：灵茶山艾府
    链接：https://leetcode.cn/problems/maximize-score-of-numbers-in-ranges/solutions/2908931/er-fen-da-an-zui-da-hua-zui-xiao-zhi-pyt-twe2/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}
