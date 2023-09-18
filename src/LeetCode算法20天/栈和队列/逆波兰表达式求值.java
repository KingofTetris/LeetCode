package LeetCode算法20天.栈和队列;

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
            //如果是数字
            if (isNum(tokens[i])) {
                int v = Integer.parseInt(tokens[i]);
                stack.push(v);
            }
            //如果是运算符
            if (tokens[i].equals("+")) {
                int v1 = stack.poll();
                int v2 = stack.poll();
                stack.push(v1 + v2);
            }
            if (tokens[i].equals("-")) {
                int v1 = stack.poll();
                int v2 = stack.poll();
                stack.push(v2 - v1);
            }
            if (tokens[i].equals("*")) {
                int v1 = stack.poll();
                int v2 = stack.poll();
                stack.push(v1 * v2);
            }
            if (tokens[i].equals("/")) {
                int v1 = stack.poll();
                int v2 = stack.poll();
                stack.push(v2 / v1);
            }
        }
        //最后剩下的这个数就是结果
        return stack.poll();
    }

    public static boolean isNum(String s) {
        //它匹配的是任意的整数或者小数，如：-123、123、-123.456、123.456等
        //要注意这个.要转义为普通的字符.
        //(\\.[0-9]+)? 表示如果你要出现小数点，那么后面的[0-9]就必须要出现1到多次。
        //不然2. 3. 这样的字符串就是非法的数字
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
