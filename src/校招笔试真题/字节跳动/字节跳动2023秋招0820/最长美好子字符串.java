package 校招笔试真题.字节跳动.字节跳动2023秋招0820;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * @author by KingOfTetris
 * @date 2023/8/26
 */
public class 最长美好子字符串 {


    /**
     * 暴力就能过。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();
        String res = "";
        for (int i = 0; i < s.length(); i++) {
            for (int j = i + 1; j <= s.length(); j++) {
                String substring = s.substring(i, j);
                if (isNiceString(substring)){
                    if (substring.length() > res.length()){
                        res = substring;
                    }
                }
            }
        }
        System.out.println(res);
    }


    /**
     * 判断字符串是否是美好字符串
     *
     * @return
     */
    //如果字符串中包含的字母同时出现了大小写，那么就是美好字符串
    // 比如
    private static boolean isNiceString(String s) {
        if (s.length() == 0) return false;//空字符串也不是美好字符串
        int[] bigFlags = new int[26];
        int[] smallFlags = new int[26];
        char[] chars = s.toCharArray();
        for (char aChar : chars) {

            //判断字符大小写，其实不用这么麻烦
            //可以
//            Character.isLowerCase(aChar);
//            Character.isUpperCase(aChar);
            //就行了，本质和这个表达式没什么区别，但是他底下是用位运算，要快一些。
            if (aChar - 'A' >= 0 && aChar - 'Z' <= 0) {
                bigFlags[aChar - 'A']++;
            }
            if (aChar - 'a' >= 0 && aChar - 'z' <= 0) {
                smallFlags[aChar - 'a']++;
            }
        }
        for (int i = 0; i < 26; i++) {
            //如果大小写都为0的，说明根本没出现，直接跳过
            if (bigFlags[i] == 0 && smallFlags[i] == 0) {
                continue;
            }
            //如果两个里面有一个为0，那么也不美好。
            if ((bigFlags[i] != 0 && smallFlags[i] == 0) ||
                    (bigFlags[i] == 0 && smallFlags[i] != 0)) {
                return false;
            }
        }
        //对应大小写都出现，或者空字符串则是美好
        return true;
    }


    //下面是一种更简单的判断是否美好的check
    boolean check(String s) {
        Set<Character> set = new HashSet<>();
        //使用集合去重，把所有字符添加到集合中
        for (char c : s.toCharArray()) set.add(c);
        //遍历集合，把c大小写。如果a,b不在set中，直接返回false
        for (char c : s.toCharArray()) {
            char a = Character.toLowerCase(c), b = Character.toUpperCase(c);
            if (!set.contains(a) || !set.contains(b)) return false;
        }
        return true;
    }
/*
    作者：宫水三叶
    链接：https://leetcode.cn/problems/longest-nice-substring/solutions/1/gong-shui-san-xie-yi-ti-san-jie-po-su-ji-oflj/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
