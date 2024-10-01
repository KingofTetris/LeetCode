package 校招笔试真题.金山办公._20240929;

import java.util.LinkedList;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/29
 */
public class 奇偶抽取 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        long x = 0, y = 0;
        for(int i = 0; i < s.length(); i++) {
            int w = s.charAt(i) - '0';
            if(w % 2 == 1) {
                x = x * 10 + w;
            } else {
                y = y * 10 + w;
            }
        }
        System.out.println(Math.abs(x - y));
    }
}
