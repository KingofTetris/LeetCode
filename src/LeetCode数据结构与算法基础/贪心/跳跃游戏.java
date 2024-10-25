package LeetCode数据结构与算法基础.贪心;

/**
 * @author by KingOfTetris
 * @date 2024/9/6
 * <p>
 * https://leetcode.cn/problems/jump-game/description/?envType=study-plan-v2&envId=top-interview-150
 */
public class 跳跃游戏 {

    public boolean canJump(int[] nums) {
        if (nums.length == 1) {
            return true;
        }
        //覆盖范围, 初始覆盖范围应该是0，因为下面的迭代是从下标0开始的
        int coverRange = 0;

        //注意这个条件是 i <= coverRange
        for (int i = 0; i <= coverRange; i++) {
            //每次贪心地更新最大覆盖范围 当前下标i + 可以跳跃的范围就是最大范围，
            // 只要覆盖范围可以超过数组长度即可
            coverRange = Math.max(coverRange, i + nums[i]);
            if (coverRange >= nums.length - 1) {
                return true;
            }
        }
        return false;
    }
}
