package LeetCode数据结构与算法基础.数学;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/8/24
 */
public class 数论_abc相乘的最大值 {

    /**
     * 几何不等式，每次+1要让相乘的数最大，就需要加给最小的数
     * @param args
     */
    public static void main(String[] args) {

        int MOD = (int) (10e9 + 7);

        Scanner sc = new Scanner(System.in);
        int a = sc.nextInt();
        int b = sc.nextInt();
        int c = sc.nextInt();
        int k = sc.nextInt();

        for (int i = 0; i < k; i++) {
            //每次求最小
            int min = Math.min(Math.min(a,b),c);
            //每次只能加1次。记得continue
            if (min == a) {
                a++;
                continue;
            }
            if (min == b) {
                b++;
                continue;
            }
            if (min == c){
                c++;
                continue;
            }
        }

        long res = ((long) a * b * c) % MOD;
        System.out.println(res);
    }
}
