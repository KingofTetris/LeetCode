package 校招笔试真题.华为.华为秋招1115;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/11/30
 */
public class 求解二元一次方程组的解 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String eq1  = sc.nextLine();
        String eq2 = sc.nextLine();
//        System.out.println(eq1 + "\t" + eq2);
        int[] solution = solveEquations(eq1, eq2);
        if (solution != null) {
            int x = solution[0];
            int y = solution[1];
            System.out.println("方程组的解为：x = " + x + ", y = " + y);
        } else {
            System.out.println("方程组无解或输入格式有误。");
        }

        sc.close();
    }

    private static int[] solveEquations(String equation1, String equation2) {
        //首先解析两个表达式
        //把未知数都移到左边，常数移到右边进行解析
        int coefficientX1 = getCoefficientX(equation1);
        int coefficientY1 = getCoefficientY(equation1);
        int constant1 = getConstant(equation1);

        int coefficientX2 = getCoefficientX(equation2);
        int coefficientY2 = getCoefficientY(equation2);
        int constant2 = getConstant(equation2);

        int determinant = coefficientX1 * coefficientY2 - coefficientX2 * coefficientY1;
        if (determinant != 0) {
            int x = (constant1 * coefficientY2 - constant2 * coefficientY1) / determinant;
            int y = (coefficientX1 * constant2 - coefficientX2 * constant1) / determinant;
            return new int[]{x, y};
        } else {
            return null; // 方程组无解
        }
    }

    private static int getCoefficientX(String equation) {
        // 从方程中提取x的系数逻辑，类似于之前的示例代码
        // ...
        int coefficientX = 0;
        int indexOfX = equation.indexOf('x');

        if (indexOfX != -1) {
            int sign = 1;
            int i = indexOfX - 1;

            while (i >= 0 && Character.isDigit(equation.charAt(i))) {
                coefficientX = coefficientX * 10 + (equation.charAt(i) - '0');
                i--;
            }

            if (i >= 0 && equation.charAt(i) == '-') {
                coefficientX = -coefficientX;
            }
        }

        return coefficientX;
    }

    private static int getCoefficientY(String equation) {
        // 从方程中提取y的系数逻辑，类似于之前的示例代码
        // ...
        int coefficientY = 0;
        int indexOfY = equation.indexOf('y');

        if (indexOfY != -1) {
            int sign = 1;
            int i = indexOfY - 1;

            while (i >= 0 && Character.isDigit(equation.charAt(i))) {
                coefficientY = coefficientY * 10 + (equation.charAt(i) - '0');
                i--;
            }

            if (i >= 0 && equation.charAt(i) == '-') {
                coefficientY = -coefficientY;
            }
        }

        return coefficientY;
    }

    private static int getConstant(String equation) {
        // 从方程中提取常数项逻辑，类似于之前的示例代码
        // ...
        int constant = 0;

        String[] parts = equation.split("=");
        if (parts.length == 2) {
            String constantStr = parts[1].trim();
            try {
                constant = Integer.parseInt(constantStr);
            } catch (NumberFormatException e) {
                // 处理无效的常数项
                e.printStackTrace();
            }
        }

        return constant;
    }
}
