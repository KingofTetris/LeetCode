package LeetCode_HOT100题;

/**
 * @Author KingofTetris
 * @Date 2022/9/30 10:44
 */
public class 正则表达式匹配 {

    public boolean isMatch2(String s, String p) {
        return s.matches(p);
    }

    /**
     * dp[i][j]表示s到第i个字符，p到第j个字符能否匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s,String p){
        if (s == null || p == null) return false;

        int n = s.length();
        int m = p.length();
        boolean[][] dp = new boolean[n + 1][m + 1];

        //dp初始值
        dp[0][0] = true;
        //dp[0][1] = false; 只是为了清晰才写出，其实没必要，本来就是false
        //而且如果m+1=1的话，还会越界。
        for (int j = 2; j <= m; j++) { //j>=2的情况
            if (p.charAt(j - 1) == '*'){ //如果j的位置是*号 可以代表0到N个字符 就相当于把 * 和 j - 1消除了。那么就去判断dp[0][j-2]
                dp[0][j] = dp[0][j -2 ];
            }else {
                dp[0][j] = false;//其他情况都是false。其实这句话没必要写，因为初始就全是false
            }
        }


        //开始计算dp
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                //当j不为*号。为普通字符或者.
                if (p.charAt(j - 1) != '*'){
                    if (p.charAt(j - 1) == '.' || p.charAt(j - 1) == s.charAt(i - 1)){
                        dp[i][j] = dp[i-1][j-1];
                    }
                }

                //如果j为*号，那么再次分类讨论
                else{
                    //1. j-1个字符不匹配
                    // 例如 a
                    //     b*
                    if (p.charAt(j - 2) != s.charAt(i - 1) && p.charAt(j-2) != '.'){
                        dp[i][j] = dp[i][j-2];
                    }
                    else { //匹配又分成匹配0，1，N个
                        dp[i][j] = dp[i][j-2] || dp[i][j-1] || dp[i - 1][j];//三个成立一个就可以
                    }
                }
            }
        }

        return dp[n][m];
    }
}
