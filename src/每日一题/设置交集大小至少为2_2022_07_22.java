package 每日一题;

import org.junit.Test;

import java.util.Arrays;

/**
 * @Author KingofTetris
 * @Date 2022/7/22 9:17
 *
 *一个整数区间 [a, b]  ( a < b ) 代表着从 a 到 b 的所有连续整数，包括 a 和 b。
 *
 * 给你一组整数区间intervals，请找到一个最小的集合 S，使得 S 里的元素与区间intervals中的每一个整数区间都至少有2个元素相交。
 *
 * 输出这个最小集合S的大小。
 *
 * 示例 1:
 *
 * 输入: intervals = [[1, 3], [1, 4], [2, 5], [3, 5]]
 * 输出: 3
 * 解释:
 * 考虑集合 S = {2, 3, 4}. S与intervals中的四个区间都有至少2个相交的元素。
 * 且这是S最小的情况，故我们输出3。
 * 示例 2:
 *
 * 输入: intervals = [[1, 2], [2, 3], [2, 4], [4, 5]]
 * 输出: 5
 * 解释:
 * 最小的集合S = {1, 2, 3, 4, 5}.
 * 注意:
 *
 * intervals 的长度范围为[1, 3000]。
 * intervals[i] 长度为 2，分别代表左、右边界。
 * intervals[i][j] 的值是 [0, 10^8]范围内的整数。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/set-intersection-size-at-least-two
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 设置交集大小至少为2_2022_07_22 {
    @Test
    public void test(){
//        int[][] intervals = {{1, 2}, {2, 3}, {2, 4}, {4, 5}};
        int[][] intervals = {{2,10},{3,7},{3,15},{4,11},{6,12},{6,16},{7,8},{7,11},{7,15},{11,12}};
        System.out.println(intersectionSizeTwo(intervals));
    }

    /**
     * 贪心的困难题
     *
     * 先按照左端点升序，再按照右端点降序，然后从 $末尾$ 向前遍历
     *
     * 假设我们有一个intervals = [[2,3]，[3,4]，[5,10]，[5,8]] (已排好序),
     * 只要我们满足了和[5,8]的交集大于等于2，则对于[5,10]
     * （左区间相同，右区间降序，保证在左区间相同的情况下让区间范围最小的在最右边）这个区间来说，
     * 必定是满足交集大于等于2的，因为小区间满足，大区间必然满足，反过来不一定， 就是[5,8]是[5,10]的子集，你满足了小的，大的就包含了小的
     *
     *在左区间相同的情况下,我们取最小区间的两个元素就可以满足所有左区间相同的区间。
     * 因此我们贪心的取interval[n-1][0]和interval[n-1][0] + 1做为开始的两个集合元素
     * 说了贪心嘛，取[5,6]两个最小的元素就可以满足[5,8],[5,10]...
     * 设初始两个元素为cur和next，则cur = intervals[n - 1][0],next = intervals[n - 1][0] + 1。
     *
     * 然后开始分类讨论前一个区间[xi,yi]的情况，首先有一个重要前提，根据排序有xi <= cur 也就是左端点是从小到大的，越前面区间越长
     * 若yi >= next ,则是一个大区间，一定满足交集至少为2的情况 eg:[5,10] [5,8]  5,8就至少是两个元素了，不变
     * 若yi < cur,那一定没有交集，我们还是贪心的取cur = xi,next = xi + 1  eg:[1,3] [5,8] 改为[1,2]
     * 若cur <= yi && yi < next,两区间存在交集，我们贪心的取next = cur，cur = xi，保证每次都是取左边界和左边界+1
     * eg: [3,7] [7,8]  取[3,7]
     *
     * @param intervals
     * @return
     */
    public int intersectionSizeTwo(int[][] intervals) {
//        首先区间排序的API
        Arrays.sort(intervals,((o1, o2) -> o1[0] == o2[0] ? -(o1[1] - o2[1]) : o1[0] - o2[0]));

      /*  for (int i = 0; i < intervals.length; i++) {
            System.out.println(intervals[i][0] + "," + intervals[i][1]);
        }*/
        //康师傅都有教集合的排序，忘了自己去看
        // a - b从小到大，反过来-(a-b)就是从大到小
        int n = intervals.length;
        int cur = intervals[n - 1][0];
        int next = intervals[n - 1][0] + 1;
        int ans = 2;

        for (int i = n - 2; i >= 0; i--) {
            //        若yi >= next ,则是一个大区间，一定满足交集为2的情况 eg:[5,10] [5,8] 不变
            if (intervals[i][1] >= next){continue;}//直接跳过，不要去比较了
//            若yi < cur,那一定没有交集，我们还是贪心的取cur = xi,next = xi + 1  eg:[1,3] [5,8] 改为[1,2]
            if (intervals[i][1] < cur){
                cur = intervals[i][0];
                next = intervals[i][0] + 1;
                ans = ans+2; //这种情况+2
            }

            //若cur <= yi < next,两区间存在交集，我们贪心的取next = cur，cur = xi，保证每次都是取左边界和左边界+1
            //[3,6] [5,8]  取[3,5]

            if (cur <= intervals[i][1] && intervals[i][1]< next){
                next = cur;
                cur = intervals[i][0];
                ans++; //这种加1 因为只多加了xi next被cur取代了。
            }
        }
        return ans;
    }

}
