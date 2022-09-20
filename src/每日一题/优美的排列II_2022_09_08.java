package 每日一题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/8 16:10
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 *
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 3, k = 1
 * 输出：[1, 2, 3]
 * 解释：[1, 2, 3] 包含 3 个范围在 1-3 的不同整数，并且 [1, 1] 中有且仅有 1 个不同整数：1
 * 示例 2：
 *
 * 输入：n = 3, k = 2
 * 输出：[1, 3, 2]
 * 解释：[1, 3, 2] 包含 3 个范围在 1-3 的不同整数，并且 [2, 1] 中有且仅有 2 个不同整数：1 和 2
 *  
 *
 * 提示：
 *
 * 1 <= k < n <= 10^4
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/beautiful-arrangement-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 优美的排列II_2022_09_08 {

    @Test
    public void test(){
        int[] ints = constructArray(100, 94);
        int count = 0;
        for (int i = 0; i < ints.length; i++) {
            System.out.print(ints[i] + "\t");
            count++;
            if (count == 10){
                System.out.println();
                count = 0;
            }
        }
    }
    /**
     * 构造序列
     * 给定范围在 [1,n] 的 n 个数，当「直接升序/降序」排列时，相邻项差值为 1，仅一种；
     * 而从首位开始按照「升序」间隔排列，次位开始按照「降序」间隔排列（即排列为 [1, n, 2, n - 1, 3, ...]）时，
     * 相邻差值会从 n - 1 开始递减至 1，共 n - 1 种。
     *
     * 那么当我们需要构造 k 种序列时，我们可以先通过「直接升序」的方式构造出若干个 1，
     * 然后再通过「间隔位分别升降序」的方式构造出从 k 到 1 的差值，共 k 个。
     *
     * 显然，我们需要 k + 1 个数来构造出 k 个差值。
     * 因此我们可以先从 1 开始，使用 n - (k + 1)个数来直接升序（通过方式一构造出若干个 1），
     * 然后从 n - k开始间隔升序排列，按照 n 开始间隔降序排列，构造出剩下的序列。
     *
     * 作者：AC_OIer
     * 链接：https://leetcode.cn/problems/beautiful-arrangement-ii/solution/by-ac_oier-lyns/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param n
     * @param k
     * @return
     */
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int t = n - k - 1;
        for (int i = 0; i < t; i++) ans[i] = i + 1; //小于n - k -1时，就直接升序排序
        int a = n - k, b = n;//a从n-k开始升序 b从n开始降序
        for (int i = t;i < n; ) { //从 n - k 开始
            // 开始间隔排序
            ans[i++] = a++;
            if (i < n) ans[i++] = b--;
        }
        return ans;
    }

}
