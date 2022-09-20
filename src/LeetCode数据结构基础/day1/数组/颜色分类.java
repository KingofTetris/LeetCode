package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 颜色分类
 * @Time 2021/10/11  15:55
 */


/*75. 颜色分类
        给定一个包含红色、白色和蓝色，一共 n 个元素的数组，原地对它们进行排序，
        使得相同颜色的元素相邻，并按照红色、白色、蓝色顺序排列。

        此题中，我们使用整数 0、 1 和 2 分别表示红色、白色和蓝色。
         

        示例 1：

        输入：nums = [2,0,2,1,1,0]
        输出：[0,0,1,1,2,2]
        示例 2：

        输入：nums = [2,0,1]
        输出：[0,1,2]
        示例 3：

        输入：nums = [0]
        输出：[0]
        示例 4：

        输入：nums = [1]
        输出：[1]
         

        提示：

        n == nums.length
        1 <= n <= 300
        nums[i] 为 0、1 或 2
         

        进阶：

        你可以不使用代码库中的排序函数来解决这道题吗？
        你能想出一个仅使用常数空间的一趟扫描算法吗？



        */


public class 颜色分类 {
    @Test
    public void test(){

    }

    //最简单就是直接调用sort那就没意义了。
    //本质就是排个序

    //双指针
    public void sortColors(int[] nums) {
        int n = nums.length;

        //经典一头一尾
        int p0 = 0, p2 = n - 1;
        for (int i = 0; i <= p2; ++i) {

            //结束条件i>p2 或者 nums[i] != 2


            //把前面的2都换到后面去 然后p2-- 换过的2就不动了
            while (i <= p2 && nums[i] == 2) {
                int temp = nums[i];
                nums[i] = nums[p2];
                nums[p2] = temp;
                --p2;
            }

            //如果为0都换到前面来 然后p0++，中间剩下的自然是1
            if (nums[i] == 0) {
                int temp = nums[i];
                nums[i] = nums[p0];
                nums[p0] = temp;
                ++p0;
            }
        }
    }
}
