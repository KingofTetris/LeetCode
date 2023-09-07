package LeetCode数据结构基础.day2.字符串;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 两字符串相乘
 * @Time 2021/10/17  12:37
 */
/*给定两个以字符串形式表示的非负整数 num1 和 num2，
返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。

        示例 1:

        输入: num1 = "2", num2 = "3"
        输出: "6"
        示例 2:

        输入: num1 = "123", num2 = "456"
        输出: "56088"
        说明：

        num1 和 num2 的长度小于110。
        num1 和 num2 只包含数字 0-9。
        num1 和 num2 均不以零开头，除非是数字 0 本身。
        不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。*/


/**
 *              9   9   9
 *              6   7   8
 * ----------------------
 *              72  72  72
 *          63  63  63
 *      54  54  54
 * ----------------------
 *      54 117 189 135  72
 * ----------------------
 *      54 117 189 142   2
 * -----------------------
 *      54 117 203   2   2
 * -----------------------
 *      54 137   3   2   2
 * -----------------------
 *      67   7   3   2   2
 * -----------------------
 *   6   7   7   3   2   2
 */
public class 两字符串相乘 {
    @Test
    public void test() {
        System.out.println(multiply("999", "678"));
    }

    public String multiply(String num1, String num2) {
        //有一个为0 直接返回0
        if (num1.charAt(0) == '0' || num2.charAt(0) == '0') return "0";
        int len1 = num1.length(), len2 = num2.length();
        //res存储的是所有位相乘后 对应相加的结果，长度一定是len1+len2-1
        int[] res = new int[len1 + len2 - 1];
        //比如在例子中，这里的res就是54 117 189 135  72
        for (int i = 0; i < len2; i++)
            for (int j = 0; j < len1; j++)
                res[i + j] = res[i + j] + (num1.charAt(j) - '0') * (num2.charAt(i) - '0');
        //长度是len1+len2-1 最后一位当然是len1+len2-2 就是72
        //i>0 则最后res[0]是67 最后的数字根本没必要拆，直接加到字符串里面就行了。
        for (int i = len1 + len2 - 2; i > 0; i--) {
            res[i - 1] = res[i - 1] + res[i] / 10;
            res[i] = res[i] % 10;
        }

        StringBuilder sb = new StringBuilder();
        for (int i : res) sb.append(i);//实际上是这样加进去的67 7 3 2 2
        return sb.toString();//返回677322
    }
}
//可以玩玩BigInteger 一般用的更多的是BigDecimal,毕竟小数点才会有在现实中才有无限位。
//而小数点前面的数字有个二十三十位就已经不得了。
//    public String multiply(String num1, String num2) {
//        BigInteger b1 = new BigInteger(num1);
//        BigInteger b2 = new BigInteger(num2);
//        BigInteger num3 = b1.multiply(b2);
//        return num3.toString();
//    }
