package 校招笔试真题.用友.用友SP0825;

/**
 * @author by KingOfTetris
 * @date 2023/9/2
 */


//https://codefun2000.com/p/P1426

import java.util.*;

public class 塔子哥的坐标系 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = Integer.parseInt(sc.nextLine());
        Line[] lines = new Line[n];
        int[] res = new int[n + 1];
        res[0] = 2;
        for (int i = 0; i < n; i++) {
            String[] s = sc.nextLine().split(" ");
            int x1 = Integer.parseInt(s[0]);
            int x2 = Integer.parseInt(s[2]);
            int y1 = Integer.parseInt(s[1]);
            int y2 = Integer.parseInt(s[3]);
            // x1 , y1,  x2 ,y2
            double K = (double) (Integer.parseInt(s[3]) -
                    Integer.parseInt(s[1])) / (Integer.parseInt(s[2])
                    - Integer.parseInt(s[0]));
            double B = (double) y1 - K * (double) x1;
            lines[i] = new Line(K, B);
        }
        for (int i = 1; i < n; i++) {
            Line cur = lines[i];
            res[i] = res[i - 1];
            for (int j = 0; j < i; j++) {
                Line extra = lines[j];
                double xx = (extra.b - cur.b) / (cur.k - extra.k);
                double yy = cur.k * xx + cur.b;
                if (xx >= 1 && xx <= 20) {
                    res[i]++;
                }
            }
            res[i]++;
        }
        System.out.println(res[n - 1]);
    }

}

class Line {
    public double k;
    public double b;

    public Line(double k, double b) {
        this.k = k;
        this.b = b;
    }
}
