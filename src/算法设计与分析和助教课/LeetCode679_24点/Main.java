package 算法设计与分析和助教课.LeetCode679_24点;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int[] nums = new int[4];
        for (int i = 0; i < 4; i++) {
            nums[i] = sc.nextInt();
        }
        System.out.println(judgePoint24(nums));
    }

    public static boolean judgePoint24(int[] nums) {
        double[] nums_2 = new double[nums.length];

        for (int i = 0; i < nums.length; i++) {
            nums_2[i] = nums[i];
        }

        return dfs(nums_2);
    }

    private static boolean dfs(double[] nums_2) {
        if (nums_2.length == 1) {
            return Math.abs(nums_2[0] - 24.0) < 0.001;
        }

        for (int i = 0; i < nums_2.length; i++) {
            for (int j = i + 1; j < nums_2.length; j++) {
                double[] nums_3 = new double[nums_2.length - 1];

                for (int k = 0, index = 0; k < nums_2.length; k++) {
                    if (k != i && k != j) {
                        nums_3[index++] = nums_2[k];
                    }
                }

                for (double r : compute(nums_2[i], nums_2[j])) {
                    nums_3[nums_3.length - 1] = r;

                    if (dfs(nums_3)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static double[] compute(double i, double j) {
        return new double[]{i + j, i - j, j - i, i * j, i / j, j / i};
    }


}
