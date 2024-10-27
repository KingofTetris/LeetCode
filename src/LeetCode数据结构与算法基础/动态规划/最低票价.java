package LeetCode数据结构与算法基础.动态规划;

/**
 * @author by KingOfTetris
 * @date 2024/10/27
 */
public class 最低票价 {

    /**
     * 注 2：动态规划有「选或不选」和「枚举选哪个」两种基本思考方式。
     * 在做题时，可根据题目要求，选择适合题目的一种来思考。本题用到的是「枚举选哪个」。
     *
     * 这句话非常重要啊，像背包就是选或者不选，像这题就是枚举选哪个,3个只能选1个。
     * 而不是多个可以多个都选，或者一个都不选。
     *
     *
     * @param days
     * @param costs
     * @return
     */
    //days是旅行日程，costs是三种票价，1天，7天，15天。
    //days是升序数组
    public int mincostTickets(int[] days, int[] costs) {
        int lastDay = days[days.length - 1];//从lastDay开始计算
        boolean[] isTravel = new boolean[lastDay + 1];

        for (int d : days) {
            isTravel[d] = true; //记录从0到lastDay哪些天需要旅行
        }

        int[] memo = new int[lastDay + 1];//记忆化搜索，加个memo备忘录
        return dfs(lastDay, isTravel, costs, memo);
    }

    private int dfs(int i, boolean[] isTravel, int[] costs, int[] memo) {
        if (i <= 0) {
            return 0;
        }
        if (memo[i] > 0) { // 之前计算过
            return memo[i];
        }
        if (!isTravel[i]) { //如果第i天不需要旅行，那么返回dfs(i-1)
            return memo[i] = dfs(i - 1, isTravel, costs, memo);
        }
        //如果需要旅行
        //那么就返回i-1,i-7,i-30的最小值。
        return memo[i] = Math.min(dfs(i - 1, isTravel, costs, memo) + costs[0],
                Math.min(dfs(i - 7, isTravel, costs, memo) + costs[1],
                        dfs(i - 30, isTravel, costs, memo) + costs[2]));
    }

  /*  作者：灵茶山艾府
    链接：https://leetcode.cn/problems/minimum-cost-for-tickets/solutions/2936177/jiao-ni-yi-bu-bu-si-kao-dpcong-ji-yi-hua-tkw4/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
