package 剑指offer第二版.动态规划;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2022/7/18
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m-1] 。请问 k[0]*k[1]*...*k[m-1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 *
 * 示例 1：
 *
 * 输入: 2
 * 输出: 1
 * 解释: 2 = 1 + 1, 1 × 1 = 1
 * 示例 2:
 *
 * 输入: 10
 * 输出: 36
 * 解释: 10 = 3 + 3 + 4, 3 × 3 × 4 = 36
 * 提示：
 *
 * 2 <= n <= 58
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jian-sheng-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer14_I_剪绳子 {
    @Test
    public void test(){
        System.out.println(cuttingRope2(10));
    }

    /**
     * 把n米长的绳子切成m段，能使得乘积最大？ 至少要切一刀也就是两段 n>1,m>1
     * 等于把正数n拆分成m个数相加，如何能让这m个数乘积最大？
     * @param n
     * @return
     */

    /**
     * 推论一，当等分为 3 的长度的线段时可以得到最大的乘积
     * 那么如果无法等分为长度为3，就尽可能多取3。
     *
     * 推论二： 尽可能将绳子以长度 3 等分为多段时，乘积最大。
     *
     * 切分规则：
     * 最优： 3 。把绳子尽可能切为多个长度为 3 的片段，留下的最后一段绳子的长度可能为 0,1,2 三种情况。
     * 次优： 2 。若最后一段绳子长度为 2 ；则保留，不再拆为 1+1 。乘1等于没乘
     * 最差： 1 。若最后一段绳子长度为 1 ；则应把一份 3 + 1 替换为 2 + 2，因为 2 * 2 > 3×1。
     *
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/mian-shi-ti-14-i-jian-sheng-zi-tan-xin-si-xiang-by/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int cuttingRope(int n) {
            if(n <= 3) return n - 1;
            int a = n / 3, b = n % 3;
            if(b == 0) return (int)Math.pow(3, a); //如果可以用3刚好等分，那就是3的a次方
            if(b == 1) return (int)Math.pow(3, a - 1) * 4; //如果余数是1，那就把其中最后的3 + 1拆成 2*2也就是4
            return (int)Math.pow(3, a) * 2;//如果余数是2，那就乘以2就行了，不用拆分
        }

    /**
     * 这题用动态规划是比较好理解的
     *
     * 我们想要求长度为n的绳子剪掉后的最大乘积，可以从前面比n小的绳子转移而来
     * 用一个dp数组记录从0到n长度的绳子剪掉后的最大乘积，也就是dp[i]表示长度为i的绳子剪成m段后的最大乘积，
     * 初始化dp[2] = 1
     * 我们先把绳子剪掉第一段（长度为j），
     * 如果只剪掉长度为1，对最后的乘积无任何增益，所以从长度为2开始剪 (j>=2,j<i)
     * 剪了第一段后，剩下(i - j)长度可以剪也可以不剪。
     * 如果不剪的话长度乘积即为j * (i - j)；如果剪的话长度乘积即为j * dp[i - j]。
     * 取两者最大值max(j * (i - j), j * dp[i - j])
     * 第一段长度j可以取的区间为[2,i)，对这个区间里面所有j不同的情况取最大值，因此最终dp[i]的转移方程为
     * dp[i] = max(dp[i], max(j * (i - j), j * dp[i - j]))
     * 最后返回dp[n]即可
     *
     * 作者：edelweisskoko
     * 链接：https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/jian-zhi-offer-14-i-jian-sheng-zi-huan-s-xopj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int cuttingRope2(int n) {
        int[] dp = new int[n + 1];//n+1只是为了返回dp[n]
        dp[2] = 1;
        for (int i = 3; i <= n; i++) {
            for (int j = 2 ; j < i; j++) {
                dp[i] = Math.max(dp[i],Math.max(j * (i - j),j * dp[i-j]));
                //Math.max(j * (i - j),j * dp[i-j]) 返回这次j里面最大的乘积，
                //Math.max(dp[i],β) 返回从2到i里面最大的乘积
            }
        }
        return dp[n];
    }

    /**
     * 贪心
     * 核心思路是：尽可能把绳子分成长度为3的小段，这样乘积最大 证明就是 1的思路
     *步骤如下：
     *
     * 如果 n == 2，返回1，如果 n == 3，返回2，两个可以合并成n小于4的时候返回n - 1
     * 如果 n == 4，返回4
     * 如果 n > 4，分成尽可能多的长度为3的小段，每次循环长度n减去3，乘积res乘以3；最后返回时乘以小于等于4的最后一小段
     * 以上2和3可以合并
     *
     * 作者：edelweisskoko
     * 链接：https://leetcode.cn/problems/jian-sheng-zi-lcof/solution/jian-zhi-offer-14-i-jian-sheng-zi-huan-s-xopj/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int cuttingRope3(int n) {
        if(n < 4){
            return n - 1;
        }
        int res = 1;
        while(n > 4){
            res *= 3;
            n -= 3;
        }
        return res * n;
    }
}
