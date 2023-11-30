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
            //如果是数字
            //因为要判断的是字符串，只能自己写一个isNum来判断。
            //数字入栈
            if (isNum(tokens[i])) {
                int v = Integer.parseInt(tokens[i]);
                stack.push(v);
            }
            //如果是运算符
            //栈顶两元素出栈 进行运算
            //要注意- 和 / 的顺序是第二个在前，第一个在后。
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


    /**
     * 当然本题保证数组中只有+-* \(转义后面的/，不然这是在注释里面)/四种符号，
     * 其他全为数字。所以其实只需要判断是不是运算符就行了。
     *
     * 下面只是给出通用的判断合法数字的正则表达式
     * @param s
     * @return
     */
    public static boolean isNum(String s) {
        //它匹配的是任意的整数或者小数，如：-123、123、-123.456、123.456等
        //要注意这个.要转义为普通的字符.
        //(\\.[0-9]+)? 表示如果你要出现小数点，那么后面的[0-9]就必须要出现1到多次。
        //不然2. 3. 这样的字符串就变合法数字了？所以要加上(\\.[0-9]+)? 注意这个+号对应的是.xxxx出现1次或多次
        //最后的?表示.xxx可以不出现。
        Pattern pattern = Pattern.compile("-?[0-9]+(\\.[0-9]+)?");
        Matcher matcher = pattern.matcher(s);
        return matcher.matches();
    }
}
