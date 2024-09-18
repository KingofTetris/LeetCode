package 剑指offer第二版.字符串;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 16:23
 * 字符串的左旋转操作是把字符串前面的若干个字符转移到字符串的尾部。请定义一个函数实现字符串左旋转操作的功能。比如，输入字符串"abcdefg"和数字2，该函数将返回左旋转两位得到的结果"cdefgab"。
 *
 * 示例 1：
 *
 * 输入: s = "abcdefg", k = 2
 * 输出: "cdefgab"
 * 示例 2：
 *
 * 输入: s = "lrloseumgh", k = 6
 * 输出: "umghlrlose"
 *  
 *
 * 限制：
 *
 * 1 <= k < s.length <= 10000
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zuo-xuan-zhuan-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer58_II_左旋转字符串 {
    @Test
    public void test(){
        String s = "helloworld";
        int n = 3;
        String reverseLeftWords = reverseLeftWords(s, n);
        System.out.println(reverseLeftWords);
    }
    public String reverseLeftWords(String s, int n) {
        if (s == null || s.length() == 0) {
            return s;
        }
        char[] ch = s.toCharArray();

        //注意：前后全就是左移，后前全就是右移。
        reverse(ch,0,n - 1);
        reverse(ch,n, s.length() - 1);
        reverse(ch,0,s.length() - 1);
        return new String(ch);
    }

    /**
     * 逆序调整start到end之间的元素顺序
     * 数组元素交换才能写到函数里面
     */
    public void reverse(char[] ch, int start, int end) {
        if (start >= end) {
            return;
        }
        while (start < end) { //核心就是一直交换。
            char tmp = ch[start];
            ch[start] = ch[end];
            ch[end] = tmp;
            start++;
            end--;
        }
    }

}
