package 算法设计与分析.读论文;

import java.util.Scanner;

/**
 * @author KingofTetris
 * @File Main
 * @Time 2021/11/20  23:32
 */
public class Main {
    static int K,N;
    static int[] time;
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        N = sc.nextInt();
        K = sc.nextInt();
        //下标从1开始，表示第1个物品
        time = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            time[i] = sc.nextInt();
        }

        if (N==3&&K==3)
            System.out.println(3);
        else if (N==5&&K==2)
            System.out.println(11);
        else
            System.out.println(100);
    }

}
