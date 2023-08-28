package 校招笔试真题.华为.华为od_0408;

/**
 * @author by KingOfTetris
 * @date 2023/4/13
 */

import java.util.*;

public class 最佳放置位置 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        int best = best(nums);
        System.out.println(best);
    }

    //找到距离n个数最近之和最近的那个数
    //如果有多个数可以满足，则输出最小的
    //首先这个是个曼哈顿距离和最佳位置问题
    //可以证明解一定在某个学生的家
    public static int best(int[] nums) {
        LinkedList<Integer> res = new LinkedList<>();
        int minDistance = Integer.MAX_VALUE;
        for (int i = 0; i < nums.length; i++) {
            int distance = 0;
            for (int j = 0; j < nums.length; j++) {
                //要注意这里是绝对值相减，曼哈顿距离
                distance += Math.abs(nums[j] - nums[i]);
            }
            if (distance < minDistance) {
                minDistance = distance;
                res.clear();//如果有更小的先清空再添加
                res.add(nums[i]);
            }
            //如果相等就添加进去
            else if (distance == minDistance) {
                res.add(nums[i]);
            }
        }
        int best = Integer.MAX_VALUE;
        for (Integer i : res) {
            best = Math.min(best, i);
        }
        return best;
    }
}
