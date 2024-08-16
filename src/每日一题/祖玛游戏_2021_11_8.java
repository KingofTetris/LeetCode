package 每日一题;

import java.util.HashMap;
import java.util.Map;

/**
 * @author KingofTetris
 * @File 祖玛游戏_2021_11_7
 * @Time 2021/11/9  12:35
 */
public class 祖玛游戏_2021_11_8 {

    /**
     * 祖玛游戏其实就是消消乐
     * @param board
     * @param hand
     * @return
     */
    private static final int MAX = 0x3F3F3F3F;

    public int findMinStep(String board, String hand) {
        //1<<hand.length：对手里的球进行状态压缩
        int ans = memoization(board, hand, new HashMap<>(), 1 << hand.length());
        return ans == MAX ? -1 : ans;
    }

    private int memoization(String board, String hand, Map<String, Integer> cache, int cur) {
        if (board.length() == 0) return 0;
        if (cache.containsKey(board)) return cache.get(board);
        int ans = MAX;
        //遍历手中的所有球
        for (int i = 0; i < hand.length(); i++) {
            //如果当前球已经用过，则不再使用
            if (((cur >> i) & 1) == 1) continue;
            //当前球没有被用过，使用当前球发射，状态压缩标记为已经使用
            int next = (1 << i) | cur;
            //枚举所有插入位置
            for (int j = 0; j <= board.length(); j++) {
                //剪枝，对于RRWW来说，手中的球如果是R，插入第一个位置和第二个位置的情况是一样的
                if (j > 0 && j < board.length() - 1 && board.charAt(j) == board.charAt(j - 1)) continue;
                //剪枝，如果选出的球的颜色和插入的球的颜色不相同，没必要进行下去
                if (j > 0 && j < board.length() - 1 && board.charAt(j) != hand.charAt(i)) continue;
                //curBoard记录插入当前球后的情况
                StringBuilder curBoard = new StringBuilder();
                curBoard.append(board, 0, j).append(hand.charAt(i));
                if (j != board.length()) curBoard.append(board.substring(j));
                //双指针进行消除相同颜色的球，StringBuilder是引用传递，不需要使用返回结果进行更新
                eliminateSameColor(curBoard, j);
                ans = Math.min(ans, memoization(curBoard.toString(), hand, cache, next) + 1);
            }
        }
        cache.put(board, ans);
        return ans;
    }

    private void eliminateSameColor(StringBuilder curBoard, int i) {
        //从i位置进行扩散消除
        while (i >= 0 && i < curBoard.length()) {
            int left = i, right = i;
            char c = curBoard.charAt(i);
            while (left >= 0 && curBoard.charAt(left) == c) {
                left--;
            }
            while (right < curBoard.length() && curBoard.charAt(right) == c) {
                right++;
            }
            //如果有3个或者以上相同色的球，就进行消除
            if (right - left > 3) {
                curBoard.delete(left + 1, right);
                i = left >= 0 ? left : right;
            } else {
                break;
            }
        }
    }
}
