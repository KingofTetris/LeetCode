package 算法设计与分析.小球排列;

import java.util.Scanner;

/**
 * @author KingofTetris
 * @File Main
 * @Time 2021/10/16  22:09
 */

/* 三种颜色的球各有n个，排成一行，要求相邻颜色不同 最多有多少种组合？*/
public class Main {
    static int total;//总共多少球
    public static void main(String[] args) {
        int n;
        Scanner sc = new Scanner(System.in);
        n = sc.nextInt();
        total = n * 3;
        int[] ball = new int[3];
        for (int i = 0; i < 3; i++) {
            ball[i] = n;
        }
        int count = numOfSequence(ball,-1,0);
        System.out.println(count % 998244353);
    }

    public static int numOfSequence(int[] ball,int last,int length){
        if (length == total) //排完一种就+1
            return 1;
        int count = 0;
        for (int i = 0; i < 3; i++) {  //这里的i  0 1 2就是三种颜色
            if (i != last && ball[i] > 0){
                ball[i]--;
                count = count + numOfSequence(ball,i,length + 1);

                //假如三种颜色是红、黄、蓝。
                // 现在要给一个特定位置的人指定一件衣服，第一次选择了红色，
                // 于是把红色去掉一件(cloth[i]--)；
                // 第二次选择黄色，在这之前得把之前那件红色要回来(cloth[i]++)，
                // 不然相当于这个人独占了两件衣服
                ball[i]++;
            }
        }
        return count;
    }
}
