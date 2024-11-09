package LeetCode数据结构与算法基础.数学;

public class 超级回文数 {


    /**
     * 方法 1：数学
     * 想法
     *
     * 假设 P=R^2 是超级回文数。
     *
     * 因为 R 是一个回文数，R 的前一半数字决定了两种可能。我们可以枚举这些数字，
     * 让 k 为前一半数字，假如 k=1234 那么 R=1234321 或者 R=12344321。
     *
     * 注意到 P < 10^18，R < (10^18)^1/2 = 10^9，同时 R = k || k' （两串数字连接），
     * 其中 k' 是 k 的反序（也有可能截掉了中间数字），所以 k < 10^5 = MAGIC，我们的神奇常数。
     *
     * 算法
     *
     * 对于每个 1 ≤ k < MAGIC，构造回文串 R 并且检验 R^2 是否为回文串。
     *
     * 我们需要将奇数和偶数长度分开考虑，这样当长度超出时就可以提前停止循环。
     *
     * 检验一个整数是否为回文数，只需要检查它是否等于它的逆。构造一个整数的逆，可以按位处理。
     * @param sL
     * @param sR
     * @return
     */
    public int superpalindromesInRange(String sL, String sR) {
        long L = Long.valueOf(sL);
        long R = Long.valueOf(sR);
        int MAGIC = 100000;
        int ans = 0;

        // count odd length;
        for (int k = 1; k < MAGIC; ++k) {
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            for (int i = sb.length() - 2; i >= 0; --i)
                sb.append(sb.charAt(i));
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v > R) break;
            if (v >= L && isPalindrome(v)) ans++;
        }

        // count even length;
        for (int k = 1; k < MAGIC; ++k) {
            StringBuilder sb = new StringBuilder(Integer.toString(k));
            for (int i = sb.length() - 1; i >= 0; --i)
                sb.append(sb.charAt(i));
            long v = Long.valueOf(sb.toString());
            v *= v;
            if (v > R) break;
            if (v >= L && isPalindrome(v)) ans++;
        }

        return ans;
    }

    public boolean isPalindrome(long x) {
        return x == reverse(x);
    }

    public long reverse(long x) {
        long ans = 0;
        while (x > 0) {
            ans = 10 * ans + x % 10;
            x /= 10;
        }

        return ans;
    }
}

/*作者：LeetCode
链接：https://leetcode.cn/problems/super-palindromes/solutions/19530/chao-ji-hui-wen-shu-by-leetcode/
来源：力扣（LeetCode）
著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
