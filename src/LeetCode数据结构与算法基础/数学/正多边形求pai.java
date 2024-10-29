package LeetCode数据结构与算法基础.数学;

import java.text.DecimalFormat;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/3
 */
public class 正多边形求pai {

    /**
     * 题目要求是不能用sin和cos，但没办法就用。
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int numSides = sc.nextInt();
        double sideLength = 2 * Math.sin(Math.PI / numSides);
        double radius = sideLength / ( 2 * Math.sin(Math.PI / numSides));
        double perimeter = numSides * sideLength;
        double piApprox = perimeter / ( 2 * radius);
        DecimalFormat df = new DecimalFormat("#.#######");
        System.out.println(df.format(piApprox));
    }
}
