package LeetCode数据结构与算法基础.手撕算法;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 多数元素 {


    //在本文中，“数组中出现次数超过一半的数字” 被称为 “众数” 。

    /**
     * HashMap统计次数 O(N) O(N)
     * 排序返回中间的数字  O(NlogN) O(1)
     * 擂台法 O(N) O(1) 这个是最佳的
     * @param nums
     * @return
     */
    //摩尔投票法，因为用例一定存在众数，最多的一定留到最后
    //也可以叫擂台法。
    public int majorityElement(int[] nums) {
        int x = 0, votes = 0;
        for (int num : nums){
            //如果擂台上没人了，num上来当擂主。
            //留到最后的一定是众数
            if (votes == 0) x = num;
            if (x == num){
                votes++;
            }
            else {
                votes--;
            }
        }
        return x;
    }
}
