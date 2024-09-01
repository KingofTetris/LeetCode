package 校招笔试真题.蚂蚁金服._20240825;

import java.util.*;


/**
 * 塔子哥定义一个数为好数，当且仅当这个数字的所有数位互不相同，例如:
 * 1234 就是一个好数，而1233就不是。
 *
 * 塔子哥现在有一个正整数x，他想知道，不小于x的最小好数是几，请你帮帮他吧。
 * 输入描述
 * 每个测试文件均包含多组测试数据。
 *
 * 第一行输入一个正整数T(1≤T≤10^5)代表数据组数，每组测试数据描述如下:
 *
 * 在一行上输入一个整数x(1≤x<10^9)代表塔子哥初始拥有的数字。
 *
 * 输出描述
 * 对于每组测试数据，在一行上输出一个整数，代表不小于x的最小好数。
 *
 * 输入
 * 3
 * 1233
 * 9876
 * 1
 *
 * 输出
 * 1234
 * 9876
 * 1
 */
public class 好数 {

    /**
     *
     TaZi LV 10 SU @ 2 天前
     思路：贪心,思维
     1.先找到最长的不含重复数字的前缀

     例如18698,它的最长合法前缀是1869

     2.从后往前找到可以增加并且不会发生重复的数位

     例如1869是6 + 1 = 7 , 因为9不能+1了,7在18里也没出现过。则新的合法前缀187

     3.对于剩下的位置，我们贪心的从小往大填，跳过新的合法前缀里包含的数。

     186 -> 18702

     4.需要增位的情况:在第二步的时候，如果找不到一个合法位置，我们需要增加一个数位，然后直接进行生成。

     比如 999 -> 1023

     其实你在纸上都快想到了，为什么不再动动脑子
     * @param args
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt(); // 读取测试用例数量

        for (int x = 0; x < t; x++) {
            String str = sc.next(); // 读取数字字符串
            char[] s = str.toCharArray(); // 转换为字符数组
            int n = s.length; // 字符串长度
            int pos = -1;

            // 找到第一个重复的数字
            for (int i = 0; i < n; i++) {
                if (new String(s).substring(0, i).contains(String.valueOf(s[i]))) {
                    pos = i;
                    break;
                }
            }

            if (pos == -1) {
                System.out.println(new String(s)); // 如果没有重复的数字，直接输出
                continue;
            }

            // 找到第一个可以增加的数位
            while (pos >= 0) {
                int k = 1;
                // 尝试反复加1,直到找到一个可以加的数位
                while (new String(s).substring(0, pos).
                        contains(String.valueOf(Character.getNumericValue(s[pos]) + k))) {
                    k++;
                }

                if (String.valueOf(Character.getNumericValue(s[pos]) + k).equals("10")) {
                    pos--;
                } else {
                    s[pos] = String.valueOf(Character.getNumericValue(s[pos]) + k).charAt(0);
                    break;
                }
            }

            if (pos == -1) {
                StringBuilder sb = new StringBuilder();
                // 生成1到n的数字字符串，在第二位插入0
                for (int i = 1; i <= n; i++) {
                    sb.append(i);
                }
                sb.insert(1, "0");
                System.out.println(sb.toString());
            } else {
                List<Character> allDig = new ArrayList<>();
                // 生成剩下的数字
                for (int i = 0; i < 10; i++) {
                    if (new String(s).indexOf(Character.forDigit(i, 10)) == -1) {
                        allDig.add(Character.forDigit(i, 10));
                    }
                }

                Collections.sort(allDig);

                for (int i = pos + 1; i < n; i++) {
                    s[i] = allDig.remove(0);
                }

                System.out.println(new String(s));
            }
        }

        sc.close();
    }
}
