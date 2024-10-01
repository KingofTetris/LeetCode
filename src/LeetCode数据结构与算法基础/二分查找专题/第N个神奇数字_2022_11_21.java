package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @Author KingofTetris
 * @Date 2022/11/22 10:06
 */

/**
 * 相似题目 丑数III
 * 找到能被a,b,c其中一个或多个整除的第n个数的大小
 */
public class 第N个神奇数字_2022_11_21 {
    static final int MOD = 1000000007;
    public int nthMagicalNumber(int n,int a,int b){
        long l = Math.min(a,b);
        long r = (long) n * Math.min(a,b);
        int c = lcm(a,b); //求最小公倍数

        //中止条件是 l > r
        while (l <= r){
            long mid = (l + r) / 2;
            //f(x) = 下取整(x/a)+下取整(x/b)-下取整(x/c) 即x是能被a或b整除的第几个数，c是a,b的最小公倍数
            long cnt = mid / a + mid / b - mid / c;
            //不能在cnt==n的时候直接返回x的原因是什么？
            if (cnt >= n){
                r = mid - 1;
            }
            else {
                l = mid + 1;
            }
        }
        return (int) ((r+1) % MOD);
    }


    //求最小公倍数
    //直接两数相乘除以最大公因数
    private int lcm(int a, int b) {
        return a * b / gcd(a,b);
    }


    //求最大公因数
    public  int gcd(int a,int b){
        //辗转求余法
        return b !=0 ? gcd(b,a%b) : a;
    }
}
