package 每日一题;

/**
 * @Author KingofTetris
 * @Date 2022/10/31 14:53
 */
public class 神奇字符串_2022_10_31 {
    /**
     * 构造字符串
     * @param n
     * @return
     */
    public int magicalString(int n) {

        //因为构造的字符串可能最后要加两个字符
        //长度就变成了 n + 1
        //最后统计1的个数的时候，要取s的前n个字符 而不是整个s
        char[] s = new char[n + 2];
        s[0] = 1; s[1] = s[2] = 2; //前3个字符固定
        char c = 2;
        //从第四个字符开始，按照i的指示构造s
        for (int i = 2, j = 3; j < n; ++i) {
            c ^= 3; // 1^3=2, 2^3=1，这样就能在 1 和 2 之间转换
            s[j++] = c;
            if (s[i] == 2) s[j++] = c; //如果是2 再加一次
        }
        int ans = 0;
        for (int i = 0; i < n; ++i) ans += 2 - s[i]; // 2-1=1，2-2=0，这样就只统计了 1
        return ans;
    }
/*
    作者：endlesscheng
    链接：https://leetcode.cn/problems/magical-string/solution/by-endlesscheng-z8o1/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
