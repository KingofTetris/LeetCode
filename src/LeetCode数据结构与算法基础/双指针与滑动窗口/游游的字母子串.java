package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 游游的字母子串 {

    //给你一个字符串和最多能出现不同次数的字母数量k
    //求满足=k的最长子串的长度是多少。
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();//
        int k = sc.nextInt();//最多能出现多少个不同字符
        String s = sc.next();
        int res = solution(s,k);
        System.out.println(res);
    }

    private static int solution(String s, int k) {
        int left = 0;
        int maxLen = 0;
        Map<Character,Integer> charCount = new HashMap<>();

        //滑动窗口，外层控制right
        //内存控制left
        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c,charCount.getOrDefault(c,0) + 1);
            //如果出现了k个以上字符
            //则进行删除，left++
            //直到map.size重新等于k
            while (charCount.size() > k){
                char leftChar = s.charAt(left);
                charCount.put(leftChar,charCount.get(leftChar) - 1);
                if (charCount.get(leftChar) == 0){
                    charCount.remove(leftChar);
                }
                left++;
            }
            maxLen = Math.max(maxLen,right - left + 1);
        }
        return maxLen;
    }
}
