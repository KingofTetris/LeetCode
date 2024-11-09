package LeetCode数据结构与算法基础.贪心;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 你可以获得的最大硬币数目 {

    //排序，贪心，让bob拿最少得，自身尽量拿最大的
    public int maxCoins(int[] piles) {
        // 排序 + 贪心
        Arrays.sort(piles);
        int alice = piles.length - 1; // alice  的位置，我在alive的左边，尽量取最大的
        int bob = 0;  // bob的位置，尽量取最小的，故从0开始，
        int ans = 0;
        while (alice - bob + 1 >= 3) {
            ans += piles[alice - 1];
            alice -= 2;
            bob += 1;
        }
        return ans;
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/maximum-number-of-coins-you-can-get/solutions/409109/ni-ke-yi-huo-de-de-zui-da-ying-bi-shu-mu-by-leetco/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
