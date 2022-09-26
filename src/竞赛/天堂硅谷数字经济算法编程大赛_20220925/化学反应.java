package 竞赛.天堂硅谷数字经济算法编程大赛_20220925;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2022/9/26
 */
public class 化学反应 {


    /**
     * 每次拿着最大值减去次大值。直到剩下一个数
     * 每次都要排序?
     * @param material
     * @return
     */
    public int lastMaterial(int[] material) {
        LinkedList<Integer> integers = new LinkedList<>();
        for (int i : material) {
            integers.add(i);
        }

        while (integers.size() > 1){
            Collections.sort(integers);
            int temp = integers.get(integers.size() - 1) - integers.get(integers.size() - 2);
            integers.pollLast();
            integers.pollLast();
            integers.add(temp);
        }

        return integers.get(0);
    }
}
