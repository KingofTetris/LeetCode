package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/9/8
 *
 * n 个孩子站成一排。给你一个整数数组 ratings 表示每个孩子的评分。
 *
 * 你需要按照以下要求，给这些孩子分发糖果：
 *
 * 每个孩子至少分配到 1 个糖果。
 * 相邻两个孩子评分更高的孩子会获得更多的糖果。（这个条件比较关键，相邻更高才需要获得更多糖果）
 * (如果相邻都一样，那么你怎么分都无所谓，只要保证至少一个就行了)
 * 请你给每个孩子分发糖果，计算并返回需要准备的 最少糖果数目 。
 *
 *
 * 示例 1：
 *
 * 输入：ratings = [1,0,2]
 * 输出：5
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。
 * 示例 2：
 *
 * 输入：ratings = [1,2,2]
 * 输出：4
 * 解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
 *      第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。
 */
public class 分发糖果 {
   /* 输入：ratings = [1,0,2]
    输出：5
    解释：你可以分别给第一个、第二个、第三个孩子分发 2、1、2 颗糖果。*/

 /*   输入：ratings = [1,2,2]
    输出：4
    解释：你可以分别给第一个、第二个、第三个孩子分发 1、2、1 颗糖果。
    第三个孩子只得到 1 颗糖果，这满足题面中的两个条件。*/


    //如果你想一次遍历同时考虑左右孩子就会上当，越陷越深。
    //只能两次遍历，一次从左到右，一次从右到左。


    @Test
    public void test(){
        int[] children = {1,2,2,5,4,3,2};
        int candy = candy(children);
    }
    public int candy(int[] ratings) {
        //从左到右遍历一次
        int[] candy = new int[ratings.length];
        //从右到左遍历一次
        //每个孩子得到的糖果至少是1
        Arrays.fill(candy,1);

        for (int i = 1; i < ratings.length; i++) {
            //右孩子大于左孩子
            //则右孩子糖果+1
            if (ratings[i] > ratings[i - 1]){
                candy[i] = candy[i - 1] + 1;
            }
        }
        //反过来遍历左孩子>右孩子的情况
        //先从n-2 和n -1 比，然后i--
        for (int i = ratings.length - 2; i >= 0; i--) {
            if (ratings[i] > ratings[i + 1]){
                //要和上一趟的candy[i]比较，哪个更大。
                /**
                 * 例如 1 2 2 5 4 3 2 这种情况
                 * 从左往右得到结果
                 *     1,2,1,2,1,1,1
                 * 反过来从右往左可以得到
                 *     1 1 1 4 3 2 1
                 * 显然在下标为1的2这里出了问题，他要保证比相邻更小的孩子得到的糖果多
                 * 那么他应该要得到2个。
                 *
                 * 所以应该选择Math.max(candy[i],candy[i + 1] + 1);
                 */
                candy[i] = Math.max(candy[i],candy[i + 1] + 1);
            }
        }

        //最后返回candy之和就行了。
        return Arrays.stream(candy).sum();
    }
}
