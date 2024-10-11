package 校招笔试真题.携程._20241010;
import java.util.Scanner;

/**
 * @author by KingOfTetris
 * @date 2024/10/10
 */

//这个DP居然有21.43 亏贼
public class 游游的最大GCD_dp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int m = sc.nextInt();
        int[] nums = new int[n];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        //dp[i][j]表示前i个nums分成j段时最大的gcd之和。

        //k要分成j-1段，至少要j-1个数。所以k从j-1到i-1.
        //那么
        //dp[i][j]= max(dp[k][j-1]+GCD(a_k+1,…., a_i))
        //也就是分成k个数分成j-1段的最大GCD值加上 a_k+1 ，a_k+2,...,a_i的GCD

        //最终dp[n][m]就是答案
        int[][] st = buildSTTable(nums); //st表
        int[] log = new int[n + 1];

        int[][] dp = new int[n + 1][m + 1];

        for (int i = 1; i <= n; i++) {
            dp[i][1] = queryGCD(st, log, 0, i - 1);
        }

        for (int j = 2; j <= m; j++) {
            for (int i = j; i <= n; i++) {
                for (int k = j - 1; k < i; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[k][j - 1] + queryGCD(st, log, k, i - 1));
                }
            }
        }

        System.out.println(dp[n][m]);
    }


    /**
     * 构建Sparse table，稀疏表
     * @param arr
     * @return
     */
    public static int[][] buildSTTable(int[] arr) {
        int n = arr.length;
        int[] log = new int[n + 1];
        for (int i = 2; i <= n; i++) {
            log[i] = log[i / 2] + 1;
        }

        int[][] st = new int[n][log[n] + 1];
        for (int i = 0; i < n; i++) {
            st[i][0] = arr[i];
        }

        for (int j = 1; j <= log[n]; j++) {
            for (int i = 0; i <= n - (1 << j); i++) {
                st[i][j] = gcd(st[i][j - 1], st[i + (1 << (j - 1))][j - 1]);
            }
        }
        return st;
    }

    //查询区间gcd
    public static int queryGCD(int[][] st, int[] log, int l, int r) {
        int j = log[r - l + 1];
        return gcd(st[l][j], st[r - (1 << j) + 1][j]);
    }

    private static int gcd(int a,int b){
        return b == 0 ? a : gcd(b, a % b);
    }
}
