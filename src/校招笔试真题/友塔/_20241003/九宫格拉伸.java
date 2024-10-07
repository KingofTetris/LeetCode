package 校招笔试真题.友塔._20241003;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/3
 */

//图像拉伸处理算法??
public class 九宫格拉伸 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        // 注意 hasNext 和 hasNextLine 的区别
        int n = sc.nextInt();
        int m = sc.nextInt();
        int l = sc.nextInt(),r = sc.nextInt(),u = sc.nextInt(),d = sc.nextInt();
        int[][] mat = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                mat[i][j] = sc.nextInt();
            }
        }

        int[][] newMat = new int[m][m];
    }
}
