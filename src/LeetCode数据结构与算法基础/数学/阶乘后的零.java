package LeetCode数据结构与算法基础.数学;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/10/30
 */
public class 阶乘后的零 {
    //其实就是找2和5因子的较小者。
    //因为这个是阶乘，那么5因子一定是少于2因子的。

    /**
     * 2出现的频率肯定是高于5的，因为:
     *
     * 每隔 2 个数就会包含因子2，比如2,4,6,..，
     * 而每个 5 个数才会出现一个包含因子5的数，比如5,10,15,..
     *
     * 5,10,15,20,25,30,...,125
     * 里面比较特殊的是
     * 5 = 5
     * 25 = 5 * 5 //5的幂次 会出现多个5 而不是一个5。 这里是5个5
     * 125 = 5 * 5 * 5 // 这里是25个5。
     *
     * 那么要计算n!里面出现了多少个包含5的因子就是。n/5+n/(5∗5)+n/(5∗5∗5)+...
     * @param n
     * @return
     */
    public int trailingZeroes(int n) {
        int zeros = 0;
        int interval = 5;
        while (n >= interval) {
            zeros += n / interval; //累加
            interval *= 5; //间隔*5
        }
        return zeros;
      /*  作者：howard
        链接：https://leetcode.cn/problems/factorial-trailing-zeroes/solutions/2588800/da-bai-hua-jiang-jie-zhao-gui-lu-si-lu-b-tp84/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }

    @Test
    public void test(){
        int n = 25;
        int zeroes = trailingZeroes(n);
        System.out.println(zeroes);
    }

}
