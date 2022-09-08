package 剑指offer第二版.链表_队列_栈;

import java.util.Stack;

/**
 * @Author KingofTetris
 * @Date 2022/7/30 16:15
 * 输入两个整数序列，第一个序列表示栈的压入顺序，请判断第二个序列是否为该栈的弹出顺序。假设压入栈的所有数字均不相等。例如，序列 {1,2,3,4,5} 是某栈的压栈序列，序列 {4,5,3,2,1} 是该压栈序列对应的一个弹出序列，但 {4,3,5,1,2} 就不可能是该压栈序列的弹出序列。
 *
 *  
 *
 * 示例 1：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,5,3,2,1]
 * 输出：true
 * 解释：我们可以按以下顺序执行：
 * push(1), push(2), push(3), push(4), pop() -> 4,
 * push(5), pop() -> 5, pop() -> 3, pop() -> 2, pop() -> 1
 * 示例 2：
 *
 * 输入：pushed = [1,2,3,4,5], popped = [4,3,5,1,2]
 * 输出：false
 * 解释：1 不能在 2 之前弹出。
 *  
 *
 * 提示：
 *
 * 0 <= pushed.length == popped.length <= 1000
 * 0 <= pushed[i], popped[i] < 1000
 * pushed 是 popped 的排列。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode.cn/problems/zhan-de-ya-ru-dan-chu-xu-lie-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */

/**
 * 判断入栈和出栈序列是否合法。这题是没有重复元素的情况
 * 如果有重复元素则会更加复杂
 */
public class 剑指Offer31_栈的压入和弹出序列 {

    /**
     * 由于题目规定 栈的所有数字均不相等 ，因此在循环入栈中，
     * 每个元素出栈的位置的可能性是唯一的（若有重复数字，则具有多个可出栈的位置）。
     * 因而，在遇到 “栈顶元素 == 弹出序列的当前元素” 就应立即执行出栈。
     *
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> stack = new Stack<>();//用于模拟的辅助栈
        int i = 0;
        for(int num : pushed) {//依次遍历pushed中所有元素，模拟入栈操作
            stack.push(num);
            //如果辅助栈的栈顶元素与popped中元素相等，模拟出栈操作
            while(!stack.isEmpty() && stack.peek() == popped[i]) {
                //注意是while，可能会弹出多次,所以要加个!stack.isEmpty()防止栈为空还继续弹的bug
                //栈顶和出栈序列相同，则pop，序列后移一位
                stack.pop();
                i++;
            }
        }
        /**
         * 遍历完整个入栈序列，直接返回stack是否为空就可以了，
         * 为空则true，不为空则false
         */
        return stack.isEmpty();
    }

    /**
     * 优化：双指针节省空间为O(1) 时间复杂度不变
     * @param pushed
     * @param popped
     * @return
     */
    public boolean validateStackSequences2(int[] pushed, int[] popped) {
        // 用pushed数组模拟栈
            // 栈顶指针
            int top = 0;
            // 出栈序列指针
            int out = 0;
            for(int num:pushed){
                // 入栈,会覆盖掉已出栈元素
                pushed[top] = num;
                // 出栈
                while(top >=0 && pushed[top]==popped[out]){
                    top--;
                    out++;
                }
                // 下一个元素入栈到栈顶的下一个位置
                top++;
            }
            return top == 0;
    }
}
