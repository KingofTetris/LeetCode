package 校招笔试真题.美团.美团2023春招0408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */
//5%
public class Main4 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.next();
        int res = 0;
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == '1') continue; //如果以1开头，直接下一轮。
            for (int j = i + 1; j <= s.length(); j++) {
                if (isGood(s.substring(i,j))){
                    res++;
                }
            }
        }
        System.out.println(res);
    }


    public static boolean isGood(String s){
        int len1 = 0;
        int len0 = 0;
        char[] array = s.toCharArray();
        for (char c : array) {
            if (c == 1) {
                len1++;
            } else {
                len0++;
            }
        }
        return len0 > len1;
    }


}
