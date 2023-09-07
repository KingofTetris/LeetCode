package 校招笔试真题.得物._20230829;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/6
 */

//n栋楼总高度m，每栋楼之间高度差不超过1，求第x栋楼的最高高度
public class 第x栋楼的高度 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(), m = sc.nextInt(), x = sc.nextInt();
        int left = x - 1;
        int right = n -x;
        int res = 1;
        while (m > 0){
            m = m - (1 + Math.min(res,left) + Math.min(res,right));
            res++;
        }
        System.out.println(res);
    }
}
