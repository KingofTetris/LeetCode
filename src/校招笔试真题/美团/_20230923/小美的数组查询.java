package 校招笔试真题.美团._20230923;

import org.junit.Test;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/23
 */
public class 小美的数组查询 {

//    40%
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int q = sc.nextInt();
        long[] nums = new long[n];
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextLong();
        }
        //查询区间
        while (q-- > 0) {
            int l = sc.nextInt();
            int r = sc.nextInt();
            int x = sc.nextInt();
            solution(l, r, x, nums);
        }
    }

    //第一个乘以x不是完全平方数的元素
    private static void solution(int l, int r, int x, long[] nums) {
        for (int i = l - 1; i <= r - 1; i++) {
            if (!isSqure(nums[i] * x)){
                System.out.println(nums[i]);
                return;
            }
        }
        System.out.println(-1);
    }

    private static boolean isSqure(long x) {
        long sqrt = (long) Math.sqrt(x);
        if (sqrt * sqrt == x) return true;
        return false;
    }

    @Test
    public void test(){
        System.out.println("isSqure(3) = " + isSqure(3));
    }
}
