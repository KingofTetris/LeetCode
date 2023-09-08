package 校招笔试真题.携程._20230907;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

//一个数组，操作:一个数+1，一个数-1
    //请问最少操作多少次能让数组中的数字都处于[L,R]中
public class 游游的数组操作 {
    /**
     * 就是需要将不在[l,r]范围内的数移到[l,r]范围内，所以首先需要计算，平均数在不在[l,r]范围内，
     * 然后只需要计算需要将超过r的数移到r，和小于l的数移动到l的最大步数即可。
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int t = in.nextInt();
        for (int i = 0; i < t; ++i) {
            int n = in.nextInt();
            //不加long 只能过85%
            long l = in.nextInt();
            long r = in.nextInt();
            long[] arr = new long[n];
            long sum = 0;
            long high = 0;
            long low = 0;
            for (int j = 0; j < n; ++j) {
                arr[j] = in.nextInt();
                sum += arr[j];
                //输入的时候就可以计算需要调整的次数
                if (arr[j] < l) {
                    low += l - arr[j];
                } else if (arr[j] > r) {
                    high += arr[j] - r;
                }
            }
            //如果sum比r*n还大
            //比l*n还小，那就是不可能调整成功的
            if (sum > r * n || sum < l * n) {
                System.out.println(-1);
            }
            //其他情况返回两者的较大者就行了？？卧槽？？？
            else {
                System.out.println(Math.max(low, high));
            }
        }
    }
/*
    作者：织梦呀
    链接：https://www.nowcoder.com/discuss/529398383690137600?sourceSSR=post
    来源：牛客网*/
}
