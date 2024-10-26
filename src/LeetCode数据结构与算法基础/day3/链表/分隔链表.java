package LeetCode数据结构与算法基础.day3.链表;

/**
 * @author by KingOfTetris
 * @date 2024/10/26
 */
public class 分隔链表 {

    //其实就是道简单题，>=x的用一条链表，<x的用一条链表
    //然后把<x的链表指向>=x的就完成了。
    public ListNode partition(ListNode head, int x) {
        ListNode smlDummy = new ListNode(0), bigDummy = new ListNode(0);
        ListNode sml = smlDummy, big = bigDummy;
        while (head != null) {
            if (head.val < x) {
                sml.next = head;
                sml = sml.next;
            } else {
                big.next = head;
                big = big.next;
            }
            head = head.next;
        }
        sml.next = bigDummy.next;
        big.next = null;
        return smlDummy.next;

      /*  作者：Krahets
        链接：https://leetcode.cn/problems/partition-list/solutions/2362068/86-fen-ge-lian-biao-shuang-zhi-zhen-qing-hha7/
        来源：力扣（LeetCode）
        著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    }
}
