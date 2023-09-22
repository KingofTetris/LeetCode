package 校招面试真题;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2023/8/12
 */


//我们前面自己手写了DoubleList 双向链表，体会了造轮子，现在我们直接继承LinkedHashMap来实现
public class _146_LRU缓存直接继承LinkedHashMap extends LinkedHashMap<Integer,Integer> {
    private int capacity;

    public _146_LRU缓存直接继承LinkedHashMap(int capacity){
        //直接使用父类的构造方法。
        //第三个参数accessOrder 排序模式
        //true:按照访问顺序排序，显然这个就是LRU
        //false:按照插入顺序排序
        super(capacity,
                0.75f,
                true);
        this.capacity = capacity;
    }

    public Integer get(Integer key) {
        return super.getOrDefault(key,-1);
    }

    public Integer put(Integer key, Integer value) {
        return super.put(key, value);
    }

    //LinkedHashMap的删除规则，自定义为如果比capacity大就删除Eldest的Entry，
    // 那么就是LRU缓存。
    @Override
    protected boolean removeEldestEntry(Map.Entry eldest) {
        return size() > capacity;//size > capacity的时候就自动删除最老的节点.
    }
}
