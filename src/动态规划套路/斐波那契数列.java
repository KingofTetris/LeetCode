package 动态规划套路;

import org.junit.Test;
/**
 * @author KingofTetris
 * @File 斐波那契数列
 * @Time 2021/10/18  17:18
 */
public class 斐波那契数列 {
    @Test
    public void test(){
        long now = System.currentTimeMillis();
        System.out.println(fib(40));
        long end = System.currentTimeMillis();
        System.out.println("暴力递归耗时:" + (end-now)+"ms");

        long now2 = System.currentTimeMillis();
        System.out.println(fib2(40));
        long end2 = System.currentTimeMillis();
        System.out.println("优化递归耗时:" + (end2-now2)+"ms");

        long now3 = System.currentTimeMillis();
        System.out.println(fib3(40));
        long end3 = System.currentTimeMillis();
        System.out.println("自底向上迭代耗时:" + (end3-now3)+"ms");

        long now4 = System.currentTimeMillis();
        System.out.println(fib4(40));
        long end4 = System.currentTimeMillis();
        System.out.println("自底向上迭代优化空间耗时:" + (end4-now4)+"ms");
    }




    //最简单直接的暴力递归解
    public int fib(int n) {
        if (n == 0|| n == 1)
            return n;
        return fib(n-1) + fib(n-2);
    }

    /**优化一下把中间结果存入memo 去除重复计算
     * 自顶向下计算*/
    public int fib2(int N) {
        //初始化全为0的n+1位数组 下标才能有0-n 方便阅读。
        //经常动态规划的数组都会+1.不管是一维还是二维
        int[] memo  = new int[N+1];
        return helper(memo,N);
    }

    public int helper(int[] memo,int n){
        if (n == 0 || n==1)
            return n;
        //开始递归前 先在备忘录里面查一下 如果算过了，就不算了。 相当于是剪枝
        if (memo[n] != 0)
            return memo[n];
        memo[n] = helper(memo,n - 1) + helper(memo,n - 2);
        return memo[n];
    }


    /**自底向上计算 写成迭代*/
    public int fib3(int N){
        //这里判断N==0 return 0 而不是直接return dp[N]
        // 是因为数组如果是new int[1] 那dp[1]会越界，所有特殊处理直接返回0了
        if (N == 0) return 0;
        int[] dp = new int[N+1];
        //base case
        dp[0] = 0; dp[1] = 1;
        //状态转移
        for (int i = 2;i <= N;i++){
            dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[N];
    }
    /**自底向上计算 写成迭代 再观察一下过程，其实求fib(n) 只需要前两个数fib(n-1) fib(n-2)
     * 就必要存储fib(0)....fib(n) 浪费空间*/

    public int fib4(int N){
        if (N == 1 || N==0 ) return N;
        int prev = 0,curr = 1;
        for (int i = 2; i <= N ; i++) {
            int sum = prev + curr;
            prev = curr;
            curr = sum;
        }
        return curr;
    }
}
