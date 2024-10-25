package LeetCode数据结构与算法基础.手撕算法;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 最长公共前缀 {

    @Test
    public void test() {
        String[] strs = {"acb", "ac","acd"};
        String s = longestCommonPrefix(strs);
        System.out.println(s);
    }

    public String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) return "";
        Arrays.sort(strs);
        //其实不排序也可以，反正我们的本质只是为了拿到最短和最长。
        //你用一次遍历也能办到。
        String shortest = strs[0];
        String longest = strs[strs.length - 1];
        int commomLen = 0;
        for (int i = 0; i < shortest.length(); i++) {
            if (shortest.charAt(i) != longest.charAt(i)){
                break;
            }
            commomLen++;
        }
        return shortest.substring(0,commomLen);
    }
}
