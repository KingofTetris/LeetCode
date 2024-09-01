package LeetCode数据结构与算法基础.day4.栈和队列;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2024/8/17
 */
public class 柱状图中最大的矩形 {

    @Test
    public void test(){
//        int[] heights = {2, 1, 5, 6, 2, 3};
        int[] heights = {1,1};
        System.out.println(largestRectangleArea(heights));
    }

    /**
     * 这个都超时，🐕题目逼着你用单调栈。
     * @param heights
     * @return
     */
    public int largestRectangleArea(int[] heights) {
        //这个和接雨水反过来，那么找最大，这边找最小。
        //去左右两边找最远的小于自己高度的柱子下标
        int[] minLeftIndex = new int[heights.length];
        int[] minRightIndex = new int[heights.length];
        int size = heights.length;

        // 记录每个柱子 左边第一个小于该柱子的下标
        minLeftIndex[0] = -1; // 注意这里初始化，防止下面while死循环
        for (int i = 1; i < size; i++) {
            int curIndex = i - 1;
            //不断向左寻找的过程
            //cur等于=0 就不断左移
            while (curIndex >= 0){
                //找到第一个比当前高度小的，更新，跳出循环
                if (heights[curIndex] < heights[i]){
                    minLeftIndex[i] = curIndex;
                    break;
                }
                curIndex--;
                //如果是这种特殊情况
                //大于等于也要放进去
                if (curIndex < 0 && heights[0] >= heights[i]){
                    minLeftIndex[i] = -1;
                }
            }
        }
        // 记录每个柱子 右边第一个小于该柱子的下标
        minRightIndex[size - 1] = size; // 注意这里初始化，防止下面while死循环
        for (int i = size - 2; i >= 0; i--) {
            int curIndex = i + 1;
            //不断向右寻找的过程
            while (curIndex < size){
                if (heights[curIndex] < heights[i]){
                    minRightIndex[i] = curIndex;
                    break;
                }
                curIndex++;
                //如果是这种特殊情况
                if (curIndex >= size && heights[size - 1] >= heights[i]){
                    minRightIndex[i] = size;
                }
            }
        }

        /**
         * 代码随想录里面求这两个数组的方式，这个可以过，但是我看不懂他怎么求的。
         *
        *  // 记录每个柱子 右边第一个小于该柱子的下标
         *         minRightIndex[size - 1] = size; // 注意这里初始化，防止下面while死循环
         *         for (int i = size - 2; i >= 0; i--) {
         *             int t = i + 1;
         *             // 这里不是用if，而是不断向右寻找的过程
         *             while (t < size && heights[t] >= heights[i]) t = minRightIndex[t];
         *             minRightIndex[i] = t;
         *         }
         *
         *         // 求和
         *         int result = 0;
         *         for (int i = 0; i < size; i++) {
         *             //每个单元的最大可形成面积就是
         *             //自己的高度*左右距离之差。
         *             int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
         *             result = Math.max(sum, result);
         *         }
        * */
        // 求和
        int result = 0;
        for (int i = 0; i < size; i++) {
            //每个单元的最大可形成面积就是
            //自己的高度*左右距离之差。
            int sum = heights[i] * (minRightIndex[i] - minLeftIndex[i] - 1);
            result = Math.max(sum, result);
        }

        return result;
    }

}
