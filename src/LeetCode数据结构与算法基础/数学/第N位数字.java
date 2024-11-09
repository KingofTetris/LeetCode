package LeetCode数据结构与算法基础.数学;

/**
 * @author by KingOfTetris
 * @date 2024/10/31
 */

//序列 1,2,3,4....,10,11,....,+∞
//问第n位数字是多少?
public class 第N位数字 {


    /**
     * 将 101112⋯ 中的每一位称为 数位 ，记为 n 。
     * 将 10,11,12,⋯ 称为 数字 ，记为 num 。
     * 数字 10 是一个两位数，称此数字的 位数 为 2 ，记为 digit 。
     * 每 digit 位数的起始数字（即：1,10,100,⋯），记为 start 。
     *
     *观察上表，可推出各 digit 下的数位数量 count 的计算公式：
     *
     * count=9×start×digit
     *
     * 根据以上分析，可将求解分为三步：
     *
     * 1.确定 n 所在 数字 的 位数 ，记为 digit 。是1位数，2位数，3位数，4位数 还是多少位数
     * 2.确定 n 所在的 数字 ，记为 num 。 具体是哪一个数字
     * 3.确定 n 是 num 中的哪一数位，并返回结果。  是数字中的哪一位
     *
     *
     * @param n
     * @return
     */
    public int findNthDigit(int n) {
        int digit = 1;
        long start = 1;
        long count = 9;
        while (n > count) { // 1.
            n -= count;
            start *= 10; // 1, 10, 100, ...
            digit += 1;  // 1,  2,  3, ...
            count = digit * start * 9; // 9, 180, 2700, ...
        }
        long num = start + (n - 1) / digit; // 2.
        return Long.toString(num).charAt((n - 1) % digit) - '0'; // 3.
    }

    /*作者：Krahets
    链接：https://leetcode.cn/problems/nth-digit/solutions/2362054/400-di-n-wei-shu-zi-qing-xi-tu-jie-by-jy-sz5y/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
