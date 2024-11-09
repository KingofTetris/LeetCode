package LeetCode数据结构与算法基础.day7.堆或者叫优先队列;

import java.util.PriorityQueue;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 最后一块石头的重量 {

    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<Integer>((a, b) -> b - a);
        for (int stone : stones) {
            pq.offer(stone);
        }

        while (pq.size() > 1) {
            int a = pq.poll();
            int b = pq.poll();
            if (a > b) {
                pq.offer(a - b);
            }
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }

    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/last-stone-weight/solutions/540130/zui-hou-yi-kuai-shi-tou-de-zhong-liang-b-xgsx/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
