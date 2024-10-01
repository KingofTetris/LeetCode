package LeetCode数据结构与算法基础.模拟;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2023/4/6 15:24
 * 给你一个整数 n ，以二进制字符串的形式返回该整数的 负二进制（base -2）表示。
 *
 * 注意，除非字符串就是 "0"，否则返回的字符串中不能含有前导零。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：n = 2
 * 输出："110"
 * 解释：(-2)^2 + (-2)^1 = 2
 * 示例 2：
 *
 * 输入：n = 3
 * 输出："111"
 * 解释：(-2)^2 + (-2)^1 + (-2)^0 = 3
 * 示例 3：
 *
 * 输入：n = 4
 * 输出："100"
 * 解释：(-2)^2 = 4
 *  
 *
 * 提示：
 *
 * 0 <= n <= 1e9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/convert-to-base-2
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 负二进制转换_2023_04_06 {

    @Test
    public void test(){
        String s = baseNeg2(54);
        System.out.println(s);
    }

    /**
     * 十进制转二进制的方法:除2取余，逆序排列。即除2取余法
     * 其实n进制换成m进制都是一样的。除基取余，逆序排列。
     * 但现在的问题是基数是负数。java中负数的余数如果不能整除，则还是负数
     * @param n
     * @return
     */
    public String baseNeg2(int n) {
       StringBuilder ans = new StringBuilder();
       while (n != 0){
           int remain = n % (-2);
           int quotient = n / (-2);
           /**
            * 所以为什么把商加1就能把remai从-1转化成1?
            * 原因在于，我们假设被除数是a 除数是b 余数是c
            * 那么 a = -2*b + c
            *     a + 2b = c
            *     a +2b + 2 = c + 2
            *     最后就有
            *     a + 2 * (b+1) = c + 2
            *     也就是说把余数从-1 改到 1
            *     那么就要把商 + 1.
            */
           ans.append(Math.abs(remain));//把-1转成1
           n = remain < 0 ? quotient + 1 : quotient;//如果余数是-1，把商+1，不然就正常算。
       }
       return ans.length() == 0 ? "0" : ans.reverse().toString();
    }
}
