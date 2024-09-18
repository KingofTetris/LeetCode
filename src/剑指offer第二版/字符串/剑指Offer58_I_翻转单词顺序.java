package 剑指offer第二版.字符串;

import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 15:55
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 * <p>
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer58_I_翻转单词顺序 {


    //顶级API应用

    /**
     * String API的一些常见方法的时间复杂度
     * split() 方法： 为 O(N) ；
     * trim() 和 strip() 方法： 最差情况下（当字符串全为空格时），为 O(N) ；
     * join() 方法： 为 O(N) ；
     * reverse() 方法： 为 O(N) ；
     * <p>
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/mian-shi-ti-58-i-fan-zhuan-dan-ci-shun-xu-shuang-z/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param s
     * @return
     */

    //直接调用API，回家等通知。
    public String reverseWords(String s) {
        String[] str = s.trim().split("\\s+");  //删除首尾的空格，然后分隔字符串
        StringBuilder sb = new StringBuilder();
        for (int j = str.length - 1; j >= 0; j--) {
            sb.append(str[j] + " ");
        }
        return sb.toString().trim();
    }

    //自己实现
    public String reverseMessage(String message) {
        message = message.trim();                               // 删除首尾空格
        int j = message.length() - 1, i = j;
        StringBuilder res = new StringBuilder();
        while (i >= 0) {
            while (i >= 0 && message.charAt(i) != ' ') i--;     // 搜索首个空格
            //搜到空格添加单词到res中。
            res.append(message.substring(i + 1, j + 1) + " ");  // 添加单词

            while (i >= 0 && message.charAt(i) == ' ') i--;     // 跳过单词间空格
            //j重新指向新单词的末尾
            j = i;                                              // j 指向下个单词的尾字符
        }
        return res.toString().trim();                           // 转化为字符串并返回
    }

/*    作者：Krahets
    链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/solutions/195224/mian-shi-ti-58-i-fan-zhuan-dan-ci-shun-xu-shuang-z/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

    @Test
    public void test() {
        String s = "a good   example";
        String reverseWords = reverseMessage(s);
        System.out.println(reverseWords);
    }
}
