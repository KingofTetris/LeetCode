package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.Stack;

/**
 * 扑克牌算分游戏:排序后的扑克牌计算总分最大者赢，
 * 扑克牌种类有1-10，和1，Q，K这13中牌，
 * 其中1-10就代表数字本身的得分值，
 * J表示加法，可以把排序在J前面的两个数字相加作为J位置的得分值;
 * Q表示得分翻倍，即在Q前面的数字的2倍作为D位置的得分值;
 * K表示删除一个得分值即K前面的数字删除(扣分)
 *
 * 给定一个排好序的扑克牌字符串列表，需要你按照如上规则返回这副牌得分的总和。
 * 示例 1:
 * 52KQJ
 * 输出:30
 * 解释:
 * 处理第1字符时:你可以得到5分，总得分5
 * 处理第2字符时:你可以增加2分。总得分7
 * 处理第3字符时为K操作:k为删除前一个得分数字即删除2，总得分5
 * 处理第4字符时为Q操作:Q为取得分系列前一个得分进行翻倍操作，剩下的最近得分是5(2已经被删除，不算)，因此得分18，总得分是15
 * 处理第5字符时为]操作:J为取得分系列前两个得分进行相加操作(5和10)，因此得分15，最后总得分是30。
 *
 * // 示例 2:
 * //输入:“524k09JJ"
 * //输出 :55
 * //解释 :
 * 处理第1字符时:总得分5
 * 处理第2字符时:总得分7
 * 处理第3字符时:总得分11
 * 处理第4字符时:总得分7
 * 处理第5字符时:总得分11
 * 处理第6字符时:总得分20
 * 处理第7字符时:
 * 总得分33
 * 处理第8字符时:总得分55
 * 注意碰到1，Q，K操作时，如果没有足够的得分系列进行处理，则计算结束，直接返回0(表示玩家牌排序有问题)
 * 如输入"k"，"9J","Q","65KJ"都是非法的，直接返回0
 */
public class 扑克牌算分游戏 {
    public static void main(String[] args) {
        String cards1 = "52KQJ";
        String cards2 = "524KQ9JJ";
        System.out.println(calculateScore(cards1)); // 输出: 30
        System.out.println(calculateScore(cards2)); // 输出: 55
    }

    public static int calculateScore(String cards) {
        int score = 0;
        Stack<Integer> stack = new Stack<>();

        for (char card : cards.toCharArray()) {
            if (card == '1') {
                score += 10; // '10' represented as '1' in the input string
                stack.push(10);
            } else if (Character.isDigit(card)) {
                score += Character.getNumericValue(card);
                stack.push(Character.getNumericValue(card));
            } else if (card == 'J') {
                if (stack.size() >= 2) {
                    int total = stack.get(stack.size() - 1) + stack.get(stack.size() - 2);
                    score += total;
                    stack.push(total);
                }
            } else if (card == 'Q') {
                if (!stack.isEmpty()) {
                    int total = stack.peek() * 2;
                    score += total;
                    stack.push(total);
                } else {
                    return 0; // Invalid input sequence
                }
            } else if (card == 'K') {
                if (!stack.isEmpty()) {
                    score -= stack.pop();
                } else {
                    return 0; // Invalid input sequence
                }
            } else {
                return 0; // Invalid input sequence
            }
        }

        return score;
    }
}
