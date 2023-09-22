package 校招笔试真题.BOSS直聘._20230919;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/19
 */
public class 最小公倍数 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        System.out.println(lcm(a, b));
    }

    //最小公倍数lcm
    //最大公因数gcd
    //lcm = (a * b) / gcd
    static long gcd(int a, int b) {
        //辗转求余法，求最大公因数
        return b == 0 ? a : gcd(b, a % b);
    }
    //lcm = (a * b) / gcd
    static long lcm(int a, int b) {
        return ((long) a * b) / gcd(a, b);
    }
}
