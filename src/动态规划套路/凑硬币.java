package 动态规划套路;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author KingofTetris
 * @File 凑硬币
 * @Time 2021/10/18  19:58
 */

//这题其实就是Leetcode 零钱兑换 II
    //好像是什么完全背包问题。所以什么是完全背包？？
public class 凑硬币 {

    @Test
    public void test(){
        int[] coins = {1,2,5};
        int amount = 20;
        int change = change(amount, coins);
        System.out.println(change);
    }

    //
    public int change(int amount, int[] coins) {
        return ways(coins, 0, amount);
    }

    private int ways(int[] coins, int i, int amount) {
        if (amount < 0) {
            //amount小于0，表示上一步选择后超出了总金额，该组合不符合
            return 0;
        }
        if (i == coins.length) {
            //硬币都选完了，总金额刚好为0，则当前组合符合要求，否则不符合
            return amount == 0 ? 1 : 0;
        }
        //不选择当前面额硬币，i指向下一面额硬币，总金额数不变
        int p1 = ways(coins, i + 1, amount);
        //选择当前面额硬币，下一步还可以继续选择，i不变，总金额-当前面额
        int p2 = ways(coins, i, amount - coins[i]);
        //两种情况和
        return p1 + p2;
    }

    /*作者：liao47
    链接：https://leetcode.cn/problems/coin-change-ii/solutions/2238588/dong-tai-gui-hua-shi-zen-yao-tui-dao-guo-ejid/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
