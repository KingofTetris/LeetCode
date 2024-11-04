package LeetCode数据结构与算法基础.动态规划;

import java.util.Scanner;

/*
 * tasksStipend is a grid where each row represents the stipends for easy task and difficult task for a day.
 *
 * 史蒂夫正在某个公司实习，每天他都会被安排简单和困难两种任务，他可以选择接受其中一种，或者都不接受。
 * 选择困难的任务需要前一天未接受任何任务。
 * 现在给你一个tasks[][] 数组。
 * 每行表示每天给出的简单 困难任务的报酬
 * 请你给出史蒂夫的最大酬劳是多少。？
 */
public class 史蒂芬的最大酬劳_两个DP数组 {
    public static int maxSalary(int[][] tasks) {
        int n = tasks.length;
        int[] dp = new int[n];  //要么接任务
        int[] no_task = new int[n]; //要么不接任务
        dp[0] = Math.max(tasks[0][0], tasks[0][1]);//接取任务为简单或者困难的较大值。
        no_task[0] = 0; //不接任务 则无酬劳。
        for (int i = 1; i < n; i++) {
            //dp[i] = 昨天接了任务，则只能接简单的。 / 昨天没接任务则可以接取困难的
            dp[i] = Math.max(dp[i - 1] + tasks[i][0], no_task[i - 1] + tasks[i][1]);
            //不接任务，那么就是前提接任务的状态和不接任务状态的最大值。
            no_task[i] = Math.max(dp[i - 1], no_task[i - 1]);
        }
        //最后返回两者的较大值就可以了。
        return Math.max(dp[n - 1], no_task[n - 1]);
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // input for tasksStipend
        int tasksStipend_row = in.nextInt();
        int tasksStipend_col = in.nextInt();
        int[][] tasksStipend = new int[tasksStipend_row][tasksStipend_col];
        for (int idx = 0; idx < tasksStipend_row; idx++) {
            for (int jdx = 0; jdx < tasksStipend_col; jdx++) {
                tasksStipend[idx][jdx] = in.nextInt();
            }
        }
        int result = maxSalary(tasksStipend);
        System.out.print(result);
    }
}
