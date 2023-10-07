package LeetCode数据结构基础.day2.字符串;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 有效的字母异位词
 * @Time 2021/10/2  15:35
 */

/*242. 有效的字母异位词
        给定两个字符串 s 和 t ，编写一个函数来判断 t 是否是 s 的字母异位词。
        注意：若 s 和 t 中每个字符出现的次数都相同，则称 s 和 t 互为字母异位词。
        示例 1:
        输入: s = "anagram", t = "nagaram"
        输出: true
        示例 2:
        输入: s = "rat", t = "car"
        输出: false
        提示:
        1 <= s.length, t.length <= 5 * 104
        s 和 t 仅包含小写字母
        进阶: 如果输入字符串包含 unicode 字符怎么办？你能否调整你的解法来应对这种情况？*/
public class 有效的字母异位词 {

    @Test
    public void test(){
        String s = "anagram";
        String t = "nagaram";
        System.out.println(isAnagram(s, t));
    }

    //这题和赎金信是一个意思。
    //进阶的unicode可以用赎金信里面的第二种HashMap方法。
    public boolean isAnagram(String s, String t) {
        int[] nums1 = new int[26];
        int[] nums2 = new int[26];
        for (int i = 0; i < s.length(); i++) {
            nums1[s.charAt(i) - 'a']++;
        }
        for (int i = 0; i < t.length() ; i++) {
            nums2[t.charAt(i) - 'a']++;
        }

        for (int i = 0; i < nums1.length; i++) {
            if(nums1[i] != nums2[i])
                return false;
        }
        return true;
    }
}
