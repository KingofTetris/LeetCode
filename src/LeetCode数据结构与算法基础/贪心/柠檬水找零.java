package LeetCode数据结构与算法基础.贪心;

import org.junit.Test;

import java.util.HashMap;

/**
 * @author by KingOfTetris
 * @date 2024/9/10
 */
public class 柠檬水找零 {


    @Test
    public void test() {

    }

    public boolean lemonadeChange(int[] bills) {
        //钞票的金额有5，10，20。
        HashMap<Integer, Integer> map = new HashMap<>();
        //初始张数都是0
        map.put(5, 0);
        map.put(10, 0);
        map.put(20, 0);
        for (int i = 0; i < bills.length; i++) {
            if (bills[i] == 5) {
                //存一张5块
                map.put(5, map.get(5) + 1);
            }
            if (bills[i] == 10) {
                if (map.get(5) == 0) {
                    return false;
                } else {
                    map.put(10, map.get(10) + 1);
                    //去掉一张5块钱
                    int v = map.get(5) - 1;
                    map.put(5, v);
                }
            }
            if (bills[i] == 20) {
                //要么找3张5块，要么找1张十块和1张5块。优先用10块钱找
                int v1 = map.get(10);
                int v2 = map.get(5);
                //如果有10块，也有5块
                if (v1 >= 1 && v2 >= 1) {
                    v1--;
                    v2--;
                    map.put(20, map.get(20) + 1);
                    map.put(10, v1);
                    map.put(5, v2);
                }
                //如果没有10块，但至少有3张5块。
                else if (v1 == 0 && v2 >= 3) {
                    map.put(20, map.get(20) + 1);
                    v2 = v2 - 3; //消耗3张5块钱。
                    map.put(5, v2);
                }
                //如果既然没有10块，也没有3张5块。
                //其他情况都找不了钱。
                else {
                    return false;
                }
            }
        }
        //走到尽头就说明全部找零成功。
        return true;
    }
}
