package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 螺旋矩阵II
 * @Time 2021/10/14  0:02
 */

//给你一个正整数 n ，生成一个包含 1 到 n^2 所有元素，
// 且元素按顺时针顺序螺旋排列的 n x n 正方形矩阵 matrix 。
//    1 <= n <= 20
public class 螺旋矩阵II {

    @Test
    public void test(){
        int[][] ans = generateMatrix(3);
        for (int i = 0; i < ans.length; i++) {
            for (int j = 0; j < ans[0].length; j++) {
                System.out.print(ans[i][j] + "\t");
            }
            System.out.println();
        }
    }

    //其实像是遍历。
    public int[][] generateMatrix(int n) {

        int[][] ans = new int[n][n];
        //一定要先走列坐标（向后走）y=1，然后向下走x=1，然后向前y=1，最后向上走x=-1
        //这个顺序是不能改的
        int[] dx={0,1,0,-1};
        int[] dy={1,0,-1,0};
        for (int i = 0,startX =0,startY =0,d=0; i < n*n; i++) {
            ans[startX][startY] = i + 1;
            //curX,curY就是上下左右走 先往右走，再往下，再往左，再往上
            int curX = startX + dx[d],curY = startY + dy[d];
            //超过边界，或者已经有数了，就不走那条路
            if (curX < 0 || curY < 0 || curX >= n || curY >= n || ans[curX][curY] != 0){
                //+1 对4取余就可以取到上下左右 满足上面的条件就换个方向走
                d = (d + 1) % 4;
            }

            //不要搞错了，StartY是列坐标
            startX += dx[d];
            startY += dy[d];
        }
        //最后返回这个数组就行了
        return ans;

    }
}
