package 校招笔试真题.美团.美团2023秋招0819;

import java.util.Scanner;

// 注意类名必须为 Main, 不要有任何 package xxx 信息


//求最大最小，一般都是DP。
// 定义一个类名为 "小美的01串翻转"

// 定义一个名为小美的01串翻转的类
public class 小美的01串翻转 {
    // 程序的入口，程序从这里开始执行
    /**
     * 这个程序用于计算在将一个由 '0' 和 '1' 组成的字符串进行翻转操作时，
     * 使得字符串满足特定条件的最小操作次数。
     *
     * @param args 命令行参数，此程序不使用命令行参数
     */
    public static void main(String[] args) {
        // 创建一个 Scanner 对象，用于从标准输入（通常是键盘）获取输入
        Scanner scanner = new Scanner(System.in);
        // 从标准输入读取一行字符串
        String s = scanner.nextLine();
        // 获取字符串的长度
        int n = s.length();
        // 最后的子串只有两种结果: 010101... 和 10101...
        //先造出两种结果对应的字符串
        StringBuilder s1 = new StringBuilder("0");
        StringBuilder s2 = new StringBuilder("1");
        for (int i = 1; i < n; i++) {
            // 如果 s1 的最后一个字符是 '1'，就在末尾添加 '0'，否则添加 '1'
            if (s1.charAt(s1.length() - 1) == '1') {
                s1.append("0");
            } else {
                s1.append("1");
            }
            // 如果 s2 的最后一个字符是 '1'，就在末尾添加 '0'，否则添加 '1'
            if (s2.charAt(s2.length() - 1) == '1') {
                s2.append("0");
            } else {
                s2.append("1");
            }
        }

        // pre1 表示若是第 1 种结果所需要的最少修改次数 以0开头的
        // pre2 表示若是第 2 种结果所需要的最少修改次数 以1开头的。
        int[] pre1 = new int[n + 1], pre2 = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            // 初始化 pre1 和 pre2 的值，它们表示以当前位置结尾的子串需要的最小修改次数
            pre1[i] = pre1[i - 1];
            pre2[i] = pre2[i - 1];
            // 如果 s 的第 i 个字符不等于 s1 的第 i 个字符，那么修改 s 到 s1 的操作次数加一
            if (s.charAt(i - 1) != s1.charAt(i - 1)) {
                pre1[i]++;
            }
            // 如果 s 的第 i 个字符不等于 s2 的第 i 个字符，那么修改 s 到 s2 的操作次数加一
            if (s.charAt(i - 1) != s2.charAt(i - 1)) {
                pre2[i]++;
            }
        }

        int res = 0;
        // 遍历所有子串，每种子串寻找两种结果下的最小操作次数
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                // 对每个子串，取它变成 s1 或 s2 的最小修改次数中的较小值，并累加到 res 中
                res += Math.min(pre1[j + 1] - pre1[i], pre2[j + 1] - pre2[i]);
            }
        }

        // 输出结果，即最小的修改次数
        System.out.println(res);
    }
}
