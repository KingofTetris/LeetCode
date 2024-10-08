package LeetCode数据结构与算法基础.回溯;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/6
 */

//75%
public class 小友的供应商组合 {

    static List<List<Integer>> res = new LinkedList<>();
    static List<Integer> path = new LinkedList<>();
    static boolean[] used;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//供应商数量
        int k = sc.nextInt();//需要采购的货物
        int[] nums = new int[n];
        used = new boolean[n];

        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        //回溯
        backTracking(nums,k,0);

        /*for (List<Integer> re : res) {
            System.out.println(re);
        }*/
        System.out.println(res.size());
    }

    private static void backTracking(int[] nums, int target,int startIndex) {
        //停止条件
        if (path.size() == 3 && target == 0) {
            res.add(new LinkedList<>(path));
            return;
        }

        //单层回溯
        //求组合
        for (int i = startIndex; i < nums.length; i++) {
            //去重
            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]){
                continue;
            }
            used[i] = true;
            path.add(nums[i]);
            backTracking(nums, target - nums[i], i + 1);
            path.remove(path.size() - 1);
            used[i] = false;
        }
    }
}
