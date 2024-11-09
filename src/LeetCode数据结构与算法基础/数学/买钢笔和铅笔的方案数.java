package LeetCode数据结构与算法基础.数学;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 买钢笔和铅笔的方案数 {

    /**
     * 现在我们有 total 的钱数，一支钢笔的价格为 cost1，
     * 一支铅笔的价格为 cost2，现在我们需要求能购买的钢笔和铅笔的不同方案数目。
     *
     * 假设我们当前买了 x 支钢笔，满足 cost1 * x ≤ total，则我们有 total - cost1 * x 的钱购买铅笔，
     * 此时能购买的铅笔数可以为 0, 1, ..., ⌊(total - cost1 * x) / cost2⌋，
     * 即方案数为 ⌊(total - cost1 * x) / cost2⌋ + 1 种。我们枚举可以购买的钢笔数，
     * 然后对每一种情况分别计算可以购买的铅笔数并求和即为总的方案数目。
     *
     * 在代码实现的过程中，由于钢笔和铅笔的价格相互独立，所以为了降低平均时间复杂度，当 cost1 < cost2 时，
     * 我们将转换为枚举 cost2 来减少枚举次数。
     * @param total
     * @param cost1
     * @param cost2
     * @return
     */
    public long waysToBuyPensPencils(int total, int cost1, int cost2) {
        if (cost1 < cost2) {
            return waysToBuyPensPencils(total, cost2, cost1);
        }
        long res = 0, cnt = 0;
        while (cnt * cost1 <= total) {
            res += (total - cnt * cost1) / cost2 + 1;
            cnt++;
        }
        return res;
    }


 /*   作者：力扣官方题解
    链接：https://leetcode.cn/problems/number-of-ways-to-buy-pens-and-pencils/solutions/2359649/mai-gang-bi-he-qian-bi-de-fang-an-shu-by-83nk/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
