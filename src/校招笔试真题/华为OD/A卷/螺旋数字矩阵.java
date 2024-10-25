package 校招笔试真题.华为OD.A卷;

import java.util.Scanner;
import java.util.StringJoiner;

public class 螺旋数字矩阵 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // 需要在螺旋矩阵中填入 1 ~ n 数字
        int n = sc.nextInt();

        // 螺旋矩阵行数
        int m = sc.nextInt();

        // 螺旋矩阵列数
        int k = (int) Math.ceil(n * 1.0 / m);

        // 螺旋矩阵
        int[][] matrix = new int[m][k]; // 由于需要填入1~n数字，因此这里未填值的位置值默认初始化为0

        // 当前要填入的值
        int step = 1;

        // 当前要填入的值的位置
        int x = 0;
        int y = 0;

        // 如果填入的值 > n，则停止填值，否则继续填
        while (step <= n) {
            // 正序填入第x行，即：行号不变，列号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (y < k && matrix[x][y] == 0 && step <= n) matrix[x][y++] = step++;
            // 正序填完第x行后，y处于末尾越界位置，因此y需要退一步
            y -= 1;
            // 正序填完第x行来到第x行的末尾，即第y列，按照螺旋矩阵顺序，应该从第x+1行开始正序填值第y列
            x += 1;

            // 正序填入第y列，即：列号不变，行号增加，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (x < m && matrix[x][y] == 0 && step <= n) matrix[x++][y] = step++;
            x -= 1;
            y -= 1;

            // 倒序填入第x行，即：行号不变，列号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (y >= 0 && matrix[x][y] == 0 && step <= n) matrix[x][y--] = step++;
            y += 1;
            x -= 1;

            // 倒序填入第y列，即：列号不变，行号减少，注意：添值过程不能发生覆盖，也不能填入超过n的值
            while (x >= 0 && matrix[x][y] == 0 && step <= n) matrix[x--][y] = step++;
            x += 1;
            y += 1;
        }

        // 打印螺旋矩阵字符串
        for (int i = 0; i < m; i++) {
            StringJoiner row = new StringJoiner(" ");
            for (int j = 0; j < k; j++) {
                if (matrix[i][j] == 0) {
                    row.add("*");
                } else {
                    row.add(matrix[i][j] + "");
                }
            }
            System.out.println(row);
        }
    }
}
