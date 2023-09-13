package 校招笔试真题.百度;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/12
 */
public class 小度的棋盘 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();
        while (t-- > 0){
            int n = sc.nextInt();
            int m = sc.nextInt();
            solution(n,m);
        }
    }
    //从1,1的位置出发。每次可以向右或者向上移动奇数次
    //小红先手，请问能否必胜？
    //差为奇数其实就可以了。
    public static void solution(int n,int m){
        if (Math.abs(n - m) % 2 == 1){
            System.out.println("Yes");
        }else {
            System.out.println("No");
        }
    }
}
