package LeetCode数据结构基础.day3.链表;

/**
 * @author KingofTetris
 * @File ListNode
 * @Time 2021/10/3  13:22
 */
public class ListNode {
    public int val;
    public ListNode next;
    public ListNode(int x) {
          val = x;
          next = null;
      }
      public ListNode(int val, ListNode next) { this.val = val; this.next = next; }
      public ListNode(){}

      public static ListNode createListNode(int[] node){
        ListNode res = new ListNode(); //不要搞错。res也是指针不是节点里的内容。
        ListNode q = res; //用q也指向头节点，让q去移动。 把数组里的值添加到链表里
          for (int i = 0; i < node.length; i++) {
              q.next = new ListNode(node[i]);
              q = q.next;
          }
          return res.next; //最后返回没有移动的指针res，res指向头节点，那么res->next就是第一个结点。
      }


    public static void printLinkedList(ListNode head) {// 将链表结果打印
        ListNode current = head;
        while (current != null) {
            System.out.printf("%d -> ", current.val);
            current = current.next;
        }
        System.out.println("NULL");
    }

    public static int size(ListNode head){
        int len = 0;
        while (head != null){
            len++;
            head = head.next;
        }
        return len;
    }


}
