package LeetCode算法20天.前缀和;

/**
 * @author by KingOfTetris
 * @date 2023/4/24
 */
public class NumArray {

    private int[] preSum;

    public NumArray(int[] nums) {
        preSum = new int[nums.length + 1]; //从1开始
        //计算前缀和
        for (int i = 1; i < preSum.length; i++) { //从1开始一直到n
            preSum[i] = preSum[i - 1] + nums[i - 1];// < n + 1最多到n 那么i - 1最多到n-1所有nums是不会越界的。放心用pre[i-1] + nums[i-1]就行了
        }
    }

    public int sumRange(int left,int right){
        //快速计算区间[left,right]之和
        //核心就下面的 sum[right + 1] - sum[left] //注意这是从1开始的情况，如果从0开始，就要注意一下边界条件
        return preSum[right + 1] - preSum[left];
    }
}
