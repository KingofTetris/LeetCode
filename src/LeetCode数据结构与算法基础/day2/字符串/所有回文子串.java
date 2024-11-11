package LeetCode数据结构与算法基础.day2.字符串;

import java.util.ArrayList;
import java.util.List;

public class 所有回文子串 {
    public static void main(String[] args) {
        String input = "aabcbadefedxyzyx";
        List<String> palindromes = findAllPalindromes(input);

        System.out.println("所有长度>=2的回文子串：");
        for (String palindrome : palindromes) {
            System.out.println(palindrome);
        }
    }

    public static List<String> findAllPalindromes(String input) {
        List<String> palindromes = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            // 以当前字符为中心的奇数长度回文子串
            expandAroundCenter(input, i, i, palindromes);
            // 以当前字符与下一个字符的间隙为中心的偶数长度回文子串
            expandAroundCenter(input, i, i + 1, palindromes);
        }
        // 过滤长度小于2的回文子串
        List<String> filteredPalindromes = new ArrayList<>();
        for (String palindrome : palindromes) {
            if (palindrome.length() >= 2) {
                filteredPalindromes.add(palindrome);
            }
        }
        return filteredPalindromes;
    }

    public static void expandAroundCenter(String input, int left, int right, List<String> palindromes) {
        while (left >= 0 && right < input.length() && input.charAt(left) == input.charAt(right)) {
            palindromes.add(input.substring(left, right + 1));
            left--;
            right++;
        }
    }
}
