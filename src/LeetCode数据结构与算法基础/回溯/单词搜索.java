package LeetCode数据结构与算法基础.回溯;

public class 单词搜索 {

    public static void main(String[] args) {
        char[][] board = {{'C', 'A', 'A'}, {'A', 'A', 'A'}, {'B', 'C', 'D'}};
        //char[][] board = {{'A','B','C','E'},{'S','F','C','S'},{'A','D','E','E'}};
        String word = "AAB";
        System.out.println(exist(board, word));
    }


    public static int k = 0;

    //深搜 + 回溯即可。
    public static boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                if (dfs(board, i, j, word, k, visited))
                    return true;
            }
        return false;
    }

    private static boolean dfs(char[][] board, int i, int j, String word, int kth, boolean[][] visited) {
        //如果长度相等，说明找到了。
        if (kth >= word.length())
            return true;
        if (i >= 0 && i < board.length && j >= 0 && j < board[0].length //检查坐标是否合法
                && !visited[i][j] //检查坐标是否已经遍历过
                && word.charAt(kth) == board[i][j]) { //检查第K个字符是否等于[i][j] 如果不等于就没必要走这个方向了。
            //走过的元素标记为true,长度+1
            visited[i][j] = true;
            kth++;
            //四个方向去搜索。
            if (        dfs(board, i, j + 1, word, kth, visited)
                    || (dfs(board, i + 1, j, word, kth, visited)
                    || (dfs(board, i, j - 1, word, kth, visited))
                    || (dfs(board, i - 1, j, word, kth, visited))))
                return true; //如果四个里面有一个是true。就返回true

            //回溯之前把visited标记为false。
            visited[i][j] = false;//注意这里需要置为false
        }
        //如果不符合条件直接返回false.
        return false;
    }


}

/*作者：字节研发工程师
链接：https://www.nowcoder.com/exam/test/73033846/submission?pid=31332154
来源：牛客网*/
