package LeetCode数据结构与算法基础.贪心;

/**
 * @Author KingofTetris
 * @Date 2022/7/8 14:36
 *
 * 1217. 玩筹码
 * 有 n 个筹码。第 i 个筹码的位置是 position[i] 。
 *
 * 我们需要把所有筹码移到同一个位置。在一步中，我们可以将第 i 个筹码的位置从 position[i] 改变为:
 *
 * position[i] + 2 或 position[i] - 2 ，此时 cost = 0   +-2为0
 * position[i] + 1 或 position[i] - 1 ，此时 cost = 1   +-1为1
 * 返回将所有筹码移动到同一位置上所需要的 最小代价 。
 *
 *输入：position = [1,2,3] 每个数字代表上面放的筹码种类
 * 输出：1
 * 解释：第一步:将位置3的筹码移动到位置1，成本为0。
 * 第二步:将位置2的筹码移动到位置1，成本= 1。
 * 总成本是1。
 *
 *输入：position = [2,2,2,3,3] 表示2的位置上有3个砝码，3的位置有2个砝码。
 * 输出：2
 * 解释：我们可以把位置3的两个筹码移到位置2。每一步的成本为1。总成本= 2。
 * 示例 3:
 *
 * 输入：position = [1,1000000000]
 * 输出：1
 *  
 *
 * 提示：
 *
 * 1 <= chips.length <= 100
 * 1 <= chips[i] <= 10^9
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/minimum-cost-to-move-chips-to-the-same-position
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 */
public class 玩筹码_2022_07_08 {

    /**
     * 题目意思就是将所有的位置的筹码移动到同一个位置上，如果距离为1则成本为1，距离为2则成本为0。
     * 我们直接将奇数的凑一堆，因为奇数和奇数移动的成本为0，同样的偶数和偶数移动到一堆，
     * 然后比较奇数和偶数堆的大小，将小的移动到大的即为最小成本。
     *
     * @param position
     * @return
     */
    public int minCostToMoveChips(int[] position) {
        int count1 = 0;
        int count2 = 0;
        for (int i : position) {
            if ((i & 1) == 0) { //判断i的奇偶可以用1来进行与运算，因为奇数的二进制个位一定是1.
                // 而偶数的二进制前几位必然存在一个1，末位肯定是0。而1的二进制是000..1 ，这就导致偶数与1相与一定是0.
                count1++;
            } else {
                count2++;
            }
        }
        return Math.min(count1, count2); //最后把小的那堆往大的那堆上移动，就是最小的代价。
    }
}
