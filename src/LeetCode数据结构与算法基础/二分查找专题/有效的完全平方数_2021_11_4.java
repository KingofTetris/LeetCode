package LeetCode数据结构与算法基础.二分查找专题;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 有效的完全平方数_2021_11_4
 * @Time 2021/11/4  12:07
 * <p>
 * 给定一个 正整数 num ，编写一个函数，如果 num 是一个完全平方数，则返回 true ，否则返回 false 。
 * <p>
 * 进阶：不要 使用任何内置的库函数，如  sqrt 。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：num = 16
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：num = 14
 * 输出：false
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= num <= 2^31 - 1
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-perfect-square
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 有效的完全平方数_2021_11_4 {
    @Test
    public void test() {
        int num = 16;
        System.out.println(isPerfectSquare1(num));
    }

    //直接暴力循环超时
    public boolean isPerfectSquare(int num) {
        if (num == 1) return true;
        for (int i = 1; i < num << 1; i++) {
            if (i * i == num) {
                return true;
            }
        }
        return false;
    }


    //二分查找 1 - num 之间  实际上num/2就够了 不过要小心1/2直接变0
    public boolean isPerfectSquare1(int num) {

        if (num == 1) return true;
        int left = 0, right = num;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            long square = (long) mid * mid;
            if (square < num) {
                left = mid + 1;
            } else if (square > num) {
                right = mid - 1;
            } else {
                return true;
            }
        }
        return false;
    }
}
