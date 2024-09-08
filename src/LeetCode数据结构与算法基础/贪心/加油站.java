package LeetCode数据结构与算法基础.贪心;

/**
 * @author by KingOfTetris
 * @date 2024/9/7
 */
public class 加油站 {

    //gas = [1,2,3,4,5]
    //cost =[3,4,5,1,2]
    //      -2,-2,-2,3,3
    //暴力法 最后一个用例超时，故意来恶心你的
    public int canCompleteCircuit2(int[] gas, int[] cost) {
        int n = cost.length;
        for (int i = 0; i < n; i++) {
            int rest = gas[i] - cost[i]; // 记录剩余油量
            //循环去找加油站
            int index = (i + 1) % n;
            //如果还有汽油，还没跑完一圈，就继续跑
            while (rest > 0 && index != i) { // 模拟以i为起点行驶一圈（如果有rest==0，那么答案就不唯一了）
                //修改汽油量
                rest += gas[index] - cost[index];
                //index往后走
                index = (index + 1) % n;
            }
            // 如果以i为起点跑一圈，剩余油量>=0，返回该起始位置
            if (rest >= 0 && index == i) return i;
        }
        return -1;
    }

    //贪心
    //一旦区间和 < 0 就选择从下一个新的加油站开始。
    public int canCompleteCircuit(int[] gas, int[] cost) {
        int curSum = 0;
        int totalSum = 0;
        int start = 0;
        for (int i = 0; i < gas.length; i++) {
            curSum += gas[i] - cost[i];
            totalSum += gas[i] - cost[i];
            if (curSum < 0) {   // 当前累加rest[i]和 curSum一旦小于0
                start = i + 1;  // 起始位置更新为i+1
                curSum = 0;     // curSum从0开始
            }
        }
        //如果总油量-总消耗 < 0，那么就不可能跑一圈。
        if (totalSum < 0) return -1; // 说明怎么走都不可能跑一圈了
        return start;
    }
}
