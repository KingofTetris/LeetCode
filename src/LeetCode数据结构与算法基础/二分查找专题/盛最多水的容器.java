package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @Author KingofTetris
 * @Date 2022/10/4 14:40
 * https://leetcode.cn/problems/container-with-most-water/?favorite=2cktkvj
 */
public class 盛最多水的容器 {

    /**
     * 经典求最值问题，DP可以做吗？递推关系式怎么写？
     * 暴力法 超时
     * @param height
     * @return
     */
    public int maxArea(int[] height) {
        int max = 0;
        for (int i = 0; i < height.length; i++) {
            for (int j = i + 1; j < height.length; j++) {
                //取短板，算面积
                int area = Math.min(height[i],height[j]) * (j - i);
                max = Math.max(area,max);
            }
        }
        return max;
    }


    /**
     * 双指针
     * 而且是首尾指针
     * 哪条边比较短，就移动哪条边
     * 因为移动短的才有可能让面积增大
     * @param height
     * @return
     */
    public int maxArea2(int[] height) {
        int left = 0,right = height.length - 1;//左右两个指针
        int max = 0;
        while (left < right){
            int area = Math.min(height[right],height[left]) * (right - left);
            max = Math.max(max,area);
            //谁小谁移动。
            if (height[left] < height[right] ) left++;
            else right--;
        }
        return max;
    }
}
