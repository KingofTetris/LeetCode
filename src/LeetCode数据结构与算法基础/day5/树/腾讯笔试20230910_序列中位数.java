package LeetCode数据结构与算法基础.day5.树;

import java.util.*;

/**
 * @author by KingOfTetris
 * @date 2024/8/21
 */
public class 腾讯笔试20230910_序列中位数 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        // 输入
        int t = in.nextInt();
        for (int i = 0; i < t; i++) {
            // 输入
            // int n = Integer.parseInt(in.nextLine());
            int n = in.nextInt();
            List<Integer> list = new ArrayList<>();
            int[] a = new int[n];
            int[] b = new int[n - 1];
            for (int j = 0; j < n; j++) {
                int val = in.nextInt();
                list.add(val);
                a[j] = val;
            }
            for (int j = 0; j < n - 1; j++) {
                b[j] = in.nextInt();
            }
            // 处理
            Collections.sort(list); // a升序排序
            List<String> res = new ArrayList<>();
            res.add(getMid(list));

            for (int j = 0; j < n - 1; j++) {
                //找到目标值
                int target = a[b[j]];
                // 删除目标值
                list.remove(Integer.valueOf(target));
                // 把中位数放进结果集
                res.add(getMid(list));
            }

            // 打印结果
            for (int j = 0; j < res.size() - 1; j++) {
                System.out.print(res.get(j) + " ");
            }
            System.out.println(res.get(res.size() - 1));
        }
    }

    /**
     * 获取中位数
     * 像这种结果对小数要求不一致的用字符串返回。
     */
    private static String getMid(List<Integer> list) {
        int n = list.size();
        if (n == 1) {
            return String.valueOf(list.get(0));
        }
        //偶数
        if (n % 2 == 0) {
            long val = list.get(n / 2 - 1) + list.get(n / 2); // 小心溢出
            if (val % 2 != 0) { //如果不能整除，有0.5，就用format "%.1f" 保留第一个小数
                return String.format("%.1f", val / 2.0);
            } else {
                return String.valueOf(val / 2);
            }
        }
        //奇数
        else {
            return String.valueOf(list.get(n / 2));
        }
    }
}
