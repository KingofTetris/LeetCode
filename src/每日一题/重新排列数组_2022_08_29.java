package 每日一题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/8/29 10:59
 * 给你一个数组 nums ，数组中有 2n 个元素，按 [x1,x2,...,xn,y1,y2,...,yn] 的格式排列。
 * <p>
 * 请你将数组按 [x1,y1,x2,y2,...,xn,yn] 格式重新排列，返回重排后的数组。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：nums = [2,5,1,3,4,7], n = 3
 * 输出：[2,3,5,4,1,7]
 * 解释：由于 x1=2, x2=5, x3=1, y1=3, y2=4, y3=7 ，所以答案为 [2,3,5,4,1,7]
 * 示例 2：
 * <p>
 * 输入：nums = [1,2,3,4,4,3,2,1], n = 4
 * 输出：[1,4,2,3,3,2,4,1]
 * 示例 3：
 * <p>
 * 输入：nums = [1,1,2,2], n = 2
 * 输出：[1,2,1,2]
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 500
 * nums.length == 2n
 * 1 <= nums[i] <= 10^3
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shuffle-the-array
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 重新排列数组_2022_08_29 {

    @Test
    public void test() {
        int nums[] = {2, 5, 1, 3, 4, 7};
        int[] shuffle = shuffle2(nums, 3);
        for (int i = 0; i < shuffle.length; i++) {
            System.out.print(shuffle[i] + "\t");
        }
    }

    /**
     * 随便写的方法
     *
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle(int[] nums, int n) {
        int len = nums.length;
        int j = 0, k = 0;
        int[] left = new int[len / 2];
        int[] right = new int[len / 2];
        for (int i = 0; i < len / 2; i++) {
            left[i] = nums[i];
        }
        for (int i = len / 2; i < len; i++) {
            right[j] = nums[i];
            j++;
        }

        j = 0;
        for (int i = 0; i < len; i++) {
            if ((i & 1) != 1) { //偶数
                nums[i] = left[j];
                j++;
            }
            if ((i & 1) == 1) { //奇数
                nums[i] = right[k];
                k++;
            }
        }
        return nums;
    }


    /**
     * 借助临时空间，一次遍历
     * @param nums
     * @param n
     * @return
     */
    public int[] shuffle2(int[] nums, int n) {
        int ans[] = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            if ((i & 1) != 1) { //如果是偶数就是 前半部分 i/2
                ans[i] = nums[i / 2];
            } else { //奇数就加上length/2取后半部分
                ans[i] = nums[i / 2 + nums.length / 2];
            }
        }
        return ans;
    }
}
