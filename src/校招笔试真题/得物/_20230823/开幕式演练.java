package 校招笔试真题.得物._20230823;

import java.util.Arrays;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */
public class 开幕式演练 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
                int count = in.nextInt();
                int nums[] = new int[count];
                for (int i = 0; i < nums.length; i++) {
                    nums[i] = in.nextInt();
                }
                if (count == 2) {
                    System.out.println(Math.abs(nums[0] - nums[1]));
                } else {
                    Arrays.sort(nums);
                    int max = 0;
                    //为什么排序完 是 nums[i] - nums[i-2]
                    //其实这就自动把排序数组分成了左右两组
                    //比如排序完是 5 5 6 7 8 10
//                    那么按照下面的算法
                    //左边:5 6 8         右边: 10 7 5
                    //合在一起其实就是5 6 8 10 7 5
                    //自动把最大的放中间，其他按顺序放两边。
                    for (int i = 2; i < count; i++) {
                        max = Math.max(max, nums[i] - nums[i - 2]);
            }
            System.out.println(max);
        }
    }
}
