package 程序员面试金典;

import LeetCode数据结构入门.day3.链表.ListNode;

/**
 * @author by KingOfTetris
 * @date 2022/9/29
 */
public class _02_03_删除中间节点 {

    /**
     * 将下一节点的值赋给当前节点，再跳过下一节点即可；
     *
     * 相当于用下一节点覆盖了当前节点，但是不能够这么写：node = node->next，因为链表的节点不能直接赋值，你只能改变它下一个节点的指向。
     *
     * 作者：wo-yao-chu-qu-luan-shuo
     * 链接：https://leetcode.cn/problems/delete-middle-node-lcci/solution/by-wo-yao-chu-qu-luan-shuo-3a3z/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param node
     */
    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;
    }
}
