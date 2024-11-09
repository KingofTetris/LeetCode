package LeetCode数据结构与算法基础.day7.堆或者叫优先队列;

import java.util.PriorityQueue;

/**
 * @author by KingOfTetris
 * @date 2024/11/9
 */
public class 座位预约管理系统 {

    private PriorityQueue<Integer> available;
    public 座位预约管理系统(int n) {
        available = new PriorityQueue<>();
        for (int i = 1; i <= n; i++) {
            available.offer(i);
        }
    }

    public int reserve() {
        return available.poll();
    }

    public void unreserve(int seatNumber) {
        available.offer(seatNumber);
    }

    /*作者：力扣官方题解
    链接：https://leetcode.cn/problems/seat-reservation-manager/solutions/754909/zuo-wei-yu-yue-guan-li-xi-tong-by-leetco-wj45/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
