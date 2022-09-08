package 每日一题;

import org.junit.Test;

/**
 *
 * shift f6 统一修改变量名
 * @author KingofTetris
 * @File 推多米诺_2022_02_22
 * @Time 2022/2/23  14:40
 *
 *
 *
 *
 * n 张多米诺骨牌排成一行，将每张多米诺骨牌垂直竖立。在开始时，同时把一些多米诺骨牌向左或向右推。
 *
 * 每过一秒，倒向左边的多米诺骨牌会推动其左侧相邻的多米诺骨牌。同样地，倒向右边的多米诺骨牌也会推动竖立在其右侧的相邻多米诺骨牌。
 *
 * 如果一张垂直竖立的多米诺骨牌的两侧同时有多米诺骨牌倒下时，由于受力平衡， 该骨牌仍然保持不变。
 *
 * 就这个问题而言，我们会认为一张正在倒下的多米诺骨牌不会对其它正在倒下或已经倒下的多米诺骨牌施加额外的力。
 *
 * 给你一个字符串 dominoes 表示这一行多米诺骨牌的初始状态，其中：
 *
 * dominoes[i] = 'L'，表示第 i 张多米诺骨牌被推向左侧，
 * dominoes[i] = 'R'，表示第 i 张多米诺骨牌被推向右侧，
 * dominoes[i] = '.'，表示没有推动第 i 张多米诺骨牌。
 * 返回表示最终状态的字符串。
 *
 *  
 * 示例 1：
 *
 * 输入：dominoes = "RR.L"
 * 输出："RR.L"
 * 解释：第一张多米诺骨牌没有给第二张施加额外的力。
 * 示例 2：
 *
 *
 * 输入：dominoes = ".L.R...LR..L.."
 * 输出："LL.RR.LLRRLL.."
 *  
 *
 * 提示：
 *
 * n == dominoes.length
 * 1 <= n <= 105
 * dominoes[i] 为 'L'、'R' 或 '.'
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/push-dominoes
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 *
 *
 *
 *思路很简单，已经是L或R的字符，肯定不会变
 *
 * 字符为.的，可能会因为两边的字符是L或R而改变
 *
 * 因此只要判断连续...两边的字符情况就可以
 *
 * 一共有三种情况 ，分类处理即可`
 *
 * 作者：fuxuemingzhu
 * 链接：https://leetcode-cn.com/problems/push-dominoes/solution/fu-xue-ming-zhu-miao-dong-xi-lie-xiang-x-xkts/
 * 来源：力扣（LeetCode）
 * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */
public class 推多米诺_2022_02_22 {

    @Test
    public void test() {
        String dominoes = ".L.R...LR..L..";
        System.out.println(pushDominoes(dominoes));
    }

    /**
     * 从左到右模拟
     */
    public String pushDominoes(String dominoes) {
        int length = dominoes.length();
        StringBuilder sb = new StringBuilder(dominoes);
        while (true){
            //记录当前遍历是否有多米诺变化, 没有则结束循环
            boolean hasMove = false;
            //上一张多米诺状态
            char pre = '.';
            for (int i = 0; i < length; i++) {
                //当前位置
                char cur = sb.charAt(i);
                if (cur != '.'){
                    pre = cur;
                    continue;
                }
                //下一个位置
                char next = i == length - 1 ? '.' : sb.charAt(i + 1);
                if (pre == 'R' && next != 'L'){
                    //左侧为R, 右侧为R或竖立, 则向右倒
                    hasMove = true;
                    sb.setCharAt(i, 'R');
                } else if (next == 'L' && pre != 'R'){
                    //右侧为L, 左侧为L或竖立, 则向左倒
                    hasMove = true;
                    sb.setCharAt(i, 'L');
                }
                pre = cur;
            }
            //没有多米诺变化, 结束循环
            if (!hasMove){
                break;
            }
        }
        return sb.toString();
    }

}
