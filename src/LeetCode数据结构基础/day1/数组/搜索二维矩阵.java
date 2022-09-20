package LeetCode数据结构基础.day1.数组;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 搜索二维矩阵
 * @Time 2021/10/14  17:26
 */

/*240. 搜索二维矩阵 II
        编写一个  ！！高效！！ 的算法来
        搜索 m x n 矩阵 matrix 中的一个目标值 target 。该矩阵具有以下特性：

        每行的元素从左到右升序排列。
        每列的元素从上到下升序排列。
        比如：
        [1,4,7,11,15],
        [2,5,8,12,19],
        [3,6,9,16,22],
        如何能快速地找到某个指定元素？
        */


public class 搜索二维矩阵 {


    @Test
    public void test(){
        int[][] matrix = {{1,4,7,11,15},{2,5,8,12,19},{3,6,9,16,22}};
        System.out.println(searchMatrix(matrix, 8));
    }
    //分析利用已经行列从小到大的排序的性质
    //可以选左下角 或者 右上角的元素当作初始点
    //和target对比大小，这样确定的方向一定是唯一的
    //这样的时间复杂度是O(n+m)
    //这里取右上角的数作为初始点
    public boolean searchMatrix(int[][] matrix, int target) {
        int row = matrix.length;
        int col = matrix[0].length;

        int curRow = 0, curCol = col - 1;


        //终止条件就是走出界
        //curRow > row 或者 curCol < 0
        while(curRow < row && curCol >= 0){
            if(matrix[curRow][curCol] == target)
                return true;
            else if(matrix[curRow][curCol] < target)
                //小于target向下走
                curRow++;
            else
                //大于target向左走
                curCol--;
        }

        //遍历完都找不到就返回false
        return false;
    }
}
