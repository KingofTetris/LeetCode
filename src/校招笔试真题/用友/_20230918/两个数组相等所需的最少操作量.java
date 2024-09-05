package 校招笔试真题.用友._20230918;

import org.junit.Test;
import 校招笔试真题.携程._20230907.游游的排列_DFS;

import java.util.Arrays;
import java.util.OptionalDouble;

/**
 * @author by KingOfTetris
 * @date 2023/9/18
 */
public class 两个数组相等所需的最少操作量 {
    //对nums2 每次选两个数进行 +- k 作为一次操作
    //或者交换i和j的位置。
    //请问从nums2 到 nums1 至少需要多少次操作后内容相同。
    //如果不行，返回-1
    @Test
    public void test() {
        //编辑距离
        int[] nums1 = {3, 8, 5, 2};
        int[] nums2 = {2, 4, 1, 6};
        int k = 3;
        long l = minOperations(nums1, nums2, k);
        System.out.println(l);
    }

    public long minOperations(int[] nums1, int[] nums2, int k) {
        return 0;
    }
}
