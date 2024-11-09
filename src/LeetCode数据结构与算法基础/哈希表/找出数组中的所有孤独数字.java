package LeetCode数据结构与算法基础.哈希表;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author by KingOfTetris
 * @date 2024/11/8
 */
public class 找出数组中的所有孤独数字 {

    public List<Integer> findLonely(int[] nums) {
        Map<Integer, Integer> counts = new HashMap<>();
        for (int num : nums) {
            counts.put(num, counts.getOrDefault(num, 0) + 1);
        }
        List<Integer> lonely = new ArrayList<>();
        for (int num : nums) {
            if (counts.get(num) == 1 && !counts.containsKey(num - 1) && !counts.containsKey(num + 1)) {
                lonely.add(num);
            }
        }
        return lonely;
    }

}
