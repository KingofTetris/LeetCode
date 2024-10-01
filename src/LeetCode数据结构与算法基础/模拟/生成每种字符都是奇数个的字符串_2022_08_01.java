package LeetCode数据结构与算法基础.模拟;

/**
 * @Author KingofTetris
 * @Date 2022/8/1 14:42
 * 给你一个整数 n，请你返回一个含 n 个字符的字符串，其中每种字符在该字符串中都恰好出现 奇数次 。
 *
 * 返回的字符串必须只含小写英文字母。如果存在多个满足题目要求的字符串，则返回其中任意一个即可。
 *
 * 1 <= n <= 500
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/generate-a-string-with-characters-that-have-odd-counts
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 生成每种字符都是奇数个的字符串_2022_08_01 {
    /**
     * 返回一个长度为n的字符串，其中每种字符出现的次数都是奇数次
     * @param n
     * @return
     */

    /**
     * 或者一行重拳出击
     */
    public String generateTheString2(int n) {
//         * String.repeat 是JDK11新增的API。就是把字符串重复n次
        return (n & 1) == 0 ? "a".repeat(Math.max(0, n - 1)) + "b" : "a".repeat(Math.max(0, n));
    }
}
