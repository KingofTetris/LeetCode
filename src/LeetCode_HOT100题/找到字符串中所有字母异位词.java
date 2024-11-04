package LeetCode_HOT100题;

import org.junit.Test;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/10/18
 */
public class 找到字符串中所有字母异位词 {
    @Test
    public void test() {
        String s = "dtacabbhbh";
        String p = "ababc";
        List<Integer> anagrams = findAnagrams(s, p);
        System.out.println(anagrams);
    }

    //相当于，输入一个串 S，一个串 T，找到 S 中所有 T 的排列，返回它们的起始索引
    //找子串一般是滑动窗口来解决
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> ans = new ArrayList<>();
        int n = s.length(), m = p.length();
        //其实c1和c2就相当于2个map
        int[] c1 = new int[26], c2 = new int[26];
        //记录p所需的字母
        for (int i = 0; i < m; i++) c2[p.charAt(i) - 'a']++;
        //滑动窗口，l,r都等于0，始终是r移动
        for (int l = 0, r = 0; r < n; r++) {
            //c1先记录字符数量
            c1[s.charAt(r) - 'a']++;
            //如果子串的长度都大于p了，那么移动left,记得把对应的字母给删去
            if (r - l + 1 > m) c1[s.charAt(l++) - 'a']--;
            //如果c1,c2一致了以后，那么l就是答案之一。
            if (check(c1, c2)) ans.add(l);
        }
        return ans;
    }

    boolean check(int[] c1, int[] c2) {
        for (int i = 0; i < 26; i++) {
            if (c1[i] != c2[i]) return false;
        }
        return true;
    }

    /*作者：宫水三叶
    链接：https://leetcode.cn/problems/find-all-anagrams-in-a-string/solutions/1125936/gong-shui-san-xie-shuang-zhi-zhen-shi-xi-t5hc/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
