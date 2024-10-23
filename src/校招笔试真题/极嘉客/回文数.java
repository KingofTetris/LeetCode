package 校招笔试真题.极嘉客;

/**
 * @author by KingOfTetris
 * @date 2024/10/18
 */


import java.util.Collections;
import java.util.Scanner;

/**
 * 给定一个10进制数56.
 * 将56+65得到121，又是一个回文数
 * 又比如
 * 对于10进制数87
 * 87+78 = 165
 * 165+561 = 726
 * 726+627 = 1353
 * 1353 + 3531 = 4884
 * 四步以后得到了一个回文数
 *
 * 给定一个N进制(2<=N<=16)数M，问M最多经过多少步可以得到一个回文数?
 *
 * 如果30步内可以，则输出"STEP=ANS"。超过三十步 输出 "Impossible!"
 */
public class 回文数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();
        int M = sc.nextInt();
        int step = 0;
        while (step <= 30) {
            if (isPalindrome(M, N)) {
                System.out.println("STEP=" + step);
                return;
            }
            int reverse = 0;
            int temp = M;
            while (temp > 0) {
                int gw = temp % N;
                if (gw >= N) {
                    System.out.println("Impossible!");
                }
                reverse = reverse * N + gw;
                temp /= N;
            }
            M += reverse;
            step++;
        }
        System.out.println("Impossible!");
    }

    // 判断一个数是否是回文数
    public static boolean isPalindrome(int num,int base) {
        String value = String.valueOf(num);
        StringBuilder sb = new StringBuilder();
        char[] chars = value.toCharArray();
        for (int i =  chars.length - 1; i >= 0; i--) {
            sb.append(chars[i]);
        }
        String reverse = sb.toString();
        int parseIntReverse = Integer.parseInt(reverse);
        //然后相加查看是否是回文数
        int val = cal(num,parseIntReverse,base);
        return false;
    }


    // 计算两个base进制数的和
    public static int cal(int num1, int num2, int base) {
        int val1 = 0;
        int val2 = 0;
        int temp = num1;
        int power = 1;
        while (temp > 0) {
            val1 += temp % 10 * power;
            temp /= 10;
            power *= base;
        }
        temp = num2;
        power = 1;
        while (temp > 0) {
            val2 += temp % 10 * power;
            temp /= 10;
            power *= base;
        }
        int sum = val1 + val2;
        int result = 0;
        power = 1;
        while (sum > 0) {
            result += sum % base * power;
            sum /= base;
            power *= 10;
        }
        return result;
    }
}
