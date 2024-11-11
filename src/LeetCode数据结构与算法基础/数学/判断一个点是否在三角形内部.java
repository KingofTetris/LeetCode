package LeetCode数据结构与算法基础.数学;

/**
 * @author by KingOfTetris
 * @date 2024/11/11
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/f9c4290baed0406cbbe2c23dd687732c
 * 来源：牛客网
 * <p>
 * 在二维坐标系中，所有的值都是double类型，那么一个三角形可以由3个点来代表，给定3个点代表的三角形，
 * 再给定一个点(x, y)，判断(x, y)是否在三角形中
 * <p>
 * <p>
 * 输入描述:
 * 输入有四行，每行两个浮点数。
 * <p>
 * 前三行的6个数分别代表三角形的三个顶点的坐标
 * <p>
 * 最后两个数分别表示(x, y)
 * <p>
 * <p>
 * 输出描述:
 * 若(x, y)在三角形中，输出"Yes"
 * <p>
 * 否则输出"No"
 * 示例1
 * 输入
 * -1.00 0.00
 * 1.50 3.50
 * 2.73 -3.12
 * 1.23 0.23
 * 输出
 * Yes
 * <p>
 * 示例2
 * 输入
 * -1.00 0.00
 * 1.50 3.50
 * 2.73 -3.12
 * 2333.33 233333.33
 * 输出
 * No
 * <p>
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/f9c4290baed0406cbbe2c23dd687732c
 * 来源：牛客网
 * <p>
 * 假设，三角形其中一条边的两个端点为(x1,y1)和(x2,y2)，待判断位置的点为(x,y)，
 * 通过向量(x2-x1,y2-y1)和(x-x1,y-y1)叉积的符号可以判断点(x,y)
 * 与向量(x2-x1,y2-y1)的位置关系（大于0为顺时针方向，小于0逆时针方向，等于0为共线）
 * 如果点(x,y)对于三角形任意一条边的向量都位于同一侧，则点在三角形内。
 * <p>
 * 链接：https://www.nowcoder.com/questionTerminal/f9c4290baed0406cbbe2c23dd687732c
 * 来源：牛客网
 * <p>
 * 这里说明一下为什么通过向量a=(x2-x1,y2-y1)和b=(x-x1,y-y1)叉积的符号可以判断点(x,y)与向量(x2-x1,y2-y1)的位置关系。
 * 由于a×b=|a||b|sinθ，不妨设θ表示向量a顺时针旋转到b的角度，如果sinθ<0，表示旋转超过了180°，
 * 相当于b向量的终点在a向量的逆时针侧，sinθ>0时在顺时针侧，
 * sinθ=0时表示旋转了0°或180°，是共线的。同样地，设θ表示向量a逆时针旋转到b的角度也可以得到类似的结论。
 */

/**
 * 链接：https://www.nowcoder.com/questionTerminal/f9c4290baed0406cbbe2c23dd687732c
 * 来源：牛客网
 *
 * 在二维坐标系中，所有的值都是double类型，那么一个三角形可以由3个点来代表，给定3个点代表的三角形，
 * 再给定一个点(x, y)，判断(x, y)是否在三角形中
 *
 *
 * 输入描述:
 * 输入有四行，每行两个浮点数。
 *
 * 前三行的6个数分别代表三角形的三个顶点的坐标
 *
 * 最后两个数分别表示(x, y)
 *
 *
 * 输出描述:
 * 若(x, y)在三角形中，输出"Yes"
 *
 * 否则输出"No"
 * 示例1
 * 输入
 * -1.00 0.00
 * 1.50 3.50
 * 2.73 -3.12
 * 1.23 0.23
 * 输出
 * Yes
 *
 * 示例2
 * 输入
 * -1.00 0.00
 * 1.50 3.50
 * 2.73 -3.12
 * 2333.33 233333.33
 * 输出
 * No
 *
 */

/**
 * 链接：https://www.nowcoder.com/questionTerminal/f9c4290baed0406cbbe2c23dd687732c
 * 来源：牛客网
 *
 * 假设，三角形其中一条边的两个端点为(x1,y1)和(x2,y2)，待判断位置的点为(x,y)，
 * 通过向量(x2-x1,y2-y1)和(x-x1,y-y1)叉积的符号可以判断点(x,y)
 * 与向量(x2-x1,y2-y1)的位置关系（大于0为顺时针方向，小于0逆时针方向，等于0为共线）
 * 如果点(x,y)对于三角形任意一条边的向量都位于同一侧，则点在三角形内。
 *
 *链接：https://www.nowcoder.com/questionTerminal/f9c4290baed0406cbbe2c23dd687732c
 * 来源：牛客网
 *
 * 这里说明一下为什么通过向量a=(x2-x1,y2-y1)和b=(x-x1,y-y1)叉积的符号可以判断点(x,y)与向量(x2-x1,y2-y1)的位置关系。
 * 由于a×b=|a||b|sinθ，不妨设θ表示向量a顺时针旋转到b的角度，如果sinθ<0，表示旋转超过了180°，
 * 相当于b向量的终点在a向量的逆时针侧，sinθ>0时在顺时针侧，
 * sinθ=0时表示旋转了0°或180°，是共线的。同样地，设θ表示向量a逆时针旋转到b的角度也可以得到类似的结论。
 *
 *
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class 判断一个点是否在三角形内部 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;
        int i = 0;
        // 三角形三个顶点的坐标以及待判断点的坐标
        double[] x = new double[4];
        double[] y = new double[4];
        while ((line = br.readLine()) != null) {
            String[] params = line.trim().split(" ");
            x[i] = Double.parseDouble(params[0]);
            y[i] = Double.parseDouble(params[1]);
            i++;
        }
        // 看待检测点是否在三角形三条边的同一方向即可
        int res = Math.abs(crossProduct(x[0], y[0], x[1], y[1], x[3], y[3]) + crossProduct(x[1], y[1], x[2], y[2], x[3], y[3]) + crossProduct(x[2], y[2], x[0], y[0], x[3], y[3]));
        if (res == 3)
            System.out.println("Yes");
        else
            System.out.println("No");
    }

    private static int crossProduct(double x1, double y1, double x2, double y2, double x, double y) {
        x2 -= x1;
        y2 -= y1;
        x -= x1;
        y -= y1;
        double crossRes = x * y2 - x2 * y;
        if (crossRes > 0)
            return 1;
        else if (crossRes < 0)
            return -1;
        else
            return 0;
    }
}

