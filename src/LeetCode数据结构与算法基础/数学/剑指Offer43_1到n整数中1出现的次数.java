package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/8/31 16:21
 * 输入一个整数 n ，求1～n这n个整数的十进制表示中1出现的次数。
 * 例如，输入12，1～12这些整数中包含1的数字有1、10、11和12，1一共出现了5次。
 * 示例 1：
 * 输入：n = 12
 * 输出：5
 * 示例 2：
 * 输入：n = 13
 * 输出：6
 * 限制：
 * 1 <= n < 2^31
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/1nzheng-shu-zhong-1chu-xian-de-ci-shu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer43_1到n整数中1出现的次数 {
    @Test
    public void test() {
        System.out.println(countDigitOne(1231232134));
    }

    /**
     * https://www.bilibili.com/video/BV19U4y1C7pt?spm_id_from=333.337.search-card.all.click&vd_source=299caa32bd4dc5f5ad17129611289250
     * 强行固定个十百....位(cur)上为1的时候，去计算会出现多少次1
     * 然后cur的前后部分记录成high和low
     * bit 从 1开始，每次/bit%10 就能取到n的个十百....位
     * 一直循环到bit*10 > n
     * cur = n / bit % 10
     * high = n / bit / 10
     * low = n % bit
     * 然后分成3种情况
     * cur > 1    count = (high + 1) * bit
     * cur = 1    count = (high * bit) + (low + 1)
     * cur = 0    count = high * bit
     * <p>
     * O(lgN) 快了都不知道多少
     *
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        //几个变量的计算: cur = n/bit%10, low = n % bit ,high = n / bit / 10;
        //几个情况
        // 拿501222举例子
        //百位上的2 high就是前面的501 low就是后面的22那么
        //固定百位为1，则有(000-501) * (00-99) 种情况百位为1，也就是(high+1) * (bit)
        // cur >1  => count = (high + 1) * bit

        //如果这个位数上本来就是1，例如千位上的1，那么 high =50,low=222
        //这个时候则有(00-49) * (000-999)
        //[50]不变 * (000-222)种情况
        //也就是 high * bit + low + 1
        // cur == 1 ==> count = (high * bit) + (low + 1)

        //最后如果位数上是0，例如万位上的0，high = 5,low = 1222
        //(0-4) * (000 - 999) 也就是high * bit

        // cur = 0  ==> count = high * bit
        long bit = 1; //当数字很大时，int可能会溢出
        int oneCount = 0;
        while (bit <= n) {
            int cur = (int) (n / bit % 10);
            int low = (int) (n % bit);
            int high = (int) (n / bit / 10);
            //如果你忘了就去看上面的视频链接
            //对每一位分类讨论 current =1?>1?=0?
            if (cur > 1) {
                //current >1 1的情况就是 (high+1)*bit
                oneCount += (high + 1) * bit;
            }
            if (cur == 1) {
                //current = 1
                oneCount += (high * bit) + (low + 1);
            }
            if (cur == 0) {
                oneCount += high * bit;
            }
            //每次计算完cur,bit * 10
            bit = bit * 10;
        }
        return oneCount;
    }
}
