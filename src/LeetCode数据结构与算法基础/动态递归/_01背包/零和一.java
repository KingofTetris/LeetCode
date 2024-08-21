package LeetCode数据结构与算法基础.动态递归._01背包;

class 零和一 {

    public int findMaxForm(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        //DP[i][j] 代表 i个0 j个1 的背包 最多能装多少个元素
        //
        //假设装入一个有x个0,y个1的元素那么
        //递推公式 dp[i][j] = Math.max(dp[i][j],dp[i-x][j-y] + 1)

        //初始值 其他也全是0，因为递推需要比较max，初始化一个最小元素即可
        dp[0][0] = 0;

        //遍历物品和背包
        for(int k = 0;k < strs.length;k++){
            int x = 0,y = 0;
            //求出这个物品的x,y
            char[] arr = strs[k].toCharArray();
            for(char c : arr){
                if(c == '0') x++;
                else y++;
            }
            //遍历背包
            for(int i = m;i >= x;i--)
             for(int j = n;j >= y;j--){
                dp[i][j] = Math.max(dp[i][j],dp[i-x][j-y] + 1);
             }
        }

        return dp[m][n];
    }
}
