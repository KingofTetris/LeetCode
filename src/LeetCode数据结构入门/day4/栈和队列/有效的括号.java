package LeetCode数据结构入门.day4.栈和队列;

import org.junit.Test;

import java.util.Stack;

/**
 * @author KingofTetris
 * @File 有效的括号
 * @Time 2021/10/4  8:43
 */

/*20. 有效的括号
        给定一个只包括 '('，')'，'{'，'}'，'['，']'
         的字符串 s ，判断字符串是否有效。

        有效字符串需满足：

        左括号必须用相同类型的右括号闭合。
        左括号必须以正确的顺序闭合。


        示例 1：

        输入：s = "()"
        输出：true
        示例 2：

        输入：s = "()[]{}"
        输出：true
        示例 3：

        输入：s = "(]"
        输出：false
        示例 4：

        输入：s = "([)]"
        输出：false
        示例 5：

        输入：s = "{[]}"
        输出：true


        提示：

        1 <= s.length <= 104
        s 仅由括号 '()[]{}' 组成*/
public class 有效的括号 {


    @Test
    public void test() {
        String s = "({[test]})";
        System.out.println(isValid(s));
    }

    public boolean isValid(String s) {
        //首先s的长度一定是偶数
        int n = s.length();
        if (n % 2 == 1)
            return false;
//        初始化栈
        Stack<Character> stack = new Stack<>();
        //只有左括号入栈 其他的不管什么字符都不入栈 遇到右括号找栈顶出栈
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (c == '{' || c == '[' || c == '(')
                stack.push(c);
                //遇到右括号找栈顶是否对应左括号，是就POP 不是return false
            else if (c == ')') {
                if (!stack.isEmpty() && stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c == '}') {
                if (!stack.isEmpty() && stack.peek() == '{') {
                    stack.pop();
                } else {
                    return false;
                }
            } else if (c == ']') {
                if (!stack.isEmpty() && stack.peek() == '[') {
                    stack.pop();
                } else {
                    return false;
                }
            }
        }
        return stack.isEmpty();
    }
}







