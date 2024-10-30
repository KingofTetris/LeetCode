package LeetCode数据结构与算法基础.常用工具;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */

//问你t次，每次输入一个整数n，问能否找到一个整数m使得，2<=m<=n,使得除了1 以外的gcd(n,m)是一个素数
public class gcd {
    //辗转求余法求最大公约数 递归就这一行 直接背下来。
    /**
     * 有个条件是必须大数放在a,小数放在b
     * @param a
     * @param b
     * @return
     */
    static public int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }
}
