package LeetCode数据结构与算法基础.DFS与BFS;

/**
 * @author by KingOfTetris
 * @date 2024/10/29
 */

import java.util.Scanner;

/**
 * 【多多的数字集合】多多君最近在研究无限数字集合，其中一种生成无限数字集合的方式是:
 * 初始状态集合中只有一个种子元素A。对于集合中的每个元素X，有X+B也属于该
 * 集合。
 * 对于集合中的每个元素X，有X*C也属于该集合。
 * 多多君想知道，对于给定的参数A、B和C，数字Q是否属于该集合。
 * 第一行，一个整数T，表示测试用例的组数。(1<=T<= 100)
 * 对于每组测试用例，分别各一行，每行四个整数:A，B，C和Q，分别代表无限集合的参数
 * 和需要进行判断的数字。(1<= A,B,C,Q<= 1.000,000.000)
 * <p>
 * <p>
 * 输入描述：
 * <p>
 * T // 测试用例数,[1, 100]
 * A B C Q // 四个数，[1, 1e9]
 * 输出描述：
 * <p>
 * // 每个用例输出一个结果，属于输出1，否则输出0
 * <p>
 * 输入：
 * <p>
 * 3
 * 1 2 3 5
 * 2 3 2 10
 * 3 3 3 7
 * 输出：
 * <p>
 * 1  // 5 = 1*3+2
 * 1  // 10 = (2+3)*2
 * 0
 */
public class 多多的无限数字集合 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        long T, A, B, C, Q;
        T = scanner.nextLong();
        while (T-- > 0) {
            A = scanner.nextLong(); //种子元素
            B = scanner.nextLong();
            C = scanner.nextLong();
            Q = scanner.nextLong();
            // x+b,x*c 都属于该集合，显然是dfs
            // *法比较特殊，如果c=1,那么相当于就没有乘法了。
            if (C == 1) {
                if ((Q - A) % B == 0) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            }
            //如果c不等于0，就需要尝试去*c了
            else {
                long flag = 0;
                long t = A;
                while (t <= Q) {
                    //如果t % B == Q % B 说明t 和 Q都可以跳过x*c + b得到
                    if (t % B == Q % B) {
                        flag = 1;
                        break;
                    }
                    t *= C;
                }
                //如果t > Q就不用再找了，不可能的
                if (flag == 1) {
                    System.out.println("1");
                } else {
                    System.out.println("0");
                }
            }
        }
        scanner.close();
    }
}
