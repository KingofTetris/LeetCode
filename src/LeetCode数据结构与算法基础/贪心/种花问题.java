package LeetCode数据结构与算法基础.贪心;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 * <p>
 * 假设有一个很长的花坛，一部分地块种植了花，另一部分却没有。
 * 可是，花不能种植在相邻的地块上，它们会争夺水源，两者都会死去。
 * <p>
 * 给你一个整数数组 flowerbed 表示花坛，由若干 0 和 1 组成，
 * 其中 0 表示没种植花，1 表示种植了花。
 * <p>
 * 另有一个数 n ，能否在不打破种植规则的情况下种入 n 朵花？能则返回 true ，不能则返回 false 。
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：flowerbed = [1,0,0,0,1], n = 1
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：flowerbed = [1,0,0,0,1], n = 2
 * 输出：false
 */
public class 种花问题 {

    //在已有基础上，能不能再插入n个不相邻的1

    /**
     * 贪心
     * 能种花的地方
     * 当前位置没有花
     * 前面要么没有花, 要么是边界
     * 后面要么没有花, 要么是边界
     */
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        int length = flowerbed.length;
        for (int i = 0; i < length; i++) {
            //旁边是边界也是合法的。这就解决了越界问题。
            if (flowerbed[i] == 0 && (i == 0 || flowerbed[i - 1] == 0)
                    && (i == length - 1 || flowerbed[i + 1] == 0)) {
                n--;
                //把花种上
                flowerbed[i] = 1;
            }
            if (n <= 0) {
                return true;
            }
        }
        return false;
    }
}
