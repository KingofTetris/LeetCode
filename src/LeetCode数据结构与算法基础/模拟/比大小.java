package LeetCode数据结构与算法基础.模拟;

import java.util.*;

/**
 *
 * 塔子哥最近在做比大小的小学数学题目，每个题目可以表示为: a op b ( ) b op a。
 *
 * a, b 分别表示两个正整数。 op 表示一个数学意义上的运算符(包括加 + 减 - 乘 * 除 / 和乘方 ^ 运算)。
 *
 * ( ) 表示你需要填写的答案，是符号 =, <, > 其中的一个。
 *
 * 本题共有 t 组数据。
 *
 * 输入描述
 * 第 1 行，一个正整数 t (1 ≤ t ≤ 5 × 10^4) 表示数据的组数。
 *
 * 第 2 行到第 t+1 行，每行先给出 2 个正整数 a, b (1 ≤ a, b ≤ 10^9)，
 * 再给出一个符号 op 表示一组询问，其中 op 是 +、-、*、/、^ 中的一个。
 *
 * 输出描述
 * 输出 t 行，每行一个符号 ans 表示答案。
 *
 * 示例1
 * 输入
 *
 * 2
 * 1 1 +
 * 1 2 -
 *
 * 输出
 *
 * =
 * <
 *
 * 说明
 *
 * 1 + 1 = 1 + 1，所以第一个询问应该输出 =
 *
 * 1 - 2 < 2 - 1，所以第二个询问应该输出 <
 *
 * 示例2
 * 输入
 *
 * 3
 * 7 8 *
 * 3 2 /
 * 9 8 ^
 *
 * 输出
 *
 * =
 *
 * <
 *
 * 说明
 *
 * 7 * 8 = 8 * 7 = 56，所以第一个询问应该输出 =
 *
 * 3 / 2 > 2 / 3，所以第二个询问应该输出 >
 *
 * 9 ^ 8 < 8 ^ 9，所以第二个询问应该输出 <
 *
 * 第二题次方运算超过double，可以使用log的换底公式呀，唉，可惜
 */
public class 比大小 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            String s = in.next();
            if (s.equals("+") || s.equals("*")) System.out.println("=");
            if (s.equals("/") || s.equals("-")) {
                if (a == b) System.out.println("=");
                else if (a > b) System.out.println(">");
                else if (a < b) System.out.println("<");
            }
            if (s.equals("^")) {
                if (a == 1) {
                    if (b > 1) System.out.println("<");
                    else System.out.println("=");
                } else if (b == 1) {
                    System.out.println(">");
                } else {
                    double a_b = b * Math.log10(a);
                    double b_a = a * Math.log10(b);
                    if (a_b == b_a) System.out.println("=");
                    else if (a_b > b_a) System.out.println(">");
                    else if (b_a > a_b) System.out.println("<");
                }
            }

        }
    }

}
