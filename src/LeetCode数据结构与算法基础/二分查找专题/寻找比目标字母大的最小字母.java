package LeetCode数据结构与算法基础.二分查找专题;

/**
 * @author by KingOfTetris
 * @date 2024/11/5
 */


import org.junit.Test;

/**
 * 给你一个字符数组 letters，该数组按非递减顺序排序，以及一个字符 target。letters 里至少有两个不同的字符。
 *
 * 返回 letters 中大于 target 的最小的字符。如果不存在这样的字符，则返回 letters 的第一个字符。
 *
 *
 *
 * 示例 1：
 *
 * 输入: letters = ["c", "f", "j"]，target = "a"
 * 输出: "c"
 * 解释：letters 中字典上比 'a' 大的最小字符是 'c'。
 * 示例 2:
 *
 * 输入: letters = ["c","f","j"], target = "c"
 * 输出: "f"
 * 解释：letters 中字典顺序上大于 'c' 的最小字符是 'f'。
 * 示例 3:
 *
 * 输入: letters = ["x","x","y","y"], target = "z"
 * 输出: "x"
 * 解释：letters 中没有一个字符在字典上大于 'z'，所以我们返回 letters[0]。
 *
 *
 * 提示：
 *
 * 2 <= letters.length <= 104
 * letters[i] 是一个小写字母
 * letters 按非递减顺序排序
 * letters 最少包含两个不同的字母
 * target 是一个小写字母
 */
public class 寻找比目标字母大的最小字母 {

    @Test
    public void test(){
        char[] letters = {'x','x','y','y'};
        char target = 'z';
        char c = nextGreatestLetter(letters, target);
        System.out.println(c);
    }
    public char nextGreatestLetter(char[] letters, char target) {
        int n = letters.length;
        if (target >= letters[n - 1]) {
            //如果不存在这样的字符，则返回 letters 的第一个字符。
            return letters[0];
        }
        int low = 0, high = n - 1;
        while (low <= high) {
            int mid = (high - low) / 2 + low;
            //mid > target 就说明还没找到最小的，继续往左边找
            if (letters[mid] > target) {
                high = mid - 1;
            }
            //反过来去找大于target的数
            else {
                low = mid + 1;
            }
        }
        return letters[low];
    }

/*
    作者：力扣官方题解
    链接：https://leetcode.cn/problems/find-smallest-letter-greater-than-target/solutions/1385592/xun-zhao-bi-mu-biao-zi-mu-da-de-zui-xiao-lhm7/
    来源：力扣（LeetCode）
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
}
