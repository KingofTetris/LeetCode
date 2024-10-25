package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/24
 */
public class 加油站 {

    //总油量，总耗油。
    //当前油量
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalDiff = 0; //总油量-总耗油
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i]; //从当前站到下一栈需要消耗的油量。
            totalDiff += gas[i] - cost[i]; //计算总差距
            //如果从当前站出发到不了下一站就重新开始计算
            if (curSum < 0) {
                start = i + 1;  // 更新站点。
                curSum = 0;     // curSum从0开始
            }
        }
        //如果总油量-总消耗 < 0，那么就不可能跑一圈。
        if (totalDiff < 0) return -1; // 说明怎么走都不可能跑一圈了
        return start;
    }
}
