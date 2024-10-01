package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 丢失的数字_2021_11_6
 * @Time 2021/11/6  9:41
 *
 *
 * 给定一个包含 [0, n] 中 n 个数的数组 nums ，找出 [0, n] 这个范围内没有出现在数组中的那个数。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：nums = [3,0,1]
 * 输出：2
 * 解释：n = 3，因为有 3 个数字，所以所有的数字都在范围 [0,3] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 2：
 *
 * 输入：nums = [0,1]
 * 输出：2
 * 解释：n = 2，因为有 2 个数字，所以所有的数字都在范围 [0,2] 内。2 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 3：
 *
 * 输入：nums = [9,6,4,2,3,5,7,0,1]
 * 输出：8
 * 解释：n = 9，因为有 9 个数字，所以所有的数字都在范围 [0,9] 内。8 是丢失的数字，因为它没有出现在 nums 中。
 * 示例 4：
 *
 * 输入：nums = [0]
 * 输出：1
 * 解释：n = 1，因为有 1 个数字，所以所有的数字都在范围 [0,1] 内。1 是丢失的数字，因为它没有出现在 nums 中。
 *  
 *
 * 提示：
 *
 * n == nums.length
 * 1 <= n <= 104
 * 0 <= nums[i] <= n
 * nums 中的所有数字都 独一无二
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/missing-number
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 丢失的数字_2021_11_6 {

    @Test
    public void test() {
        int[] nums = {9,6,4,2,3,5,7,0,1};
        int i = missingNumber2(nums);
        System.out.println(i);
    }
    public int missingNumber(int[] nums) {
        int n = nums.length;
        if (n == 0) return 0;

        int[] newNum = new int[n+1];
        for (int i = 0; i < n; i++) {
            newNum[nums[i]] = 1;
        }

        for (int i = 0; i < n+1; i++) {
            if (newNum[i] != 1)
                return i;
        }

        return 0;
    }

    //异或，用0先把原数组n-1个数异或一遍
    // ,再和n个数异或一遍
    //异或的性质
    //0^x = x x^x = 0
    //A^B^C^A^C = (A^A)^(C^C)^B = 0^0^B = 0^B = B。
    public int missingNumber2(int[] nums) {
        int xor = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            xor ^= nums[i];
        }
        for (int i = 0; i <= n; i++) {
            xor ^= i;
        }
        return xor;
    }

}
