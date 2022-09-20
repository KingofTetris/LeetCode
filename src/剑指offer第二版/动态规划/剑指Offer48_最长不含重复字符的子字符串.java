package 剑指offer第二版.动态规划;

import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @Author KingofTetris
 * @Date 2022/9/2 14:50
 * 请从字符串中找出一个最长的不包含重复字符的子字符串，计算该最长子字符串的长度。
 * 示例 1:
 * 输入: "abcabcbb"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "abc"，所以其长度为 3。
 * 示例 2:
 * 输入: "bbbbb"
 * 输出: 1
 * 解释: 因为无重复字符的最长子串是 "b"，所以其长度为 1。
 * 示例 3:
 * 输入: "pwwkew"
 * 输出: 3
 * 解释: 因为无重复字符的最长子串是 "wke"，所以其长度为 3。
 *      请注意，你的答案必须是 子串 的长度，"pwke" 是一个子序列，不是子串。
 * 提示：
 * s.length <= 40000
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zui-chang-bu-han-zhong-fu-zi-fu-de-zi-zi-fu-chuan-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer48_最长不含重复字符的子字符串 {

    @Test
    public void test() {
        String s = "auesffssss";
        System.out.println(lengthOfLongestSubstring(s));
    }


    /**
     * 动态规划，但是用substring无法处理最长不重复持续到末尾的情况
     *
     * @param s
     * @return
     */
    //子字符串需要连续
    public int lengthOfLongestSubstring(String s) {
        //特殊情况
        if (s.length() == 0) return 0;
        int max = 1;
        int[] dp = new int[s.length()];
        int start = 0;
        //有BUG。如果最长不重复出现在末尾，那就会少1  因为substring是左闭右开的 但是charAt是闭的。
        for (int i = 0; i < s.length() - 1; i++) {
            //如果字母不重复则，dp[i] = dp[i - 1] + 1;
            String s1 = String.valueOf(s.charAt(i + 1));
            if (i != 0 && !s.substring(start, i + 1).contains(s1)) {//每次记录前面的子串是否包含当前字符。 0除外
                //若不包含则dp + 1。
                dp[i] = dp[i - 1] + 1;
                max = Math.max(dp[i], max);//记录最长长度
            } else {
                //包含则dp[i]从1重新计数
                dp[i] = 1;
                start = i;
            }
        }
        return max;
    }


    //TODO 搞清楚
    /**
     * Map + 双指针
     *
     * @param s
     * @return
     * 一句话讲就是右指针遇到重复字符时，
     * 左指针跳到子串里第一个重复字符的位置，
     * 并且set把子串中重复字符前的字符去掉，重新右指针再往右扩展
     */
    public int lengthOfLongestSubstring2(String s) {
        //存储元素的下标
        Map<Character , Integer> map = new HashMap<>();
        //最长子串
        int max = 0;
        //记录即时长度
        int temp = 0;
        //左边界
        int left = 0;
        char[] c = s.toCharArray();
        for(int i = 0; i < c.length; i++){
            if(!map.containsKey(c[i])){
                //如果是第一次出现的字符，存入map，即时长度+1
                map.put(c[i] , i);
                temp++;
            }else{
                //如果遇上重复字符，首先判断左边界与被重复字符谁离重复字段更近 更新左边界
                //这样做是为了防止已剔除的字符混入其中 如 abba 在第二个b之后 子串已经更新为b
                //如果不更新左边界 在遇到第二个a之后就会计算与第一个a的距离 这样的逻辑是错误的
                //应该计算与子串的左边界的距离 即（3-2）+ 1 = 2
                left = Math.max(left,map.get(c[i]));
                //计算即时长度 即重复字符与左边界的距离
                temp = i - left;
                //更新字符的下标
                map.put(c[i] , i);
            }
            //更新最大长度
            max = Math.max(temp ,max);
        }
        return max;
    }

    /**
     * 滑动窗口，用set维护一个不重复的窗口
     * @param s
     * @return
     */
    public int lengthOfLongestSubstring3(String s) {
        int res = 0;
        Set<Character> set = new HashSet<>();
        int l = 0, r = 0;
        for(; r < s.length(); r++) {
            char c = s.charAt(r);
            while(set.contains(c)) { //重复就删除前面的字串
                set.remove(s.charAt(l));
                l++; //l留在r的起点，直到r遇到重复元素了，l开始动。直到l再次走到r的位置重新开始下一轮。
            }
            set.add(c); //第一次遇到就添加进Set
            res = Math.max(res, r - l + 1);//每次添加计算res的最大值也就是r - l + 1。
        }

        return res;
    }
}
