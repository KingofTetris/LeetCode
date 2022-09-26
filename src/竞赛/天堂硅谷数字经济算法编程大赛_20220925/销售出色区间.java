package 竞赛.天堂硅谷数字经济算法编程大赛_20220925;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2022/9/26
 */
public class 销售出色区间 {

    @Test
    public void test(){
        int[] sales = {9,9,6,0,6,6,9};
        int i = longestESR(sales);
        System.out.println(i);
    }
    /**
     * //TODO 没看懂啥意思
     * HashMap
     * @param sales
     * @return
     */
    public int longestESR(int[] sales) {
        int cur = 0; //记录当前区间长度
        int res = 0; // 记录最长
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < sales.length; i++) {
            cur = sales[i] > 8 ? cur + 1 : cur - 1; //大于8 +1，小于8 -1
            if (cur > 0) { //如果当前长度大于0
                res = i + 1; //那么res = i + 1
            } else {
                if (!map.containsKey(cur)) {
                    map.put(cur, i);
                }
                if (map.containsKey(cur - 1)) {
                    res = Math.max(res, i - map.get(cur - 1));
                }
            }
        }
        return res;
    }
}
