package LeetCode数据结构与算法基础.双指针与滑动窗口;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 检查字符串是否为数组前缀 {

    public boolean isPrefixString(String s, String[] words) {
        int i = 0;
        for (String w : words) {
            int j = 0;
            while (j < w.length()) {
                if (i >= s.length()) {
                    break;
                } else if (w.charAt(j) != s.charAt(i)) {
                    return false;
                }
                ++i;
                ++j;
            }
            if (i == s.length()) {
                return j == w.length();
            }
        }
        return false;
    }
}
