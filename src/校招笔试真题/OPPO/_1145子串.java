package 校招笔试真题.OPPO;

import java.util.*;

public class _1145子串 {

    static int MOD = (int) (10e9 + 7);

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        String str = sc.next();
        char[] ch = str.toCharArray();

        //二维DP
        /**
         * DP的含义，DP的初始值，DP的递推公式
         */

        //dp[i][0]表示下标为i时，11的数量
        //dp[i][1]表示下标为i时，114的数量
        //dp[i][2]表示下标为i时，1145的数量
        int[][] dp = new int[n][3];

        for (int i = 1; i < n; i++) {

            //更新i的对应数量为i-1的数量
            dp[i][0] = dp[i - 1][0]; // "11"
            dp[i][1] = dp[i - 1][1]; // "114"
            dp[i][2] = dp[i - 1][2]; // "1145"

            if (ch[i] == '1' && ch[i - 1] == '1') {
                dp[i][0] = (dp[i][0] + 1) % MOD;
            } else if (ch[i] == '4') {
                //
                dp[i][1] = (dp[i][1] + dp[i][0]) % MOD;
            } else if (ch[i] == '5') {
                dp[i][2] = (dp[i][2] + dp[i][1]) % MOD;
            }
        }

        //最后输出长度为n上，1145的数量即可
        System.out.println(dp[n - 1][2]);

    /*    作者：好好好菜啊
        链接：https://www.nowcoder.com/discuss/651923928130072576?sourceSSR=search
        来源：牛客网*/
    }
}
