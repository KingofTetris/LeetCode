package 每日一题;

import java.util.Arrays;

/**
 * @author by KingOfTetris
 * @date 2022/10/13
 */
public class 最多能完成排序的块_2022_10_13 {


    /**
     * 把数组分块后，把这些分块从小到大排序
     * 然后合并成一个数组保持升序
     * 返回尽可能多的块
     * @param arr
     * @return
     */

    /**
     * 思路:
     * eg:4 3 2 1 0
     *    0 1 2 3 4 排序后
     *
     *    1 0 2 3 4
     *    0 1 2 3 4 排序后
     *
     * 考虑前缀和
     * 如果前缀和一样就分成一块，不同就继续往后走
     *
     * 但其实由于题目的限制数组arr的值在[0,n-1]之间
     * arr排序后 arr[i] = i
     * 也就没必要再给arr排序了
     * @param arr
     * @return
     */
    public int maxChunksToSorted(int[] arr) {
        int arrSum = 0,sortSum = 0 ,res = 0;
        for (int i = 0; i < arr.length; i++) {
            arrSum += arr[i];
            sortSum += i;
            //如果相等重新置为0
            if (arrSum == sortSum){
                arrSum = 0;
                sortSum = 0;
                res++;//分块 + 1
            }
        }
        return res; //返回结果即可
    }


    /**
     * 官解最佳解法，没看懂
     * O(n) O(1)
     * @param arr
     * @return
     */
    public int maxChunksToSorted2(int[] arr){
        int m = 0, res = 0;
        for (int i = 0; i < arr.length; i++) {
            m = Math.max(m, arr[i]);
            if (m == i) {
                res++;
            }
        }
        return res;
    }
}
