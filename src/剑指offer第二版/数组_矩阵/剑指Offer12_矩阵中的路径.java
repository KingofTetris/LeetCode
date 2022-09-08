package 剑指offer第二版.数组_矩阵;

/**
 * @Author KingofTetris
 * @Date 2022/7/15 14:44
 * 给定一个 m x n 二维字符网格 board 和一个字符串单词 word 。
 * 如果 word 存在于网格中，返回 true ；否则，返回 false 。
 * 单词必须按照字母顺序，通过相邻的单元格内的字母构成，其中“相邻”单元格是那些水平相邻或垂直相邻的单元格。
 * 同一个单元格内的字母不允许被重复使用。
 *
 * 例如，在下面的 3×4 的矩阵中包含单词 "ABCCED"（单词中的字母已标出）。
 * [A,B,C,E
 *  S,F,C,S
 *  A,D,E,E]
 *
 *
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/ju-zhen-zhong-de-lu-jing-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 剑指Offer12_矩阵中的路径 {
    public boolean exist(char[][] board, String word) {
        /**
         * 从某个元素出发深度遍历矩阵
         */
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                /**
                 * 从左上角开始搜索
                 */
                if (dfs(board,word,i,j,0)) //搜索成功返回true
                    return true;
            }
        }
        return false; //全部遍历完了都没有
    }

    /**
     * 每个元素向四周去深度搜索
     * 输入的参数是每个元素的下标索引和数组
     * @param i
     * @param j
     * @return
     */
    public boolean dfs(char[][] board,String word,int i,int j,int k){
        if(i >= board.length || i < 0 || j >= board[0].length || j < 0 || board[i][j] != word.charAt(k)) return false; //如果超出边界，或者不匹配就返回false，回溯
        if(k == word.length() - 1) return true;//字符匹配而且是最后一位了，就是匹配成功
        board[i][j] = '\0'; //写成"\0"就是个标记符号，这样使得board[i][j] != word.charAt(k) 一定成立
        //当然也可以拿个visited数组来表示走没走过 比如int[][] visited，走过就标记为1 不过有用到O(mn)的空间。
      /*  int[] dx = {-1,0,1,0},dy = {0,1,0,-1}; //四个方向。{-1,0},{0,1},{1,0},{0,-1} 左上下右
        for (int q = 0; q < 4; q++) {
            int m = i + dx[q]; //
            int n = j + dy[q]; //
            if (dfs(board,word,m,n,k+1)) return true;
        }
        */
        //这个方向数组和for循环就是为了替代下面这个特别长的表达式，但是我感觉不如直接写下面这个清晰
        //直接就是左右上下
        boolean res = dfs(board, word, i + 1, j, k + 1) || dfs(board, word, i - 1, j, k + 1) ||
                dfs(board, word, i, j + 1, k + 1) || dfs(board, word, i , j - 1, k + 1);
                //四个方向都去探索，只要有一个不是false就会往那个方向一直深度搜索下去。
        board[i][j] = word.charAt(k); //因为上面改了[i][j] 最后把他还原回去，也是重点。有了这句话才能实现回溯
        return res;
    }

    public boolean dfs2(char[][] board,String word,int i,int j,int k){
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] != word.charAt(k)) return false;
        if (k == word.length() - 1) return true;
        board[i][j] = '\0';
        boolean res = dfs2(board,word,i + 1,j,k+1) || dfs2(board,word,i - 1,j,k+1) ||
                dfs2(board,word,i ,j + 1,k+1) || dfs2(board,word,i ,j - 1,k+1);
        board[i][j] = word.charAt(k);
        return res;
    }
}
