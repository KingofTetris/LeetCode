package 每日一题;

import LeetCode数据结构入门.day3.链表.ListNode;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/4/10
 *
 * 给定一个长度为 n 的链表 head
 *
 * 对于列表中的每个节点，查找下一个 更大节点 的值。也就是说，对于每个节点，找到它旁边的第一个节点的值，
 * 这个节点的值 严格大于 它的值。
 *
 * 返回一个整数数组 answer ，其中 answer[i] 是第 i 个节点( 从1开始 )的下一个更大的节点的值。
 * 如果第 i 个节点没有下一个更大的节点，设置 answer[i] = 0 。
 *
 * 示例 1：
 *
 * 输入：head = [2,1,5]
 * 输出：[5,5,0]
 * 示例 2：
 * 输入：head = [2,7,4,3,5]
 * 输出：[7,0,5,5,0]
 * 提示：
 * 链表中节点数为 n
 * 1 <= n <= 10^4
 * 1 <= Node.val <= 10^9
 *
 * 在leetcode里，暴力极限一般可以跑 1e8 所以这题用暴力法也可以通过
 *
 * 但是本意是要你用单调栈把复杂度降到O(n)
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/next-greater-node-in-linked-list
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 链表中的下一个更大节点_2023_04_10 {

    //暴力
    public int[] nextLargerNodes(ListNode head) {
        ListNode q = head;
        int length = 0;
        while (q != null){
            length++;
            q = q.next;
        }
        int[] largeNum = new int[length];//初始化一个全0数组
        for (int i = 0; i < length; i++) {
            ListNode now = head; //now取当前开头节点
            int val = now.val;//取当前开头的值
            while (now.next != null){
                if (now.next.val > val){
                    largeNum[i] = now.next.val;
                    break;//找到就可以提前结束
                }
                //没找到就全为0，初始化就全为0，不用再改变。
                now = now.next;//now后移
            }
            head = head.next;// head后移
        }
        return largeNum;
    }


    /**
     * 单调栈
     * @param head
     * @return
     */
    public int[] nextLargerNodes2(ListNode head) {
        List<Integer> ans = new ArrayList<>();
        Deque<int[]> stack = new ArrayDeque<>();

        ListNode cur = head;
        int idx = -1;
        while (cur != null) {
            ++idx;
            ans.add(0);
            while (!stack.isEmpty() && stack.peek()[0] < cur.val) {
                ans.set(stack.pop()[1], cur.val);
            }
            stack.push(new int[]{cur.val, idx});
            cur = cur.next;
        }

        int size = ans.size();
        int[] arr = new int[size];
        for (int i = 0; i < size; ++i) {
            arr[i] = ans.get(i);
        }
        return arr;
    }
}
