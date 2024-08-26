package 校招笔试真题.华为.华为od_0408;

/**
 * @author by KingOfTetris
 * @date 2023/4/13
 */

import java.util.*;

/**
 * 题目内容
 * 为了解决新学期学生暴涨的问题，塔子村要建所新学校。
 * 考虑到学生上学安全问题，需要所有学生家到学校距离最短。
 * 假设学校和所有的学生家，走在一条直线上。
 * 请问，学校要建在什么位置能使得学校到各个学生家的距离之和最短?
 *
 * 输入描述
 * 输入的第一行是一个整数，表示有N户家庭。
 *
 * 输入的第二行是一个数组，表示每户家庭的位置.
 *
 * 输出描述
 * 输出一行，一个整数，表示确定的学校位置。如有多个位置相同，则输出值最小的位置。
 *
 * 样例
 * 输入1
 * 5
 * 0 20 40 10 30
 * 输出1
 * 20
 *
 * 输入2
 * 1
 * 20
 * 输出2
 * 20
 *
 * 输入3
 * 2
 * 0 20
 * 输出3
 * 0
 *
 */
public class 新学校选址 {
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
        /**
         * 遍历所有两点之间的距离
         */
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
