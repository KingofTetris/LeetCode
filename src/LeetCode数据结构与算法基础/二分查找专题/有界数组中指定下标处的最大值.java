package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 有界数组中指定下标处的最大值 {

    public int maxValue(int n, int index, int maxSum) {
        int left = 1, right = maxSum;
        while (left < right) {
            int mid = (left + right + 1) / 2;
            if (valid(mid, n, index, maxSum)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    public boolean valid(int mid, int n, int index, int maxSum) {
        int left = index;
        int right = n - index - 1;
        return mid + cal(mid, left) + cal(mid, right) <= maxSum;
    }

    public long cal(int big, int length) {
        if (length + 1 < big) {
            int small = big - length;
            return (long) (big - 1 + small) * length / 2;
        } else {
            int ones = length - (big - 1);
            return (long) big * (big - 1) / 2 + ones;
        }
    }

  /*  作者：力扣官方题解
    链接：https://leetcode.cn/problems/maximum-value-at-a-given-index-in-a-bounded-array/solutions/2042360/you-jie-shu-zu-zhong-zhi-ding-xia-biao-c-aav4/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
