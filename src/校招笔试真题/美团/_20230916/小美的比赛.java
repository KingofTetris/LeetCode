package 校招笔试真题.美团._20230916;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 *
 * 小美正在参加比赛，一共有 n 场比赛，赢得一场可以获得 1 分，
 * 如果上一场也赢了，可以获得额外的 1 分。现在给你 n 场比赛的结果，你需要计算小美的分数。
 *
 * 输入描述
 * 第一行一个整数n，表示比赛的场数。 下一行n个整数，表示每场比赛的结果，1 表示赢，0 表示输。 1 ≤ n ≤ 10 ^ 5
 *
 * 输出描述
 * 一个整数，表示小美的分数。
 * 示例
 * 输入：
 * 5
 * 1 1 1 0 1
 *
 * 输出：
 * 6
 *
 * 说明：
 * 第一场比赛获胜，获得1分。
 * 第二场比赛获胜，并且上场比赛也获胜，获得2分。
 * 第三次比赛获胜，并且上场比赛也获胜，获得2分。
 * 第四场比赛输了，获得0分。
 * 第五场比赛获胜，获得1分。
 *
 * 作者：code5bug
 * 链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
 * 来源：牛客网
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

    private static int solution(int[] a) {
        //第一次胜场只有1分，后面的连胜都得2分
        int n = a.length;
        int score = a[0]; //初始分数为score.
        for (int i = 1; i < n; i++) {
            if (a[i] == 1) { //后面的1，分数看前面是否为1，是的话+2，不然+1就可以了。
                //就这么简单。。
                score += (a[i - 1] == 1) ? 2 : 1;
            }
        }
        return score;
     /*   作者：code5bug
        链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
        来源：牛客网*/
    }

}
