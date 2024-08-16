package LeetCode数据结构与算法基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 螺旋矩阵II
 * @Time 2021/10/14  0:02
 */

//给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，
// 且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//    1 <= n <= 20
    //其实还可以换种方式问，螺旋矩阵[i][j]的值是多少?
public class 螺旋矩阵II {
    @Test
    public void test() {
        int[][] ans = generateMatrix(4);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //2 K神的题解。这个比较好理解
    // 链接：https://leetcode.cn/problems/spiral-matrix-ii/solutions/12594/spiral-matrix-ii-mo-ni-fa-she-ding-bian-jie-qing-x/
    public int[][] generateMatrix(int n) {
        //和螺旋矩阵I差不多，还是上下左右先定义好。
        int l = 0, r = n - 1, t = 0, b = n - 1;
        int[][] mat = new int[n][n];
        int num = 1, tar = n * n;
        while(num <= tar){
            //还是l->r,t->b,r->l,b->t的顺序去走。
            for(int i = l; i <= r; i++) mat[t][i] = num++; // 只要右边还没到尽头，先往右
            //去掉上面一层 t++;
            t++;
            for(int i = t; i <= b; i++) mat[i][r] = num++; // 右边到头再往下
            //去掉右边一层 r--
            r--;
            for(int i = r; i >= l; i--) mat[b][i] = num++; // 下边到头再往左
            //去掉下面一层 b--
            b--;
            for(int i = b; i >= t; i--) mat[i][l] = num++; // 左边到头在网上
            //去掉左边一层 l++
            l++;
        }
        return mat;
     /*   作者：Krahets
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }


    //
}
