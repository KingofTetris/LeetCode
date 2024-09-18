package 剑指offer第二版.数组_矩阵;

import org.junit.Test;

import java.util.ArrayList;

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


    /**
     * 最后两个用例超时，TMD还能怎么剪?
     */
    @Test
    public void test(){
        char[][] board = {
                {'b','a','a','b','a','b'},
                {'a','b','a','a','a','a'},
                {'a','b','a','a','a','b'},
                {'a','b','a','b','b','a'},
                {'a','a','b','b','a','b'},
                {'a','a','b','b','b','a'},
                {'a','a','b','a','a','b'}
        };
        String s = "aabbbbabbaababaaaabababbaaba";
        boolean exist = exist(board, s);
        System.out.println(exist);
    }

    boolean[][] visited;
    int[] dx = {0,0,1,-1};
    int[] dy = {1,-1,0,0};
    ArrayList<String> resList = new ArrayList<>();

    public boolean exist(char[][] board, String word) {
        int n = board.length;
        int m = board[0].length;
        visited = new boolean[n][m];
        //DFS搜索罢了。
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                StringBuffer sb = new StringBuffer();
                sb.append(board[i][j]);
                visited[i][j] = true;
                dfs(board,word,i,j,sb,0); //找到了一条路径等于word就返回true
                visited[i][j] = false;
            }
        }
        return !resList.isEmpty();
    }

    //index是用来判断word的下标
    private void dfs(char[][] board, String word, int i, int j,StringBuffer sb,int index) {
//        System.out.println(sb);
        //剪枝，如果字符对应不上也没必要DFS了
        if (word.charAt(index) != board[i][j]){
            return;
        }
        if (index == word.length() - 1){
            //找到长度一致的，就可以直接剪枝结束了。
            //再往后更长的，也不可能匹配了。
            resList.add("1");
            return;
        }
        int n = board.length;
        int m = board[0].length;
        //否则就去DFS
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k];
            int y = j + dy[k];
            if (x >= 0 && x < n && y >= 0 && y < m && !visited[x][y]){
                visited[x][y] = true;
                sb.append(board[x][y]);
                index++;
                dfs(board,word,x,y,sb,index);
                index--;
                sb.deleteCharAt(sb.length() - 1);
                visited[x][y] = false;
            }
        }
    }
}
