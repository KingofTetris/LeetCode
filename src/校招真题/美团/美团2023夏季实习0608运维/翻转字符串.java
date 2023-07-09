package 校招真题.美团.美团2023夏季实习0608运维;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/6/21
 */
public class 翻转字符串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = sc.nextInt(); //要翻转的位数
        char[] chars = s.toCharArray();
        reverse(chars,0,n-1);
        reverse(chars,n,s.length()-1);
        reverse(chars,0,s.length()-1);
        System.out.println(new String(chars));
    }

    private static void reverse(char[] chars, int start, int end) {
        while (start < end){
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
