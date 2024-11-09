package LeetCode数据结构与算法基础.回溯;

/**
 * @author by KingOfTetris
 * @date 2024/10/31
 */

import java.util.Arrays;

/**
 * 首先计算所有火柴的总长度 totalLen，如果 totalLen 不是 4 的倍数，那么不可能拼成正方形，返回 false。
 * 当 totalLen 是 4 的倍数时，每条边的边长为 len= totalLen / 4
 * ​用 edges 来记录 4 条边已经放入的火柴总长度。对于第 index 火柴，
 * 尝试把它放入其中一条边内且满足放入后该边的火柴总长度不超过 len，
 * 然后继续枚举第 index+1 根火柴的放置情况，如果所有火柴都已经被放置，那么说明可以拼成正方形。
 * <p>
 * 作者：力扣官方题解
 * 链接：https://leetcode.cn/problems/matchsticks-to-square/solutions/1528435/huo-chai-pin-zheng-fang-xing-by-leetcode-szdp/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 火柴拼正方形 {

    /**
     * 回溯时间复杂度 O(4^n)，其中 n 是火柴的数目。每根火柴都可以选择放在 4 条边上
     * 空间复杂度：O(n)。递归栈需要占用 O(n) 的空间。
     *
     * @param matchsticks
     * @return
     */
    public boolean makesquare(int[] matchsticks) {
        int sum = Arrays.stream(matchsticks).sum();
        if (sum % 4 != 0) return false;
        //为了减少搜索量，需要对火柴长度从大到小进行排序。
        //Arrays.sort对于基本数据类型只能从小到大排序
        Arrays.sort(matchsticks);
        //后面降序得自己动手做
        int n = matchsticks.length;
        for (int i = 0, j = n - 1; i < j; i++, j--) {
            int temp = matchsticks[i];
            matchsticks[i] = matchsticks[j];
            matchsticks[j] = temp;
        }
        //然后开始尝试回溯,去放4根火柴
        int[] edges = new int[4];
        return backTracking(matchsticks, edges, 0, sum / 4);
    }

    //每个火柴只能用一次。
    //设正方形边长为k
    private boolean backTracking(int[] matchsticks, int[] edges, int startIndex, int k) {
        //终止条件 如果能把所有火柴都放下去
        if (startIndex == matchsticks.length) {
            return true;
        }
        //注意是遍历边长数组！
        for (int i = 0; i < edges.length; i++) {
            edges[i] += matchsticks[startIndex];
            // <=k 的时候就继续往下组合
            if (edges[i] <= k && backTracking(matchsticks, edges, startIndex + 1, k)) {
                return true;//如果edges > k 了，就不会走这里了，说明edges[i] 已经等于k，那么去凑edges[i+1]
            }
            //回溯
            edges[i] -= matchsticks[startIndex];
        }

        //如果上面都凑不齐，return false
        return false;

    }

   /* 作者：力扣官方题解
    链接：https://leetcode.cn/problems/matchsticks-to-square/solutions/1528435/huo-chai-pin-zheng-fang-xing-by-leetcode-szdp/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/

}















