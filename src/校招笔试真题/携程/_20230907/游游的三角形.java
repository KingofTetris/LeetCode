package 校招笔试真题.携程._20230907;

import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2023/9/7
 */
public class 游游的三角形 {
    /**
     * 这题写复杂了，不用分成四个象限
     * 对于每个点，如果属于'you'中的一个字符，比如说是'y'，
     * 以它为直角，然后再找到它左边‘o’，上边的点'u'的个数相乘+左边'u'与上边'o'的个数相乘的个数
     * 同样的，需要找到其余三个象限（左边+下边，右边+上边，右边+下边）相加即可
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt();
        int m = in.nextInt();
        int[][][] dp = new int[n + 1][m + 1][3];
        char[] c = new char[]{'y', 'o', 'u'};
        //构造
        for (int i = 0; i < n; ++i) {
            String s = in.next();
            for (int j = 0; j < s.length(); ++j) {
                for (int k = 0; k < 3; ++k) {
                    dp[i + 1][j + 1][k] = dp[i + 1][j][k] + dp[i][j + 1][k]
                            - dp[i][j][k] + (c[k] == s.charAt(j) ? 1 : 0);
                }
            }
        }
        long sum = 0;
        int[][] dir = new int[4][3];
        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= m; ++j) {
                int target = isTargetChar(dp, i, j);
                if (target == -1) continue;
                for (int k = 0; k < 3; ++k) {
                    dir[0][k] = dp[i][j - 1][k] - dp[i - 1][j - 1][k];//左边k字符的个数
                    dir[1][k] = dp[i - 1][j][k] - dp[i - 1][j - 1][k];//上边k字符的个数
                    dir[2][k] = dp[n][j][k]
                            - dp[i][j][k]
                            - dp[n][j - 1][k]
                            + dp[i][j - 1][k];//下边k字符的个数
                    dir[3][k] = dp[i][m][k]
                            - dp[i][j][k]
                            - dp[i - 1][m][k]
                            + dp[i - 1][j][k];//右边k字符的个数
                }
                //lu
                sum += (long) dir[0][(target + 1) % 3]
                        * dir[1][(target + 2) % 3]
                        + (long) dir[1][(target + 1) % 3]
                        * dir[0][(target + 2) % 3];
                //ld
                sum += (long) dir[0][(target + 1) % 3]
                        * dir[2][(target + 2) % 3]
                        + (long) dir[2][(target + 1) % 3]
                        * dir[0][(target + 2) % 3];
                //ru
                sum += (long) dir[3][(target + 1) % 3]
                        * dir[1][(target + 2) % 3]
                        + (long) dir[1][(target + 1) % 3]
                        * dir[3][(target + 2) % 3];
                //rd
                sum += (long) dir[3][(target + 1) % 3]
                        * dir[2][(target + 2) % 3]
                        + (long) dir[2][(target + 1) % 3]
                        * dir[3][(target + 2) % 3];
            }
        }
        System.out.println(sum);
    }

    static int isTargetChar(int[][][] dp, int x, int y) {
        for (int i = 0; i < 3; ++i) {
            int cnt = dp[x][y][i] + dp[x - 1][y - 1][i] - dp[x - 1][y][i] - dp[x][y - 1][i];
            if (cnt != 0) return i;
        }
        return -1;
    }

  /*  作者：织梦呀
    链接：https://www.nowcoder.com/discuss/529398383690137600?sourceSSR=post
    来源：牛客网*/
}
