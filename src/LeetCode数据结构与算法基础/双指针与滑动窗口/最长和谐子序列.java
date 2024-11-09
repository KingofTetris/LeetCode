package LeetCode数据结构与算法基础.双指针与滑动窗口;

import java.util.Arrays;
import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 最长和谐子序列 {

    //和谐子序列指的的是这个子序列的 最大值 - 最小值 = 1
    //找出最长的

    //这题有点特殊，不要被子序列迷惑了，虽然指定是子序列，但是他只要求返回长度，不要求你返回子序列的样子
    //而你要关心的只有最大值的最小值的差
    //最大最小出现的相对位置 其实无所谓。因此可以排序 然后滑动窗口就可以了。

    public int findLHS(int[] nums) {
        Arrays.sort(nums);
        int begin = 0;
        int res = 0;
        for (int end = 0; end < nums.length; end++) {
            while (nums[end] - nums[begin] > 1) {
                begin++;
            }
            if (nums[end] - nums[begin] == 1) {
                res = Math.max(res, end - begin + 1);
            }
        }
        return res;
    }

    //还有一种用hashmap 不需要排序的方法
    //因为其实最大最小值出现的相对位置根本无关紧要。
    //你只需要找到相差=1 的两个数，最多出现多少次就可以了。
    public int findLHS2(int[] nums) {
        HashMap<Integer, Integer> cnt = new HashMap <>();
        int res = 0;
        for (int num : nums) {
            cnt.put(num, cnt.getOrDefault(num, 0) + 1);
        }
        for (int key : cnt.keySet()) {
            if (cnt.containsKey(key + 1)) {
                res = Math.max(res, cnt.get(key) + cnt.get(key + 1));
            }
        }
        return res;
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/longest-harmonious-subsequence/solutions/1110137/zui-chang-he-xie-zi-xu-lie-by-leetcode-s-8cyr/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
