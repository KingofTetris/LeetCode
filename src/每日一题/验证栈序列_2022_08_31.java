package 每日一题;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * @Author KingofTetris
 * @Date 2022/8/31 10:00
 * 给定 pushed 和 popped 两个序列，每个序列中的 值都不重复，
 * 只有当它们可能是在最初空栈上进行的推入 push 和弹出 pop 操作序列的结果时，返回 true；否则，返回 false 。
 * 示例 1：
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 * 提示：
 * 1 <= pushed.length <= 1000
 * 0 <= pushed[i] <= 1000
 * pushed 的所有元素 互不相同
 * popped.length == pushed.length
 * popped 是 pushed 的一个排列
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/validate-stack-sequences
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class 验证栈序列_2022_08_31 {

    /**
     * 就是判断出栈入栈序列是否匹配合法。序列元素均不相同
     * @param pushed
     * @param popped
     * @return
     */
    //模拟栈的入栈，出栈。
    //pushed = [1,2,3,4,5], popped = [4,5,3,2,1] true
    //pushed = [1,2,3,4,5], popped = [4,3,5,1,2] false
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Deque<Integer> stack = new ArrayDeque<>();
        int n = pushed.length;
        int j = 0;//用于记录出栈序列的位置。
        for (int i = 0; i < n; i++) {
            stack.push(pushed[i]);//入栈
            while (!stack.isEmpty() && stack.peek() == popped[j]) {
                //当栈顶不等于出栈序列首位时，就一直入栈。直到相同。
                // 当然要栈不为空。
                stack.pop();   //相同则重复出栈。
                j++;   //出栈序列往后移动
            }
        }
        //最后判断栈是否为空即可。
        return stack.isEmpty();
    }
}
