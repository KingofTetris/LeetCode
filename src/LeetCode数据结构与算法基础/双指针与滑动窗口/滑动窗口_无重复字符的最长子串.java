package LeetCode数据结构与算法基础.双指针与滑动窗口;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author KingofTetris
 * @File 无重复字符的最长子串
 * @Time 2021/9/22  22:54
 */

/*3. 无重复字符的最长子串
        给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串 的长度。
        示例 1:

        输入: s = "abcabcbb"
        输出: 3
        解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
        示例 2:

        输入: s = "bbbbb"
        输出: 1
        解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
        示例 3:

        输入: s = "pwwkew"
        输出: 3
        解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
        请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
        示例 4:

        输入: s = ""
        输出: 0


        提示：

        0 <= s.length <= 5 * 104
        s 由英文字母、数字、符号和空格组成*/
public class 滑动窗口_无重复字符的最长子串 {
    //首先子串一定是连续的
    @Test
    public void test() {
//        String s = "abcabcbb";
        String s = "";
        System.out.println(lengthOfLongestSubstring(s));
    }

    //方法一 暴力法
    //两重遍历，列出所有子串，然后判断子串有没用重复的字符
    //时间复杂度n平方 必然会超时
    /*public int lengthOfLongestSubstring(String s) {
        String longest = "";
        int max = 0 ;
        for (int i = 0; i < s.length(); i++) {
            for (int j = i+1 ; j <= s.length(); j++) {
                //注意substring左闭右开 所以要j<=s.length() 即j=n也不会越界，才是真正的n-1
                String str = s.substring(i,j);
//                System.out.println(str);
                if (!containRepeatChar(str) && str.length() > max){
                    max = str.length();
                    longest = str;
                }
            }
        }
        System.out.println(longest);
        return max;
    }
    public boolean containRepeatChar(String s){
        //包含返回true
        //indexOf(char e) 返回e第一次在字符中出现的位置
        //lastIndexOf(e) 最后一次出现的位置
        //如果前后相等，则只出现一次，不等自然是重复出现了。
        char[] elments = s.toCharArray();
        for (char e: elments) {
            if (s.indexOf(e) != s.lastIndexOf(e))
                return true;
        }
        return false;
    }*/

    //法二 滑动窗口
    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 0) return 0;
        //map用于存储字符的位置
        HashMap<Character, Integer> map = new HashMap<>();
        int maxLen = 0;//用于记录最大不重复子串的长度
        int left = 0;//滑动窗口左指针
        int right = 0;//右指针
        //外层控制right，内层控制left,中间去进行check。
        while (right < s.length()) {
            //移动右指针，检测是否重复
            if (map.containsKey(s.charAt(right))) {
                //重复则需要移动左指针，到该字符在原本字符串中出现的下一个位置
                left = Math.max(left, map.get(s.charAt(right)) + 1);
            }
            //不管是否重复，都要更新 s.charAt(right)字符 的最新位置
            map.put(s.charAt(right), right);
            //计算长度
            maxLen = Math.max(maxLen, right - left + 1);
            right++;
        }
        return maxLen;
    }
}
