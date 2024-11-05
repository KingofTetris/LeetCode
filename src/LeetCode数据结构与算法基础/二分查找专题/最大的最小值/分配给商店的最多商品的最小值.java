package LeetCode数据结构与算法基础.二分查找专题.最大的最小值;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */

//珂珂的魔爪！

public class 分配给商店的最多商品的最小值 {

    public int minimizedMaximum(int n, int[] quantities) {
        int max  = 0;
        for (int q : quantities) max = Math.max(max, q);
        int l = 1 , r = max;
        while(l <= r) {
            int mid  = l + (r - l >> 1);
            int cr = check(n, mid, quantities);
            if (cr >= 0) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        return l;
    }

    public int check(int n, int mid, int[] quantities) {
        for (int i = 0; i < quantities.length; i++)
            n -= (quantities[i] + mid - 1) / mid;
        return n;
    }
}
