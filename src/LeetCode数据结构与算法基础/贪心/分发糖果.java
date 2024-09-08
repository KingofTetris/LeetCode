package LeetCode数据结构与算法基础.贪心;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2024/9/8
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
    public int candy(int[] ratings) {
        //从左到右遍历一次
        int[] candy = new int[ratings.length];
        //从右到左遍历一次

        //每个孩子得到的糖果至少是1
        Arrays.fill(candy,1);

        for (int i = 1; i < ratings.length; i++) {
            //右孩子大于左孩子
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
                 *     1 2 2 3 1 1 1
                 * 反过来从右往左可以得到
                 *     1 1 1 4 3 2 1
                 * 显然在第二个2这里出了问题，他要保证比相邻更小的孩子得到的糖果多
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
