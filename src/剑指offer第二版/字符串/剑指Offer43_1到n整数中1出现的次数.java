package 剑指offer第二版.字符串;

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
        System.out.println(countDigitOne2(1231232134));
    }

    /**
     *
     * 暴力法，把数字转化成字符串
     * 比较每个字符是不是'1'
     * 如果是'1',sum++
     * 824883294就TLE了。O(MN) M为数字长度
     * @param n
     * @return
     */
    public int countDigitOne(int n) {
        int count = 0;
        for (int i = 1; i <= n; i++) {
            String s = String.valueOf(i);
            char[] chars = s.toCharArray();
            for (char aChar : chars) {
                if (aChar - '1' == 0) {
                    count++;
                }
            }
        }
        return count;
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
     *
     * O(lgN) 快了都不知道多少
     * @param n
     * @return
     */
    public int countDigitOne2(int n) {
        //几个变量的计算: cur = n/bit%10, low = n % bit ,high = n / bit / 10;
        //几个情况
        // cur >1  => count = (high + 1) * bit
        // cur == 1 ==> count = (high * bit) + (low + 1)
        // cur = 0  ==> count = high * bit
        long bit = 1; //当数字很大时，int可能会溢出
        int oneCount = 0;
        while (bit <= n) {
            int cur = (int) (n / bit % 10);
            int low = (int) (n % bit);
            int high = (int) (n / bit / 10);
            //如果你忘了就去看上面的视频链接
            //对每一位分类讨论 current =1?>1?=0?
            if (cur > 1){
                //current >1 1的情况就是 (high+1)*bit
                oneCount += (high + 1) * bit;
            }
            if (cur == 1){
                //current = 1
                oneCount += (high * bit) + (low + 1);
            }
            if (cur == 0){
                oneCount += high * bit;
            }
            bit = bit * 10;
        }
        return oneCount;
    }
}
