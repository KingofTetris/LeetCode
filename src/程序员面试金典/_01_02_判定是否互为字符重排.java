package 程序员面试金典;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2022/9/22
 * 给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
 * 示例 1：
 * 输入: s1 = "abc", s2 = "bca"
 * 输出: true
 * 示例 2：
 *
 * 输入: s1 = "abc", s2 = "bad"
 * 输出: false
 * 说明：
 *
 * 0 <= len(s1) <= 100
 * 0 <= len(s2) <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/check-permutation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _01_02_判定是否互为字符重排 {

    /**
     * 两个Map 两个都用来加次数 然后对比次数是否相同 O(n)
     *
     * 当然还可以像下面这个写法，一个Map,不过一个加，一个减。最好判断是否为0
     * 能省下一点空间。
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation(String s1, String s2) {
        if (s1.length() != s2.length()) return false;//首先长度不同 比都不用比了
        HashMap<Character,Integer> map = new HashMap<>();
        for (int i = 0; i < s1.length(); i++) {
            map.put(s1.charAt(i),map.getOrDefault(s1.charAt(i),0) + 1);
            map.put(s2.charAt(i),map.getOrDefault(s2.charAt(i),0) - 1);
        }
        for (Map.Entry<Character, Integer> characterIntegerEntry : map.entrySet()) {
            if (characterIntegerEntry.getValue() != 0) return false;
        }
        return true;
    }

    /**
     * 如果有提示字符串仅含小写字母a -z的话，那么可以只用一个数组
     * 一个加，一个减。如果刚好是异位词，那么一定为0
     * @param s1
     * @param s2
     * @return
     */
    public boolean CheckPermutation2(String s1, String s2) {
        if (s1.length() != s2.length()) {
            return false;
        }
        int[] table = new int[26];
        for (int i = 0; i < s1.length(); i++) {
            table[s1.charAt(i) - 'a']++;
        }
        for (int i = 0; i < s2.length(); i++) {
            table[s2.charAt(i) - 'a']--;
            if (table[s2.charAt(i) - 'a'] < 0) {
                return false;
            }
        }
        return true;
    }

}
