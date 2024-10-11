package 校招笔试真题.携程._20241010;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/10
 */
public class 驼峰转换 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while (n-- > 0){
            String s = sc.next();
            String[] s1 = s.split("_");
            StringBuffer sb = new StringBuffer();
            for (String s2 : s1) {
                char c = s2.charAt(0);
                char upperCase = Character.toUpperCase(c);
                char[] subString = s2.substring(1).toCharArray();
                sb.append(upperCase);
                sb.append(subString);
            }
            System.out.println(sb);
        }
    }
}
