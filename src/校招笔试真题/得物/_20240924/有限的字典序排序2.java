package 校招笔试真题.得物._20240924;

import java.util.Arrays;
import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息
public class 有限的字典序排序2 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < a.length; i++) {
            a[i] = sc.nextInt();
        }
        int[] maxNum = findMaxNumber(a, 2);
        System.out.println(Arrays.toString(maxNum));
    }

    private static int[] findMaxNumber(int[] nums, int swap) {
        int n = nums.length;
        int[] max = Arrays.copyOf(nums, n);

        for (int i = 0; i < n - 1; i++) {
            int maxIndex = i;
            for (int j = i + 1; j < n && j <= i + swap; j++) {
                if (max[j] > max[maxIndex]) {
                    maxIndex = j;
                }
            }
            swap -= maxIndex - i;

            for (int j = maxIndex; j > i; j--) {
                int temp = max[j];
                max[j] = max[j - 1];
                max[j - 1] = temp;
            }
        }
        return max;
    }

}
