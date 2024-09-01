package LeetCode数据结构与算法基础.day2.字符串;

/**
 * @author by KingOfTetris
 * @date 2024/8/30
 */


import org.junit.Test;

/**
 * 给定一个非空的字符串，判断它是否可以由它的一个子串重复多次构成。给定的字符串只含有小写英文字母，并且长度不超过10000。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "abab"
 * 输出: True
 * 解释: 可由子字符串 "ab" 重复两次构成。
 * 示例 2:
 * <p>
 * 输入: "aba"
 * 输出: False
 * 示例 3:
 * <p>
 * 输入: "abcabcabcabc"
 * 输出: True
 * 解释: 可由子字符串 "abc" 重复四次构成。 (或者子字符串 "abcabc" 重复两次构成。)
 */
public class 重复的子字符串 {


    @Test
    public void test() {
        String s = "aba";
        boolean repeatedSubstringPattern = repeatedSubstringPattern2(s);
        System.out.println(repeatedSubstringPattern);
    }

    public boolean repeatedSubstringPattern(String s) {
        //暴力法 O(n^3)
        //其实可以优化成O(n^2)
        //因为子串一定要包含开头才有可能组成整个字符串。
        int n = s.length();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j <= n; j++) {
                if (i == 0 && j == n) continue; //整串就不要判断了。
                String subString = s.substring(i, j);
                StringBuilder sb = new StringBuilder();
                while (sb.toString().length() < s.length()) {
                    sb.append(subString);
                }
                if (sb.toString().equals(s)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * 这题还 有个思路和判断S是否是由T移位而来的差不多
     * 就是利用(S+S).contains(T)
     * 不过这里要修改一下去掉头尾，不然s + s肯定包含一个完整的子串s重复两次
     * 不是废话吗
     *
     * 这样的算法复杂度是O(N + M)
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern2(String s) {
        //例如aba
        String ss = s + s;
        int n = ss.length();
        //去掉首尾  abaaba -> baab
        //subString [x,y)
        String subString = ss.substring(1, n - 1);
//        System.out.println(subString);
        //如果ss去掉首位以后还是包含s，那么一定有一个子串可能重复多次得到s
        // baab 就不包含 aba
        return subString.contains(s);
    }


    /**
     * 还有终极优化KMP 优化到O(N)
     * 这就有点费脑子了。
     *
     * 假设字符串s使用多个重复子串构成（这个子串是最小重复单位），重复出现的子字符串长度是x，所以s是由n * x组成。
     *
     * 因为字符串s的最长相同前后缀的长度一定是不包含s本身，所以 最长相同前后缀长度必然是m * x，
     * 而且 n - m = 1，（这里如果不懂，看上面的推理）
     *
     * 所以如果 nx % (n - m)x = 0，就可以判定有重复出现的子字符串。
     *
     * next 数组记录的就是最长相同前后缀 字符串：KMP算法精讲 (opens new window)这里介绍了什么是前缀，
     * 什么是后缀，什么又是最长相同前后缀)， 如果 next[len - 1] != -1，
     * 则说明字符串有最长相同的前后缀（就是字符串里的前缀子串和后缀子串相同的最长长度）。
     *
     * 最长相等前后缀的长度为：next[len - 1] + 1。(这里的next数组是以统一减一的方式计算的，因此需要+1，
     * 两种计算next数组的具体区别看这里：字符串：KMP算法精讲 (opens new window))
     *
     * 数组长度为：len。
     *
     * 如果len % (len - (next[len - 1] + 1)) == 0 ，
     * 则说明数组的长度正好可以被 (数组长度-最长相等前后缀的长度) 整除 ，说明该字符串有重复的子字符串。
     * @param s
     * @return
     */
    public boolean repeatedSubstringPattern3(String s){
        if (s.equals("")) return false;

        int len = s.length();
        // 原串加个空格(哨兵)，使下标从1开始，这样j从0开始，也不用初始化了
        s = " " + s;
        char[] chars = s.toCharArray();
        int[] next = new int[len + 1];

        // 构造 next 数组过程，j从0开始(空格)，i从2开始
        for (int i = 2, j = 0; i <= len; i++) {
            // 匹配不成功，j回到前一位置 next 数组所对应的值
            while (j > 0 && chars[i] != chars[j + 1]) j = next[j];
            // 匹配成功，j往后移
            if (chars[i] == chars[j + 1]) j++;
            // 更新 next 数组的值
            next[i] = j;
        }

        // 最后判断是否是重复的子字符串，这里 next[len] 即代表next数组末尾的值
        if (next[len] > 0 && len % (len - next[len]) == 0) {
            return true;
        }
        return false;
    }

}
