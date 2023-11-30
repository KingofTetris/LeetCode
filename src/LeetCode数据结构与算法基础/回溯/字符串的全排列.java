package LeetCode数据结构与算法基础.回溯;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class 字符串的全排列 {

    static List<String> result = new ArrayList<>();

    public static void permute(String s) {
        permuteHelper(s.toCharArray(), 0);
    }

    private static void permuteHelper(char[] chars, int index) {
        if (index == chars.length - 1) {
            result.add(new String(chars));
            return;
        }
        for (int i = index; i < chars.length; i++) {
            swap(chars, index, i);
            //i + 1不重复使用。
            permuteHelper(chars, index + 1);
            swap(chars, index, i);
        }
    }
    private static void swap(char[] chars, int i, int j) {
        char temp = chars[i];
        chars[i] = chars[j];
        chars[j] = temp;
    }
    public static void main(String[] args) {
        String s = "abcd";
        permute(s);
        result.sort(Comparator.reverseOrder());
        for (String perm : result) {
            System.out.println(perm);
        }
    }
}
