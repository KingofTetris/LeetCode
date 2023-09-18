package 校招笔试真题.美团._20230916;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */
public class 小美的比赛 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        int res = solution(nums);
        System.out.println(res);
    }

    private static int solution(int[] nums) {
        //第一次胜场只有1分，后面的连胜都得2分
        int len = 0;
        int res = 0;
        int n = nums.length;
        for (int i = 0; i < n; i++) {
            while (i < n && nums[i] == 1) {
                i++;
                len++;
            }
            //每场都当2分，然后减去第一场的1分
            //要注意如果len==0，也就是遇到0，直接跳过就可以了。就没用必要-1了。
            if (len == 0){
                continue;
            }
            res += 2 * len - 1;
            len = 0;
        }
        return res;
    }

/*
    @Test
    public void test(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];

        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int res = 0;
        //就是找连续的1.。
        int len = 0;
        for (int i = 0; i < n;) {
            while (i < n && nums[i] == 1) {
                len++;
                i++;
            }
            res += 2 * len - 1;
            len = 0;
            if (i < n && nums[i] == 0) {
                i++;
            }
        }
        System.out.println(res);
    }
*/

}
