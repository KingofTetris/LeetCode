package 校招笔试真题.星网锐捷;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by KingOfTetris
 * @date 2023/9/24
 */
public class 字符匹配 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String pattern = sc.next();
        String s = sc.next();
        boolean flag = s.matches(pattern);
        System.out.println(flag);
    }
}
