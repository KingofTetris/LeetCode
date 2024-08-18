package LeetCode数据结构与算法基础.day1.数组;

/**
 * @author by KingOfTetris
 * @date 2024/8/14
 */
public class 接雨水 {

    //计算每一列能够接住的雨水
    //实际上就是 min(lHeight,rHeight) - height[i]
    // 左右两边的高度的较小值减去当前列的高度，因为宽度都是1 就没必要*1了
    //并且要注意第一个柱子和最后一个柱子不接雨水
    public int trap(int[] height) {
        //如果柱子的数量如果小于2 那没得玩了
        if (height.length < 2) return 0;
        int N = height.length;
        int[] maxLeft = new int[N];
        int[] maxRight = new int[N];

        //记录每根柱子左边的最大高度
        maxLeft[0] = height[0];
        for (int i = 1; i < N; i++) {
            maxLeft[i] = Math.max(height[i], maxLeft[i - 1]);
        }
        //记录右边柱子的最大高度
        maxRight[N - 1] = height[N - 1];
        for (int i = N - 2; i >= 0; i--) {
            maxRight[i] = Math.max(height[i], maxRight[i + 1]);
        }

        //求每列柱子的接水面积
        int sum = 0;
        for (int i = 0; i < N; i++) {
            //左右两根柱子更小的那个 - Height[i]
            int count = Math.min(maxLeft[i], maxRight[i]) - height[i];
            //如果能接住水，如果是负数是接不了雨水的
            if (count > 0) sum += count;
        }
        return sum;
    }
}
