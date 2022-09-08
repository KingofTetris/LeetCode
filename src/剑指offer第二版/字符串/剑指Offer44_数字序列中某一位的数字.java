package 剑指offer第二版.字符串;

/**
 * @Author KingofTetris
 * @Date 2022/9/1 14:15
 * 数字以0123456789101112131415…的格式序列化到一个字符序列中。
 * 在这个序列中，第5位（从下标0开始计数）是5，第13位是1，第19位是4，等等。
 * 请写一个函数，求任意第n位对应的数字。
 * 示例 1：
 * 输入：n = 3
 * 输出：3
 * 示例 2：
 * 输入：n = 11
 * 输出：0
 * 限制：
 * 0 <= n < 2^31
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer44_数字序列中某一位的数字 {

    /**
     * 画表格找规律
     * https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solution/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
     * 1.确定在哪个区间 2.确定是哪个数 3.确定是哪一位
     * 确定区间，n - count 直到 n > count
     * 确定是区间内的哪个数 记为num 因为 0 不算入内 所以要 n -1
     * num = start + ( n - 1 ) / i
     * 确定是这个数的哪一位
     * index = ( n - 1 ) % i + 1
     * @param n
     * @return
     */
    public int findNthDigit(int n) {

        if (n == 0) return 0; //先判断边界情况

        long start = 1;//个十百千.......位 1,10,100,1000........相当于每1,2,3,4,...位数的起点
        int i = 1; //个位1个字符，百位2个字符,....,xx位xx个字符
        long count = 9; //表示某个区间有多少个字符，一开始在个位数 就是9个

        /**
         * 求出n是哪个区间
         */
        while (count < n){ //一直到 n 小于等于当前区间长度，说明n落在这个区间里面
            n = (int) (n - count);
            //位数增加，起点*10,位数++
            start = start * 10;
            i++;

            count = start * 9 * i ; //数位长度为i的数字范围中所有数字的字符总和，也就是下个区间长度
        }

        /**
         * 求出是哪个数num
         */
        int num = (int) (start + (n - 1) / i);
        /**
         * 求出是num的哪一位
         */
//        int index = (n - 1) % i  + 1; //如果去取余就需要 +1,从1开始。因为数字的第几位是从1开始。
        int index = (n - 1) % i  ; //如果用charAt的方法就不要+1,因为字符串从0开始

        // 除10取十位，除100取百位，余10取个位。
        //因为哪一位是从左往右算。所以用 i - index
        //比较特殊的是如果index = i 也就是最后一位
        // 那么就相当于/1 那就相当于没变，所以还要对10取余求出最后一位
//        return (num / (int) Math.pow(10,i - index) ) % 10;

        /**
         * 当然也可以转化成字符串返回
         */
        return String.valueOf(num).charAt(index) - '0';//不减去'0'就变成纯ASCII码了
    }
}
