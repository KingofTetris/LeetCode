package 每日一题;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File _1比特字符与2比特字符_2022_02_20
 * @Time 2022/2/23  16:15
 * 有两种特殊字符：
 *
 * 第一种字符可以用一比特 0 表示
 * 第二种字符可以用两比特（10 或 11）表示
 * 给你一个以 0 结尾的二进制数组 bits ，如果最后一个字符必须是一个一比特字符，则返回 true 。
 *
 *  
 *
 * 示例 1:
 *
 * 输入: bits = [1, 0, 0]
 * 输出: true
 * 解释: 唯一的解码方式是将其解析为一个两比特字符和一个一比特字符。
 * 所以最后一个字符是一比特字符。
 * 示例 2:
 *
 * 输入：bits = [1,1,1,0]
 * 输出：false
 * 解释：唯一的解码方式是将其解析为两比特字符和两比特字符。
 * 所以最后一个字符不是一比特字符。
 *  
 *
 * 提示:
 *
 * 1 <= bits.length <= 1000
 * bits[i] 为 0 或 1
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/1-bit-and-2-bit-characters
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class _1比特字符与2比特字符_2022_02_20 {
    @Test
    public void test() {
        int[] bits = new int[]{1,1,1,0};
        System.out.println(isOneBitCharacter(bits));

    }


    //实际上只有0 10 11三种比特
    //从数组开头开始
    //遇到0走一步，遇到1走两步
    //因为最后一定以0为结尾，判断pos == N-1 即可
    public boolean isOneBitCharacter(int[] bits) {
        int N = bits.length;
        int pos = 0;
        while (pos < N - 1) {
            pos += bits[pos] == 1 ? 2 : 1;
        }
        return pos == N - 1;
    }
}
