package LeetCode数据结构与算法基础.贪心;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 * 问题描述
 * K小姐是一位珠宝设计师,她有一串由
 * N 颗宝石组成的项链。这些宝石分别由 1 到 N 标号,且没有重复的宝石。
 *
 * 为了让项链看起来更加美观,K小姐可以选择相邻的两颗宝石进行交换。不过,每颗宝石最多只能被交换两次。
 *
 * 经过若干次交换后,K小姐希望得到一串字典序最大的项链。
 * 所谓字典序,就是从项链的第一颗宝石开始,逐个比较对应位置宝石的标号大小,
 * 直到找到第一个不同的宝石,通过比较这两颗宝石的标号大小来决定两串项链的字典序大小。
 *
 * 现在,K小姐想知道,在满足每颗宝石最多被交换两次的前提下,她能得到的字典序最大的项链是什么样的?
 * 样例输入
 * 8
 * 3 7 2 1 6 5 4 8
 * 样例输出
 * 7 3 6 5 2 1 8 4
 *
 * 作者：春秋招笔试突围
 * 链接：https://www.nowcoder.com/discuss/668428267480743936?sourceSSR=search
 * 来源：牛客网
 */
public class K小姐的珠宝交换 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        // 读取宝石数量
        int N = Integer.parseInt(br.readLine());
        // 读取初始项链
        int[] jewelry = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        // 调用函数
        int[] result = rearrangeJewelry(N, jewelry);
        // 输出结果
        System.out.println(Arrays.toString(result).replaceAll("[\\[\\],]", ""));
    }

    private static int[] rearrangeJewelry(int N, int[] jewelry) {
        // 记录每颗宝石的交换次数
        int[] swapCount = new int[N + 1];
        Arrays.fill(swapCount, 2);

        for (int i = 0; i < N; i++) {
            if (swapCount[jewelry[i]] == 0) {
                continue;
            }
            int maxIdx = i;
            for (int j = i + 1; j < Math.min(N, i + 3); j++) {
                if (swapCount[jewelry[j]] < (j - i)) {
                    break;
                }
                if (jewelry[j] > jewelry[maxIdx]) {
                    maxIdx = j;
                }
            }

            if (maxIdx != i) {
                int maxVal = jewelry[maxIdx];
                System.arraycopy(jewelry, i, jewelry, i + 1, maxIdx - i);
                System.arraycopy(swapCount, jewelry[i], swapCount, jewelry[i + 1], maxIdx - i);
                jewelry[i] = maxVal;
                swapCount[jewelry[i]] -= (maxIdx - i);
                for (int j = i + 1; j <= maxIdx; j++) {
                    swapCount[jewelry[j]]--;
                }
            }
        }

        return jewelry;
    }
}

/*作者：春秋招笔试突围
链接：https://www.nowcoder.com/discuss/668428267480743936?sourceSSR=search
来源：牛客网*/
