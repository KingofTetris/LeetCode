package LeetCode数据结构与算法基础.day1.数组;

import java.util.Iterator;
import java.util.LinkedList;

/**
 * @Author KingofTetris
 * @Date 2022/10/27 15:25
 */
public class 设计哈希映射 {

    class MyHashMap {

        private class Pair{
            int key;
            int value;

            public Pair(int key, int value) {
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
        private LinkedList[] data;

        public MyHashMap() {
            data = new LinkedList[BASE];
            for (int i = 0; i < BASE; i++) {
                data[i] = new LinkedList<Integer>();
            }
        }


        public void put(int key,int value){
            int h =  hash(key);
            //现在存的是Pair 不是单纯的整数
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()){
                Pair pair = iterator.next();
                //有就覆盖
                if (pair.getKey() == key){
                    pair.setValue(value);
                    return;
                }
            }
            //如果没有就直接放进去
            data[h].addLast(new Pair(key,value));
        }


        public int get(int key){
            int h =  hash(key);
            //现在存的是Pair 不是单纯的整数
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()){
                Pair pair = iterator.next();
                //有就返回value
                if (pair.getKey() == key){
                    return pair.getValue();
                }
            }
            //没有就-1
            return -1;
        }

        public void remove(int key) {
            int h = hash(key);
            Iterator<Pair> iterator = data[h].iterator();
            while (iterator.hasNext()) {
                Pair pair = iterator.next();
                if (pair.key == key) {
                    data[h].remove(pair);
                    return;
                }
            }
        }

        public int hash(int key){
            return key % BASE;
        }
    }



}
