package LeetCode算法20天.回溯;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2023/9/11
 */
public class N皇后 {


    @Test
    public void test1(){
        double a = 0.152;
        double aa = 0.24;
        if (a + aa == 0.392){
            System.out.println(1);
        }
    }

    @Test
    public void test() {
        List<List<String>> lists = solveNQueens(9);
        for (List<String> l : lists) {
            System.out.println(l);
        }
    }

    //用String表示每行的状态 .代表空 Q表示皇后
    List<List<String>> res = new LinkedList<>();
//    List<String> path = new LinkedList<>(); 这题比较特殊，最后要转化成List<String>就不需要path了。

    public List<List<String>> solveNQueens(int n) {
        char[][] chessboard = new char[n][n];
        for (char[] chars : chessboard) {
            Arrays.fill(chars,'.');
        }
        //从第一行下
        backtracking(chessboard,n,0);
        return res;
    }

    private void backtracking(char[][] chessboard, int n, int row) {
         if (row == n){
             res.add(Arrays2List(chessboard));
             return;
         }

         //每行一列一列去放
        for (int col = 0; col < n; col++) {
            //如果有效
            if (isValid(chessboard,n,row,col)){
                chessboard[row][col] = 'Q';
                //回溯，其实就是递归。
                backtracking(chessboard,n,row+1);
                //修改重置
                chessboard[row][col] = '.';
            }
        }
    }

    //数组转List
    private List<String> Arrays2List(char[][] chessboard) {
        List<String> list = new ArrayList<>();
        for (char[] c : chessboard) {
            //直接添加一行
            list.add(new String(c));
        }
        return list;
    }


    public boolean isValid(char[][] chessboard, int n, int row, int col) {
        //然后判断是否能往i,j放棋子

        //不用检查行，因为这一行一定是空的，剪掉他。
        // 检查列 因为我们是一层一层放 row后面都是空的，所以没必要到n
        for (int i = 0; i < row; ++i) { // 相当于剪枝
            if (chessboard[i][col] == 'Q') {
                return false;
            }
        }

        // 检查45度对角线上的元素
        for (int i = row - 1, j = col - 1; i >= 0 && j >= 0; i--, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row + 1, j = col + 1; i < n && j < n; i++, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        // 检查135度对角线上的元素
        for (int i = row + 1, j = col - 1; i < n && j >= 0; i++, j--) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }
        for (int i = row - 1, j = col + 1; i >= 0 && j < n; i--, j++) {
            if (chessboard[i][j] == 'Q') {
                return false;
            }
        }

        return true;
    }
}
