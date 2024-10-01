package LeetCode数据结构与算法基础.贪心;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2022/10/13
 *
 *
 * 给定一个长度为 n 的整数数组 arr ，它表示在 [0, n - 1] 范围内的整数的排列。
 *
 * 我们将 arr 分割成若干 块 (即分区)，并对每个块单独排序。将它们连接起来后，
 * 使得连接的结果和按升序排序后的原数组相同。
 *
 * 返回数组能分成的最多块数量。
 *
 *
 *
 * 示例 1:
 *
 * 输入: arr = [4,3,2,1,0]
 * 输出: 1
 * 解释:
 * 将数组分成2块或者更多块，都无法得到所需的结果。
 * 例如，分成 [4, 3], [2, 1, 0] 的结果是 [3, 4, 0, 1, 2]，这不是有序的数组。
 * 示例 2:
 *
 * 输入: arr = [1,0,2,3,4]
 * 输出: 4
 * 解释:
 * 我们可以把它分成两块，例如 [1, 0], [2, 3, 4]。
 * 然而，分成 [1, 0], [2], [3], [4] 可以得到最多的块数。
 * 对每个块单独排序后，结果为 [0, 1], [2], [3], [4]
 *
 *
 * 提示:
 *
 * n == arr.length
 * 1 <= n <= 10
 * 0 <= arr[i] < n
 * arr 中每个元素都 不同
 */
public class 最多能完成排序的块_2022_10_13 {


    /**
     * 把数组分块后，把这些分块从小到大排序
     * 然后合并成一个数组保持升序
     * 返回尽可能多的块
     * @param arr
     * @return
     */

    /**
     * 思路:
     * eg:4 3 2 1 0
     *    0 1 2 3 4 排序后
     *
     *    1 0 2 3 4
     *    0 1 2 3 4 排序后
     *
     * 考虑前缀和
     * 如果前缀和一样就分成一块，不同就继续往后走
     *
     * 但其实由于题目的限制数组arr的值在[0,n-1]之间
     * arr排序后 arr[i] = i
     * 也就没必要再给arr排序了
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int arrSum = 0,sortSum = 0 ,res = 0;
        for (int i = 0; i < arr.length; i++) {
            arrSum += arr[i];
            sortSum += i;
            //如果相等重新置为0
            if (arrSum == sortSum){
                arrSum = 0;
                sortSum = 0;
                res++;//分块 + 1
            }
        }
        return res; //返回结果即可
    }


    /**
     * 官解最佳解法，没看懂
     * O(n) O(1)
     * @param arr
     * @return
     */
    public int maxChunksToSorted2(int[] arr){
        int m = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            m = Math.max(m, arr[i]);
            if (m == i) {
                res++;
            }
        }
        return res;
    }
}
