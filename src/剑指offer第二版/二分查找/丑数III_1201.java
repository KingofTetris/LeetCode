package 剑指offer第二版.二分查找;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2023/3/27 15:45
 * 这题和第N个神奇数字其实是一样的。和49_丑数并没有什么关系
 * <p>
 * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数。
 * <p>
 * 丑数是可以被 a 或 b 或 c 整除的 正整数 。
 */
public class 丑数III_1201 {
    public int nthUglyNumber(int n, int a, int b, int c) {
        int l = 1, r = Integer.MAX_VALUE;
        while (l < r) {
            int mid = l + (r - l) / 2;
            if (check(mid, n, a, b, c)) r = mid;
            else l = mid + 1;
        }
        //当r > l的时候，返回r即可
        return r;
    }


    /**
     * 计算从1到mid有多少个数字能被 a,b,c其中之一整除
     * 如果大于等于n，那么说明 mid+1 之后的数字都没必要算了 right = mid
     * 如果小于n 说明 [1,mid]这个区间中的丑数还不够。 那么应该提高mid的值扩大区间
     * 那么在right不变的情况下，就只能让left = mid + 1，才能提升mid的值
     * @param mid
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    boolean check(int mid, int n, int a, int b, int c) {
        long ab = lcm(a, b), ac = lcm(a, c), bc = lcm(b, c), abc = lcm(lcm(a, b), c);
        long cnt = (long) mid / a + mid / b + mid / c - mid / ac - mid / bc - mid / ab + mid / abc;
        return cnt >= n;
    }

    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }

    @Test
    public void test() {
        丑数III_1201 cs = new 丑数III_1201();
        int i = cs.nthUglyNumber(1, 2, 3, 4);
        System.out.println(i);
    }
}
