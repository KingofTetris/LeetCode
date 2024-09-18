package 剑指offer第二版.字符串;

import org.junit.Test;

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


    @Test
    public void test(){
        int n = 1923;
        int nthDigit = findNthDigit(n);
        System.out.println(nthDigit);
    }
    /**
     * 画表格找规律
     * https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solution/mian-shi-ti-44-shu-zi-xu-lie-zhong-mou-yi-wei-de-6/
     *
     *
     * 第一步，我们得找到 n 属于哪个数位里的索引。比如 n = 5，那 n 就是个位这个数位里的索引；
     * 或者 n = 11，那 n 就是十位这个数位里的索引。
     *
     * 第二步，确定了 n 属于哪个数位，我们需要进一步定位到 n 具体属于哪个数。比如 n = 11，指的就是 10 这个数。
     *
     * 第三步，确定了 n 属于哪个数，我们就需要算出 n 是这个数的第几位，从而得到最终答案。比如 n = 11，
     * 指的是 10 这个数的第 1 位（索引从 0 开始），从而最终答案就是 0。
     *
     * 作者：superkakayong
     * 链接：https://leetcode.cn/problems/shu-zi-xu-lie-zhong-mou-yi-wei-de-shu-zi-lcof/solutions/473940/zi-jie-ti-ku-jian-44-zhong-deng-shu-zi-xu-lie-zhon/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param n
     * @return
     */
    public int findNthDigit(int n) {

        if (n == 0) return 0; //先判断边界情况

        long start = 1;//个十百千.......位 start就是1,10,100,1000........相当于每1,2,3,4,...位数的起点
        int digits = 1; //个位(1-9),1个字符，十位(10-99),2个字符,百位(100-999),3个字符....,xx位xx个字符
        long count = 9; //表示某个区间有多少个字符，例如1-9就是9个，10-99则有180个，100-999则有2700个
        //个位是9，十位则是180个，百位900个，千位2700个
        //规律就出来了 count = start * 9 * digits

        /**
         * 求出n是哪个区间
         */
        while (count < n){ //一直到 n 小于等于当前区间长度，说明n落在这个区间里面
            n = (int) (n - count);
            //位数增加，起点*10,位数++
            start = start * 10;
            digits++;

            count = start * 9 * digits ; //数位长度为i的数字范围中所有数字的字符总和，也就是下个区间长度
        }

        // 上面的循环结束后：
        // digit 等于原始的 n 所属的数位；start 等于原始的 n 所属数位的数的起始点
        // count 等于原始的 n 所属数位的索引总个数（不重要了，下面不用）
        // n 等于在当前数位里的第 n - 1 个索引（索引从 0 开始算起）

        int num = (int) (start + (n - 1) / digits); // 算出原始的 n 到底对应哪个数字
        /**
         * 求出是num的哪一位
         */
        int remainder = (n - 1) % digits; // 余数就是原始的 n 是这个数字中的第几位

        // 除10取十位，除100取百位，余10取个位。
        //因为哪一位是从左往右算。所以用 digits - index
        //比较特殊的是如果index = digits 也就是最后一位
        // 那么就相当于/1 那就相当于没变，所以还要对10取余求出最后一位
//        return (num / (int) Math.pow(10,digits - index) ) % 10;

        /**
         * 当然也可以转化成字符串返回
         */
        return String.valueOf(num).charAt(remainder) - '0';//不减去'0'就变成纯ASCII码了
    }
}
