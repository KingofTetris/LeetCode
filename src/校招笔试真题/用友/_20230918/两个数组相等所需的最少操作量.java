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
        //[3,8,5,2],[2,4,1,6],1
        //3
        int nums1[] = {1,3,7,1};
        int nums2[] = {4,3,1,4};
//        int[] nums1 = {3, 8, 5, 2};
//        int[] nums2 = {2, 4, 1, 6};
        int k = 3;
        long l = minOperations(nums1, nums2, k);
        System.out.println(l);
    }

    public long minOperations(int[] nums1, int[] nums2, int k) {
        int n = nums1.length;
        int m = nums2.length;
        int sum1 = Arrays.stream(nums1).sum();
        int sum2 = Arrays.stream(nums2).sum();

        int minSum = Math.min(sum1, sum2);
        int maxSum = Math.max(sum1, sum2);

        if (k * (6 - n - m) < maxSum - minSum) {
            return -1;
        }
        int[] diff = new int[6];
        for (int num : nums1) {
            diff[num - 1]++;
        }
        for (int num : nums2) {
            diff[6 - num]++;
        }
        int count = 0;
        int i = 5;
        while (minSum < maxSum) {
            while (i > 0 && diff[i] == 0) {
                i--;
            }

            if (i == 0) {
                break;
            }

            int minChange = Math.min(k * (6 * n - m), diff[i]);
            int maxChange = Math.min((maxSum - minSum + k - 1) / k, diff[i]);

            if (minChange <= maxChange) {
                minSum += minChange;
                maxSum -= minChange * i;
                diff[i] -= minChange;
                count += minChange;
            } else {
                minSum += maxChange * k;
                maxSum -= maxChange * i;
                diff[i] -= maxChange;
                count += maxChange;
            }
        }
        return count;
    }
}
