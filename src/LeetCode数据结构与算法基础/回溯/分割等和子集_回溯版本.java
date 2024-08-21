package LeetCode数据结构与算法基础.回溯;

import java.util.ArrayList;
import java.util.List;

/**
 * @author by KingOfTetris
 * @date 2024/8/20
 */
public class 分割等和子集_回溯版本 {

    List<List<Integer>> res = new ArrayList<>();
    List<Integer> path = new ArrayList<>();

    public boolean canPartition(int[] nums) {
        backTracking(nums,0);
        return true;
    }

    private void backTracking(int[] nums, int startIndex) {
        //回溯终止条件

        //单层回溯条件

    }



    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/partition-equal-subset-sum/solutions/442320/fen-ge-deng-he-zi-ji-by-leetcode-solution/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
