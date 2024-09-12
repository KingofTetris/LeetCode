package LeetCode数据结构与算法基础.前缀和;

import java.util.Scanner;

public class 开发商购买土地 {


    //给你一个二维数组
    //横切一刀或者竖切一刀，求分开以后两个子区域的差。
    //让他们尽量最小。这个最小值是多少?

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();


        //求出总和，再减去一半的和就是另外一半的和。
        int sum = 0;
        int[][] vec = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                vec[i][j] = scanner.nextInt();
                sum += vec[i][j];
            }
        }

        // 统计横向 每行的和
        int[] horizontal = new int[n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                horizontal[i] += vec[i][j];
            }
        }

        // 统计纵向 每列的和
        int[] vertical = new int[m];
        for (int j = 0; j < m; j++) {
            for (int i = 0; i < n; i++) {
                vertical[j] += vec[i][j];
            }
        }


        int result = Integer.MAX_VALUE;

        /**
         * 横着的切法有 n - 1种
         * 竖着的切法有 m - 1种
         */
        //横着切
        int horizontalCut = 0;
        for (int i = 0; i < n; i++) {
            //没往后切一刀就把Cut的值累加。
            horizontalCut += horizontal[i];
            result = Math.min(result, Math.abs(sum - 2 * horizontalCut));
        }

        //竖着切
        int verticalCut = 0;
        for (int j = 0; j < m; j++) {
            verticalCut += vertical[j];
            result = Math.min(result, Math.abs(sum - 2 * verticalCut));
        }

        System.out.println(result);
        scanner.close();
    }
}
