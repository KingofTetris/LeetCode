package 剑指offer第二版.动态规划;

import java.util.Scanner;

/**
 * @Author KingofTetris
 * @Date 2022/7/12 18:05
 * 谈学姐开始在商汤科技工作了。为了使得自己的资产增长，谈学姐决定进行一些投资活动。
 * 这些投资活动的期限是1到3年，具有不同的收益率，
 * 不妨记，谈学姐工作i年后，资产总额是fi，其中f0表示谈学姐大学四年攒下来的钱。
 * 则有以下公式：
 * fi = 0,i<0
 * fi = f0,i = 0
 * fi = af_i-1 + bf_i-2 + cf_i-3 + 2i^2 - i + 32767
 * 谈学姐想了解自己工作 n 年以后的资产，你能帮她计算一下吗？
 * 由于答案可能很大，请对 1000000007 取模
 * 输入描述：第一行5个数，n,a,b,c,f0
 * 输出描述：一个整数ans
 *
 * eg: 输入：10 0 0 0 100 输出：32957
 *
 * 提示： 0<=n<=10^18,0<=a,b,c<=10,0<=f0<=9999;对于30%的数据 n <= 10^6
 *
 */
public class 矩阵快速幂应用_商汤科技笔试第三题 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt(),b = sc.nextInt(),c = sc.nextInt();
        int f0 = sc.nextInt();
        int d = 32767;//只是为了方便对齐，直接放进去太大坨。
        int[][] M = {
                {a, 1, 0, 0, 0, 0},
                {b, 0, 1, 0, 0, 0},
                {c, 0, 0, 0, 0, 0},
                {2, 0, 0, 1, 0, 0},
                {-1, 0, 0, 2, 1, 0},
                {d, 0, 0, 1, 1, 1}  //6 * 6
        };
        剑指Offer10_i_斐波那契数列 fib = new 剑指Offer10_i_斐波那契数列();
        int[][] res = fib.pow(M,n); //快速计算M的n次方 矩阵快速幂一定是方阵。
        int[][] F = { {f0,0,0,1,1,1} }; //1*6
//        System.out.println(F[0].length + " " + res.length);
        int[][] result = fib.multiply(F, res);
        System.out.println(result[0][0]);
    }
}
