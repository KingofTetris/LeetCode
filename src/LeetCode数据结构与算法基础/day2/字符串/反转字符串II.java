package LeetCode数据结构与算法基础.day2.字符串;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */


import java.util.Scanner;

/**
 * 给定一个字符串 s 和一个整数 k，从字符串开头算起, 每计数至 2k 个字符，就反转这 2k 个字符中的前 k 个字符。
 * <p>
 * 如果剩余字符少于 k 个，则将剩余字符全部反转。
 * <p>
 * 如果剩余字符小于 2k 但大于或等于 k 个，则反转前 k 个字符，其余字符保持原样。
 * <p>
 * 示例:
 * <p>
 * 输入: s = "abcdefg", k = 2
 * 输出: "bacdfeg"
 */
public class 反转字符串II {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        int k = sc.nextInt();
        System.out.println(solution(s, k));
    }

    //每2k个字符反转前k个字符
    //如果后面的字符>=k 那么反正前k个，其他不变。
    public static String solution(String s, int k) {
        //交换ch数组的位置就可以了，不用substring一直考虑start,end真的SB
        char[] ch = s.toCharArray();
        //从0开始，每次i+2k
        for (int i = 0; i < ch.length; i += 2 * k) {
            int start = i;
            // 判断尾数够不够k个来取决end指针的位置
            //start + k - 1就是结束的位置。
            int end = Math.min(ch.length - 1, start + k - 1);
            while (start < end) {
                char temp = ch[start];
                ch[start] = ch[end];
                ch[end] = temp;
                start++;
                end--;
            }
        }
        return new String(ch);
    }
}
