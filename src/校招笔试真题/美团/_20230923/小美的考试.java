package 校招笔试真题.美团._20230923;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
public class 小美的考试 {

    //100
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //找出目前所有元素中的max和min
        //如果当前元素>max 或者 <min就成立
        int maxPre = nums[0];
        int minPre = nums[0];
        int res = 0;
        for (int i = 1; i < n; i++) {
            if (nums[i] > maxPre){
                maxPre = nums[i];
                res++;
            }
            else if(nums[i] < minPre){
                minPre = nums[i];
                res++;
            }
            //如果或者处于min和max之间就不管
        }
        System.out.println(res);
    }
}
