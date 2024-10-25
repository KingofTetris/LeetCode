package LeetCode数据结构与算法基础.手撕算法;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 整数转罗马数字 {
    @Test
    public void test(){
        int nums = 3492;
        String s = intToRoman(nums);
        System.out.println(s);
    }

    //只考虑加法表示，不考虑减法。
    //一些特殊的值要进行映射。并且是从大到小排序
    //这里的数字都是-100，-10，-1获得的。
    int[] values = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
    String[] symbols = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
    public String intToRoman(int num) {
        //数字转罗马数字
        StringBuffer roman = new StringBuffer();
        for (int i = 0; i < values.length; ++i) {
            //从大到小遍历值
            int value = values[i];
            String symbol = symbols[i];
            //只要num还大于当前的value 就一直向后添加对应的字符。
            while (num >= value) {
                num -= value;
                roman.append(symbol);
            }
            if (num == 0) {
                break;
            }
        }
        return roman.toString();
    }
}
