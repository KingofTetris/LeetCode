package 剑指offer第二版.动态规划;

/**
 * @author by KingOfTetris
 * @date 2022/7/18
 * 给你一根长度为 n 的绳子，请把绳子剪成整数长度的 m 段（m、n都是整数，n>1并且m>1），
 * 每段绳子的长度记为 k[0],k[1]...k[m - 1] 。请问 k[0]*k[1]*...*k[m - 1] 可能的最大乘积是多少？
 * 例如，当绳子的长度是8时，我们把它剪成长度分别为2、3、3的三段，此时得到的最大乘积是18。
 * 答案需要取模 1e9+7（1000000007），如计算初始结果为：1000000008，请返回 1。
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
 *
 * 提示：
 * 2 <= n <= 1000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer14_II_剪绳子 {
    final int MOD = 1000000007;
     /**
      * 和I相比唯一不同在于本题目涉及 “大数越界情况下的求余问题” 。
      * 大数越界：当 a 增大时，最后返回的 3^a大小以指数级别增长，可能超出 int32 甚至 int64 的取值范围，导致返回值错误。
      * 大数求余问题： 在仅使用 int32 类型存储的前提下，正确计算 x^a对 p 求余（即 x^a ⊙p ）的值。
      * 解决方案： 循环求余 、 快速幂求余 ，其中后者的时间复杂度更低
      *
      * 作者：jyd
      * 链接：https://leetcode.cn/problems/jian-sheng-zi-ii-lcof/solution/mian-shi-ti-14-ii-jian-sheng-zi-iitan-xin-er-fen-f/
      * 来源：力扣（LeetCode）
      * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int cuttingRope3(int n) {
        if(n < 4){
            return n - 1;
        }
        long res = 1; //注意res的类型是long,如果是int确实取模之前已经溢出
        while(n > 4){
            res  = res * 3 % MOD; //每次乘3都要取余
            n -= 3; //只要n>4就每次减掉3 为什么等于4不减 是因为如果减了最后一段就变成3+1
            //3*1=3 < 2*2=4
        }
        return (int) (res * n % MOD); //当n小于3了，还要是要乘以n,n可能是1，2，3，4
    }
}
