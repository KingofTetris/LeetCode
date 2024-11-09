package LeetCode数据结构与算法基础.双指针与滑动窗口;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 追加字符以获得子序列 {

    public int appendCharacters(String s, String t) {
        int len1 = s.length();
        int len2 = t.length();
        int l1 = 0, l2 = 0;

        while (l1 < len1) {
            if (l2 < len2 && s.charAt(l1) == t.charAt(l2)) {
                l2++;
            }
            l1++;
        }
        return len2 - l2;
    }
}
