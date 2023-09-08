package 校招笔试真题.美团.美团2023春招0408;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */

//you矩阵?
public class Main2 {
    static long res = 0;
    static int[][] visited;
    //包含YOU的三角形个数
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(),m = in.nextInt();
        int t = n;
        char[][] matrix = new char[n][m];
        visited = new int[n][m];//初始都为0，表示都没走过。
        int i = 0;
        while (t-- > 0){
            String s = in.next();
            char[] chars = s.toCharArray();
            for (int j = 0; j < m; j++) {
                matrix[i][j] = chars[j];
            }
            i++;
        }
        solution(matrix);
    }

    //这个矩阵能凑出多少个包含you三个字母的三角形？
    //三角形的顶点分别是y,o,u
    //三角形为直角三角形。直角边一个水平，一个垂直
    private static void solution(char[][] matrix) {
        //bfs吧
        bfs(matrix,0,0);
    }

    //从0，0出发，BFS整个matrix，看有多少符合要求的三角形
    //但是你怎么保证他不会重复呢？
    private static void bfs(char[][] matrix, int x, int y) {
        //如果没走过就BFS
        int n = matrix.length;
        int m = matrix[0].length;
        if (x < n && y < m && matrix[x][y] != 1 ){
            bfs(matrix,x + 1,y);
        }
    }
}
