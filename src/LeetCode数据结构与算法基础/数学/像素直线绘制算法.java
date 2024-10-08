package LeetCode数据结构与算法基础.数学;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/9/11
 */

/**
 * 无限大的像素平面由一个一个像素方块组成
 * 每个像素由x,y坐标标识位置
 * 坐标从0开始，向右和上增长
 * 现在从(0,0)像素的左下角向(x,y)的右上角画一条直线
 * 如果这条直线和像素有交点，就把像素染色，请问一共有多少个像素块被染色？
 **/
public class 像素直线绘制算法 {

    static int gcd(int a, int b) {
        return b != 0 ? gcd(b, a % b) : a;
    }

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int x = in.nextInt();
        int y = in.nextInt();

        if (x == y) {
            System.out.println(x + 1);
            return;
        }

        /**
         * 辗转求余法，必须大数赋给a,小数赋给b
         */
        if (y > x) {
            int temp = x;
            x = y;
            y = temp;
        }
        //实际位置是x + 1,y + 1
        //求最大公约数
        int gcd = gcd(x + 1, y + 1);

        if (gcd == 1) {
            System.out.println(x + y + 1);
        }
        else {
            int temp = (x + 1) / gcd - 1 + (y + 1) / gcd - 1 + 1;
            System.out.println(temp * gcd);
        }

      /*  作者：梦屿千寻a
        链接：https://www.nowcoder.com/feed/main/detail/e49f68e3df6a484abee2049e5486fd9d?sourceSSR=search
        来源：牛客网*/
    }


}
