package LeetCode数据结构与算法基础.day1.数组;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/10/27 15:25
 */
public class 设计哈希映射_使用链表数组 {

    private class Entry {
        private int key;
        private int value;

        public Entry(int key, int value) {
            this.key = key;
            this.value = value;
        }

        public int getKey() {
            return key;
        }

        public int getValue() {
            return value;
        }

        public void setValue(int value) {
            this.value = value;
        }
    }

    private static final int BASE = 769;
    private LinkedList<Entry>[] data;

    /** Initialize your data structure here. */
    public 设计哈希映射_使用链表数组() {
        data = new LinkedList[BASE];
        for (int i = 0; i < BASE; ++i) {
            data[i] = new LinkedList<>();
        }
    }

    /** value will always be non-negative. */
    public void put(int key, int value) {
        int h = hash(key);
        for (Entry pair : data[h]) {
            if (pair.getKey() == key) {
                pair.setValue(value);
                return;
            }
        }
        data[h].offerLast(new Entry(key, value));
    }

    /** Returns the value to which the specified key is mapped, or -1 if this map contains no mapping for the key */
    public int get(int key) {
        int h = hash(key);
        for (Entry pair : data[h]) {
            if (pair.getKey() == key) {
                return pair.value;
            }
        }
        return -1;
    }

    /** Removes the mapping of the specified value key if this map contains a mapping for the key */
    public void remove(int key) {
        int h = hash(key);
        for (Entry pair : data[h]) {
            if (pair.key == key) {
                data[h].remove(pair);
                return;
            }
        }
    }

    private static int hash(int key) {
        return key % BASE;
    }

 /*   作者：力扣官方题解
    链接：https://leetcode.cn/problems/design-hashmap/solutions/654139/she-ji-ha-xi-ying-she-by-leetcode-soluti-klu9/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
