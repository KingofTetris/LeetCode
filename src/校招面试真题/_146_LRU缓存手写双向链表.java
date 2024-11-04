package 校招面试真题;

/**
 * @author by KingOfTetris
 * @date 2023/8/12
 */


import java.util.HashMap;
import java.util.Map;

/**
 * 本题的最大要求就是函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。
 * 那么O(1)的复杂度访问，我们很容易想到数组。
 * 但问题在于PUT的话，数组就没有办法实现O(1)

 为了达到上面get 和 put 能够实现O(1)的复杂度，那么应该要能实现以下三点。

 1、显然 cache 中的元素必须有时序，以区分最近使用的和久未使用的数据，
 当容量满了之后要删除最久未使用的那个元素腾位置。

 2、我们要在 cache 中 在O(1)时间内 找某个 key 是否已存在并得到对应的 val；

 3、每次访问 cache 中的某个 key，需要将这个元素变为最近使用的，
 也就是说 cache 要支持在任意位置快速插入和删除元素。

 那么，什么数据结构同时符合上述条件呢？

 哈希表查找快，但是数据无固定顺序；
 链表有顺序之分，插入删除快，但是查找慢。

 所以结合一下，形成一种新的数据结构：哈希链表 LinkedHashMap。

 于是我们用key来定位元素在链表中的位置，达到get O(1)
 使用表头存储最久不使用的元素，表尾存储最新元素，使得删除 直接删除表头。
 存储新元素直接存在表尾，如果是Put旧元素，因为get是O(1) 那么找到元素以后，更新也是O(1)

 只是更新过程中可能会遇到节点的移动，这个操作就不是O(1)了。


 总结以下就是
 链表显然是支持在任意位置快速插入和删除的，改改指针就行。
 只不过传统的链表无法按照索引快速访问某一个位置的元素，
 而这里借助哈希表，可以通过 key 快速映射到任意一个链表节点，然后进行插入和删除。


那么问题来了，为什么我们不能使用单链表，必须要使用双向链表？为什么有了KEY映射，我们还要在节点里面存储key??
 1.必须，因为我们的需要删除操作，删除得先找到删除节点的前一个节点。
 如果是单链表，那么你得遍历一次整个链表。显然是O(n) 不符合题意。
2.

 */

//我们自己造一下轮子 加深理解
    //先写双链表的节点
    //key val
    //next pre
class Node{
    public int key, val;
    public Node next, prev;
    public Node(int k, int v) {
        this.key = k;
        this.val = v;
    }
}
//循环双链表
class DoubleList{
    //头尾虚节点
    private Node head,tail;

    private int size;//List的容量

    //初始化
    public DoubleList(){
        head = new Node(-1,-1);
        tail = new Node(-1,-1);
        head.next = tail;
        tail.prev = head;
        size = 0;
    }

    //尾部添加节点x
    public void addLast(Node x){
        //修改x的指向
        x.prev = tail.prev;
        x.next = tail;
        //修改x前一个节点的指向
        tail.prev.next = x;
        //修改tail节点的指向
        tail.prev = x;
        size++;
    }

    //删除链表中的x节点
    public void remove(Node x){
        x.prev.next = x.next;
        x.next.prev = x.prev;
        size--;
    }

    //删除链表中最旧的x节点
    //我们把第一个节点设置为最老节点，末尾节点设置为最新节点。
    public Node removeFirst(){
        //如果是空链表，返回null
        if (head.next == tail){
            return null;
        }
        Node first = head.next;
        remove(first);
        return first;
    }

    public int size() {return size;}
}

public class _146_LRU缓存手写双向链表 {
    //手写了双向链表，我们使用Map来映射key和Node
    private Map<Integer,Node> map;

    private DoubleList cache;//这个双链表才是真正存储元素的数据结构

    private int capacity;

    public _146_LRU缓存手写双向链表(int capacity){
        this.capacity = capacity;
        map = new HashMap<>();
        cache = new DoubleList();
    }

    /**
     * ok 我们开始实现get put
     */
    public int get(int key){
        if (!map.containsKey(key)){
            return -1;
        }
        Node x = map.get(key);
        //如果包含这个key，那么就把这个key对应的Node删掉，然后放到末尾
        cache.remove(x);
        cache.addLast(x);
        return x.val;
    }

    public void put(int key,int val){
        //判断是更新还是新建
        //如果是更新，那么更新以后放表尾。
        if (map.containsKey(key)){
            Node x = map.get(key);
            x.val = val;//更新x的值
            cache.remove(x);
            cache.addLast(x);
        }else{//如果不包含,首先建立map映射
            //如果不包含才能去new出来，这种链表的节点要小心new这个关键词，new的对象，虽然key一样
            //但是已经不是同一个对象了。
            Node x = new Node(key,val);
            //建立映射
            map.put(key,x);
            //然后那么我们去判断链表的长度是否还放得下
            //如果放得下，直接插入最后
            if (cache.size() < capacity){
                cache.addLast(x);
            }
            //如果放不下了 就要删掉表头
            else {
                Node remove = cache.removeFirst();
                //别忘了map也要删
                map.remove(remove.key);
                //最后把新节点插入到双链表的尾巴
                cache.addLast(x);
            }
        }
        //ok 大功告成
    }

    public static void main(String[] args) {
        _146_LRU缓存手写双向链表 lRUCache = new _146_LRU缓存手写双向链表(2);
        lRUCache.put(2, 1); // 缓存是 {2=1}
        lRUCache.put(2, 2); // 缓存是 {2=2}
        System.out.println(lRUCache.get(2));
        lRUCache.put(1,1);
        lRUCache.put(4,1);
        System.out.println(lRUCache.get(2));//节点2已经被删掉了。
    }
}
