package 校招笔试真题.CVTE._20240906;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/9/30
 */
public class Main {
    public static void main(String[] args) {
        String s1 = "Hello";
        String s2 = new String("Hello");
        String s3 = "Hello";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
    }
}

