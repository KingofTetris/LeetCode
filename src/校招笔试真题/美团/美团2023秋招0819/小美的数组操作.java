package 校招笔试真题.美团.美团2023秋招0819;

import java.util.*;
//最少操作(一个数+1,一个数-1) 使得数组中的众数最多
public class 小美的数组操作 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt(); // 读取数组长度
        List<Long> nums = new ArrayList<>(); // 创建一个用于存储数组元素的列表
        for (int i = 0; i < n; ++i) {
            nums.add(scanner.nextLong()); // 读取数组的每个元素
        }
        long sum = 0;
        for (long num : nums) {
            sum += num; // 计算数组元素的总和
        }


        /**
         * 能想到平均值，然后求差值/2 可以骗到16%。
         * 其实也就是众数只有1个数值的情况下。
         */
        if (sum % n == 0) { // 如果总和能被数组长度整除，说明平均值是整数
            long avg = sum / n; // 计算平均值
            long ans = 0;
            for (long a : nums) {
                ans += Math.abs(a - avg); // 计算每个元素与平均值的绝对差，并累加到答案中
            }
            System.out.println(ans / 2); // 输出操作次数的一半（因为每次操作可以同时改变两个元素）
            return;
        }

        /**
         * 剩下的84% 众数可能有多个数值，就要点硬功夫了。
         */
        //如果不能整除就有点麻烦了。
        Collections.sort(nums); // 为了二分，先对数组进行排序
        long res1 = comp(nums, 0, n - 2); // 调用 comp 函数处理部分元素
        long res2 = comp(nums, 1, n - 1); // 调用 comp 函数处理另一部分元素
        System.out.println(Math.min(res1, res2)); // 输出两部分处理结果的最小值
    }


    //二分

    /**
     * 函数 comp 的目标是找到一种操作方式，
     * 使得在范围 [l, r] 内的数组元素尽量接近某个值（平均值或平均值加1），
     * 并返回这种操作方式的最小代价。
     * @param nums
     * @param l
     * @param r
     * @return
     */
    static long comp(List<Long> nums, int l, int r) {

//        1.计算给定范围 [l, r] 内元素的总和 tot，然后计算该范围内元素的平均值 avg。
        long tot = 0;
        for (int i = l; i <= r; ++i) {
            tot += nums.get(i); // 计算指定范围内元素的总和
        }
//      2.  计算两种操作方式的代价：
//        对于平均值 avg，计算在范围内将元素调整到平均值的差值之和 a，以及将元素调整到平均值以下的差值之和 b。
//        对于平均值 avg + 1，同样计算调整到平均值的差值之和 c，以及调整到平均值以上的差值之和 d。
        long avg = tot / (r - l + 1); // 计算指定范围内元素的平均值
        long avg2 = avg + 1; // 平均值加1
        long a = 0, b = 0, c = 0, d = 0;

        //返回两种操作方式的代价的最小值，即 min(max(a, b), max(c, d))。
        for (int i = l; i <= r; ++i) {
            if (nums.get(i) >= avg) {
                //计算调整到avg要多少次，分为+1和-1两种方式逼近，最后取里面的最大值，才能使得众数最多。
                a += nums.get(i) - avg; // 调整到avg
            } else {
                b += avg - nums.get(i); // 调整到avg
            }

            //这里也是相同地向 avg + 1 逼近。
            if (nums.get(i) >= avg2) {
                c += nums.get(i) - avg2; // 计算元素与 (平均值+1) 的差值并累加
            } else {
                d += avg2 - nums.get(i); // 计算 (平均值+1) 与元素的差值并累加
            }
        }

        //最后返回次数最小值
        return Math.min(Math.max(a, b), Math.max(c, d)); // 返回处理结果中的最小值
    }
}
