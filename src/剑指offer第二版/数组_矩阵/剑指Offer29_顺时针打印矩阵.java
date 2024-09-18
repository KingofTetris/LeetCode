package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/7/29 16:10
 * 输入一个矩阵，按照从外向里以顺时针的顺序依次打印出每一个数字。
 * 示例 1：
 *
 * 输入：matrix = [[1,2,3],[4,5,6],[7,8,9]]
 * 输出：[1,2,3,6,9,8,7,4,5]
 * 示例 2：
 *
 * 输入：matrix = [[1,2,3,4],[5,6,7,8],[9,10,11,12]]
 * 输出：[1,2,3,4,8,12,11,10,9,5,6,7]
 *
 * 限制：
 *
 * 0 <= matrix.length <= 100
 * 0 <= matrix[i].length <= 100
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer29_顺时针打印矩阵 {

    @Test
    public void test(){

    }

    /**
     * 比较巧妙的牛逼解法
     * 模拟从左到右，从上到下，从右到左，从下到上
     * @param matrix
     * @return
     */
    public int[] spiralOrder(int[][] matrix) {
        if(matrix.length == 0) return new int[0];
        int l = 0, r = matrix[0].length - 1, t = 0, b = matrix.length - 1;//从左到右，从上到下都是从0开始
        //矩阵左、右、上、下 四个边界 l , r , t , b ，用于打印的结果列表 res 。
        int x = 0; //只是为了给res当指针
        int[] res = new int[(r + 1) * (b + 1)];
        /**
         * 循环打印： “从左向右、从上向下、从右向左、从下向上” 四个方向循环，
         * 每个方向打印中做以下三件事 （各方向的具体信息见下表） ；
         * 1.根据边界打印，即将元素按顺序添加至列表 res 尾部；
         * 2.边界向内收缩 1 （代表已被打印）；
         * 3.判断是否打印完毕（边界是否相遇），若打印完毕则跳出。
         *
         * https://leetcode.cn/problems/shun-shi-zhen-da-yin-ju-zhen-lcof/solution/mian-shi-ti-29-shun-shi-zhen-da-yin-ju-zhen-she-di/
         * 去看上面这个网站的那张表很清晰
         */
        while(true) {
            for(int i = l; i <= r; i++) res[x++] = matrix[t][i]; // left to right.
            if(++t > b) break;
            for(int i = t; i <= b; i++) res[x++] = matrix[i][r]; // top to bottom.
            if(l > --r) break;
            for(int i = r; i >= l; i--) res[x++] = matrix[b][i]; // right to left.
            if(t > --b) break;
            for(int i = b; i >= t; i--) res[x++] = matrix[i][l]; // bottom to top.
            if(++l > r) break;
        }
        return res;
    }
}
