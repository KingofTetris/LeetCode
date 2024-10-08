package LeetCode数据结构与算法基础.数学;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 *
 * 这题保证方程解为整数，所以解法才都用的int
 * 不然就只能用double。这种数学运算就不是Java的强项，直接用python两行代码就搞定了
 *
import numpy as np

# 示例系数矩阵 A 和结果向量 b       Ax = b
# 相当于
 3x + y = 9
 x + 2y = 8

#死去的线性代数开始攻击我。

A = np.array([[3, 1], [1, 2]])
b = np.array([9, 8])

# 使用 numpy.linalg.solve 解方程
x = np.linalg.solve(A, b)

print(x)  # 输出解向量
 */
public class 求解二元一次方程组的解 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eq1  = sc.nextLine();
        String eq2 = sc.nextLine();
        int[] eq1Nums = parseEquation(eq1);
        int[] eq2Nums = parseEquation(eq2);
//        System.out.println(Arrays.toString(eq1Nums));
//        System.out.println(Arrays.toString(eq2Nums));
        solve(eq1Nums,eq2Nums);
    }

    private static void solve(int[] eq1Nums, int[] eq2Nums) {
        int a1 = eq1Nums[0],b1 = eq1Nums[1],c1 = eq1Nums[2];
        int a2 = eq2Nums[0],b2 = eq2Nums[1],c2 = eq2Nums[2];

        //公式算子 其实就是系数矩阵的行列式的值
        //为0 无解
        //不为0 有唯一解
        int denominator = a1 * b2 - a2 * b1;

        if (denominator == 0){
            System.out.println("方程无解或者有无穷多解");
        }
        else {
            //公式法
            /**
             * 就是
             * x =  | c1   b1 |
             *      | c2   b2 |
             *      -----------
             *      denominator
             * y = |a1    c1|
             *     |a2    c2|
             *     -----------
             *     denominator
             *
             * 怎么来的，你让两行，先乘以一个b1,b2就可以消去x了
             * a1x + b1y = c1
             * a2x + b2y = c2
             * -->
             * a1b2x + b1b2y = c1b2
             * a2b1x + b1b2y = c2b1
             *
             * 那么 (a1b2 - a2b1)x = c1b2 - c2b1;
             * x = c1b2 - c2b1
             *     -----------
             *     a1b2 - a2b1
             * 也就是上面的行列式表达式了
             * 同理可以得到y。
             */
            int x = (b2 * c1 - b1 * c2) / denominator;
            int y = (a1 * c2 - a2 * c1) / denominator;
            System.out.println("方程有唯一解");
            System.out.println("x=" + x + " " + "y=" + y);
        }
    }

    public static int[] parseEquation(String equation) {
        // 去除空格并根据等号分割方程
        String[] parts = equation.split("=");
        // 初始化系数数组
        int[] coefficients = new int[3];
        // 处理左边的表达式
        String left = parts[0];
        // 处理 x 部分
        int xIndex = left.indexOf("x");
        if (xIndex != -1) {
            String xPart = left.substring(0, xIndex);
            if (xPart.equals("")) {
                coefficients[0] = 1;
            }
            else if (xPart.equals("-") || xPart.equals("+")){
                coefficients[0] = -1;
            }
            else {
                coefficients[0] = Integer.parseInt(xPart);
            }
        }

        // 处理 y 部分
        int yIndex = left.indexOf("y");
        if (yIndex != -1) {
            String yPart = left.substring(xIndex + 1, yIndex);
            if (yPart.equals("") || yPart.equals("+")) {
                coefficients[1] = 1;
            }
            else if (yPart.equals("-")){
                coefficients[1] = -1;
            }
            else {
                coefficients[1] = Integer.parseInt(yPart);
            }
        }

        // 处理右边的表达式
        coefficients[2] = Integer.parseInt(parts[1]);

        return coefficients;
    }

}
