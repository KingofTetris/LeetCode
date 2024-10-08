package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/8 16:10
 * 给你两个整数 n 和 k ，请你构造一个答案列表 answer ，
 * 该列表应当包含从 1 到 n 的 n 个不同正整数，并同时满足下述条件：
 *
 * 假设该列表是 answer = [a1, a2, a3, ... , an] ，
 * 那么列表 [|a1 - a2|, |a2 - a3|, |a3 - a4|, ... , |an-1 - an|] 中应该有且仅有 k 个不同整数。
 * 返回列表 answer 。如果存在多种答案，只需返回其中 任意一种 。
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

//    https://leetcode.cn/problems/beautiful-arrangement-ii/solutions/1809575/you-by-capital-worker-rnwi/
    public int[] constructArray(int n, int k) {
        int[] ans = new int[n];
        int i = 0;
        //p从小到大 q从大到小
        int p = 1, q = n;
        //构造前k个数组 k-1个不同的整数
        //奇数位从大到小，偶数位从小到大
        for (int j = 0; j < k; j++) {
            if (j % 2 == 0) {
                ans[i++] = p++;
            } else {
                ans[i++] = q--;
            }
        }
        //构造剩下的绝对值为1的序列
        if (k % 2 == 0) {
            //偶数时，降序
            while (i < n) {
                ans[i++] = q--;
            }
        } else {
            //奇数时，升序
            while (i < n) {
                ans[i++] = p++;
            }
        }
        return ans;

     /*   作者：京城打工人
        链接：https://leetcode.cn/problems/beautiful-arrangement-ii/solutions/1809575/you-by-capital-worker-rnwi/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

}
