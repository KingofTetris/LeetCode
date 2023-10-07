package LeetCode数据结构基础.差分数组;

/**
 * @author by KingOfTetris
 * @date 2023/4/24
 */
public class 航班预定 {
    public int[] corpFlightBookings(int[][] bookings, int n) {
        int[] nums = new int[n];
        Difference difference = new Difference(nums);
        for (int[] booking : bookings) {
            int i = booking[0] - 1;
            int j = booking[1] - 1;
            int val = booking[2];
            //对区间[i-1,j-1]增加val
            difference.increment(i,j,val);
        }
        return difference.result();
    }
}
