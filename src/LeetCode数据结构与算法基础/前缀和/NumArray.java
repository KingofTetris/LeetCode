package LeetCode数据结构与算法基础.前缀和;

/**
 * @author by KingOfTetris
 * @date 2023/4/24
 */
public class NumArray {

    private int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1]; //从1开始
        //计算前缀和
        /**
         * sum[i]定义为0到i的区间和
         * 前缀和数组的计算公式
         * sum[i] = sum[i - 1] + arr[i] , i >= 1
         * sum[i] = 0,i = 0;
         * sum[L,R] = sum[R] - sum[L - 1]
         *
         * 从1开始算就是 sum[R + 1] - sum[L] R + 1才对应1，2，3，...
         * 因为preSum[0] = 0;
         */
        preSum[0] = 0;
        for (int i = 0; i < nums.length; i++) { //从1开始一直到n
            preSum[i + 1] = preSum[i] + nums[i];
        }
    }

    //快速计算区间[left,right]之和
    //下标从0到N
    public int sumRange(int left, int right) {
        //快速计算区间[left,right]之和
        //核心就下面的 sum[right + 1] - sum[left]

        // 注意这是从1开始的情况，如果从0开始，就要注意一下边界条件
        // 从0开始 就是 preSum[right] - preSum[left - 1]
//        return preSum[right + 1] - preSum[left];
        //总之不是right + 1    - left
//        就是right -  left -1
        //自己试一下就知道了。
        return preSum[right + 1] - preSum[left];
    }
}
