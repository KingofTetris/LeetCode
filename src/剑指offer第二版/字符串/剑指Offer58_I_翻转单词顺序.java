package 剑指offer第二版.字符串;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @Author KingofTetris
 * @Date 2022/9/13 15:55
 * 输入一个英文句子，翻转句子中单词的顺序，但单词内字符的顺序不变。为简单起见，标点符号和普通字母一样处理。例如输入字符串"I am a student. "，则输出"student. a am I"。
 *
 *  
 *
 * 示例 1：
 *
 * 输入: "the sky is blue"
 * 输出: "blue is sky the"
 * 示例 2：
 *
 * 输入: "  hello world!  "
 * 输出: "world! hello"
 * 解释: 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 *
 * 输入: "a good   example"
 * 输出: "example good a"
 * 解释: 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 *
 * 说明：
 *
 * 无空格字符构成一个单词。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *
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
     *
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/mian-shi-ti-58-i-fan-zhuan-dan-ci-shun-xu-shuang-z/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String reverseWords2(String s){
        // 除去开头和末尾的空白字符
        s = s.trim();
        // 正则匹配连续的空白字符作为分隔符分割
        List<String> wordList = Arrays.asList(s.split("\\s+"));
        Collections.reverse(wordList);//Collections自带反转功能
        return String.join(" ", wordList);//给每个单词之间添加空格String.join(delimiter,words)
    }
    public String reverseWords(String s) {
        String s1 = s.trim().replaceAll("\\s+", " ");//去掉前后空格，再把多个空格都替换成1个
        String[] s2 = s1.split(" ");//按空格划分句子
        StringBuffer res = new StringBuffer();
        for (int i = s2.length - 1; i >= 0; i--) {
            res.append(s2[i]);
            if (i != 0){
                res.append(" ");//追加一个空格
            }
        }
        return res.toString();
    }

    /**
     * 双指针
     */
    class Solution {
        public String reverseWords(String s) {
            s = s.trim(); // 删除首尾空格
            int j = s.length() - 1, i = j;
            StringBuilder res = new StringBuilder();
            while(i >= 0) {
                while(i >= 0 && s.charAt(i) != ' ') i--; // 搜索首个空格
                res.append(s.substring(i + 1, j + 1) + " "); // 添加单词
                while(i >= 0 && s.charAt(i) == ' ') i--; // 跳过单词间空格
                j = i; // j重新指向新单词的末尾
            }
            return res.toString().trim(); // 转化为字符串并返回
        }
    }

/*    作者：jyd
    链接：https://leetcode.cn/problems/fan-zhuan-dan-ci-shun-xu-lcof/solution/mian-shi-ti-58-i-fan-zhuan-dan-ci-shun-xu-shuang-z/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
