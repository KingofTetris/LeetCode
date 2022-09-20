package 每日一题;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 范围求和II_2021_11_7
 * @Time 2021/11/7  12:07
 *
 * 给定一个初始元素全部为 0，大小为 m*n 的矩阵 M 以及在 M 上的一系列更新操作。
 *
 * 操作用二维数组表示，其中的每个操作用一个含有两个正整数 a 和 b 的数组表示，含义是将所有符合 0 <= i < a 以及 0 <= j < b 的元素 M[i][j] 的值都增加 1。
 *
 * 在执行给定的一系列操作后，你需要返回矩阵中含有最大整数的元素个数。
 *
 * 示例 1:
 *
 * 输入:
 * m = 3, n = 3
 * operations = [[2,2],[3,3]]
 * 输出: 4
 * 解释:
 * 初始状态, M =
 * [[0, 0, 0],
 *  [0, 0, 0],
 *  [0, 0, 0]]
 *
 * 执行完操作 [2,2] 后, M =
 * [[1, 1, 0],
 *  [1, 1, 0],
 *  [0, 0, 0]]
 *
 * 执行完操作 [3,3] 后, M =
 * [[2, 2, 1],
 *  [2, 2, 1],
 *  [1, 1, 1]]
 *
 * M 中最大的整数是 2, 而且 M 中有4个值为2的元素。因此返回 4。
 * 注意:
 *
 * m 和 n 的范围是 [1,40000]。
 * a 的范围是 [1,m]，b 的范围是 [1,n]。
 * 操作数目不超过 10000。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/range-addition-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 范围求和II_2021_11_7 {
    @Test
    public void test() {
//        int[][] ops = {{2,2},{3,3}};
        int[][] ops = {{199999,199999}};
//        System.out.println(maxCount(3, 3, ops));
        System.out.println(maxCount2(399999, 399999, ops));
    }

    //暴力模拟 当数很大时会OOM，java.lang.OutOfMemoryError: Java heap space
    public int maxCount(int m, int n, int[][] ops) {
        if (ops.length == 0)
            return m*n;
        int[][] matrix = new int[m][n];
        int count = 0;
        int max = matrix[0][0];
        for (int i = 0; i < ops.length; i++) {
            int[] op = ops[i];
            for (int k = 0; k < op[0] ; k++) {
                for (int l = 0; l < op[1]; l++) {
                    matrix[k][l]++;
                    if (matrix[k][l] == max){
                        count++;
                    }
                    if (matrix[k][l] > max){
                        max = matrix[k][l];
                        count = 1;
                    }
                }
            }
        }
        return count;
    }

    //转化思路，全是正整数，实际上就是找到操作数中被操作最多次的位置。那些数一定最大
    //所以也就是 op[i][0]和op[i][1]中的最小值 即为被操作最多次的位置
    //最好直接返回两数之积即可。
    public int maxCount2(int m,int n,int[][] ops){

        //初始值设为m n 是为了防止ops为空
        int mina = m, minb = n;
        for (int[] op : ops) {
            mina = Math.min(mina, op[0]);
            minb = Math.min(minb, op[1]);
        }
        return mina * minb;
    }
}
