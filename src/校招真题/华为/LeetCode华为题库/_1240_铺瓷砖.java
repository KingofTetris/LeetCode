package 校招真题.华为.LeetCode华为题库;

import org.junit.Test;

import java.util.Arrays;
import java.util.LinkedList;

/**
 * @author by KingOfTetris
 * @date 2023/7/27
 */
public class _1240_铺瓷砖 {

    static int solution = 0;
    LinkedList<int[]> solutions = new LinkedList<>();
    @Test
    public void test(){
        System.out.println(tilingRectangle(11, 13));
        for (int[] ints : solutions) {
            System.out.println(Arrays.toString(ints));
        }
    }

    private int res; // 答案。会在DFS过程中更新。
    public int tilingRectangle(int n, int m) {
        boolean[][] tiled = new boolean[n][m];
        res = m * n;
        process(tiled, 0);
        return res;
    }

    // DFS：当前地上瓷砖状态为tiled，已经铺了count个瓷砖，继续铺下去把地面铺满
    private void process(boolean[][] tiled, int count) {
        if (count >= res) { // 剪枝
            return;
        }
        int[] emptyPos = checkEmpty(tiled); // 地上没瓷砖的第一个位置
        if (emptyPos[0] == -1 && emptyPos[1] == -1) { // 已经铺完了所有地方，收集答案
            res = Math.min(res, count);
            return;
        }
        // 从大到小，开始尝试铺瓷砖
        int maxLen = Math.min(tiled.length - emptyPos[0], tiled[0].length - emptyPos[1]);
        for (int len = maxLen; len >= 1; len--) {
            // 尝试用len*len的瓷砖的左上角去对齐地上没瓷砖的这个位置
            if (setStatus(tiled, emptyPos[0], emptyPos[1], emptyPos[0] + len - 1, emptyPos[1] + len - 1, false)) {
                process(tiled, count + 1);
                setStatus(tiled, emptyPos[0], emptyPos[1], emptyPos[0] + len - 1, emptyPos[1] + len - 1, true);
            }
        }
    }

    // 尝试反转 (row1, col1) 和 (row2, col2) 之间的铺瓷砖状态
    // 必须确保这个区域内初始都是hasTiled状态，否则不会反转
    private boolean setStatus(boolean[][] tiled, int row1, int col1, int row2, int col2, boolean hasTiled) {
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                if (tiled[i][j] != hasTiled) {
                    return false;
                }
            }
        }
        for (int i = row1; i <= row2; i++) {
            for (int j = col1; j <= col2; j++) {
                tiled[i][j] = !tiled[i][j];
            }
        }
        return true;
    }

    // 顺序遍历寻找第一个没铺瓷砖的位置（从上到下，从左到右）
    private int[] checkEmpty(boolean[][] tiled) {
        int i = 0, j = 0;
        for (; i < tiled.length; i++) {
            for (j = 0; j < tiled[0].length; j++) {
                if (!tiled[i][j]) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[]{-1, -1};
    }

   /* 作者：arglone
    链接：https://leetcode.cn/problems/tiling-a-rectangle-with-the-fewest-squares/solution/java-dfsbao-li-di-gui-by-arglone-alau/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/


    /**
     * 递归贪心选择n m较小的边，一直化到n,m有一个为1，或者n == m停止
     * 虽然能解，但是这个方法不能得到最少的砖。
     * @param n
     * @param m
     * @return
     */
    public int tilingRectangle2(int n, int m) {
        //递归终止条件
        if (n == m){
            solutions.add(new int[]{n,n});
            solution++;
            return solution;
        }
        if (n == 1 || m == 1){
            if (n == 1 && m != 1){
                for (int i = 0; i < m; i++) {
                    solutions.add(new int[]{1,1});
                }
                solution += m;
                return solution;
            }
            if (m == 1 && n != 1){
                for (int i = 0; i < n; i++) {
                    solutions.add(new int[]{1,1});
                }
                solution += n;
                return solution;
            }
        }
        //如果终止条件不成立，递归贪心选择较小者
        int min = Math.min(n,m);
        if (min == n){
            tilingRectangle(n,m-n);
            solutions.add(new int[]{n,n});
            solution++;
        }
        else {
            tilingRectangle(n-m,m);
            solutions.add(new int[]{m,m});
            solution++;
        }
        return solution;
    }


}
