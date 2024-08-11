package 校招笔试真题.美团._20230916;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 *
 * 小美在训练场打靶，靶一共有 10 环，靶的中心位于 (0,0)，如果打中的位置在以靶心为圆心，
 * 半径为 1 的圆内，则得 10 分，之后每向外一圈分数减 1，直到 1 分，每圈的半径都比上一圈大 1。
 * 即如果打中的位置在以靶心为圆心，半径为i的圆内，则得 11 - i 分。
 *
 * 如果打中的位置不在任何一圈内，则得 0分。
 *
 * 输入描述
 * 一行两个整数 x，y，表示打中的位置坐标。
 *
 * 1 ≤ x,y ≤ 10
 *
 * 输出描述
 * 输出一个整数，表示得分。
 *
 * 示例1
 * 输入：
 * 1 0
 *
 * 输出：
 * 10
 * 示例2
 * 输入：
 * 1 1
 *
 * 输出：
 * 9
 *
 * 作者：code5bug
 * 链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
 * 来源：牛客网
 */

//从(0,0)向外扩张10环，内环是10分，每向外一个环-1。
    //不中靶0分，现在输入一个坐标，请你判断这个坐标的分数。
//其实就是简单的求x,y到原点的距离，看是第几环就可以了。
public class 小美打靶 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt(), y = in.nextInt();
        int dd = x * x + y * y; //到原点距离的平方。

        // 特别容易遗漏
        //如果dd == 0,那么res = 11。
        int res = dd == 0 ? 11 : 0;
        for (int i = 1; i < 12; i++) {
            //如果dd落在半径为i的圆内，则返回11 - i;
            if (dd <= i * i) {
                res = 11 - i;
                break;
            }
        }
        System.out.println(res);

     /*   作者：code5bug
        链接：https://www.nowcoder.com/discuss/534003042790780928?urlSource=sitemapApi
        来源：牛客网*/
    }
}
