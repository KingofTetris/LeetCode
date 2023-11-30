package LeetCode数据结构与算法基础.day2.字符串;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/10/19 10:37
 */
public class 字符串转换整数_atoi {


    @Test
    public void test() {
        String s = "  000000000000-123213";
        int i = myAtoi(s);
        System.out.println(i);
    }

    //整数
    public int myAtoi(String s) {
        int sign = 1;
        int res = 0; //res默认为0，包括只有一个正负号,空字符串的情况
        int m = s.length();
        int i = 0;
        //去掉所有前导空格
        while (i < m && s.charAt(i) == ' ') {
            i++;
        }
        int start = i;//从前导空格下一位继续

        for (; i < m; i++) {
            char c = s.charAt(i);
            //记录前导空格完了之后的一位是不是+ -号
            if (i == start && c == '+') {
                sign = 1;
            } else if (i == start && c == '-') {
                sign = -1;
            }
            //不是正负号就判断是不是数字
            else if (Character.isDigit(c)) {
                int num = c - '0';
                //判断越界的条件
                //每次乘以10，那你就算MAX/10是否小于res
                //还有就是res 刚好等于 MAX / 10,那你就要算 num 会不会大于 MAX % 10
                if (res > Integer.MAX_VALUE / 10 || (res == Integer.MAX_VALUE / 10 && num > Integer.MAX_VALUE % 10)) {
                    return Integer.MAX_VALUE;
                }
                //MIN也是同理
                if (res < Integer.MIN_VALUE / 10 || (res == Integer.MIN_VALUE / 10 && -num < Integer.MIN_VALUE % 10)) {
                    return Integer.MIN_VALUE;
                }
                //每次乘以10加上sign * num
                res = res * 10 + sign * num;
            }
            //如果不是数字就跳出
            else {
                break;
            }
        }
        return res;
    }
}
