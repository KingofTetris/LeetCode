package LeetCode数据结构基础.差分数组;

/**
 * @author by KingOfTetris
 * @date 2023/4/24
 */


//差分数组快速让区间进行加减
public class Difference {

    //差分数组
    private int[] diff;

    public Difference(int[] nums){
        if (nums.length != 0)
        {
            diff = new int[nums.length];//差分不需要n+1
            diff[0] = nums[0];
            for (int i = 1; i < nums.length; i++) {
                diff[i] = nums[i] - nums[i - 1];
            }
        }
    }

    /**
     * 把nums[left,right]区间的元素都+val
     * 核心就两句话
     * d[left] += val;
     * d[right + 1] -= val;
     * @param left
     * @param right
     * @param val
     */
    public void increment(int left,int right,int val){
        diff[left] += val;
        if (right + 1 < diff.length)
            diff[right + 1] -= val;
    }

    /**
     * 根据差分数组还原原来的数组
     * @return
     */
    public int[] result(){
        int[] res = new int[diff.length];
        res[0] = diff[0];
        for (int i = 1; i < diff.length; i++) {
            res[i] = diff[i] + res[i + 1];
        }
        return res;
    }
}
