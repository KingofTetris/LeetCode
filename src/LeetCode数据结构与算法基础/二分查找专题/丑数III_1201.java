package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2023/3/27 15:45
 * 这题和第N个神奇数字其实是一样的。和49_丑数并没有什么关系
 * <p>
 * 给你四个整数：n 、a 、b 、c ，请你设计一个算法来找出第 n 个丑数。
 * <p>
 * 丑数是可以被 a 或 b 或 c 整除的 正整数 。或者说是ka,tb,lc。
 */
public class 丑数III_1201 {
    @Test
    public void test(){
        int n = 10,a = 5,b = 4,c = 3;
        int nthUglyNumber = nthUglyNumber(n, a, b, c);
        System.out.println(nthUglyNumber);
    }

    /**前置知识： [1,i]中能够被a整除的数个数为 i/a。
    那么[1,i]中能够被a或b或c整除的数个数是否=i/a+i/b+i/c？,
    显然不等于，因为其中有些数即能被a，b和c整除。因此，我们要考虑如何去除重复计算的数。

     容斥原理:
     假设班里有10个学生喜欢数学， 15个学生喜欢语文， 21个学生喜欢编程，
     班里至少喜欢一门学科的有多少个学生呢？是10 + 15 + 21个吗？
     不是的，因为有些学生可能同时喜欢数学和语文，或者语文和编程，甚至还有可能三者都喜欢.

     我们定义喜欢数学的学生为A集合，喜欢数学的学生为B集合，喜欢编程的为C集合。
     那么学生总数为∣A∪B∪C∣,如果直接将三个集合的个数相加即∣A∣+∣B∣+∣C∣会有重复个数，
     因此需要扣掉一些元素即∣A∩B∣,∣A∩C∣,∣B∩C∣,但是这时候我们发现∣A∩B∩C∣这部分又会被多扣一次，
     所以最后再加上这一部分。
     ∣A∪B∪C∣=∣A∣+∣B∣+∣C∣−∣A∩B∣−∣A∩C∣−∣B∩C∣+∣A∩B∩C∣

     那么
     [1,i]中能被a或b或c整除的数的个数=i/a + i/b + i/c −i/lcm(ab) −i/lcm(ac) −i/lcm(bc) +i/lcm(abc)。
     其中i/ab代表能被a和b整除的数，其他同理。


     作者：Tizzi
     链接：https://leetcode.cn/problems/ugly-number-iii/solutions/2003797/javac-rong-chi-yuan-li-er-fen-cha-zhao-b-bf69/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
    **/


    /**
     * 如何利用以上信息计算答案呢?
     * 题目需要返回第n个丑数，由于n很大，直接暴力求解显然不行。但是其实明白了上面的信息后，
     * 很容易就可以想到二分法来求解答案。
     *
     * 可以将题目转化为：在[1,i]中求解能被a或b或c整除的数 个数之和大于等于n的最小的i即是我们的答案。
     *
     * 显然对于答案x来说，[1,x−1]必然是不满足要求，而[x,INF]都能够满足要求。
     * 那么就可以使用二分法来寻找这个问题的边界,左边界为1，右边界为无穷大。
     *
     * 作者：Tizzi
     * 链接：https://leetcode.cn/problems/ugly-number-iii/solutions/2003797/javac-rong-chi-yuan-li-er-fen-cha-zhao-b-bf69/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */

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
     *
     * @param mid
     * @param n
     * @param a
     * @param b
     * @param c
     * @return
     */
    boolean check(int mid, int n, int a, int b, int c) {
        long ab = lcm(a, b), ac = lcm(a, c), bc = lcm(b, c), abc = lcm(lcm(a, b), c);
        long cnt = (long) mid / a + mid / b + mid / c
                - mid / ac - mid / bc - mid / ab
                + mid / abc;
        return cnt >= n;
    }


    //最大公约数
    long gcd(long a, long b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    //最小公倍数
    long lcm(long a, long b) {
        return a / gcd(a, b) * b;
    }
}
