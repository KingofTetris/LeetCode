package LeetCode数据结构与算法基础.动态递归._01背包;



//每个物品只能取1次。
public class BagProblem {
    public static void main(String[] args) {
        int[] weight = {1,3,4}; // 物品重量
        int[] value = {15,20,30};// 物品价值
        int bagSize = 4;//背包容量
        testWeightBagProblem(weight,value,bagSize);
    }

    /**
     * 动态规划获得结果
     * @param weight  物品的重量
     * @param value   物品的价值
     * @param bagSize 背包的容量
     */
    public static void testWeightBagProblem(int[] weight, int[] value, int bagSize){

        // 创建dp数组
        int goods = weight.length;  // 获取物品的数量
        int[][] dp = new int[goods][bagSize + 1]; //bagSize + 1表示物体重量从0开始，到bagSize结束
        // 初始化dp数组
        // 创建数组后，其中第一列，也就是bagSize=0时，谁也放不了，则默认的值就是0
        for (int j = weight[0]; j <= bagSize; j++) {
            dp[0][j] = value[0];
        }

        // 填充dp数组
        //首先遍历物品的重量
        for (int i = 1; i < weight.length; i++) {
            //然后遍历剩余的包重
            for (int j = 1; j <= bagSize; j++) {
                if (j < weight[i]) {
                    /**
                     * 当前背包的容量都没有当前物品i大的时候，是放不了物品i的
                     * 那么前i-1个物品能放下的最大价值就是当前情况的最大价值
                     */
                    dp[i][j] = dp[i-1][j];
                } else {
                    /**
                     * 当前背包的容量可以放下物品i
                     * 那么此时分两种情况：
                     *    1、不放物品i
                     *    2、放物品i
                     * 比较这两种情况下，哪种背包中物品的最大价值最大
                     */
                    //dp[i-1][j]就是不放入物品i,背包容量的j的最大价值
                    //dp[i-1][j-weight[i]] + value[i] 就是放入物品i,背包容量为j-weight[i]的最大价值。
                    dp[i][j] = Math.max(dp[i-1][j] , dp[i-1][j-weight[i]] + value[i]);
                }
            }
        }

        // 打印dp数组
        for (int i = 0; i < goods; i++) {
            for (int j = 0; j <= bagSize; j++) {
                System.out.print(dp[i][j] + "\t");
            }
            System.out.println("\n");
        }
    }
}
