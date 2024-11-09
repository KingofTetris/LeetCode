package LeetCode数据结构与算法基础.贪心;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 青蛙过河II {

    /**
     * 如果只有两块，跳跃距离最大为 stones[1]-stones[0]
     * 否则对于起点，距离最近的两块石头，必然包含一去一回。否则跳跃长度不会比这两块石头更优。
     * 那么每次选择隔一块的石头是最佳策略。stones[i]-stones[i-2]
     * 比较获得最大跳跃长度
     *
     * 作者：钰娘娘丿-曱-乀
     * 链接：https://leetcode.cn/problems/frog-jump-ii/solutions/2159100/yu-niang-niang-2498-qing-wa-guo-he-ii-ta-rynw/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param stones
     * @return
     */
    public int maxJump(int[] stones) {
        int n = stones.length;
        int ans = stones[1]-stones[0];
        for(int i = 2; i < n; i++){
            ans = Math.max(stones[i]-stones[i-2],ans);
        }

        ans = Math.max(stones[n-1]-stones[n-2],ans);
        return ans;
    }

  /*  作者：钰娘娘丿-曱-乀
    链接：https://leetcode.cn/problems/frog-jump-ii/solutions/2159100/yu-niang-niang-2498-qing-wa-guo-he-ii-ta-rynw/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
