package 校招笔试真题.用友._20240906;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/6
 */


//83.33 还有一个用例不对

public class 小友打怪_lc原题055_跳跃游戏_代码随想录的贪心部分就有_让你不刷 {

    public static boolean res;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
//        System.out.println(s);
        String[] s1 = s.split(" ");
        int n = s1.length;
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = Integer.parseInt(s1[i]);
        }

        //只要能达到最后一关就是true
        if (nums[0] == 0) {
            System.out.println(false);
            return;
        }
        dfs(nums,nums[0],0);
        System.out.println(res);
    }

    private static void dfs(int[] nums, int curEnergy,int nowIndex) {
        //如果能跳到n - 1，那么就可以结束了
        if (nowIndex >= nums.length - 1){
            res = true;
            return;
        }
        //比如curE 至少是 1，那么从1到1去遍历所有nums[i]
        for (int i = nowIndex + 1; i <= nowIndex + curEnergy && i < nums.length; i++) {
              dfs(nums,nums[i],i);
        }
    }
}
