package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 安排工作以达到最大收益 {

    //经典双指针+排序
    public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
        int n = difficulty.length;
        int[][] jobs = new int[n][2];
        for (int i = 0; i < n; i++) {
            jobs[i][0] = difficulty[i];
            jobs[i][1] = profit[i];
        }
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        Arrays.sort(worker);
        int ans = 0, j = 0, maxProfit = 0;
        for (int w : worker) {
            while (j < n && jobs[j][0] <= w) {
                maxProfit = Math.max(maxProfit, jobs[j++][1]);
            }
            ans += maxProfit;
        }
        return ans;
    }

   /* 作者：灵茶山艾府
    链接：https://leetcode.cn/problems/most-profit-assigning-work/solutions/2780326/pai-xu-shuang-zhi-zhen-pythonjavacgojsru-gthg/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}
