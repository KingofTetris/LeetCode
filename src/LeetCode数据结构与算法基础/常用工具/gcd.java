package LeetCode数据结构与算法基础.常用工具;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */

//问你t次，每次输入一个整数n，问能否找到一个整数m使得，2<=m<=n,使得除了1 以外的gcd(n,m)是一个素数
public class gcd {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        for (int i = 0; i < t; i++) {
            int n = sc.nextInt();
            //去找m
            for (int j = 2; j <= n; j++) {
                //最大公约数至少有一个1
                // 1一定是素数 那题目的意思是??
                int gcdNum = gcd(n, j);
                if (gcdNum != 1 && isPrime(gcdNum)){
                    System.out.println(j);
                    break;
                }
            }
        }
    }

    /**
     * 判断一个数是否是素数
     *
     * @param n
     * @return
     */
    static public boolean isPrime(int n) {
        for (int i = 2; i < Math.sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }
        //说明只有1和他自己。就是素数
        return true;
    }


    //辗转求余法求最大公约数 递归就这一行 直接背下来。
    static public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
