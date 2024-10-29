package LeetCode数据结构与算法基础.数学;

/**
 * @author by KingOfTetris
 * @date 2024/10/29
 */

import java.util.Scanner;

/**
 * 定义为：每个数字的十进制表示中(0~9)，每个数位各不相同且各个数位之和等于N。注意每个数位不能重复！
 * 满足条件的数字可能很多，找到其中的最小值即可。 限制 N <= 45
 * <p>
 * eg 输入5
 * 可能的方案有 5,14,23,32,41 最小是5
 */
public class 和为N的最小数字 {
    public static void main(String[] args) {
        Scanner n = new Scanner(System.in);
        int N = n.nextInt();
        //首先最大的数字组合为987654321，和为45，所以大于45的数不存在数字组合。
        if (N > 45) {
            System.out.println(-1);
            return;
        }
        if (N < 10) {
            System.out.println(N);
            return;
        }
        int num = 0;
        int dig = 0;
        //从大到小遍历，尽量把大的数字放在后面，小的放在前面保证数字最小。
        for (int i = 9; i > 0; i--) {
            if (N != 0 && i <= N) {
                N -= i;
                num += (int) Math.pow(10, dig) * i;
                dig++;
            }
        }
        System.out.println(num);
    }

}
