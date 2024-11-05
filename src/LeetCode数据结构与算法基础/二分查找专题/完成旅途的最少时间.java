package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */

//其实就是在问每辆车都用时 x，总共能完成多少趟旅途？能否达到 totalTrips？
public class 完成旅途的最少时间 {

    public long minimumTime(int[] time, int totalTrips) {
        int minT = Integer.MAX_VALUE;
        int maxT = 0;
        for (int t : time) {
            minT = Math.min(minT, t);
            maxT = Math.max(maxT, t);
        }
        int avg = (totalTrips - 1) / time.length + 1;
        // 循环不变量：check(left) 恒为 false
        long left = (long) minT * avg - 1;
        // 循环不变量：check(right) 恒为 true
        long right = Math.min((long) maxT * avg, (long) minT * totalTrips);
        // 开区间 (left, right) 不为空
        while (left + 1 < right) {
            long mid = (left + right) >>> 1;
            if (check(mid, time, totalTrips)) {
                // 缩小二分区间为 (left, mid)
                right = mid;
            } else {
                // 缩小二分区间为 (mid, right)
                left = mid;
            }
        }
        // 此时 left 等于 right-1
        // check(left) = false 且 check(right) = true，所以答案是 right
        return right; // 最小的 true
    }

    private boolean check(long x, int[] time, int totalTrips) {
        long sum = 0;
        for (int t : time) {
            sum += x / t;
            if (sum >= totalTrips) {
                return true;
            }
        }
        return false;
    }

    /*作者：灵茶山艾府
    链接：https://leetcode.cn/problems/minimum-time-to-complete-trips/solutions/1295955/er-fen-da-an-python-yi-xing-gao-ding-by-xwvs8/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
