package LeetCode_HOT100题;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/10/18
 */
public class 找到字符串中所有字母异位词 {
    @Test
    public void test(){
        String s = "dtacabbhbh";
        String p = "ababc";
        List<Integer> anagrams = findAnagrams(s, p);
        System.out.println(anagrams);
    }

    //相当于，输入一个串 S，一个串 T，找到 S 中所有 T 的排列，返回它们的起始索引
    //找子串一般是滑动窗口来解决
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        /**
         * needs 和 window 相当于计数器，分别记录 T 中字符出现次数
         * 和「窗口」中的相应字符的出现次数。
         */
        Map<Character, Integer> need = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (int i = 0; i < p.length(); i++) {
            need.put(p.charAt(i), need.getOrDefault(p.charAt(i), 0) + 1);
        }
        //窗口指针
        int left = 0, right = 0;

        int valid = 0;
        while (right < s.length()) {
            char cur = s.charAt(right);
            right++;
            // 进行窗口内数据的一系列更新
            if (need.containsKey(cur)) {
                //更新窗口
                Integer total = window.getOrDefault(cur, 0) + 1;
                window.put(cur, total);
                //如果当前字符已经匹配needs,valid++
                //表示已经有一个字母完全匹配了。
                if (window.get(cur).equals(need.get(cur))) {
                    valid++;
                }
            }
            // 判断左侧窗口是否要收缩
            while (right - left >= p.length()) {
                // 当valid已经和need长度一致的时候，就是异位词了
                if (valid == need.size()) {
                    result.add(left);
                }
                char d = s.charAt(left);
                left++;
                // 进行窗口数据当一系列更新
                if (need.containsKey(d)) {
                    if (window.get(d).equals(need.get(d))) {
                        valid--;
                    }
                    window.put(d, window.get(d) - 1);
                }
            }
        }
        return result;
    }
}
