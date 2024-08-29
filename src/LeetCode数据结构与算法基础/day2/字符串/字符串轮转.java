package LeetCode数据结构与算法基础.day2.字符串;

import org.junit.Test;

/**
 * @Author KingofTetris
 * @Date 2022/9/28 10:41
 * 字符串轮转。给定两个字符串s1和s2，请编写代码检查s2是否为s1旋转而成
 * （比如，waterbottle是erbottlewat旋转后的字符串）。
 * <p>
 * 示例1:
 * <p>
 * 输入：s1 = "waterbottle", s2 = "erbottlewat"
 * 输出：True
 * 示例2:
 * <p>
 * 输入：s1 = "aa", s2 = "aba"
 * 输出：False
 * 提示：
 * <p>
 * 字符串长度在[0, 100000]范围内。
 * 说明:
 * <p>
 * 你能只调用一次检查子串的方法吗？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/string-rotation-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 字符串轮转 {

    @Test
    public void test() {
        String s1 = "waterbottle";
        String s2 = "lewaterbott";
        System.out.println(isFlipedString(s1, s2));
    }


    /**
     * 这个办法没法解决
     * abcd
     * acdb
     * 返回的是true?应该是false
     *
     * @param s1
     * @param s2
     * @return
     */
    public boolean isFlipedString(String s1, String s2) {
        if (s1.length() != s2.length()) return false;

        int i = 0, j = 0;//双指针
        StringBuffer sb1 = new StringBuffer();//记录旋转的部分

        for (; i < s1.length() && j < s2.length(); ) {
            if (s1.charAt(i) != s2.charAt(j)) { //不相等 s1 动
                sb1.append(s1.charAt(i));
                i++;
            } else { //相等的话一起动
                i++;
                j++;
            }
        }

        String sb2 = new String();
        if (j < s2.length()) {
            s2 = s2.substring(j, s2.length());
        }

        return sb1.toString().equals(s2);//如果一样就是旋转得来的。
    }


    /**
     * 换个思路
     * 把 s1 看成是 L R 两部分
     * 则 s2 是将子串 R 移动到了 L 前面 变成 R L
     * 那么我们再拼凑一个 s2 + s2 也就是 R L R L
     * 中间的 R (L R) L 就是 R S1 L
     * 那么我们只要判断字符串s2 + s2 是否包含 s1 即可
     *
     * @param s1
     * @param s2
     * @return 时间复杂度： 设字符串 s1 ,s2 长度为n
     * <p>
     * 暴力是 O(n^2)
     * 「子串匹配 KMP 算法」的时间复杂度为 O(N)
     * 「Boyer–Moore string-search algorithm」时间复杂度为 O(N) 。
     * 本文直接调用编程语言的库函数，时间复杂度由库函数的具体实现方法确定。
     * 空间复杂度 O(N)
     * <p>
     * 作者：jyd
     * 链接：https://leetcode.cn/problems/string-rotation-lcci/solution/mian-shi-ti-0109-zi-fu-chuan-lun-zhuan-q-eyi1/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     */
    public boolean isFlipedString2(String s1, String s2) {

        //JDK8的 String.contains 底层并没有用到KMP
        //反而是最被人唾弃的暴力法O(nm)，在 m 长的字符串里 匹配 n 长的子串。
        //同样是先找到 子串的开头字符，然后比较后面的部分是否一样
        /**
         * 为什么不用KMP，而是采用暴力法
         *  KMP的O(n+m)在实际应用并不那么理想，
         *  平均性能大都比不上Boyer-Moore 或 简化的 horspool算法，
         *  facebook 的folly库就采用Boyer-Moore算法，
         *  不过与KMP类似的Aho-Corasick在压缩空间后还是很可观的。
         *  Snort初期采用的是Aho-Corasick，但现在好像改用wu-manber了，
         *  太久没关注了。若想深入了解，可以去查阅这本书<< 柔性字符串匹配 >>
         *
         * 作者：楊.DL
         * 链接：https://www.zhihu.com/question/27852656/answer/38428419
         * 来源：知乎
         * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
         */
        return s1.length() == s2.length() ? (s2 + s2).contains(s1) : false;
    }
}
