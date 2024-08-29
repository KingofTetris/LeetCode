package LeetCode数据结构与算法基础.day2.字符串;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/8/28
 */
public class 翻转字符串里的单词 {

    @Test
    public void test(){
        String s = "  !hello    world";
        String words = reverseWords(s);
        System.out.println(words);
    }

    /**
     * 这题的本意是不让你使用string自带的API，自己去实现相关功能。
     * 整体思路就是
     * 1.先去掉 前后空格，把中间多余的空格化成一个
     * 2.翻转整个字符串
     * 3.翻转每个单词
     * 4.返回结果即可。
     * @param s
     * @return
     */
    public String reverseWords(String s) {
        StringBuilder sb = trimSpaces(s);
        // 翻转字符串
        reverse(sb, 0, sb.length() - 1);
        // 翻转每个单词
        reverseEachWord(sb);
        return sb.toString();
    }

    //去掉首尾空格和中间多余的空格
    public StringBuilder trimSpaces(String s) {
        int left = 0, right = s.length() - 1;
        // 去掉字符串开头的空白字符
        while (left <= right && s.charAt(left) == ' ') {
            ++left;
        }

        // 去掉字符串末尾的空白字符
        while (left <= right && s.charAt(right) == ' ') {
            --right;
        }

        // 将字符串间多余的空白字符去除
        StringBuilder sb = new StringBuilder();
        while (left <= right) {
            char c = s.charAt(left);
            if (c != ' ') {
                sb.append(c);
            }
            else if (sb.charAt(sb.length() - 1) != ' ') {
                sb.append(c);
            }
            ++left;
        }
        return sb;
    }

    public void reverse(StringBuilder sb, int left, int right) {
        while (left < right) {
            char tmp = sb.charAt(left);
            sb.setCharAt(left++, sb.charAt(right));
            sb.setCharAt(right--, tmp);
        }
    }

    public void reverseEachWord(StringBuilder sb) {
        int n = sb.length();
        int start = 0, end = 0;
        while (start < n) {
            // 循环至单词的末尾
            while (end < n && sb.charAt(end) != ' ') {
                ++end;
            }
            // 翻转单词
            reverse(sb, start, end - 1);
            // 更新start，去找下一个单词
            start = end + 1;
            ++end;
        }
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/reverse-words-in-a-string/solutions/194450/fan-zhuan-zi-fu-chuan-li-de-dan-ci-by-leetcode-sol/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    /**
     * 字母之间可能有多个空格 正则表达式 \s+ 就表示1个或者多个空格
     * @param s
     * @return
     */
    public String reverseWords2(String s) {
        String sTrim = s.trim();
        //然后按照空格分割，不管有多少个空格。当一个处理
        String[] split = sTrim.split("\\s+"); // \s代表空格，+代表1次或多次
        StringBuilder sb = new StringBuilder();
        for (int i = split.length - 1; i >= 0;i--) {
            if (i > 0){
                sb.append(split[i]).append(" ");
            }
            else {
                sb.append(split[i]);
            }
        }
        return sb.toString();
    }

}
