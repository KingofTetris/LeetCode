package 校招笔试真题.信也科技;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2023/10/19
 */
public class 数组求和 {


    /**
     * 从数组nums中取出连续的n个元素
     * 使得留下的数字中剩余的不同数字最多，
     * 最找这个不同数字最多的剩余数组的最大元素和。
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int m = sc.nextInt(), n = sc.nextInt();
        int[] nums = new int[m];
        for (int i = 0; i < nums.length; i++) {
            nums[i] = sc.nextInt();
        }
        int res = maxSumNums(nums, n);
        System.out.println(res);
    }
    //先找出删除哪些连续区间可以让剩余元素最多。
    private static int maxSumNums(int[] nums, int n) {
        HashMap<int[],HashSet<Integer>> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // 0 1 2
            // 1 2 3
            // 2 3 4
            //i + n - 1就是结尾下标。
            if (i + n - 1 < nums.length){
                HashSet<Integer> set = new HashSet<>();
                //从i+n开始去记录剩余元素
                for (int j = 0; j < nums.length; j++) {
                    //如果小于i 或者 大于i+n-1这个区间，那么set.add
                    if (j < i || j > i + n - 1){
                        set.add(nums[j]);
                    }
                }
                map.put(new int[]{i,i + n - 1},set);
            }
        }
        //统计完成以后，再去比较最大的set的size
        int maxSize = Integer.MIN_VALUE;
        for (HashSet<Integer> value : map.values()) {
            maxSize = Math.max(value.size(),maxSize);
        }

        //找到最大不同以后，再去找最大值。
        int res =  Integer.MIN_VALUE;
        for (Map.Entry<int[], HashSet<Integer>> entry : map.entrySet()) {
            if (entry.getValue().size() == maxSize){
                int[] key = entry.getKey();//区间
                int sum = getSum(nums,key);
                res = Math.max(res,sum);
            }
        }
        return res;
    }

    private static int getSum(int[] nums, int[] key) {
        int sum = 0;
        for (int i = 0; i < nums.length; i++) {
            if (i < key[0] || i > key[1]){
                sum += nums[i];
            }
        }
        return sum;
    }


}
