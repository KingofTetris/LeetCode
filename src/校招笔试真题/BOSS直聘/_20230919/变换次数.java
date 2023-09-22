package 校招笔试真题.BOSS直聘._20230919;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/19
 */

//72.73

//变换次数，每次把数字拆分开来相乘，如果结果>10，则继续拆分。
    //直到相乘结果<10，返回需要操作的次数。
public class 变换次数 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        System.out.println(changeNum(n));
    }


    public static int changeNum(int num){
        if (num < 10) return 0;
        long curProduct = 1;
        //先算当前的curProduct
        while (num != 0){
            int gw = num % 10;
            curProduct = curProduct * gw;
            num = num / 10;
        }
        //变为个位数
        if (curProduct < 10){
            return 1;
        }
        //如果不< 10，继续变换。
        int res = 1;
        long temp = 1;
        while (curProduct != 0){
            int gw = (int) (curProduct % 10);
            //如果有一个个位是0，就没必要再去算了。任何数字乘以0都是0，一定满足乘积是个位数。
         /*   if (gw == 0){
                res++;
                return res;
            }*/
            curProduct = curProduct / 10;
            temp *= gw;
            //如果已经除到0了，temp还是大于10，就继续循环。
            if (curProduct == 0 && temp > 10){
                curProduct = temp;
                res++;//变换次数+1
            }else if (curProduct == 0 && temp < 10){
                res++;
                break;
            }
        }
        return res;
    }
}
