package 校招笔试真题.米哈游._20240907;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 数位统计 {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int left = sc.nextInt();
        int right = sc.nextInt();

        int max = -1;
        int res = right;//默认为最大
        for (int i = left; i <= right; i++) {
            //统计4和6的数量
            int count = countFS(i);
            if (count >= max){
                max = count;
                res = i;//记录res
            }
        }
        System.out.println(res);
    }

    public static int countFS(int n){
        int count = 0;
        while(n != 0){
            int gw = n % 10;
            if(gw == 4 || gw == 6){
                count++;
            }
            n = n / 10;
        }
        return count;
    }
}
