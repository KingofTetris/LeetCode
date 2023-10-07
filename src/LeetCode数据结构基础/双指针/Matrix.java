package LeetCode数据结构基础.双指针;

import java.util.Scanner;

/**
 * @author KingofTetris
 * @File Matrix
 * @Time 2021/9/20  16:27
 */
public class Matrix {
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

    //Java里在有参函数调用时，如果参数传递的是基本类型，进行的是 ！值传递！，!值传递！
    // C和C++这样写可以，Java不行
    // 而不是 地址 或 引用传递。
    // 用函数就不能这样写
    //实际上结果并没有交换，就像下面这些错误示范
//    static void swap(int a,int b){
//        int temp;
//        temp = a;
//        a = b;
//        b = temp;
//    }

    //用加减法实现
//    static void swap(int a,int b){
//        a = a + b;
//        b = a - b; //此时 a = a+b
//        a = a - b; //此时 a = a + b b=a;
//    }
    //用异或实现
//    static void swap(int a,int b){
//        a = a^b;
//        b = b^a;
//        a = a^b;
//    }
    //全部是形参不会影响到实参，最后还是没变

    //Java舍弃指针带来了无法地址传递的后果。T V T




    /*//matrix[i].length 就是列的长度
    static void see(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] +"\t");
                }
            System.out.println();
            }
        }
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
                see(matrix);
            }
            if(flag == 1){
                for (int i = 0; i < n; i++) {
                    int temp =matrix[i][x-1];
                    matrix[i][x-1] = matrix[i][y-1];
                    matrix[i][y-1] = temp;
                }
                see(matrix);
            }
            if (flag == 2){
                System.out.println(matrix[x-1][y-1]);
            }
            q--;
        }
    }*/
}
