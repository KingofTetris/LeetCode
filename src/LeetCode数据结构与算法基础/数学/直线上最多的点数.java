package LeetCode数据结构与算法基础.数学;

/**
 * @author by KingOfTetris
 * @date 2024/10/30
 */

//给你一个点数组，请你确定最多能有几个点在同一条直线上?
public class 直线上最多的点数 {

    /**
     * 两点确定一条直线，最简单的方式考虑任意两点组成一条直线，然后判断其他点在不在这条直线上。
     *
     * 两点确定一条直线，直线方程可以表示成下边的样子。
     *
     * y2 - y1     y - y2
     * ------- =   ------    //其实就是点斜式的变形，前面就是K
     * x2 - x1     x - x2
     *
     * 所以当来了一个点 (x,y) 的时候，理论上，我们只需要代入到上边的方程进行判断两边会不会相等，
     * 就能判断在不在这条直线上。
     *
     * 第一个想法是，等式两边分子乘分母，转换为乘法的形式。
     *  (y2 - y1)(x-x2) = (y-y2)(x2 - x1)
     *
     *所以我们可以写一个 test 函数来判断点 (x,y) 是否在由点 (x1,y1) 和 (x2,y2) 组成的直线上。
     *
     * private boolean test(int x1, int y1, int x2, int y2, int x, int y) {
     * 	return (y2 - y1) * (x - x2) == (y - y2) * (x2 - x1);
     * }
     *
     * 此外，还有一个方案。不用相乘，直接把分数化为最简，比较分子分母是否相等即可
     *
     * 所以，我们需要求分子和分母的最大公约数，直接用辗转相除法gcd即可。
     *
     *然后 test 函数就可以写成下边的样子。需要注意的是，我们求了y - y2 和 x - x2 最大公约数，所以要保证他俩都不是 0 ，防止除零错误。
     *
     * private boolean test(int x1, int y1, int x2, int y2, int x, int y) {
     *     int g1 = gcd(y2 - y1, x2 - x1);
     *     if(y == y2 && x == x2){
     *         return true;
     *     }
     *     int g2 = gcd(y - y2, x - x2);
     *     return (y2 - y1) / g1 == (y - y2) / g2 && (x2 - x1) / g1 == (x - x2) / g2;
     * }
     *
     * 有了 test 函数，接下来，我们只需要三层遍历。前两层遍历选择两个点的所有组合构成一条直线，
     * 第三层遍历其他所有点，来判断当前点在不在之前两个点组成的直线上。
     *
     * 需要注意的是，因为我们两点组成一条直线，必须保证这两个点不重合。
     * 所以我们进入第三层循环之前，如果两个点相等就可以直接跳过。
     *
     * if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
     *     continue;
     * }
     *此外，我们还需要考虑所有点都相等的情况，这样就可以看做所有点都在一条直线上。
     *
     * 还有就是点的数量只有两个，或者一个，零个的时候，直接返回点的数量即可。
     *
     *综上所述，代码就出来了。其中test有多种写法
     *
     * @param points
     * @return
     */
    public int maxPoints(int[][] points) {
        if (points.length < 3) {
            return points.length;
        }
        int i = 0;
        for (; i < points.length - 1; i++) {
            if (points[i][0] != points[i + 1][0] || points[i][1] != points[i + 1][1]) {
                break;
            }

        }
        if (i == points.length - 1) {
            return points.length;
        }
        int max = 0;
        for (i = 0; i < points.length; i++) {
            for (int j = i + 1; j < points.length; j++) {
                if (points[i][0] == points[j][0] && points[i][1] == points[j][1]) {
                    continue;
                }
                int tempMax = 0;
                for (int k = 0; k < points.length; k++) {
                    if (k != i && k != j) {
                        if (test(points[i][0], points[i][1],
                                points[j][0], points[j][1],
                                points[k][0], points[k][1])) {
                            tempMax++;
                        }
                    }

                }
                if (tempMax > max) {
                    max = tempMax;
                }
            }
        }
        //加上直线本身的两个点
        return max + 2;
    }

    private boolean test(int x1, int y1, int x2, int y2, int x, int y) {
        int g1 = gcd(y2 - y1, x2 - x1);
        if(y == y2 && x == x2){
            return true;
        }
        int g2 = gcd(y - y2, x - x2);
        return (y2 - y1) / g1 == (y - y2) / g2 && (x2 - x1) / g1 == (x - x2) / g2;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = a % b;
            a = b;
            b = temp;
        }
        return a;
    }

  /*  作者：windliang
    链接：https://leetcode.cn/problems/max-points-on-a-line/solutions/36797/xiang-xi-tong-su-de-si-lu-fen-xi-duo-jie-fa-by--35/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
