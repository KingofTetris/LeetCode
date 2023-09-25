/*
package 算法设计与分析;
import java.util.Scanner;
*/
/**
 * @author KingofTetris
 * @File YY_And_Matrix
 * @Time 2021/10/4  13:39
 *//*


public class Main{
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int q = sc.nextInt();
        int matrix[][] = new int[n][m];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                matrix[i][j] = sc.nextInt();
            }
        }
        while(q > 0) {
            int flag, x, y;
            flag = sc.nextInt();
            x = sc.nextInt();
            y = sc.nextInt();
            if(flag == 0){
                for (int i = 0; i < m; i++) {
                    int temp = matrix[x-1][i];
                    matrix[x-1][i] = matrix[y-1][i];
                    matrix[y-1][i] = temp;
                }
            }
            if(flag == 1){
                for (int i = 0; i < n; i++) {
                    int temp =matrix[i][x-1];
                    matrix[i][x-1] = matrix[i][y-1];
                    matrix[i][y-1] = temp;
                }
            }
            if (flag == 2){
                System.out.println(matrix[x-1][y-1]);
            }
            q--;
        }
    }
}
*/
