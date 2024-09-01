package 校招笔试真题.蚂蚁金服._20240825;

import java.util.*;

public class 一维坐标系 {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt(); // 读取n
        int k = sc.nextInt(); // 读取k
        String s = sc.next(); // 读取字符串s

        // dp[i][j] 代表移动了i步后，第j个位置是否能到达,简化成一个bitset表示
        BitSet b = new BitSet(100001); // 代表移动了i步后，第j个位置是否能到达,简化成一个bitset表示
        BitSet mask = new BitSet(100001); // 用于去掉超出右边界的位

        // 0~n-1位都是1,表示有效位,超出n-1位的都是0
        for (int i = 0; i < n; i++) {
            mask.set(i);
        }

        b.set(k - 1); // 初始位置为k-1

        for (int i = 0; i < n; i++) {
            // L就是把所有的位向右移动一位(因为是从左往右输出二进制数)
            if (s.charAt(i) == 'L') {
                boolean first = b.get(0);
                b = shiftRight(b, 1);
                // 注意第一个数如果是1,则右移后第一个数也是1，因为本题中在边界的数是不能往左移动的
                if (first) {
                    b.set(0);
                }
            } else if (s.charAt(i) == 'R') { // 同理
                boolean last = b.get(n - 1);
                b = shiftLeft(b, 1);
                if (last) {
                    b.set(n - 1);
                }
            } else {
                // ?就是把所有的位向左移动一位 | 向右移动一位 , 上述两种情况的合并
                boolean first = b.get(0);
                boolean last = b.get(n - 1);
                b.or(shiftLeft(b, 1));
                b.or(shiftRight(b, 1));
                if (first) {
                    b.set(0);
                }
                if (last) {
                    b.set(n - 1);
                }
            }
            // 去掉超出右边界的位.这里一定要有mask是因为超出右边界的位的1之后可能还会因为L再回到有效区域
            // 但是按照题意,超出右边界是被禁止的
            b.and(mask);
        }

        // 输出结果
        for (int i = 0; i < n; i++) {
            if (b.get(i)) {
                System.out.print(1);
            } else {
                System.out.print(0);
            }
        }

        sc.close();
    }

    private static BitSet shiftLeft(BitSet bs, int n) {
        BitSet shifted = new BitSet(bs.size());
        for (int i = n; i < bs.size(); i++) {
            if (bs.get(i)) {
                shifted.set(i - n);
            }
        }
        return shifted;
    }

    private static BitSet shiftRight(BitSet bs, int n) {
        BitSet shifted = new BitSet(bs.size());
        for (int i = 0; i < bs.size() - n; i++) {
            if (bs.get(i)) {
                shifted.set(i + n);
            }
        }
        return shifted;
    }
}
