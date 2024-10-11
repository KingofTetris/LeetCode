package 校招笔试真题.携程._20241010;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/10
 */
public class 游游的最大GCD_回溯超时 {

    static List<List<List<Integer>>> result = new ArrayList<>();
    static List<List<Integer>> current = new ArrayList<>();
    static int max = -1;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }

        //分割成连续的m组，求最大gcd之和
        backTracking(nums, m, 0);

//        System.out.println(result);
        System.out.println(max);
    }

    private static void backTracking(int[] nums, int m, int start) {
        if (m == 0 && start == nums.length) {
            int maxTemp = 0;
            for (List<Integer> list : current) {
                if (list.size() == 1) {
                    maxTemp += list.get(0);
                    continue;
                }
                //如果是多个
                int temp = list.get(0);
                for (int i = 1; i < list.size(); i++) {
                    temp = gcd(temp,list.get(i));
                }
                maxTemp += temp;
            }
//            System.out.println(maxTemp);
            //每有一个current就统计一次max
            max = Math.max(max,maxTemp);
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < nums.length; i++) {
            List<Integer> subarray = new ArrayList<>();
            for (int j = start; j <= i; j++) {
                subarray.add(nums[j]);
            }
            current.add(subarray);
            backTracking(nums, m - 1, i + 1);
            current.remove(current.size() - 1);
        }
    }

    //辗转求余法求最大公约数 递归就这一行
    static public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
