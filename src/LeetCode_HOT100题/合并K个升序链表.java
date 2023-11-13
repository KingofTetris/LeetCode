package LeetCode_HOT100题;

import LeetCode数据结构基础.day3.链表.ListNode;
import org.junit.Test;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author by KingOfTetris
 * @date 2022/10/11
 */
public class 合并K个升序链表 {

    //优先队列 取名叫pq的时候就是队列，如果叫heap 那就是堆(默认是小根堆)

    @Test
    public void test(){
        ListNode l1 = ListNode.createListNode(new int[]{1,2,3});
        ListNode l2 = ListNode.createListNode(new int[]{2,5,6});
        ListNode l3 = ListNode.createListNode(new int[]{-2,-1,0});
        ListNode[] lists = new ListNode[3];
        lists[0] = l1;
        lists[1] = l2;
        lists[2] = l3;
        ListNode listNode = mergeKLists(lists);
        ListNode.printLinkedList(listNode);
    }

    /**
     * n个升序链表合并就需要借助最小堆来完成合并。
     * 因为你每次合并都要找最小的开头，最小堆就能完成这个任务。
     * @param lists
     * @return
     */
    public ListNode mergeKLists(ListNode[] lists) {
        //默认就是小根堆,比较节点的值。
        //因为ListNode这个是自定义类型，你必须要指定比较方式。
        PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(o -> o.val));
        //链表入堆
        for (ListNode node : lists) {
            if (node != null) {
                pq.offer(node);
            }
        }
        //新建一个哑节点dummyHead
        ListNode dummyHead = new ListNode();
        //res
        //dummyHead.next指向链表开头。
        ListNode res = dummyHead;

        //当pq不为空
        while(!pq.isEmpty()) {
            //当前链表出队
            ListNode cur = pq.poll();
            //res.next指向cur
            res.next = cur;
            res = res.next;//res后移
            //如果cur.next不为空就继续添加进优先队列
            //每次添加进优先队列都会把链头最小的整理到队头
            //这样你每次poll出来都能保证最小。
            if (cur.next != null) {
                pq.offer(cur.next);
            }
        }

        //最后返回dummyHead.next就是整条完整的升序链
        return dummyHead.next;
    }
}
