package 校招笔试真题.美团.美团2023夏季实习0608运维;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/6/21
 */

//不使用额外空间
public class 翻转字符串 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int n = s.length();
        int k = sc.nextInt(); //要翻转的位数
        char[] chars1 = s.toCharArray();
        char[] chars2 = s.toCharArray();
//        如果超过字符串的长度，先取余
        if (k > chars1.length) {
            k = k % chars1.length;
        }
        /**
         * 向右平移 k 位
         */
        reverse(chars1, 0, n - k - 1); //先反转左边
        reverse(chars1, n - k, n - 1); //再反转右边
        reverse(chars1, 0, n - 1);//反转全部
        System.out.println(new String(chars1));
        /**
         * 向左平移 k 位 其实就等于向右平移 n - k
         */
        reverse(chars2, 0, k - 1); //反转左边
        reverse(chars2, k, n - 1);//反转右边
        reverse(chars2, 0, n - 1); //反转全部
        System.out.println(new String(chars2));
        /**
         * 要注意如果是偶数长度，那么左移右移n位的结果是一样的，不要被迷惑了。
         */
    }

    /**
     * 原地翻转
     *
     * @param chars
     * @param start
     * @param end
     */
    private static void reverse(char[] chars, int start, int end) {
        while (start < end) {
            char temp = chars[start];
            chars[start] = chars[end];
            chars[end] = temp;
            start++;
            end--;
        }
    }
}
