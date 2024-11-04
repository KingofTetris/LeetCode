package LeetCode数据结构与算法基础.双指针与滑动窗口;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2023/11/19
 */
public class 最大连续1的个数III {

    @Test
    public void test(){
        int[] nums = {1,1,0,1,1,0,1,1,1,1,0,0};
        int i = maxOne(nums, 1);
        System.out.println(i);
    }

    //滑动窗口
    /**
     * 重点：题意转换。把「最多可以把 K 个 0 变成 1，
     * 求仅包含 1 的最长子数组的长度」转换为 「找出一个最长的子数组，该子数组内最多允许有 K 个 0 」。
     * 我们可知本题是求最大连续子区间，可以使用滑动窗口方法
     *
     * @param nums
     * @param k
     * @return
     */

    /**
     * 代码思路：
     *
     * 1.使用 left 和 right 两个指针，分别指向滑动窗口的左右边界。
     * 2.right 主动右移：right 指针每次移动一步。当 A[right] 为 0，说明滑动窗口内增加了一个 0；
     * 3.left 被动右移：判断此时窗口内 0 的个数，如果超过了 K，则 left 指针被迫右移，直至窗口内的 0 的个数小于等于 K 为止。
     * 4.滑动窗口长度的最大值就是所求。
     *
     * 作者：负雪明烛
     * 链接：https://leetcode.cn/problems/max-consecutive-ones-iii/solutions/609055/fen-xiang-hua-dong-chuang-kou-mo-ban-mia-f76z/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param A
     * @param K
     * @return
     */
    public int maxOne(int[] A,int K){
        int N = A.length;
        int res = 0;
        int left = 0, right = 0;
        int zeros = 0;
        while (right < N) {
            if (A[right] == 0)
                zeros++;
            while (zeros > K) {
                if (A[left++] == 0)
                    zeros--;
            }
            res = Math.max(res, right - left + 1);
            right++;
        }
        return res;
    }
}
