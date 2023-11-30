package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */

import java.util.Arrays;

/**
 * 给你一个按 非递减顺序 排序的整数数组 nums，返回 每个数字的平方 组成的新数组，要求也按 非递减顺序 排序。
 *
 * 示例 1：
 *
 * 输入：nums = [-4,-1,0,3,10]
 * 输出：[0,1,9,16,100]
 * 解释：平方后，数组变为 [16,1,0,9,100]，排序后，数组变为 [0,1,9,16,100]
 * 示例 2：
 *
 * 输入：nums = [-7,-3,2,3,11]
 * 输出：[4,9,9,49,121]
 */
public class 有序数组的平方 {
    public static void main(String[] args) {
        int[] nums = {-7,-3,2,3,11};
        solution(nums);
    }

    //计算以后排序O(nlogn)。回家等通知
    //这题面试写法应该要思考如何提高时间复杂度

    //其实这道题的本意是双指针，从首尾出发去遍历数组
    //从大到小去填满数组。
    public static int[] solution(int[] nums){
        int n = nums.length;
        int[] res = new int[n];
        int head = 0,tail = n - 1;
        n--;
        while (head <= tail){
            int temp1 = nums[head] * nums[head];
            int temp2 = nums[tail] * nums[tail];
            if (temp1  > temp2){
                res[n] = temp1;
                head++;
            }else if (temp1  < temp2){
                res[n] = temp2;
                tail--;
            }else { //如果temp1 = temp2的时候比如-3和+3，那么你随便移动一个就行了。
                //这里选择移动head
                res[n] = temp1;
                head++;
            }
            n--;
        }
        System.out.println(Arrays.toString(res));
        return res;
    }

}
