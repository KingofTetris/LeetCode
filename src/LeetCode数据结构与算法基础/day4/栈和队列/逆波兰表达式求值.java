package LeetCode数据结构与算法基础.day4.栈和队列;

import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author by KingOfTetris
 * @date 2023/9/13
 */
public class 逆波兰表达式求值 {
    public static void main(String[] args) {
        String[] s = {"10","6","9","3","+","-11","*","/","*","17","+","5","+"};
//        System.out.println(isNum(s[0]));
        double v = evalRPN(s);
        System.out.println((int) v);
    }


    public static int evalRPN(String[] tokens) {
        LinkedList<Integer> stack = new LinkedList<>();
        for (int i = 0; i < tokens.length; i++) {
            //如果是运算符
            //栈顶两元素出栈 进行运算
            //要注意- 和 / 的顺序是第二个在前，第一个在后。
            if (tokens[i].equals("+")) {
                int v1 = stack.pop();
                int v2 = stack.pop();
                stack.push(v1 + v2);
            }
            else if (tokens[i].equals("-")) {
                int v1 = stack.pop();
                int v2 = stack.pop();
                // s:[13 5 /]
                // v1 = 5
                // v2 = 13
                // v2 / v1 减号也是一样
                stack.push(v2 - v1);
            }
            else if (tokens[i].equals("*")) {
                int v1 = stack.pop();
                int v2 = stack.pop();
                stack.push(v1 * v2);
            }
            else if (tokens[i].equals("/")) {
                int v1 = stack.pop();
                int v2 = stack.pop();
                stack.push(v2 / v1);
            }
            else {
                //如果是数字
                //字符串转整型 Integer.parseInt
                //直接入栈
                stack.push(Integer.parseInt(tokens[i]));
            }
        }
        //最后剩下的这个数就是结果
        return stack.pop();
    }
}
