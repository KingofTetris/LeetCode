package LeetCode数据结构基础.day2.字符串;

/**
 * @author KingofTetris
 * @File 重复的DNA序列
 * @Time 2021/10/18  9:41
 */

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/** 所有 DNA 都由一系列缩写为 'A'，'C'，'G' 和 'T' 的核苷酸组成，
 * 例如："ACGAATTCCG"。在研究 DNA 时，识别 DNA 中的重复序列有时会对研究非常有帮助。
        编写一个函数来找出所有目标子串，目标子串的长度为 10，
        且在 DNA 字符串 s 中出现次数超过一次。
        示例 1：
        输入：s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
        重复AAAAACCCCC CAAAAACCCCCC ||  CCCCCAAAAA CCCCCAAAAA
        所以输出这两个字符串
        输出：["AAAAACCCCC","CCCCCAAAAA"]
        示例 2：
        输入：s = "AAAAAAAAAAAAA"
        输出：["AAAAAAAAAA"]
        提示：
        0 <= s.length <= 105
        s[i] 为 'A'、'C'、'G' 或 'T' */

public class 重复的DNA序列 {

    static final int L = 10;
    @Test
    public void test(){

    }
    public List<String> findRepeatedDnaSequences(String s) {
        List<String> ans = new ArrayList<String>();
        Map<String, Integer> cnt = new HashMap<String, Integer>();
        int n = s.length();

        //超过n-L 不可能再有L个字符，就不用再循环了。
        for (int i = 0; i <= n - L; ++i) {
            String sub = s.substring(i, i + L);
            cnt.put(sub, cnt.getOrDefault(sub, 0) + 1);
            if (cnt.get(sub) == 2) {
                ans.add(sub);
            }
        }
        return ans;
    }
}










