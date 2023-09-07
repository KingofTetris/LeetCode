package 校招笔试真题.得物._20230829;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */
public class 是否存在长度为x的回文子串 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(),x = sc.nextInt();
        String str = sc.next();
        if (x > n) {
            System.out.println("0");
            return;
        }
        //暴力就能过
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ( ishwc(str.substring(i,j)) && j - i == x){
                    System.out.println("1");
                    return;
                }
            }
        }
        System.out.println("0");
    }

    public static boolean ishwc(String s){
        int left = 0;
        int right = s.length() - 1;
        char[] ss = s.toCharArray();
        while (left < right){
            if (ss[left] != ss[right]){
                return false;
            }
            left++;
            right--;
        }
        return true;
    }
}
