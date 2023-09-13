package 校招笔试真题.百度;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/12
 */

public class 小度的K排列 {

    /**
     * 6 3
     * 3 2 4 5 6 1
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int k = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }
        //排列是指1-len的长度中，1-len每个数字都出现并且只出现一次！
        solution(nums, k);
    }

    private static void solution(int[] nums, int k) {
        //交换排列?
        isPL(nums, k);
        if (count == 0) {
            System.out.println("YES");
            System.out.println(0);
        }
        //如果不是，那就需要尝试交换。
        else {
            //缺少多个，1次是不可能交换成功的
            if (count > 1) {
                System.out.println("NO");
            }
            //如果只缺少一个
            if (count == 1) {
                //要交换的下标
                int v = 0;
                int u = 0;
                for (int i = 0; i < flags.length; i++) {
                    if (flags[i] == 0){
                        missingNum = i + 1;
                    }
                }
                for (int i = 0; i < k; i++) {
                    //找到1-k中那个大于k的数字
                    if (nums[i] > k) {
                        u = i + 1; //要交换的位置就是i + 1
                    }
                }
                //从k开始往后找
                for (int i = k; i < nums.length; i++) {
                    if (nums[i] == missingNum) {
                        v = i + 1; //要交换的位置就是i + 1
                    }
                }
                System.out.println("YES");
                System.out.println(1);
                System.out.println(u + " " + v);
            }
        }
    }

    static int count = 0;//统计缺少多少个排列，如果缺少2个以上，那么就不可能通过一次交换得到。
    static int missingNum;
    static int[] flags;

    private static void isPL(int[] nums, int len) {
        flags = new int[len];
        //原来的数字应该是1-len
        //记录是否出现
        for (int i = 0; i < len; i++) {
            //如果nums[i] 在1-len之间
            //那么把flags[nums[i] - 1]对应的位置标记为1.
            if (nums[i] <= len) {
                flags[nums[i] - 1]++;
            }
        }
        //flags[i] = 0,那么i+1就是缺少的数字
        for (int i = 0; i < flags.length; i++) {
            if (flags[i] == 0) {
                count++;
            }
        }
    }
}
