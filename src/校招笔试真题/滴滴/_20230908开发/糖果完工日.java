package 校招笔试真题.滴滴._20230908开发;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.IntStream;

/**
 * @author by KingOfTetris
 * @date 2023/9/8
 */

//
public class 糖果完工日 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int a = sc.nextInt();
        int b = sc.nextInt();
        int[] c = new int[n];
        for (int i = 0; i < n; i++) {
            c[i] = sc.nextInt();
        }
        // 二分查找，初始化左右边界
        //Arrays.stream(c).sum() 可以用于求一个整型数组 c 中所有元素的和。
        // 它会将数组中的元素依次累加，最后返回它们的总和。
        int left = a * b / Arrays.stream(c).sum();
        int right = a * b / n;
        while (left < right) {
            int mid = (left + right) / 2;
            // 当前生产的糖果包数
            Map<Integer, Integer> map = new HashMap<>();
            for (int x : c) {
                map.put(x, x * mid / b);
            }
            int cnt = 0;
            for (int value : map.values()) {
                cnt += value;
            }
            if (cnt < a) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        System.out.println(right);
    }
}
