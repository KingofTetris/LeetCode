package 校招笔试真题.拼多多.拼多多秋招0822;

import java.util.Scanner;

public class 塔子哥的回文修改 {
    //AC 94 有一个用例过不去
    public static void main(String[] args){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        while(n-- > 0){
            String s = sc.next();
            int num = sc.nextInt();
            int least = solve(s);
            if(num >= least) System.out.println("Yes");
            else System.out.println("No");
        }
    }

    //判断s成为回文数最少需要多少次。 >= 最少次数都是Yes。
    public static int solve(String s){
        char[] chars = s.toCharArray();
        int n = chars.length - 1;
        int left = 0;
        int right = n;
        int count = 0;
        while(left < right){
            if(chars[left] != chars[right]){
                //修改其中一次为对称
                count++;
            }
            left++;
            right--;
        }
        return count;
    }
}
