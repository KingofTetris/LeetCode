package 校招笔试真题.美团.美团2023秋招0819;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class 小美的外卖订单编号 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        while(n-- > 0){
            int m = in.nextInt();
            int x = in.nextInt();
            solution(m,x);
        }
    }

    public static void solution(int m,int x){
        if(x % m == 0) System.out.println(m);
        else System.out.println(x % m);
    }
}
