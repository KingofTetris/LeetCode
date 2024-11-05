package LeetCode数据结构与算法基础.二分查找专题.最大的最小值;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */


import org.junit.Test;

import java.util.Arrays;

/**
 * 珂珂喜欢吃香蕉。这里有 n 堆香蕉，第 i 堆中有 piles[i] 根香蕉。警卫已经离开了，将在 h 小时后回来。
 * 珂珂可以决定她吃香蕉的速度 k （单位：根/小时）。每个小时，她将会选择一堆香蕉，
 * 从中吃掉 k 根。如果这堆香蕉少于 k 根，她将吃掉这堆的所有香蕉，然后这一小时内不会再吃更多的香蕉。
 * 珂珂喜欢慢慢吃，但仍然想在警卫回来前吃掉所有的香蕉。
 * 返回她可以在 h 小时内吃掉所有香蕉的最小速度 k（k 为整数）。
 * <p>
 * 示例 1：
 * <p>
 * 输入：piles = [3,6,7,11], h = 8
 * 输出：4
 * 示例 2：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 5
 * 输出：30
 * 示例 3：
 * <p>
 * 输入：piles = [30,11,23,4,20], h = 6
 * 输出：23
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= piles.length <= 104
 * piles.length <= h <= 109
 * 1 <= piles[i] <= 109
 * <p>
 * 题目给的设定还比较复杂， 假设你先不看限制，就求一个最小k，使得珂珂能够在h内吃完所有香蕉
 * 那么很简单 就是 sum / h 向上取整就可以了。
 * <p>
 * [3,6,7,11], h = 8
 * 3+6+7+11 = 27  ceil(27/8) = 4
 * 那么4就是最小可以保证他可能在8h内吃完所有香蕉的时间。
 * <p>
 * 如果k = 1,那么需要的时间就是 27
 * 如果k = max = 11,那么只需要吃4次就能完成，也就是h = 4
 * <p>
 * 那么问题就变成了在 1-max里面找一个最小的k，使得消耗时间小于等于h
 */
public class 爱吃香蕉的珂珂 {

    @Test
    public void test() {
//        int[] nums = {30,11,23,4,20};
        int[] nums = {3, 6, 7, 11};
        int h = 8;
        int speed = minEatingSpeed(nums, h);
        System.out.println(speed);
    }

    public int minEatingSpeed(int[] piles, int h) {
        int max = Integer.MIN_VALUE;
        for (int pile : piles) {
            max = Math.max(pile, max);
        }
        int left = 1, right = max;

        //没有必要判断两边canEat
        //用一个k记录一下就好了。
      /*  while (left <= right){
            int mid = (left + right) / 2;
            //如果mid满足条件
            if (canEatAll(mid,piles,h)){
                //但是mid - 1不满足，那么mid就是最小的speed
                if(!canEatAll(mid - 1,piles,h)){
                    return mid;
                }
                right = mid - 1;
            }
            else {
                left = mid + 1;
            }
        }*/
        int k = right;
        while (left < right) { //这里的闭区间还有点恶心
            int mid = (right - left) / 2 + left;
            long time = canEatAll(mid, piles);
            //如果满足条件，就继续去找更小的k
            //注意，必须是等于right = mid，不是mid -1
            //你mid - 1的话，那么mid-1就永远取不到了。
            //但是mid - 1可能是结果。
            if (time <= h) {
                k = mid;
                right = mid;
            }
            //不满足就增大k
            else {
                left = mid + 1;
            }
        }
        return k;
    }

    private long canEatAll(int mid, int[] piles) {
        //这里就需要用到题目的设定了。
        long time = 0;
        for (int pile : piles) {
            //吃掉pile所需要的时间,需要向上取整
            //为什么是所有都向上取整?? 那么 <= mid的不也全部都是1个小时？
            //md 不是说休息一个小时吗??
            int cost = (int) Math.ceil((double) pile / mid);
            time += cost;
        }
        return time;
    }

}
