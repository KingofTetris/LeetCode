package 校招笔试真题.金山办公._20241109;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 游游的字母子串 {

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

        for (int right = 0; right < s.length(); right++) {
            char c = s.charAt(right);
            charCount.put(c,charCount.getOrDefault(c,0) + 1);
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
