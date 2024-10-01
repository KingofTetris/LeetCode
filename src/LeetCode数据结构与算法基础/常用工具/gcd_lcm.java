package LeetCode数据结构与算法基础.常用工具;

/**
 * @author by KingOfTetris
 * @date 2024/9/30
 */
public class gcd_lcm {

    //最小公倍数lcm
    //最大公因数gcd
    //lcm = (a * b) / gcd
    //顺序无所谓
    static long gcd(int a, int b) {
        //辗转求余法，求最大公因数
        return b == 0 ? a : gcd(b, a % b);
    }
    //lcm = (a * b) / gcd
    static long lcm(int a, int b) {
        return ((long) a * b) / gcd(a, b);
    }
}
