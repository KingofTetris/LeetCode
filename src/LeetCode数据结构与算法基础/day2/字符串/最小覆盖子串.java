package LeetCode数据结构与算法基础.day2.字符串;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/8/14
 */
public class 最小覆盖子串 {

    /**
     * 给你一个字符串 s 、一个字符串 t 。
     * 返回 s 中涵盖 t 所有字符的最小子串。如果 s 中不存在涵盖 t 所有字符的子串，则返回空字符串 "" 。
     * <p>
     * <p>
     * <p>
     * 注意：
     * <p>
     * 对于 t 中重复字符，我们寻找的子字符串中该字符数量必须不少于 t 中该字符数量。
     * 如果 s 中存在这样的子串，我们保证它是唯一的答案。
     * <p>
     * <p>
     * 示例 1：
     * <p>
     * 输入：s = "ADOBECODEBANC", t = "ABC"
     * 输出："BANC"
     * 解释：最小覆盖子串 "BANC" 包含来自字符串 t 的 'A'、'B' 和 'C'。
     * 示例 2：
     * <p>
     * 输入：s = "a", t = "a"
     * 输出："a"
     * 解释：整个字符串 s 是最小覆盖子串。
     * 示例 3:
     * <p>
     * 输入: s = "a", t = "aa"
     * 输出: ""
     * 解释: t 中两个字符 'a' 均应包含在 s 的子串中，
     * 因此没有符合条件的子字符串，返回空字符串。
     * <p>
     * <p>
     * 提示：
     * <p>
     * m == s.length
     * n == t.length
     * 1 <= m, n <= 105
     * s 和 t 由英文字母组成
     * <p>
     * <p>
     * 进阶：你能设计一个在 o(m+n) 时间内解决此问题的算法吗？
     *
     * @param s
     * @param t
     * @return
     */

    /**
     * 滑动窗口模板
     * 外层控制r 内层控制l
     * (r < n,r++){
     * //内层check,满足l++
     * //不满足r++
     * }
     */


    @Test
    public void test() {
        String s = "neichegncheck";
        String t = "ink";
        String window = minWindow(s, t);
        System.out.println(window);
    }

    //need是需要的字符数量，win是窗口目前字符的统计数量
    Map<Character, Integer> need = new HashMap<>(); //需要的字符数量
    Map<Character, Integer> win = new HashMap<>(); //窗口当前的字符数量

    public String minWindow(String s, String t) {
        int tLen = t.length();
        for (int i = 0; i < tLen; i++) {
            char c = t.charAt(i);
            need.put(c, need.getOrDefault(c, 0) + 1);
        }

        //左右指针
        int l = 0, r = -1;
        int len = Integer.MAX_VALUE, ansL = -1, ansR = -1; //最小窗口的长度，和L,R
        int sLen = s.length();
        while (r < sLen) {
            //出现在外层就是窗口不满足条件，那么r只要是需要的就put进去。
            ++r;
            // 如果 r < sLen并且r这个字符包含在t中。
            if (r < sLen && need.containsKey(s.charAt(r))) {
                //更新当前滑动窗口的字符统计数量
                win.put(s.charAt(r), win.getOrDefault(s.charAt(r), 0) + 1);
            }

            //如果已经覆盖了，并且l <= r 那就要开始考虑最小
            //也就是开始移动left 缩小窗口
            /**
             * 因为现在窗口已经满足need了，那么你right单向还是满足而且窗口会更大，没有意义
             * 所以应该是去移动left，尽量满足且窗口最小，不满足的时候再去移动right
             * 重复这个过程，直到 l > r && r < len
             */
            while (check() && l <= r) {
                //r - l + 1就是滑动窗口字符串的长度
                if (r - l + 1 < len) { //假设l = 2，r = 5 则len = r - l + 1 = 4
                    len = r - l + 1;
                    ansL = l;
                    //ansR = 6??为什么不是直接等于right,因为最后要使用substring[L,R)
                    //所以ansR是开区间。
                    ansR = l + len;
                }
                //如果left是需要的字符，那么cnt就需要-1
                //left移动
                if (need.containsKey(s.charAt(l))) {
                    win.put(s.charAt(l), win.getOrDefault(s.charAt(l), 0) - 1);
                }
                ++l;
            }
        }
        //最后返回找到的字符串，substring(ansL,ansR)，注意ansR是开区间。
        return ansL == -1 ? "" : s.substring(ansL, ansR);
    }

    //判断当前滑动窗口是否已经覆盖字符串t
    public boolean check() {
        //遍历ori的entry
        //注意这个遍历map的写法，非常重要，经常会用到
        //for(Map.Entry<K,V> entry: xx.entrySet())
        for (Map.Entry<Character, Integer> entry : need.entrySet()) {
            Character key = entry.getKey();
            Integer value = entry.getValue();
            //只要还有key没覆盖完就是false
            if (win.getOrDefault(key, 0) < value) {
                return false;
            }
        }
        return true;
    }
}
    /*    作者：力扣官方题解
        链接：https://leetcode.cn/problems/minimum-window-substring/solutions/257359/zui-xiao-fu-gai-zi-chuan-by-leetcode-solution/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

