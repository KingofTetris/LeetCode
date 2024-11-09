package LeetCode数据结构与算法基础.模拟;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 等差子数组 {

    //暴力判断
    public List<Boolean> checkArithmeticSubarrays(int[] nums, int[] l, int[] r) {
        List<Boolean> ans = new ArrayList<>();
        for (int i = 0; i < l.length; i++) {
            int arr[] = Arrays.copyOfRange(nums, l[i], r[i] + 1);
            Arrays.sort(arr);
            int d = arr[1] - arr[0];
            boolean ch = true;
            for (int j = 2; j <= r[i] - l[i]; j++) {
                if (d != arr[j] - arr[j - 1]) {
                    ch = false;
                    break;
                }
            }
            ans.add(ch);
        }
        return ans;
    }
}
