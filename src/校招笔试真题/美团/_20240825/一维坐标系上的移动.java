package 校招笔试真题.美团._20240825;

import java.util.*;




//Java实现Bitset
class MyBitset {
    long[] data;
    int n;

    MyBitset(int n) {
        this.data = new long[(n - 1) / 64 + 1];
        this.n = n;
    }

    void set(int idx) {
        this.data[idx / 64] |= 1L << (idx % 64);
    }

    boolean get(int idx) {
        return (this.data[idx / 64] >>> (idx % 64) & 1) != 0;
    }

    void moveLeft() {
        long pre = 0;
        for (int i = data.length - 1; i >= 0; i--) {
            long nxt = data[i];
            data[i] = (data[i] >>> 1) | ((pre & 1) << 63);
            pre = nxt;
        }
    }

    void moveRight() {
        long pre = 0;
        for (int i = 0; i < data.length; i++) {
            long nxt = data[i];
            data[i] = (data[i] << 1) | (pre >>> 63);
            pre = nxt;
        }
        if (n % 64 > 0) {
            int r = n % 64;
            data[data.length - 1] &= (1 << r) - 1;
        }
    }

    void moveRandom() {
        long[] tmp = data.clone();
        moveRight();
        long pre = 0;
        for (int i = tmp.length - 1; i >= 0; i--) {
            long nxt = tmp[i];
            data[i] |= (tmp[i] >>> 1) | ((pre & 1) << 63);
            pre = nxt;
        }
    }

}

/**
 *
 */
public class 一维坐标系上的移动 {

    /**
     * 思路:bitset优化dp
     * 考虑一种朴素的dp: dp[i][j] 代表移动了i步后，第j个位置是否能到达。转移为:
     *
     * if s[i] == 'L':
     *
     * dp[i][j] = dp[i - 1][j - 1]
     * elif s[i] == 'R':
     *
     * dp[i][j] = dp[i - 1][j + 1]
     * else:
     *
     * dp[i][j] = dp[i - 1][j - 1] | dp[i - 1][j + 1]
     *
     * 这样的时间复杂度是O(n^2)的，不太可行。
     *
     * 我们考虑用bitset来优化这个dp(C++独有,其他语言需要自行实现)：用一个bitset来表示当前的状态，
     * 然后用位运算来模拟转移的过程。
     *
     * 我们发现,L就是把所有的位向右移动一位，R就是把所有的位向左移动一位，?就是把所有的位向左移动一位 | 向右移动一位。
     *
     * 这样复杂度就降到了O(n^2 / 64),据考生反应该做法在n=10^5的数据下可以通过。
     *
     * bitset学习网站:https://blog.csdn.net/haokan123456789/article/details/138976064
     *
     * @param args
     */
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int n = in.nextInt(), k = in.nextInt();
        MyBitset bs = new MyBitset(n);
        bs.set(k - 1);
        String s = in.next();
        for (char ch : s.toCharArray()) {
            if (ch == 'L') {
                boolean b = bs.get(0);
                bs.moveLeft();
                if (b) bs.set(0);
            } else if (ch == 'R') {
                boolean b = bs.get(n - 1);
                bs.moveRight();
                if (b) bs.set(n - 1);
            } else {

                boolean b1 = bs.get(0);
                boolean b2 = bs.get(n - 1);

                bs.moveRandom();

                if (b1) bs.set(0);
                if (b2) bs.set(n - 1);
            }
        }
        for (int i = 0; i < n; i++) {
            System.out.print(bs.get(i) ? 1 : 0);
        }
        //1  3
        //
        System.out.println();
    }
}
