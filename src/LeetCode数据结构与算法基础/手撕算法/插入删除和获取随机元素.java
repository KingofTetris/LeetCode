package LeetCode数据结构与算法基础.手撕算法;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/10/25
 */
public class 插入删除和获取随机元素 {

    class RandomizedSet {
        List<Integer> nums; //集合
        Map<Integer, Integer> indices; //记录每个数的下标
        Random random; //随机值

        public RandomizedSet() {
            nums = new ArrayList<>();
            indices = new HashMap<>();
            random = new Random();
        }

        public boolean insert(int val) {
            if (indices.containsKey(val)) {
                return false;
            }
            int index = nums.size();
            nums.add(val);
            indices.put(val, index);
            return true;
        }

        public boolean remove(int val) {
            if (!indices.containsKey(val)) {
                return false;
            }
            int index = indices.get(val);
            int last = nums.get(nums.size() - 1);
            nums.set(index, last);
            indices.put(last, index);
            nums.remove(nums.size() - 1);
            indices.remove(val);
            return true;
        }

        public int getRandom() {
            int randomIndex = random.nextInt(nums.size());
            return nums.get(randomIndex);
        }
    }


}
