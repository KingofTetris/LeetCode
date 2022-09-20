package LeetCode数据结构基础.day4.栈和队列;

import org.junit.Test;

/**
 * @author KingofTetris
 * @File 移除无效的括号
 * @Time 2021/10/24  15:03
 */

/*给你一个由 '('、')' 和小写字母组成的字符串 s。

        你需要从字符串中删除最少数目的 '(' 或者 ')' (可以删除任意位置的括号)，
        使得剩下的「括号字符串」有效。

        请返回任意一个合法字符串。

        有效「括号字符串」应当符合以下 任意一条 要求：

        空字符串或只包含小写字母的字符串
        可以被写作 AB（A 连接 B）的字符串，其中 A 和 B 都是有效「括号字符串」
        可以被写作 (A) 的字符串，其中 A 是一个有效的「括号字符串」
         

        示例 1：

        输入：s = "lee(t(c)o)de)"
        输出："lee(t(c)o)de"
        解释："lee(t(co)de)" , "lee(t(c)ode)" 也是一个可行答案。
        示例 2：

        输入：s = "a)b(c)d"
        输出："ab(c)d"
        示例 3：

        输入：s = "))(("
        输出：""
        解释：空字符串也是有效的
        示例 4：

        输入：s = "(a(b(c)d)"
        输出："a(b(c)d)"
         

        提示：

        1 <= s.length <= 10^5
        s[i] 可能是 '('、')' 或英文小写字母*/
public class 移除无效的括号 {

    @Test
    public void test(){
        String test = "((a)()";
        System.out.println(minRemoveToMakeValid(test));
    }
    public String minRemoveToMakeValid(String s) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for (char c : s.toCharArray()) {
            if (c == '(') {
                sb.append(c);
                //正括号数 + 1
                count++;
            }
            else if (c == ')') {
                if (count > 0 ){
                    //有正括号， 所以合法，把 ")"加到 sb里
                    sb.append(c);
                    //抵消了一个正括号，正括号 - 1
                    count--;
                }
                //如果没有正括号，说明 ")" 不合法， 什么也不做
            }else sb.append(c); //字母正常输出

        }
        //如果没有多余的正括号，返回结果
        if (count == 0) return sb.toString();

        //如果有多余的正括号， 那就反向遍历删除多余的正括号。因为是反向遍历，
        // 删除多出的任意count个正括号仍然合法。
        //这也是为啥Example 1 有多个解
        for (int i = sb.length() - 1; i >= 0; i--) {
            if (count == 0) break;
            if (sb.charAt(i) == '(') {
                sb.deleteCharAt(i);
                count--;
            }
        }
        return sb.toString();
    }
}
