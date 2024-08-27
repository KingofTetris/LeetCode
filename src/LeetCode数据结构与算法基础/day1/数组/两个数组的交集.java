package LeetCode数据结构与算法基础.day1.数组;

import java.util.HashSet;

/**
 * @author by KingOfTetris
 * @date 2024/8/27
 */
public class 两个数组的交集 {

    /**
     * 给你两个未排序的数组，请你求出他们的交集
     * @param nums1
     * @param nums2
     * @return
     */
    public int[] intersect(int[] nums1, int[] nums2) {
        HashSet<Integer> set1 = new HashSet<>();
        HashSet<Integer> resSet = new HashSet<>();

        for (Integer num : nums1) {
            set1.add(num);
        }

        for (int num : nums2) {
            //如果两个集合都出现了。就是交集
            if (set1.contains(num)){
                resSet.add(num);
            }
        }
        //流式转数组，你要一个一个遍历set也可以。
        int[] res = resSet.stream().mapToInt(x -> x).toArray();
        return res;
    }
}
