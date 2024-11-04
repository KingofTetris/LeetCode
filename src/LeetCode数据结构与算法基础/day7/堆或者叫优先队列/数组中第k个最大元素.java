package LeetCode数据结构与算法基础.day7.堆或者叫优先队列;

import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author KingofTetris
 * @File 数组中第k大的元素
 * @Time 2021/10/30  10:03
 */


/*输入: [3,2,3,1,2,4,5,5,6] 和 k = 4
        输出: 4

   6,5,5,4  第四个最大元素4。
        */
public class 数组中第k个最大元素 {

    @Test
    public void test(){
        int[] nums = {3,2,3,1,2,4,5,5,6};
        System.out.println(findKthLargest(nums, 4));
    }


    //直接调用api的双pivot快速排序。当然本意不是让你调用API，而是TOP-K问题。
    public int findKthLargest(int[] nums, int k) {
        //注意降序是无法直接对基本数据类型使用的，int要换成integer
        int n = nums.length;
        Integer Nums[] = new Integer[n];
        for (int i = 0; i < n; i++) {
            Nums[i] = nums[i];
        }

        Arrays.sort(Nums, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -(o1 - o2);
            }
        });

        return Nums[k-1];
    }



    //TOP-K问题的经典做法，堆和快速排序
}
