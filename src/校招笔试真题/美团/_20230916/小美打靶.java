package 校招笔试真题.美团._20230916;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/16
 */

//从(0,0)向外扩张10环，内环是10分，每向外一个环-1。
    //不中靶0分，现在输入一个坐标，请你判断这个坐标的分数。
//其实就是简单的求x,y到原点的距离，看是第几环就可以了。
public class 小美打靶 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int x = sc.nextInt();
        int y = sc.nextInt();
        if (x == 0 && y == 0){
            System.out.println(10);
            return;
        }
        //判断x,y 能得几分?
        int distance = x * x + y * y;
        int radius = 0;
        double sqrt1 = Math.sqrt(distance);
        int sqrt2 = (int) Math.sqrt(distance);
        if (sqrt1 * sqrt1 > sqrt2 * sqrt2) {
            radius = (int) (Math.sqrt(distance) + 1);
        } else {
            radius = sqrt2;
        }
        int score = 11 - radius;
        score = Math.max(score, 0);
        System.out.println(score);
    }
}
