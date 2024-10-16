package 校招笔试真题.百度._20241015;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/15
 */


//73.33% 怎么剪枝?
public class 字符串挪移 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        StringBuilder sb = new StringBuilder(s);
        f(sb,0);
        System.out.println(sb);
    }

    private static void f(StringBuilder sb,int cur) {
        if (cur >= sb.length()){
            return;
        }
        //每次把si移动到最后。
        char temp = sb.charAt(cur);
        sb.deleteCharAt(cur);
        //添加在末尾
        sb.append(temp);
        //递归
        f(sb,cur + 1);
    }


}
