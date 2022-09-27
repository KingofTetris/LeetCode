package 程序员面试金典;

import org.junit.Test;

/**
 * @author by KingOfTetris
 * @date 2022/9/22
 * 实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
 *
 * 示例 1：
 *
 * 输入: s = "leetcode"
 * 输出: false
 * 示例 2：
 *
 * 输入: s = "abc"
 * 输出: true
 * 限制：
 *
 * 0 <= len(s) <= 100
 * s[i]仅包含小写字母
 * 如果你不使用额外的数据结构，会很加分。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/is-unique-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _01_01_判定字符是否唯一 {

    @Test
    public void test(){
        char a = 'A'; //字符也是可以直接进行位运算的
        char b = 'a';
        int d = 11; //甚至字符和数字都可以位运算
        int c = a ^ d;
        System.out.println(c);
    }

    /**
     * 比较普通的就是暴力，HashMap，或者借助辅助数组。
     * 借助辅助数组会借助数组这个数据结构，所以稍微差一点。
     *
     * 本体的最佳解===>位运算 O(n) O(1)
     * 用 int 这个 32位 里面的 26位 来表示26个字母 a - z
     * 哪一个出现就从右到左在相应位置标上1
     * 如果已经有1了，就说明重复
     * @param astr
     * @return
     */
    public boolean isUnique(String astr) {

        int mark = 0;//一开始32位为全0
        for (int i = 0; i < astr.length(); i++) {
            int move_bit = astr.charAt(i) - 'a';//算出i是第几位，然后我们给把这第i位标为1
            int temp = 1;
            temp = temp << (move_bit);//左移1的位置到i
            if ((mark & temp) != 0) return false;//如果不为0，那么就是i位置上的字母出现过了
            else mark = mark | temp; //把这一位标记为1
        }
        return true;//遍历完都没有重复
    }
}
