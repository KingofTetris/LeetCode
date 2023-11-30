package 每日一题;

import LeetCode数据结构与算法基础.day3.链表.ListNode;

/**
 * @Author KingofTetris
 * @Date 2022/10/12 9:43
 */
public class 链表组件_2022_10_12 {

    /**
     * 找到链表连续连接部分的个数
     * 因为链表的长度限制在1-10000，所以给10001 不会越界
     * 因为只要连续才能形成组件
     * 所以我们设置一个index数组 把index[nums[i]]设为1；
     * 然后遍历链表，遇到pre为0,index[head.val]为1的就是一个组件
     * @param head
     * @param nums
     * @return
     */
    public int numComponents(ListNode head, int[] nums) {
        int[] index = new int[10001];
        for (int num: nums) index[num] = 1;

        //初始pre为0
        int result = 0, pre = 0;
        while (head != null) {
            if (pre == 0 && index[head.val] == 1) result++;
            //pre设置为前一个数组位置的标记
            //链表后移
            pre = index[head.val]; head = head.next;
        }
        return result;

 /*       作者：muse-77
        链接：https://leetcode.cn/problems/linked-list-components/solution/zhua-wa-mou-si-by-muse-77-1rz4/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
