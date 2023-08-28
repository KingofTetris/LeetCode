package 校招笔试真题.京东.春招20230408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/4/26
 */
public class 塔子哥的数学题 {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        while (n-- > 0){
            int x = sc.nextInt();
            int y = sc.nextInt();

            //y = x + a*b (a,b > 0 ,t>=1)
            // y - x = a * b //只要不是质数就能组合
            int diff = y - x;
            if (diff <= 0) System.out.println("-1 -1");
            else {
                System.out.println("1" + " " + diff);
            }
        }
    }
}
