package LeetCode数据结构与算法基础.day3.链表;

import java.util.HashMap;
import java.util.Map;
import java.util.Queue;


// Definition for a Node.
//注意这题的node 要多一个random引用。
/*class Node {
    int val;
    Node next, random;
    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}*/

class 随机链表的复制 {

    //这题的问题在于 复制的时候，随机节点可能还没生成，需要先生成节点再指向random
    //这就需要一个HashMap来记录这些新节点。

    public Node copyRandomList(Node head) {
        if(head == null) return null;
        Node cur = head;
        // 3. 复制各节点，并建立 “原节点 -> 新节点” 的 Map 映射
        Map<Node, Node> map = new HashMap<>();
        while(cur != null) {
            //这里的new Node(cur.val)就把值给复制好了，节点也创建出来了
            map.put(cur, new Node(cur.val));
            cur = cur.next;
        }
        //cur重新回到head,开始赋值random
        cur = head;
        // 4. 构建新链表的 next 和 random 指向
        while(cur != null) {
            //map.get(cur)就是新节点
            map.get(cur).next = map.get(cur.next);//复制next
            map.get(cur).random = map.get(cur.random); //复制random
            cur = cur.next;
        }
        // 5. 返回新链表的头节点
        return map.get(head);
    }
}

/*作者：Krahets
链接：https://leetcode.cn/problems/copy-list-with-random-pointer/solutions/2361362/138-fu-zhi-dai-sui-ji-zhi-zhen-de-lian-b-6jeo/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
