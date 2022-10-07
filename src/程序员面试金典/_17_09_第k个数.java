package 程序员面试金典;

/**
 * @Author KingofTetris
 * @Date 2022/9/28 9:29
 * 有些数的素因子只有 3，5，7，请设计一个算法找出第 k 个数。
 * 注意，不是必须有这些素因子，而是必须不包含其他的素因子。例如，前几个数按顺序应该是 1，3，5，7，9，15，21。
 *
 * 示例 1:
 *
 * 输入: k = 5
 *
 * 输出: 9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/get-kth-magic-number-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _17_09_第k个数 {

    //以前好像有做过这道题 或者类似的   是剑指Offer49_丑数.
    //其实就是要求 3a * 5b * 7c 的第 k 小的值
    //问题是 a,b,c怎么确定

    /**
     * 后面的数 一定是前面的某个数 *3,*5,*7得到的
     * 假设前面的数是 Xa,Xb,Xc
     * 那么Xd = Min{Xa*3,Xb*5,Xc*7};
     * 每次得到最小的 避免重复
     * @param k
     * @return
     */
    public int getKthMagicNumber(int k) {
        int a = 1,b = 1,c = 1;
        int[] dp = new int[k + 1];//k + 1舍弃0的位置
        dp[1] = 1;
        for (int i = 2; i <= k; i++) {
            dp[i] = Math.min(Math.min(dp[a]*3,dp[b]*5),dp[c]*7);
            if (dp[i] == dp[a] * 3) a++;
            if (dp[i] == dp[b] * 5) b++;
            if (dp[i] == dp[c] * 7) c++;
        }

        return dp[k];
    }



}
