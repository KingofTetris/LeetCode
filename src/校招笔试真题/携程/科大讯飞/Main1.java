package 校招笔试真题.携程.科大讯飞;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/15
 */
public class Main1 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        StringBuilder sb = new StringBuilder();
        int num = 0;
        while (sb.length() < n ){
            sb.append(num);
            num += 2;
        }
        System.out.println(sb.charAt(n - 1));
    }

}
