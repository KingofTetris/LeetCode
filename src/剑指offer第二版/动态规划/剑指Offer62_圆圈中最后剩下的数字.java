package 剑指offer第二版.动态规划;

import java.util.ArrayList;

/**
 * @Author KingofTetris
 * @Date 2022/9/16 10:43
 * 0,1,···,n-1这n个数字排成一个圆圈，从数字0开始，每次从这个圆圈里删除第m个数字（删除后从下一个数字开始计数）。
 * 求出这个圆圈里剩下的最后一个数字。
 *
 * 例如，0、1、2、3、4这5个数字组成一个圆圈，
 * 从数字0开始每次删除第3个数字，则删除的前4个数字依次是2、0、4、1，因此最后剩下的数字是3。
 *
 * 示例 1：
 *
 * 输入: n = 5, m = 3
 * 输出: 3
 * 示例 2：
 *
 * 输入: n = 10, m = 17
 * 输出: 2
 * 限制：
 *
 * 1 <= n <= 10^5
 * 1 <= m <= 10^6
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer62_圆圈中最后剩下的数字 {

    /**
     * 0 - n-1
     * 链表模拟法
     * 数据量大了就会TLE
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining(int n, int m) {
        //虽然有大量的删除操作，但是用LinkedList会超时。ArrayList反而不会？
        ArrayList<Integer> list = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            list.add(i);
        }
        int idx = 0;
        while (n > 1) {
            /**
             * 假设当前删除的位置是 idx，下一个删除的数字的位置是 idx + m
             * 但是，由于把当前位置的数字删除了，后面的数字会前移一位，所以实际的下一个位置是 idx + m - 1
             * 由于数到末尾会从头继续数，所以最后取模一下，就是 (idx + m - 1) mod n。
             *
             * 作者：sweetieeyi
             * 链接：https://leetcode.cn/problems/yuan-quan-zhong-zui-hou-sheng-xia-de-shu-zi-lcof/solution/javajie-jue-yue-se-fu-huan-wen-ti-gao-su-ni-wei-sh/
             * 来源：力扣（LeetCode）
             * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
             */
            idx = (idx + m - 1) % n;
            list.remove(idx);//删掉当前元素
            n--;//长度减一
        }
        return list.get(0);
    }

    /**
     * 公式法
     *                     f(n,m) = 0                  , n = 1
     *                            = [f(n-1,m) + m] % n, n >1
     * @param n
     * @param m
     * @return
     */
    public int lastRemaining2(int n, int m) {
        //实际上就是动态规划，不过从始至终只需要要给position变化
        //所以就没用额外的dp数组

        int position = 0;//最终活下来的人的初始位置 相当与dp[1] = 0
        for (int i = 2; i <= n; i++) {
            position = (position + m) % i; //每次去算dp[i] 一直到dp[n]
        }
        return position;//返回dp[n]即可
    }
}
