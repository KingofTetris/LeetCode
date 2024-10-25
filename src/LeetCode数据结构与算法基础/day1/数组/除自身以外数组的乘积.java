package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 除自身以外数组的乘积
 * @Time 2021/10/15  9:48
 */
/*给你一个长度为 n 的整数数组 nums，

//已经限制n>1 非空
其中 n > 1，返回输出数组 output ，其中 output[i] 等于 nums 中除 nums[i] 之外其余各元素的乘积。
        示例:

        输入: [1,2,3,4]
        输出: [24,12,8,6] 
        提示：题目数据保证数组之中任意元素的全部前缀元素和后缀（甚至是整个数组）的乘积都在 32 位整数范围内。
        说明: 请不要使用除法，且在 O(n) 时间复杂度内完成此题。
        进阶：
        你可以在常数空间复杂度内完成这个题目吗？（ 出于对空间复杂度分析的目的，输出数组不被视为额外空间。）*/

public class 除自身以外数组的乘积 {

    @Test
    public void test() {
        int[] num = {1, 2, 3, 4};
        int[] product = productExceptSelf(num);
        for (int i : product) {
            System.out.print(i + "\t");
        }
    }


    //简单的暴力法，时间复杂度O(n^2)超时。
    //能过19/24
/*    public int[] productExceptSelf(int[] nums) {
        int len = nums.length;
        int product[] = new int[len];
        for (int i = 0; i < len; i++) {
            int product_out = 1;
            for (int j = 0; j < len; j++) {
                if (i != j){
                   product_out = product_out * nums[j];
                }
            }
            product[i] = product_out;
        }
        return product;
    }*/


    //怎么改进到O(n) 一次遍历
    //用nums[i]把数组分割为前后两段，分别求前后两段的积
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int pre_product[] = new int[n];
        int post_product[] = new int[n];
        int ans[] = new int[n];

        //第一个数左侧没有数，乘积设为1,直接等于1*后缀之积
        pre_product[0] = 1;
        //反过来最后一个数右侧没有数，直接1*前缀之积
        post_product[n - 1] = 1;

        //pre_product[i] 实际上就是每个数的前缀之积
        for (int i = 1; i < n; i++) {
            pre_product[i] = nums[i - 1] * pre_product[i - 1];
        }

        //post_product[i] 就是每个数的后缀之积
        // 小细节i>=0,不然第一个数没被赋值到，就为0
        for (int i = n - 2; i >= 0; i--) {
            post_product[i] = nums[i + 1] * post_product[i + 1];
        }

        //计算完每个数的前后缀积就可以直接相乘得到答案了。
        for (int i = 0; i < n; i++) {
            ans[i] = pre_product[i] * post_product[i];
        }

        return ans;
    }
}
